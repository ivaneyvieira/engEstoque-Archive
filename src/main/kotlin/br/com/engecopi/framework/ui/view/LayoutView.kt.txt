package br.com.engecopi.framework.ui.view

import br.com.engecopi.estoque.ui.title
import br.com.engecopi.framework.viewmodel.ViewModel
import com.github.vok.karibudsl.VaadinDsl
import com.github.vok.karibudsl.isMargin
import com.vaadin.navigator.View
import com.vaadin.ui.Grid
import com.vaadin.ui.VerticalLayout

abstract class LayoutView<V : ViewModel> : VerticalLayout(), View {
  abstract val viewModel: V
  
  fun form(titleForm: String, block: (@VaadinDsl VerticalLayout).() -> Unit = {}) {
    viewModel.showMessage { e ->
      MessageDialog.warning(message = e.message ?: "")
    }
    isMargin = true
    setSizeFull()
    title(titleForm)
    this.block()
  }
  
  fun <T> Grid<T>.actionSelected(msgErro: String = "Selecione um item", action: (T) -> Unit) {
    this.selectedItems.firstOrNull()?.let { item -> action(item) } ?: MessageDialog.warning(message = msgErro)
  }
}