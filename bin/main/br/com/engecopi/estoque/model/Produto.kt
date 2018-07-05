package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.TipoMov.ENTRADA
import br.com.engecopi.estoque.model.TipoMov.SAIDA
import br.com.engecopi.estoque.model.finder.ProdutoFinder
import br.com.engecopi.estoque.model.TipoProduto.PECA
import br.com.engecopi.framework.model.BaseModel
import br.com.engecopi.saci.QuerySaci
import br.com.engecopi.saci.beans.ProdutoSaci
import io.ebean.annotation.Index
import java.time.LocalDate
import javax.persistence.CascadeType.ALL
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
    return QuerySaci.querySaci.findProduto(codigo).filter { it.grade == grade }.firstOrNull()
  }
  
  val descricao: String?
    @Transient
    get() = produtoSaci()?.nome
  
  companion object Find : ProdutoFinder() {
    fun findProduto(codigo: String, grade: String): Produto? {
      return where().codigo.eq(codigo).grade.eq(grade).findOne()
    }
    
    fun findProdutos(codigo: String): List<Produto> {
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
      val produtoSaci = QuerySaci.querySaci.findProduto(codigoProduto)
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
    TODO()
  }
  
}

enum class TipoProduto(val descricao: String) {
  NORMAL("Normal"),
  PECA("Peça"),
  BOBINA("Bobina"),
  CAIXA("Piso")
}
