package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.TipoMov.ENTRADA
import br.com.engecopi.estoque.model.TipoMov.SAIDA
import br.com.engecopi.estoque.model.finder.ProdutoFinder
import br.com.engecopi.estoque.model.TipoProduto.PECA
import br.com.engecopi.framework.model.BaseModel
import br.com.engecopi.saci.QuerySaci
import br.com.engecopi.saci.beans.ProdutoSaci
import java.time.LocalDate
import javax.persistence.CascadeType.ALL
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.persistence.Transient
import javax.validation.constraints.Size

@Entity
@Table(name = "produtos")
class Produto : BaseModel() {
  @Size(max = 16)
  var codigo: String = ""
  @Size(max = 8)
  var grade: String = ""
  @Size(max = 16)
  var codebar: String = ""
  var dataCadastro: LocalDate = LocalDate.now()
  @Enumerated(EnumType.STRING)
  var tipo: TipoProduto = PECA
  var tamanhoLote: Int = 0
  @OneToMany(mappedBy = "produto", cascade = [ALL])
  val itensNota: List<ItemNota>? = null
  @OneToMany(mappedBy = "produto", cascade = [ALL])
  val lotes: List<Lote>? = null
  @OneToMany(mappedBy = "produto", cascade = [ALL])
  val saldos: List<Saldo>? = null
  
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
    
    fun createProduto(codigoProduto: String, gradeProduto: String): Produto {
      val produtoSaci = QuerySaci.querySaci.findProduto(codigoProduto)
              .firstOrNull { it.grade == gradeProduto }
      return Produto().apply {
        produtoSaci?.let { pSaci ->
          codigo = pSaci.codigo ?: codigo
          grade = pSaci.grade ?: grade
          tipo = TipoProduto.valueOf(pSaci.tipo ?: "NORMAL")
          codebar = pSaci.codebar ?: codebar
        }
      }
    }
  }
  
  fun saldoLoja(loja: Loja): Int {
    refresh()
    return saldos?.filter { it.loja == loja }?.sumBy { it.quantidade } ?: 0
  }
  
  fun saldoTotal(): Int {
    refresh()
    return saldos?.sumBy { it.quantidade } ?: 0
  }
  
  fun atualizaSaldo() {
    val saidas = ItemNota
            .where()
            .produto.id.eq(this.id)
            .nota.tipoMov.eq(SAIDA)
            .findList()
            .groupBy { it.nota?.loja }
    val entradas = ItemNota
            .where()
            .produto.id.eq(this.id)
            .nota.tipoMov.eq(ENTRADA)
            .findList()
            .groupBy { it.nota?.loja }
    val lojas = (saidas.keys + entradas.keys).distinct()
    lojas.forEach { loja ->
      val qtSaida = saidas[loja]?.sumBy { it.quantidade } ?: 0
      val qtEntrada = entradas[loja]?.sumBy { it.quantidade } ?: 0
      val quant = qtEntrada + qtSaida
      val saldo = Saldo.where().produto.id.eq(this.id).loja.id.eq(loja?.id).findOne() ?: Saldo().apply {
        this.produto = this@Produto
        this.loja = loja
      }
      saldo.quantidade = quant
      saldo.save()
    }
  }
}

enum class TipoProduto(val descricao: String) {
  NORMAL("Normal"),
  PECA("Peça"),
  BOBINA("Bobina"),
  CAIXA("Piso")
}
