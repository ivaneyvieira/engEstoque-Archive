package br.com.engecopi.framework.ui.view

import br.com.engecopi.framework.viewmodel.ViewModel
import com.vaadin.navigator.View
import com.vaadin.ui.Notification
import com.vaadin.ui.Notification.Type
import com.vaadin.ui.VerticalLayout

abstract class LayoutView : VerticalLayout(), View {
  abstract val viewModel: ViewModel
  
  init {
    viewModel.showMessage { e ->
      Notification.show(e.message, Type.WARNING_MESSAGE)
    }
  }
}