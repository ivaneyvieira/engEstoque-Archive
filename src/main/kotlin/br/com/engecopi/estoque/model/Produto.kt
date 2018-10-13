package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.RegistryUserInfo.LOJA_FIELD
import br.com.engecopi.estoque.model.finder.ProdutoFinder
import br.com.engecopi.framework.model.BaseModel
import br.com.engecopi.utils.lpad
import io.ebean.annotation.Cache
import io.ebean.annotation.FetchPreference
import io.ebean.annotation.Formula
import io.ebean.annotation.Index
import io.ebean.annotation.Transactional
import java.time.LocalDate
import javax.persistence.CascadeType.MERGE
import javax.persistence.CascadeType.PERSIST
import javax.persistence.CascadeType.REFRESH
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Table
import javax.persistence.Transient
import javax.validation.constraints.Size

@Cache(enableQueryCache = true)
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
    cascade = [PERSIST, MERGE, REFRESH]
            )
  val itensNota: List<ItemNota>? = null
  @OneToOne(cascade = [])
  @FetchPreference(1)
  @JoinColumn(name = "id")
  var vproduto: ViewProduto? = null
  @FetchPreference(2)
  @OneToMany(
    mappedBy = "produto",
    cascade = [REFRESH]
            )
  var viewProdutoLoc: List<ViewProdutoLoc>? = null
  @Formula(
    select = "LOC.localizacao",
    join = "LEFT join (select produto_id, localizacao from v_loc_produtos where storeno = @$LOJA_FIELD group by produto_id) AS LOC ON LOC.produto_id = \${ta}.id"
          )
  var localizacao: String? = ""
  @Formula(
    select = "SAL.saldo_total",
    join = "LEFT JOIN (select produto_id, SUM(quantidade*(IF(tipo_mov = 'ENTRADA', 1, -1))) AS saldo_total from itens_nota AS I  inner join notas AS N\n    ON N.id = I.nota_id\n  inner join lojas AS L    ON L.id = N.loja_id WHERE L.numero = @$LOJA_FIELD group by produto_id) AS SAL ON SAL.produto_id = \${ta}.id"
          )
  var saldo_total: Int? = 0
  val descricao: String?
    @Transient get() = vproduto?.nome

  fun localizacao(loja: Loja?, usuario: Usuario?): String? {
    val user = usuario ?: return ""
    val localizacaoUser = user.localizacoesProduto(this)
    val locs = if (loja == null) viewProdutoLoc
    else
      ViewProdutoLoc.find(
        loja = loja,
        produto = this
                         )

    return locs
      .orEmpty().asSequence()
      .filterNotNull()
      .filter { localizacaoUser.contains(it.localizacao) }
      .firstOrNull()
      ?.localizacao
  }

  @Transactional
  fun recalculaSaldos(loja: Loja?) {
    ViewProdutoLoc.find(
      loja,
      this
                       ).map { it.localizacao }.forEach { localizacao ->
      recalculaSaldos(
        loja,
        localizacao
                     )
    }
  }

  @Transactional
  fun recalculaSaldos(loja: Loja?, localizacao: String?): Int {
    loja ?: return 0
    localizacao ?: return 0
    var saldo = 0
    refresh()
    val itensNotNull = itensNota ?: return 0
    itensNotNull
      .asSequence()
      .filter {
        it.nota?.loja?.id == loja.id &&
        it.localizacao == localizacao
      }
      .sortedWith(
        compareBy(
          ItemNota::data,
          ItemNota::id
                 )
                 )
      .toList()
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
      return where().codigo.eq(
        codigo.lpad(
          16,
          " "
                   )
                              ).grade.eq(grade ?: "").findOne()
    }

    fun findProdutos(codigo: String?): List<Produto> {
      codigo ?: return emptyList()
      return where().codigo.eq(
        codigo.lpad(
          16,
          " "
                   )
                              ).findList()
    }

    fun createProduto(produtoSaci: ViewProdutoSaci?): Produto? {
      produtoSaci ?: return null
      return Produto().apply {
        produtoSaci.let { pSaci ->
          codigo = pSaci.codigo ?: codigo
          grade = pSaci.grade ?: grade
          codebar = pSaci.codebar ?: codebar
        }
      }
    }

    fun createProduto(codigoProduto: String?, gradeProduto: String?): Produto? {
      val produtoSaci = ViewProdutoSaci.find(
        codigoProduto,
        gradeProduto
                                            )
      return createProduto(produtoSaci)
    }
  }

  fun somaSaldo(item: ItemNota): Int {
    val multiplicador = item.nota?.tipoMov?.multiplicador ?: 0
    return multiplicador * item.quantidade
  }

  fun saldoLoja(loja: Loja?, localizacao: String?): Int {
    loja ?: return 0
    refresh()
    return itensNota
      .orEmpty().asSequence()
      .filter { it.nota?.loja?.id == loja.id && it.localizacao == localizacao }
      .sumBy(this::somaSaldo)
  }

  fun saldoTotal(): Int {
    refresh()
    return itensNota.orEmpty()
      .sumBy(this::somaSaldo)
  }

  fun ultimaNota(): ItemNota? {
    refresh()
    return itensNota
      ?.asSequence()
      ?.sortedBy { it.id }
      ?.lastOrNull()
  }

  fun finItensNota(): List<ItemNota> {
    return ItemNota.where().produto.id.eq(id).findList()
  }
}

