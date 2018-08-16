package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.finder.ProdutoFinder
import br.com.engecopi.framework.model.BaseModel
import br.com.engecopi.utils.lpad
import io.ebean.annotation.Cache
import io.ebean.annotation.Index
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
  @OneToMany(mappedBy = "produto", cascade = [PERSIST, MERGE, REFRESH])
  val itensNota: List<ItemNota>? = null
  @OneToOne(cascade = [])
  @JoinColumn(name = "id")
  var vproduto: ViewProduto? = null
  @OneToMany(mappedBy = "produto", cascade = [REFRESH])
  var viewProdutoLoc: List<ViewProdutoLoc>? = null
  
  val descricao: String?
    @Transient
    get() = vproduto?.nome
  
  fun localizacao(loja: Loja? = null): String? {
    val locs = if (loja == null)
      viewProdutoLoc
    else
      listOf(ViewProdutoLoc.find(loja, this))
    
    return locs.orEmpty().filterNotNull().joinToString { it.localizacao }
  }
  
  fun recalculaSaldos(): Int {
    var saldo = 0
    refresh()
    itensNota?.sortedWith(compareBy(ItemNota::data, ItemNota::id))?.forEach { item ->
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
      return where().codigo.eq(codigo.lpad(16, " ")).grade.eq(grade ?: "").findOne()
    }
    
    fun findProdutos(codigo: String?): List<Produto> {
      codigo ?: return emptyList()
      return where().codigo.eq(codigo.lpad(16, " ")).findList()
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
      val produtoSaci = ViewProdutoSaci.find(codigoProduto, gradeProduto)
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

