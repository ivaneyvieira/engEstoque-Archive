package br.com.engecopi.framework.ui.view

import br.com.engecopi.estoque.ui.title
import br.com.engecopi.framework.viewmodel.IView
import br.com.engecopi.framework.viewmodel.ViewModel
import br.com.engecopi.saci.beans.ProdutoSaci
import com.github.vok.karibudsl.VaadinDsl
import com.github.vok.karibudsl.bind
import com.github.vok.karibudsl.isMargin
import com.vaadin.data.Binder
import com.vaadin.data.Binder.Binding
import com.vaadin.data.HasDataProvider
import com.vaadin.data.HasFilterableDataProvider
import com.vaadin.data.HasItems
import com.vaadin.data.HasValue
import com.vaadin.data.ReadOnlyHasValue
import com.vaadin.data.ValueProvider
import com.vaadin.data.provider.DataProvider
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent
import com.vaadin.ui.ComboBox
import com.vaadin.ui.Grid
import com.vaadin.ui.VerticalLayout
import kotlin.reflect.KProperty

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

fun <T> ComboBox<T>.default(
        valueEmpty: T? = null,
        captionGenerator: (T) -> String = { it.toString() }
                           ) {
  isEmptySelectionAllowed = false
  isTextInputAllowed = false
  valueEmpty?.let {
    this.emptySelectionCaption = "Todas"
    isEmptySelectionAllowed = true
  }
  setItemCaptionGenerator(captionGenerator)
}

fun <V, T> ComboBox<T>.bindItens(
        binder: Binder<V>,
        getter: (V) -> List<T>
                                ): Binding<V, List<T>> {
  val field = ReadOnlyHasValue<List<T>> { itens -> setItems(itens) }
  return field.bind(binder).bind(getter, null)
}

fun <V, T> Grid<T>.bindItens(
        binder: Binder<V>,
        getter: (V) -> List<T>
                            ): Binding<V, List<T>> {
  val field = ReadOnlyHasValue<List<T>> { itens -> setItems(itens) }
  return field.bind(binder).bind(getter, null)
}

fun Binder<*>.reload() {
  bean = bean
}

fun <BEAN, FIELDVALUE> HasValue<FIELDVALUE>.reloadBinderOnChange(binder: Binder<BEAN>) {
  addValueChangeListener { event ->
    if (event.isUserOriginated)
      binder.reload()
  }
}