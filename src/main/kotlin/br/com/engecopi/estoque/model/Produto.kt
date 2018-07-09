package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.TipoProduto.PECA
import br.com.engecopi.estoque.model.finder.ProdutoFinder
import br.com.engecopi.framework.model.BaseModel
import br.com.engecopi.saci.beans.ProdutoSaci
import br.com.engecopi.saci.saci
import io.ebean.annotation.Index
import java.time.LocalDate
import javax.persistence.CascadeType.MERGE
import javax.persistence.CascadeType.PERSIST
import javax.persistence.CascadeType.REFRESH
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.persistence.Transient
import javax.validation.constraints.Size

@Entity
@Table(name = "produtos")
@Index(unique = true, columnNames = ["codigo", "grade"])
class Produto : BaseModel() {
  @Size(max = 16)
  var codigo: String = ""
  @Size(max = 8)
  var grade: String = ""
  @Size(max = 16)
  @Index(unique = false)
  var codebar: String = ""
  var dataCadastro: LocalDate = LocalDate.now()
  @Enumerated(EnumType.STRING)
  var tipo: TipoProduto = PECA
  var tamanhoLote: Int = 0
  @OneToMany(mappedBy = "produto", cascade = [PERSIST, MERGE, REFRESH])
  val itensNota: List<ItemNota>? = null
  @OneToMany(mappedBy = "produto", cascade = [PERSIST, MERGE, REFRESH])
  val lotes: List<Lote>? = null
  
  fun produtoSaci(): ProdutoSaci? {
    return saci.findProduto(codigo).filter { it.grade == grade }.firstOrNull()
  }
  
  val descricao: String?
    @Transient
    get() = produtoSaci()?.nome
  
  companion object Find : ProdutoFinder() {
    fun findProduto(codigo: String?, grade: String?): Produto? {
      codigo ?: return null
      return where().codigo.eq(codigo).grade.eq(grade?: "").findOne()
    }
    
    fun findProdutos(codigo: String?): List<Produto> {
      codigo ?: return emptyList()
      return where().codigo.eq(codigo).findList()
    }
    
    fun createProduto(produtoSaci: ProdutoSaci?): Produto {
      return Produto().apply {
        produtoSaci?.let { pSaci ->
          codigo = pSaci.codigo ?: codigo
          grade = pSaci.grade ?: grade
          tipo = TipoProduto.valueOf(pSaci.tipo ?: "NORMAL")
          codebar = pSaci.codebar ?: codebar
        }
      }
    }
    
    fun createProduto(codigoProduto: String, gradeProduto: String): Produto {
      val produtoSaci = saci.findProduto(codigoProduto)
              .firstOrNull { it.grade == gradeProduto }
      return createProduto(produtoSaci)
    }
  }
  
  fun saldoLoja(loja: Loja?): Int {
    loja ?: return 0
    refresh()
    return lotes?.filter { it.loja == loja }?.sumBy { it.saldo } ?: 0
  }
  
  fun saldoTotal(): Int {
    refresh()
    return lotes?.sumBy { it.saldo } ?: 0
  }
  
  fun atualizaSaldo() {
    refresh()
    lotes?.forEach { it.atualizaSaldo() }
  }
  
  fun ultimoLoteLoja(loja: Loja?): Lote? {
    loja ?: return null
    return Lote.where().loja.id.eq(loja.id)
            .produto.id.eq(this.id)
            .orderBy().sequencia.desc()
            .findList().firstOrNull()
  }
}

enum class TipoProduto(val descricao: String, val processaCodBar: ProcessaCodBar) {
  NORMAL("Normal", ProcessaCodBarNormal()),
  PECA("Peça", ProcessaCodBarFios()),
  BOBINA("Bobina", ProcessaCodBarFios()),
  CAIXA("Piso", ProcessaCodBarPiso())
}

class ProcessaCodBarPiso : ProcessaCodBar {
  override fun processaCodBar(codbar: String): CodBarResult? {
    val sep1 = " "
    val sep2 = ";"
    val prdno = codbar.split(sep1).getOrNull(0) ?: return null
    val grade = codbar.split(sep1).getOrNull(1) ?: return null
    val tamanhoLote = codbar.split(sep1).getOrNull(2)?.toIntOrNull() ?: return null
    val chave = codbar.split(sep1).getOrNull(3) ?: return null
    val sequencia = chave.split(sep2).getOrNull(0)?.toIntOrNull() ?: return null
    val total = chave.split(sep2).getOrNull(1)?.toIntOrNull() ?: return null
    return CodBarResult(prdno, grade, tamanhoLote, sequencia, total)
  }
}

class ProcessaCodBarFios : ProcessaCodBar {
  override fun processaCodBar(codbar: String): CodBarResult? {
    val sep1 = " "
    val sep2 = ";"
    val prdno = codbar.split(sep1).getOrNull(0) ?: return null
    val grade = codbar.split(sep1).getOrNull(1) ?: return null
    val qtPecas = codbar.split(sep1).getOrNull(2)?.toIntOrNull() ?: return null
    val tamanhoBobina = codbar.split(sep1).getOrNull(3)?.toIntOrNull() ?: return null
    val tamanhoLote = if (tamanhoBobina == 0) qtPecas else tamanhoBobina
    val chave = codbar.split(sep1).getOrNull(4) ?: return null
    val sequencia = chave.split(sep2).getOrNull(0)?.toIntOrNull() ?: return null
    val total = chave.split(sep2).getOrNull(1)?.toIntOrNull() ?: return null
    return CodBarResult(prdno, grade, tamanhoLote, sequencia, total)
  }
}

class ProcessaCodBarNormal : ProcessaCodBar {
  override fun processaCodBar(codbar: String): CodBarResult? {
    val sep1 = " "
    val sep2 = ";"
    val prdno = codbar.split(sep1).getOrNull(0) ?: return null
    val grade = ""
    val tamanhoLote = 1
    val chave = codbar.split(sep1).getOrNull(1) ?: return null
    val sequencia = chave.split(sep2).getOrNull(0)?.toIntOrNull() ?: return null
    val total = chave.split(sep2).getOrNull(1)?.toIntOrNull() ?: return null
    return CodBarResult(prdno, grade, tamanhoLote, sequencia, total)
  }
}

data class CodBarResult(
        val prdno: String = "",
        val grade: String = "",
        val tamanhoLote: Int? = 0,
        val sequencia: Int = 0,
        val total: Int = 0
                       )

interface ProcessaCodBar {
  fun processaCodBar(codbar: String): CodBarResult?
}
