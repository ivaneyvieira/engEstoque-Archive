package br.com.engecopi.framework.ui.view

import br.com.engecopi.estoque.ui.title
import br.com.engecopi.framework.viewmodel.IView
import br.com.engecopi.framework.viewmodel.ViewModel
import com.github.vok.karibudsl.VaadinDsl
import com.github.vok.karibudsl.isMargin
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent
import com.vaadin.ui.Grid
import com.vaadin.ui.VerticalLayout

abstract class LayoutView<V : ViewModel> : VerticalLayout(), View, IView {
  abstract val viewModel: V
  
  fun form(titleForm: String, block: (@VaadinDsl VerticalLayout).() -> Unit = {}) {
    isMargin = true
    setSizeFull()
    title(titleForm)
    this.block()
  }
  
  override fun enter(event: ViewChangeEvent) {
      updateView(viewModel)
  }
  
  fun <T> Grid<T>.actionSelected(msgErro: String = "Selecione um item", action: (T) -> Unit) {
    this.selectedItems.firstOrNull()?.let { item -> action(item) } ?: showWarning(msgErro)
  }
  
  override fun showWarning(msg: String) {
    if (msg.isNotBlank())
      MessageDialog.warning(message = msg)
  }
  
  override fun showError(msg: String) {
    if (msg.isNotBlank())
      MessageDialog.error(message = msg)
  }
  
  override fun showInfo(msg: String) {
    if (msg.isNotBlank())
      MessageDialog.info(message = msg)
  }
}