package br.com.engecopi.framework.viewmodel

import br.com.engecopi.framework.model.Transaction

abstract class ViewModel(val view: IView) {
  private var inExcection = false

  private fun updateView(exception: EViewModel? = null) {
    exception?.let { e ->
      e.message?.let {
        view.showError(e.message)
      }
    }
    view.updateView(this)
  }

  private fun updateModel() {
    view.updateModel()
  }

  @Throws(EViewModel::class)
  fun <T> execValue(block: () -> T): T? {
    var ret : T? = null
     transaction {
      try {
        if (inExcection)
          ret= block()
        else {
          inExcection = true
          updateModel()

          ret= block()

          updateView()
          inExcection = false
        }
      } catch (e: EViewModel) {
        updateView(e)
      }
    }
    return ret
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
     transaction {
      try {
        if (inExcection)
          block()
        else {
          inExcection = true
          updateModel()

          block()

          updateView()
          inExcection = false
        }
      } catch (e: EViewModel) {
        updateView(e)
        throw e
      }
    }
  }

  @Throws(EViewModel::class)
  fun <T> execList(block: () -> List<T>): List<T> {
    return execValue(block).orEmpty()
  }

  private fun <T> transaction(block: () -> T) {
    return try {
      block()
      Transaction.commit()
    }catch (ev : EViewModel){
      Transaction.rollback()
    }
    catch (e: Throwable) {
      Transaction.rollback()
      throw e
    }
  }
}

class EViewModel(msg: String) : Exception(msg)

interface IView {
  fun updateView(viewModel: ViewModel)

  fun updateModel()

  fun showWarning(msg: String)

  fun showError(msg: String)

  fun showInfo(msg: String)
}

