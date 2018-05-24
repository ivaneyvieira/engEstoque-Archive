package br.com.engecopi.framework.ui.view

import br.com.engecopi.estoque.ui.title
import br.com.engecopi.framework.viewmodel.ViewModel
import com.github.vok.karibudsl.VaadinDsl
import com.github.vok.karibudsl.isMargin
import com.github.vok.karibudsl.verticalLayout
import com.vaadin.navigator.View
import com.vaadin.ui.Notification
import com.vaadin.ui.Notification.Type
import com.vaadin.ui.VerticalLayout

abstract class LayoutView<V : ViewModel> : VerticalLayout(), View {
  abstract val viewModel: V
  
  fun form(titleForm : String, block: (@VaadinDsl VerticalLayout).()->Unit = {}){
    viewModel.showMessage { e ->
      Notification.show(e.message, Type.WARNING_MESSAGE)
    }
    isMargin = true
    setSizeFull()
    title(titleForm)
    this.block()
  }
}