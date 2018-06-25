package br.com.engecopi.framework.viewmodel

import br.com.engecopi.estoque.viewmodel.UsuarioCrudVo
import kotlin.reflect.KClass

open class CrudViewModel<C : Any>(view: IView, val crudClass: KClass<C>) : ViewModel(view) {
  override fun execUpdate() {}
}