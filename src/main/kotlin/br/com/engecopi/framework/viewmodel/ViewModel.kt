package br.com.engecopi.framework.viewmodel

import br.com.engecopi.framework.model.Transaction

abstract class ViewModel(private val updateView: (e: ViewModel) -> Unit) {
  private var inExcection = false
  
  var showMessage: ((e: EViewModel) -> Unit)? = null
  
  fun showMessage(block: (e: EViewModel) -> Unit) {
    showMessage = block
  }
  
  protected abstract fun execUpdate()
  
  fun updateModel(exception: EViewModel? = null) {
    if (exception == null)
      execUpdate()
    else
      this.showMessage?.let { showMsg ->
        showMsg(exception)
      }
    
    updateView(this)
  }
  
@Throws(EViewModel::class)
  fun <T>exec(block: () -> T) : T?{
    return transaction {
      try {
        if (inExcection)
          block()
        else {
          inExcection = true
          val retBlock = block()
          inExcection = false
          updateModel()
          retBlock
        }
      } catch (e: EViewModel) {
        updateModel(e)
        null
      }
    }
  }
  
  private fun <T> transaction(block: () -> T): T {
    return try {
      val ret = block()
      Transaction.commit()
      ret
    } catch (e: Throwable) {
      Transaction.rollback()
      throw e
    }
  }
}

class EViewModel(msg: String) : Exception(msg)