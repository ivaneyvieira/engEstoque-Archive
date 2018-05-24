package br.com.engecopi.framework.viewmodel

import org.jetbrains.exposed.sql.transactions.transaction

open class ViewModel(private val updateView: (e:ViewModel) -> Unit) {
  private var inExcection = false
  
  var showMessage: ((e: EViewModel) -> Unit)? = null
  
  fun showMessage(block :(e: EViewModel) -> Unit){
    showMessage = block
  }
  
  protected open fun execUpdate(){
  
  }
  
  fun updateModel(exception: EViewModel? = null) {
    if(exception == null)
      execUpdate()
    else    {
      this.showMessage?.let { showMsg ->
        showMsg(exception)
      }
    }
    updateView(this)
  }
  
  fun exec(block: () -> Unit) {
    return transaction {
      try {
        if (inExcection)
          block()
        else {
          inExcection = true
          block()
          inExcection = false
          updateModel()
        }
      } catch (e: EViewModel) {
        updateModel(e)
      }
    }
  }
}

class EViewModel(msg: String) : Exception(msg)