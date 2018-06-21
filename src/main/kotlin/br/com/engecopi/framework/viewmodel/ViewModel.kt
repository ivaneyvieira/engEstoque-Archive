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
  fun <T> execValue(block: () -> T): T? {
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
  
  @Throws(EViewModel::class)
  fun execString(block: () -> String): String {
    return execValue(block) ?: ""
  }
  
  @Throws(EViewModel::class)
  fun execInt(block: () -> Int): Int {
    return execValue(block) ?: 0
  }
  
  @Throws(EViewModel::class)
  fun exec(block: () -> Unit) {
    execValue(block)
  }
  
  @Throws(EViewModel::class)
  fun <T> execList(block: () -> List<T>): List<T> {
    return execValue(block).orEmpty()
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