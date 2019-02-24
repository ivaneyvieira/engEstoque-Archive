package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.RegistryUserInfo.LOJA_FIELD
import br.com.engecopi.estoque.model.finder.ProdutoFinder
import br.com.engecopi.framework.model.BaseModel
import br.com.engecopi.utils.lpad
import io.ebean.annotation.Cache
import io.ebean.annotation.CacheQueryTuning
import io.ebean.annotation.Formula
import io.ebean.annotation.Index
import io.ebean.annotation.Transactional
import java.time.LocalDate
import javax.persistence.CascadeType.REFRESH
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Table
import javax.persistence.Transient
import javax.validation.constraints.Size

@Cache(enableQueryCache = true)
@CacheQueryTuning(maxSecsToLive = 30)
@Entity
@Table(name = "produtos")
@Index(
  unique = true,
  columnNames = ["codigo", "grade"]
      )
class Produto : BaseModel() {
  @Size(max = 16)
  var codigo: String = ""
  @Size(max = 8)
  var grade: String = ""
  @Size(max = 16)
  @Index(unique = false)
  var codebar: String = ""
  var dataCadastro: LocalDate = LocalDate.now()
  @OneToMany(
    mappedBy = "produto",
    cascade = [REFRESH]
            )
  val itensNota: List<ItemNota>? = null
  @OneToOne(cascade = [])
  //  @FetchPreference(1)
  @JoinColumn(name = "id")
  var vproduto: ViewProduto? = null
  //@FetchPreference(2)
  @OneToMany(
    mappedBy = "produto",
    cascade = [REFRESH]
            )
  var viewProdutoLoc: List<ViewProdutoLoc>? = null
  @Formula(
    select = "LOC.localizacao",
    join = "LEFT join (select produto_id, GROUP_CONCAT(DISTINCT localizacao ORDER BY localizacao SEPARATOR ' - ') as localizacao from t_loc_produtos where storeno = @$LOJA_FIELD group by produto_id) AS LOC ON LOC.produto_id = \${ta}.id"
          )
  var localizacao: String? = ""
  @Formula(
    select = "SAL.saldoTotal",
    join = "LEFT JOIN (select produto_id, SUM(quantidade*(IF(status = 'RECEBIDO', 1, if(status = 'ENTREGUE', -1, 0)))) AS saldoTotal from itens_nota AS I  inner join notas AS N\n    ON N.id = I.nota_id\n  inner join lojas AS L    ON L.id = N.loja_id WHERE L.numero = @$LOJA_FIELD group by produto_id) AS SAL ON SAL.produto_id = \${ta}.id"
          )
  var saldoTotal: Int? = 0
  val descricao: String?
    @Transient get() = vproduto?.nome

  fun localizacao(usuario: Usuario?): String? {
    val user = usuario ?: return ""
    val localizacaoUser = user.localizacoesProduto(this)
    val locs = ViewProdutoLoc.find(produto = this)

    return locs
      .asSequence()
      .filterNotNull()
      .filter { localizacaoUser.contains(it.localizacao) }
      .firstOrNull()
      ?.localizacao
  }

  @Transactional
  fun recalculaSaldos() {
    ViewProdutoLoc.find(this)
      .map { it.localizacao }
      .forEach { localizacao ->
        recalculaSaldos(localizacao)
      }
  }

  @Transactional
  fun recalculaSaldos(localizacao: String): Int {
    val loja = RegistryUserInfo.lojaDefault
    var saldo = 0
    val itensNotNull = ItemNota.where()
      .produto.id.eq(id)
      .nota.loja.equalTo(loja)
      .localizacao.like(if(localizacao == "") "%" else localizacao)
      .findList()
    itensNotNull
      .asSequence()
      .filter { it.nota?.loja?.id == loja.id && it.localizacao == localizacao }
      .sortedWith(compareBy(ItemNota::data, ItemNota::id))
      .forEach { item ->
        item.refresh()
        saldo += item.quantidadeSaldo
        item.saldo = saldo
        item.update()
      }
    return saldo
  }

  companion object Find : ProdutoFinder() {
    fun findProduto(codigo: String?, grade: String?): Produto? {
      codigo ?: return null
      return where()
        .codigo.eq(codigo.lpad(16, " "))
        .grade.eq(grade ?: "")
        .findOne()
    }

    fun findProdutos(codigo: String?): List<Produto> {
      codigo ?: return emptyList()
      return where().codigo.eq(codigo.lpad(16, " "))
        .findList()
    }
  }

  private fun somaSaldo(item: ItemNota): Int {
    val multiplicador = item.status.multiplicador
    return multiplicador * item.quantidade
  }

  fun saldoLoja(localizacao: String?): Int {
    localizacao ?: return 0
    if (localizacao == "")
      return 0
    val loja = RegistryUserInfo.lojaDefault
    return findItensNota()
      .asSequence()
      .filter { it.nota?.loja?.id == loja.id && it.localizacao == localizacao }
      .sumBy(this::somaSaldo)
  }

  fun saldoTotal(): Int {
    return findItensNota()
      .sumBy(this::somaSaldo)
  }

  fun ultimaNota(): ItemNota? {
    return findItensNota()
      .asSequence()
      .sortedBy { it.id }
      .lastOrNull()
  }

  fun findItensNota(): List<ItemNota> {
    return ItemNota.where().produto.id.eq(id).findList()
  }

  fun localizacoes(): List<String> {
    return ViewProdutoLoc.localizacoes(produto = this).sorted()
  }

  fun prefixoLocalizacoes(): String {
    val localizacoes = localizacoes()
    if (localizacoes.size == 1)
      return localizacoes[0]
    val localizacoesSplit = localizacoes.map { it.split("[.\\-]".toRegex()) }
    val ctParte = localizacoesSplit.asSequence().map { it.size - 1 }.min() ?: 0
    for (i in ctParte downTo 0) {
      val prefix = localizacoesSplit.asSequence()
        .map { it.subList(0, i) }
        .map { it.joinToString(separator = ".") }
        .distinct()
        .toList()

      if (prefix.count() == 1)
        return prefix[0]
    }
    return ""
  }
}

data class LocProduto(val localizacao: String) : Comparable<LocProduto> {
  val prefixo = localizacao.split("-").getOrNull(0) ?: localizacao
  // val sufixo = localizacao.split("-").getOrNull(1) ?: localizacao
  override fun compareTo(other: LocProduto): Int {
    return localizacao.compareTo(other.localizacao)
  }

  override fun toString(): String {
    return localizacao
  }
}
