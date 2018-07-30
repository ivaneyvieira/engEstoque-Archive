package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.ItemNota
import br.com.engecopi.estoque.model.Loja
import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.estoque.model.query.QProduto
import br.com.engecopi.framework.viewmodel.CrudViewModel
import br.com.engecopi.framework.viewmodel.IView
import br.com.engecopi.saci.saci

class ProdutoViewModel(view: IView, val lojaDefault: Loja?) :
        CrudViewModel<Produto, QProduto, ProdutoVo>(view, ProdutoVo::class) {
  override fun update(bean: ProdutoVo) {
    Produto.findProduto(bean.codigoProduto, bean.gradeProduto)?.let { produto ->
      produto.codebar = bean.codebar ?: ""
      produto.update()
    }
  }
  
  override fun add(bean: ProdutoVo) {
    Produto().apply {
      this.codigo = bean.codigoProduto ?: ""
      this.grade = bean.gradeProduto ?: ""
      this.codebar = bean.codebar ?: ""
      this.insert()
    }
  }
  
  override fun delete(bean: ProdutoVo) {
    Produto.findProduto(bean.codigoProduto, bean.gradeProduto)?.let { produto ->
      produto.delete()
    }
  }
  
  override val query: QProduto
    get() = Produto.where()
  
  override fun Produto.toVO(): ProdutoVo {
    val produto = this
    return ProdutoVo().apply {
      codigoProduto = produto.codigo
      gradeProduto = produto.grade
      codebar = produto.codebar
    }
  }
  
  override fun QProduto.filterString(text: String): QProduto {
    return codigo.eq(text)
            .codebar.eq(text)
  }
}

class ProdutoVo {
  var lojaDefault: Loja? = null
  var codigoProduto: String? = ""
    set(value) {
      field = value
      val produto = saci.findProduto(value ?: "")
      produto.firstOrNull()?.let { prdSaci ->
        codebar = produtosSaci.firstOrNull { it.grade == gradeProduto }?.codebar ?: ""
      }
    }
  var gradeProduto: String? = ""
  val produtosSaci
    get() = saci.findProduto(codigoProduto ?: "")
  val descricaoProduto: String?
    get() = produtosSaci.firstOrNull()?.nome ?: ""
  val grades
    get() = produtosSaci.mapNotNull { it.grade }
  
  var codebar: String? = null
  
  val produto: Produto?
    get() = Produto.findProduto(codigoProduto, gradeProduto)
  
  val itensNota: List<ItemNota>
    get() {
      val itens = produto?.itensNota.orEmpty().filter {
        lojaDefault?.let { lDef ->
          it.nota?.loja?.id == lDef.id
        } ?: true
      }
      var saldo = 0
      itens.forEach { item ->
        saldo += item.quantidadeSaldo
        item.saldoTransient = saldo
      }
      return itens
    }
}
