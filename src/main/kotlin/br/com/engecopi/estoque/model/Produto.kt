package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.finder.ProdutoFinder
import br.com.engecopi.estoque.model.TipoProduto.PECA
import br.com.engecopi.framework.model.BaseModel
import br.com.engecopi.saci.beans.ProdutoSaci
import java.time.LocalDate
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
  var codigo : String = ""
  @Size(max = 8)
  var grade : String = ""
  @Size(max = 16)
  var codebar : String=""
  var data_cadastro : LocalDate = LocalDate.now()
  @Enumerated(EnumType.STRING)
  var tipo : TipoProduto = PECA
  var quant_lote : Int = 0
  var quant_bobina : Int = 0
  @OneToMany(mappedBy = "produto")
  val itensNota: List<ItemNota>? = null
  @OneToMany(mappedBy = "produto")
  val lotes: List<Lote>? = null
  
  fun produtoSaci() : ProdutoSaci {
    TODO()
  }
  
  val descricao : String?
    @Transient
    get() = produtoSaci().nome
  
 
  companion object Find : ProdutoFinder() {
    fun findProduto(codigo: String, grade: String): Produto? {
      TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun findProdutos(codigo: String): List<Produto> {
      TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
  }
  
  fun saldoLoja(loja : Loja) : Int {
    TODO("not implemented")
  }

  fun saldoTotal() : Int {
    TODO("not implemented")
  }
}

enum class TipoProduto { NORMAL,
  PECA,
  BOBINA,
  CAIXA
}
