package br.com.engecopi.framework.viewmodel

import kotlin.reflect.KClass

abstract class CrudViewModel<C : Any>(view: IView, val crudClass: KClass<C>) : ViewModel(view) {
  var crudBean: C? = null
  override fun execUpdate() {}
  
  abstract fun update()
  
  abstract fun add()
  
  abstract fun findAll() : List<C>
  
  abstract fun delete()
}