package br.com.engecopi.framework.viewmodel

import kotlin.reflect.KClass

abstract class CrudViewModel<C : Any>(view: IView, val crudClass: KClass<C>) : ViewModel(view) {
  var crudBean: C? = null
  override fun execUpdate() {}
  
  abstract fun update(bean: C)
  abstract fun add(bean: C)
  abstract fun delete(bean: C)
  abstract fun allBeans(): List<C>
  
  fun update() = exec {
    crudBean?.let { bean -> update(bean) }
  }
  
  fun add() = exec {
    crudBean?.let { bean -> add(bean) }
  }
  
  fun findAll(): List<C> = execList {
    allBeans()
  }
  
  fun delete() = exec {
    crudBean?.let { bean -> delete(bean) }
  }
}