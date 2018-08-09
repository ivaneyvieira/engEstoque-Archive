package br.com.engecopi.framework.viewmodel

import br.com.engecopi.framework.model.BaseModel
import io.ebean.typequery.TQRootBean
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.reflect.KClass

abstract class CrudViewModel<MODEL : BaseModel, Q : TQRootBean<MODEL, Q>, VO : Any>
(view: IView, val crudClass: KClass<VO>) : ViewModel(view) {
  var crudBean: VO? = null
  var filter: String? = null
  override fun execUpdate() {}
  
  abstract fun update(bean: VO)
  abstract fun add(bean: VO)
  abstract fun delete(bean: VO)
  
  fun update() = exec {
    crudBean?.let { bean -> update(bean) }
  }
  
  fun add() = exec {
    crudBean?.let { bean -> add(bean) }
  }
  
  fun delete() = exec {
    crudBean?.let { bean -> delete(bean) }
  }
  
  //Query Lazy
  
  abstract val query: Q
  
  abstract fun MODEL.toVO(): VO
  
  open fun Q.filterString(text: String): Q {
    return this
  }
  
  open fun Q.filterInt(int: Int): Q {
    return this
  }
  
  open fun Q.filterDate(date: LocalDate): Q {
    return this
  }
  
  fun Q.filterBlank(filter: String): Q {
    return if (filter.isBlank()) this
    else {
      val date = parserDate(filter)
      val int = filter.toIntOrNull()
      val q1 = or().filterString(filter)
      val q2 = date?.let { q1.filterDate(it) } ?: q1
      val q3 = int?.let { q2.filterInt(it) } ?: q2
      q3.endOr()
    }
  }
  
  open fun Q.orderQuery(filter: String): Q {
    return this
  }
  
  private fun parserDate(filter: String): LocalDate? {
    val frm = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    return try {
      LocalDate.parse(filter, frm)
    } catch (e: Exception) {
      null
    }
  }
  
  open fun findQuery(filter: String): List<VO> = execList {
    query.filterBlank(filter).orderQuery(filter)
            .findList()
            .map { it.toVO() }
  }
  
  fun findAll(): List<VO> {
    return findQuery(filter ?: "")
  }
}

data class Sort(val propertyName: String, val descending: Boolean = false)