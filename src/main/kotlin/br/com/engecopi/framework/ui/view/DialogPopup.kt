package br.com.engecopi.framework.ui.view

import com.vaadin.data.BeanValidationBinder
import com.vaadin.shared.Registration
import com.vaadin.ui.Button
import com.vaadin.ui.Component
import com.vaadin.ui.FormLayout
import com.vaadin.ui.HorizontalLayout
import com.vaadin.ui.Label
import com.vaadin.ui.UI
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.Window
import com.vaadin.ui.themes.ValoTheme
import kotlin.reflect.KClass

open class DialogPopup<BEAN : Any>(
        caption: String, classBean: KClass<BEAN>
                                  ) : Window(caption) {
  val binder = BeanValidationBinder(classBean.java)
  val form = FormLayout().apply {
    setSizeUndefined()
  }
  
  private val btnOk: Button = Button("Confirma").apply {
    addStyleName(ValoTheme.BUTTON_PRIMARY)
  }
  private val btnCancel = Button("Cancela")
  
  val toolBar = buildToolBar()
  
  init {
    isClosable = false
    isResizable = false
    isModal = true
    addStyleName(ValoTheme.PANEL_WELL)
  }
  
  fun show() {
    center()
    content = VerticalLayout(form, toolBar)
    UI.getCurrent().addWindow(this)
  }
  
  fun initForm(condigForm: (FormLayout) -> Unit) {
    condigForm(form)
  }
  
  private fun buildToolBar(): Component {
    val espaco = Label()
    val tool = HorizontalLayout()
    tool.setWidth("100%")
    tool.isSpacing = true
    tool.addStyleName(ValoTheme.WINDOW_BOTTOM_TOOLBAR)
    tool.addComponents(espaco, btnOk, btnCancel)
    tool.setExpandRatio(espaco, 1f)
    btnOk.addClickListener({ this.btnOkClick() })
    btnCancel.addClickListener({ this.btnCancelClick() })
    return tool
  }
  
  fun addClickListenerOk(listener: (Button.ClickEvent) -> Unit): Registration {
    return btnOk.addClickListener(listener)
  }
  
  fun addClickListenerCancel(listener: (Button.ClickEvent) -> Unit): Registration {
    return btnCancel.addClickListener(listener)
  }
  
  private fun btnCancelClick() {
    close()
  }
  
  private fun btnOkClick() {
    val status = binder.validate()
    if (!status.hasErrors())
      close()
  }
}
