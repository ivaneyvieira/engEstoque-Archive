package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.Etiqueta
import br.com.engecopi.estoque.model.TipoMov
import br.com.engecopi.estoque.model.query.QEtiqueta
import br.com.engecopi.framework.viewmodel.CrudViewModel
import br.com.engecopi.framework.viewmodel.EViewModel
import br.com.engecopi.framework.viewmodel.EntityVo
import br.com.engecopi.framework.viewmodel.IView

class EtiquetaViewModel(view: IView) : CrudViewModel<Etiqueta, QEtiqueta, EtiquetaVo>(view, EtiquetaVo::class) {
  override fun update(bean: EtiquetaVo) {
    bean.entityVo?.apply {
      this.titulo = bean.titulo ?: throw EViewModel("A etiqueta está sem título")
      this.template = bean.template ?: throw EViewModel("O template está vazio")
      this.tipoMov = bean.tipoMov ?: throw EViewModel("O tipo está vazio")
      update()
    }
  }

  override fun add(bean: EtiquetaVo) {
    Etiqueta().apply {
      this.titulo = bean.titulo ?: throw EViewModel("A etiqueta está sem título")
      this.template = bean.template ?: throw EViewModel("O template está vazio")
      this.tipoMov = bean.tipoMov ?: throw EViewModel("O tipo está vazio")
      insert()
    }
  }

  override fun delete(bean: EtiquetaVo) {
    bean.entityVo?.delete()
  }

  override val query: QEtiqueta
    get() = Etiqueta.where()

  override fun Etiqueta.toVO(): EtiquetaVo {
    val etiqueta = this
    return EtiquetaVo().apply {
      this.entityVo = etiqueta
      this.titulo = etiqueta.titulo
      this.template = etiqueta.template
      this.tipoMov = etiqueta.tipoMov
    }
  }

  override fun QEtiqueta.filterString(text: String): QEtiqueta {
    return titulo.contains(text)
  }
}

class EtiquetaVo : EntityVo<Etiqueta>() {
  override fun findEntity(): Etiqueta? {
    return Etiqueta.find(titulo, tipoMov)
  }

  var titulo: String? = ""
  var template: String? = ""
  var tipoMov: TipoMov? = null
}