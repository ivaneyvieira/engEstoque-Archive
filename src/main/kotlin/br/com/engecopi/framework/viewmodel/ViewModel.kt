package br.com.engecopi.framework.viewmodel

import br.com.engecopi.framework.model.Transaction

abstract class ViewModel(val view: IView) {
  private var inExcection = false

  private fun updateView(exception: EViewModel? = null) {
    exception?.let {e ->
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
    var ret: T? = null

    try {
      if(inExcection) ret = block()
      else transaction {
        inExcection = true
        updateModel()

        ret = block()

        updateView()
        inExcection = false
      }
    } catch(e: EViewModel) {
      inExcection = false
      updateView(e)
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
    try {
      if(inExcection) block()
      else transaction {
        inExcection = true
        updateModel()

        block()

        updateView()
        inExcection = false
      }
    } catch(e: EViewModel) {
      updateView(e)
      inExcection = false
      throw e
    }
  }

  @Throws(EViewModel::class)
  fun <T> execList(block: () -> List<T>): List<T> {
    return execValue(block).orEmpty()
  }

  private fun <T> transaction(block: () -> T) = Transaction.execTransacao {
    block()
  }
}

class EViewModel(msg: String): Exception(msg)

interface IView {
  fun updateView(viewModel: ViewModel)

  fun updateModel()

  fun showWarning(msg: String)

  fun showError(msg: String)

  fun showInfo(msg: String)
}

