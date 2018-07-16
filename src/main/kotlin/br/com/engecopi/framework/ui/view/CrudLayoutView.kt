package br.com.engecopi.framework.ui.view

import br.com.engecopi.framework.viewmodel.CrudViewModel
import br.com.engecopi.framework.viewmodel.ViewModel
import com.github.vok.karibudsl.addGlobalShortcutListener
import com.github.vok.karibudsl.expandRatio
import com.github.vok.karibudsl.init
import com.vaadin.data.Binder
import com.vaadin.event.ShortcutAction.KeyCode
import com.vaadin.event.ShortcutAction.KeyCode.ENTER
import com.vaadin.ui.Alignment
import com.vaadin.ui.Button.ClickListener
import com.vaadin.ui.Component
import com.vaadin.ui.Grid.Column
import com.vaadin.ui.HasComponents
import com.vaadin.ui.UI
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.Window
import org.vaadin.crudui.crud.CrudListener
import org.vaadin.crudui.crud.CrudOperation
import org.vaadin.crudui.crud.CrudOperation.ADD
import org.vaadin.crudui.crud.CrudOperation.DELETE
import org.vaadin.crudui.crud.CrudOperation.READ
import org.vaadin.crudui.crud.CrudOperation.UPDATE
import org.vaadin.crudui.crud.impl.GridCrud
import org.vaadin.crudui.form.AbstractAutoGeneratedCrudFormFactory
import org.vaadin.crudui.form.CrudFormFactory
import org.vaadin.crudui.layout.CrudLayout
import org.vaadin.crudui.layout.impl.WindowBasedCrudLayout
import kotlin.reflect.KProperty1

abstract class CrudLayoutView<C : Any, V : CrudViewModel<C>> : LayoutView<V>() {
  override fun updateView(viewModel: ViewModel) {}
  
  override fun updateModel() {}
  
  fun HasComponents.gridCrud(
          domainType: Class<C>,
          block: GridCrudFlex<C>.() -> Unit = {}
                            ): GridCrudFlex<C> {
    setSizeFull()
    
    val crudLayout = WindowsCrud()
    crudLayout.defaults()
    val crudFormFactory = CustomCrudFormFactory(domainType, ::layoutForm)
    crudFormFactory.defaults()
    val crudListener = ViewModelCrudListener(viewModel)
    val gridCrud = GridCrudFlex(domainType, crudLayout, crudFormFactory, crudListener)
    gridCrud.defaults()
    
    return init(gridCrud) {
      expandRatio = 1f
      block()
    }
  }
  
  fun <T> GridCrudFlex<C>.column(property: KProperty1<C, T>, block: Column<C, T?>.() -> Unit = {}): Column<C, T?> {
    val column: Column<C, T?> = grid.addColumn(property)
    column.block()
    return column
  }
  
  abstract fun layoutForm(
          formLayout: VerticalLayout, operation: CrudOperation?, binder: Binder<C>, readOnly: Boolean
                         )
  
  private fun WindowBasedCrudLayout.defaults() {
    setWindowCaption(READ, "Consulta")
    setWindowCaption(ADD, "Adiciona")
    setWindowCaption(UPDATE, "Modifica")
    setWindowCaption(DELETE, "Apaga")
    setFormWindowWidth("80%")
  }
  
  private fun <T> CustomCrudFormFactory<T>.defaults() {
    setButtonCaption(READ, "Consulta")
    setButtonCaption(ADD, "Adiciona")
    setButtonCaption(UPDATE, "Modifica")
    setButtonCaption(DELETE, "Apaga")
    
    setCancelButtonCaption("Cancela")
  }
  
  private fun <T> GridCrudFlex<T>.defaults() {
    grid.removeAllColumns()
    
    setRowCountCaption("%d registro(s) encontrados")
    addButton.description = "Adiciona"
    findAllButton.description = "Atualiza"
    updateButton.description = "Modifica"
    deleteButton.description = "Apaga"
  }
}

class CustomCrudFormFactory<T>(
        domainType: Class<T>,
        private val layoutForm: (VerticalLayout, CrudOperation?, Binder<T>, Boolean) -> Unit
                              ) : AbstractAutoGeneratedCrudFormFactory<T>(domainType) {
  override fun buildNewForm(
          operation: CrudOperation?, domainObject: T, readOnly: Boolean, cancelButtonClickListener: ClickListener?,
          operationButtonClickListener: ClickListener?
                           ): Component {
    binder = buildBinder(operation, domainObject)
    val layout = VerticalLayout()
    binder.bean = domainObject
    layoutForm(layout, operation, binder, readOnly)
    
    val footerLayout = buildFooter(operation, domainObject, cancelButtonClickListener, operationButtonClickListener)
    
    val mainLayout = VerticalLayout()
    mainLayout.addComponentsAndExpand(layout)
    mainLayout.addComponent(footerLayout)
    mainLayout.setComponentAlignment(footerLayout, Alignment.BOTTOM_RIGHT)
    mainLayout.setMargin(true)
    
    return mainLayout
  }
}

class ViewModelCrudListener<T : Any>(val crudViewModel: CrudViewModel<T>) : CrudListener<T> {
  override fun update(domainObjectToUpdate: T): T {
    crudViewModel.crudBean = domainObjectToUpdate
    crudViewModel.update()
    return crudViewModel.crudBean!!
  }
  
  override fun add(domainObjectToAdd: T): T {
    crudViewModel.crudBean = domainObjectToAdd
    crudViewModel.add()
    return crudViewModel.crudBean!!
  }
  
  override fun findAll(): List<T> {
    return crudViewModel.findAll()
  }
  
  override fun delete(domainObjectToDelete: T) {
    crudViewModel.crudBean = domainObjectToDelete
    crudViewModel.delete()
  }
}

class WindowsCrud : WindowBasedCrudLayout() {
  override fun showForm(operation: CrudOperation, form: Component) {
    if (operation != CrudOperation.READ) {
      showWindow(windowCaptions[operation], form)
    }
  }
  
  private fun showWindow(caption: String?, form: Component) {
    val windowLayout = VerticalLayout(form)
    windowLayout.setWidth("100%")
    windowLayout.setMargin(false)
    windowLayout.isResponsive = true
    
    formWindow = Window(caption, windowLayout)
    //formWindow.setWidth(formWindowWidth)
    formWindow.isClosable = true
    formWindow.isResizable = false
    formWindow.isModal = true
    formWindow.styleName = "modal"
    formWindow.center()
    formWindow.addCloseShortcut(KeyCode.ESCAPE)
    UI.getCurrent().addWindow(formWindow)
  }
}

class GridCrudFlex<T>(
        domainType: Class<T>,
        crudLayout: CrudLayout,
        crudFormFactory: CrudFormFactory<T>,
        crudListener: CrudListener<T>
                     ) : GridCrud<T>(domainType, crudLayout, crudFormFactory, crudListener) {
  init {
    grid.addGlobalShortcutListener(ENTER) {
      if (!grid.selectedItems.isEmpty())
        updateButtonClicked()
    }
    grid.addItemClickListener { e ->
      if (e.mouseEventDetails.isDoubleClick)
        if (!grid.selectedItems.isEmpty())
          updateButtonClicked()
    }
  }
}