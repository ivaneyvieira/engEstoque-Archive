package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.finder.ProdutoFinder
import br.com.engecopi.framework.model.BaseModel
import br.com.engecopi.saci.beans.ProdutoSaci
import br.com.engecopi.saci.saci
import io.ebean.annotation.Index
import java.time.LocalDate
import javax.persistence.CascadeType.MERGE
import javax.persistence.CascadeType.PERSIST
import javax.persistence.CascadeType.REFRESH
import javax.persistence.CascadeType.REMOVE
import javax.persistence.Entity
import javax.persistence.ManyToMany
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
  @ManyToMany(mappedBy = "produtos", cascade = [PERSIST, MERGE, REFRESH])
  val usuarios: List<Usuario>? = null
  @OneToMany(mappedBy = "produto", cascade = [PERSIST, MERGE, REFRESH, REMOVE])
  val itensNota: List<ItemNota>? = null
  
  fun produtoSaci(): ProdutoSaci? {
    return saci.findProduto(codigo).filter { it.grade == grade }.firstOrNull()
  }
  
  val descricao: String?
    @Transient
    get() = produtoSaci()?.nome
  
  companion object Find : ProdutoFinder() {
    fun findProduto(codigo: String?, grade: String?): Produto? {
      codigo ?: return null
      return where().codigo.eq(codigo).grade.eq(grade ?: "").findOne()
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
    return itensNota.orEmpty().filter { it.nota?.loja?.id == loja.id }.sumBy { item ->
      val multiplicador = item.nota?.tipoMov?.multiplicador ?: 0
      multiplicador * item.quantidade
    }
  }
  
  fun saldoTotal(): Int {
    refresh()
    return itensNota.orEmpty().sumBy { item ->
      val multiplicador = item.nota?.tipoMov?.multiplicador ?: 0
      multiplicador * item.quantidade
    }
  }
  
  fun ultimaNota(): ItemNota? {
    refresh()
    return itensNota?.sortedBy { it.id }?.lastOrNull()
  }
}

