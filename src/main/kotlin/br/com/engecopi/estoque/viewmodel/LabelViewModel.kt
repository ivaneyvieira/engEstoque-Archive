package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.Label
import br.com.engecopi.estoque.model.TipoProduto
import br.com.engecopi.estoque.model.TipoProduto.NORMAL
import br.com.engecopi.framework.printer.ZPLPreview
import br.com.engecopi.framework.viewmodel.CrudViewModel
import br.com.engecopi.framework.viewmodel.IView

class LabelViewModel(view: IView) : CrudViewModel<LabelVo>(view, LabelVo::class) {
  override fun update(bean: LabelVo) {
    Label.find(bean.tipoProduto)?.let { label ->
      label.layoutEntrada = bean.layoutEntrada ?: ""
      label.layoutSaida = bean.layoutSaida ?: ""
      label.update()
    }
  }
  
  override fun add(bean: LabelVo) {
    Label().apply {
      this.tipo = bean.tipoProduto ?: NORMAL
      this.layoutEntrada = bean.layoutEntrada ?: ""
      this.layoutSaida = bean.layoutSaida ?: ""
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
        this.layoutEntrada = label.layoutEntrada
        this.layoutSaida = label.layoutSaida
      }
    }
  }
}

class LabelVo {
  var tipoProduto: TipoProduto? = null
  var layoutEntrada: String? = null
  var layoutSaida: String? = null
  
  val imageEntrada: ByteArray?
    get() = ZPLPreview.createImage(layoutEntrada ?: "")
  val imageSaida: ByteArray?
    get() = ZPLPreview.createImage(layoutSaida ?: "")
}