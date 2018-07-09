package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.estoque.model.TipoProduto
import br.com.engecopi.estoque.model.TipoProduto.NORMAL
import br.com.engecopi.framework.viewmodel.CrudViewModel
import br.com.engecopi.framework.viewmodel.IView
import br.com.engecopi.saci.saci

class ProdutoViewModel(view: IView) : CrudViewModel<ProdutoVo>(view, ProdutoVo::class) {
  override fun update(bean: ProdutoVo) {
    Produto.findProduto(bean.codigoProduto, bean.gradeProduto)?.let { produto ->
      produto.codebar = bean.codebar ?: ""
      produto.tipo = bean.tipo ?: NORMAL
      produto.tamanhoLote = bean.tamanhoLote ?: 0
      produto.update()
      produto.atualizaSaldo()
    }
  }
  
  override fun add(bean: ProdutoVo) {
    Produto().apply {
      this.codigo = bean.codigoProduto ?: ""
      this.grade = bean.gradeProduto ?: ""
      this.codebar = bean.codebar ?: ""
      this.tipo = bean.tipo ?: NORMAL
      this.tamanhoLote = bean.tamanhoLote ?: 0
      this.insert()
    }
  }
  
  override fun delete(bean: ProdutoVo) {
    Produto.findProduto(bean.codigoProduto, bean.gradeProduto)?.let { produto ->
      produto.delete()
    }
  }
  
  override fun allBeans(): List<ProdutoVo> {
    return Produto.all().map { produto ->
      ProdutoVo().apply {
        codigoProduto = produto.codigo
        gradeProduto = produto.grade
        codebar = produto.codebar
        tipo = produto.tipo
        tamanhoLote = produto.tamanhoLote
      }
    }
  }
}

class ProdutoVo {
  var codigoProduto: String? = ""
    set(value) {
      field = value
      val produto = saci.findProduto(value ?: "")
      produto.firstOrNull()?.let { prdSaci ->
        tipo = TipoProduto.values().firstOrNull { it.toString() == prdSaci.tipo }
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
  var tipo: TipoProduto? = null
  var tamanhoLote: Int? = 0
  
  var codebar: String? = null
}