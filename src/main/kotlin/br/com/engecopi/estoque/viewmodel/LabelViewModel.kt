package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.Label
import br.com.engecopi.estoque.model.TipoProduto
import br.com.engecopi.estoque.model.TipoProduto.NORMAL
import br.com.engecopi.framework.viewmodel.CrudViewModel
import br.com.engecopi.framework.viewmodel.IView

class LabelViewModel(view: IView) : CrudViewModel<LabelVo>(view, LabelVo::class) {
  override fun update(bean: LabelVo) {
    Label.find(bean.tipoProduto)?.let { label ->
      label.layout = bean.layout ?: ""
      label.update()
    }
  }
  
  override fun add(bean: LabelVo) {
    Label().apply {
      this.tipo = bean.tipoProduto ?: NORMAL
      this.layout = bean.layout ?: ""
      this.insert()
    }
  }
  
  override fun delete(bean: LabelVo) {
    Label.find(bean.tipoProduto)?.let { label ->
      label.delete()
    }
  }
  
  override fun allBeans(): List<LabelVo> {
    return Label.all().map { label ->
      LabelVo().apply {
        this.tipoProduto = label.tipo
        this.layout = label.layout
      }
    }
  }
}

class LabelVo {
  var tipoProduto: TipoProduto? = null
  var layout: String? = null
  
  val image: ByteArray?
    get() = ZPLPreview.createImage(layout ?: "")
}