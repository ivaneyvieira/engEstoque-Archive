package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.Etiqueta
import br.com.engecopi.framework.viewmodel.CrudViewModel
import br.com.engecopi.framework.viewmodel.EViewModel
import br.com.engecopi.framework.viewmodel.IView

class EtiquetaViewModel(view: IView) : CrudViewModel<EtiquetaVo>(view, EtiquetaVo::class) {
  override fun update(bean: EtiquetaVo) {
    Etiqueta.byId(bean.id)?.apply {
      this.titulo = bean.titulo ?: throw EViewModel("A etiqueta está sem título")
      this.template = bean.template ?: throw EViewModel("O template está vazio")
      update()
    }
  }
  
  override fun add(bean: EtiquetaVo) {
    Etiqueta().apply {
      this.titulo = bean.titulo ?: throw EViewModel("A etiqueta está sem título")
      this.template = bean.template ?: throw EViewModel("O template está vazio")
      insert()
    }
  }
  
  override fun delete(bean: EtiquetaVo) {
    Etiqueta.deleteById(bean.id)
  }
  
  override fun allBeans(): List<EtiquetaVo> {
    return Etiqueta.all().map { etiqueta ->
      EtiquetaVo().apply {
        this.id = etiqueta.id
        this.titulo = etiqueta.titulo
        this.template = etiqueta.template
      }
    }
  }
}

class EtiquetaVo {
  var id: Long = 0
  var titulo: String? = ""
  var template: String? = ""
}