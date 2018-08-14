package br.com.engecopi.framework.ui.view

import br.com.engecopi.framework.viewmodel.CrudViewModel
import br.com.engecopi.framework.viewmodel.EntityVo
import br.com.engecopi.framework.viewmodel.Sort
import br.com.engecopi.framework.viewmodel.ViewModel
import com.github.vok.karibudsl.addGlobalShortcutListener
import com.github.vok.karibudsl.expandRatio
import com.github.vok.karibudsl.init
import com.vaadin.data.Binder
import com.vaadin.data.provider.CallbackDataProvider
import com.vaadin.data.provider.DataProvider
import com.vaadin.data.provider.Query

import com.vaadin.event.ShortcutAction.KeyCode
import com.vaadin.event.ShortcutAction.KeyCode.ENTER
import com.vaadin.icons.VaadinIcons
import com.vaadin.shared.data.sort.SortDirection
import com.vaadin.ui.Alignment
import com.vaadin.ui.Button
import com.vaadin.ui.Button.ClickListener
import com.vaadin.ui.Component
import com.vaadin.ui.Grid
import com.vaadin.ui.Grid.Column
import com.vaadin.ui.HasComponents
import com.vaadin.ui.TextField
import com.vaadin.ui.UI
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.Window
import org.vaadin.crudui.crud.CrudListener
import org.vaadin.crudui.crud.CrudOperation
import org.vaadin.crudui.crud.CrudOperation.ADD
import org.vaadin.crudui.crud.CrudOperation.DELETE
import org.vaadin.crudui.crud.CrudOperation.READ
import org.vaadin.crudui.crud.CrudOperation.UPDATE
import org.vaadin.crudui.crud.CrudOperationException
import org.vaadin.crudui.crud.impl.GridCrud
import org.vaadin.crudui.form.AbstractAutoGeneratedCrudFormFactory
import org.vaadin.crudui.form.CrudFormFactory
import org.vaadin.crudui.layout.CrudLayout
import org.vaadin.crudui.layout.impl.WindowBasedCrudLayout
import java.util.stream.*
import kotlin.reflect.KProperty1

abstract class CrudLayoutView<C : EntityVo<*>, V : CrudViewModel<*, *, C>> : LayoutView<V>() {
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
          formLayout: VerticalLayout,
          operation: CrudOperation?,
          binder: Binder<C>,
          readOnly: Boolean
                         )
  
  private fun WindowBasedCrudLayout.defaults() {
    setWindowCaption(READ, "Consulta")
    setWindowCaption(ADD, "Adiciona")
    setWindowCaption(UPDATE, "Modifica")
    setWindowCaption(DELETE, "Apaga")
    setFormWindowWidth("80%")
  }
  
  private fun <T : Any> CustomCrudFormFactory<T>.defaults() {
    setButtonCaption(READ, "Consulta")
    setButtonCaption(ADD, "Adiciona")
    setButtonCaption(UPDATE, "Modifica")
    setButtonCaption(DELETE, "Apaga")
    
    setCancelButtonCaption("Cancela")
  }
  
  private fun <T : EntityVo<*>> GridCrudFlex<T>.defaults() {
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
    if (operation == DELETE || operation == READ)
      binder.fields.forEach {
        val com = it as? Component
        it.isReadOnly = com?.let {
          it.id != "filtro"
        } ?: true
      }
    
    val footerLayout = buildFooter(operation, domainObject, cancelButtonClickListener, operationButtonClickListener)
    
    val mainLayout = VerticalLayout()
    mainLayout.addComponentsAndExpand(layout)
    mainLayout.addComponent(footerLayout)
    mainLayout.setComponentAlignment(footerLayout, Alignment.BOTTOM_RIGHT)
    mainLayout.setMargin(true)
    
    return mainLayout
  }
}

class ViewModelCrudListener<T : EntityVo<*>>(val crudViewModel: CrudViewModel<*, *, T>) : CrudListener<T> {
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
    return emptyList()
  }
  
  override fun delete(domainObjectToDelete: T) {
    crudViewModel.crudBean = domainObjectToDelete
    crudViewModel.delete()
  }
  
  fun findQuery(offset: Int, limit: Int, filter: String, sorts: List<Sort>): List<T> {
    return crudViewModel.findQuery(offset, limit, filter, sorts)
  }
  
  fun countQuery(filter: String): Int {
    return crudViewModel.countQuery(filter)
  }
}

class WindowsCrud : WindowBasedCrudLayout() {
  override fun showForm(operation: CrudOperation, form: Component) {
    showWindow(windowCaptions[operation], form)
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

open class GridCrudFlex<T : EntityVo<*>>(
        domainType: Class<T>,
        crudLayout: CrudLayout,
        crudFormFactory: CrudFormFactory<T>,
        val crudListener: ViewModelCrudListener<T>
                                ) : GridCrud<T>(domainType, crudLayout, crudFormFactory, crudListener) {
  val find = CallbackDataProvider.FetchCallback<T, String> { query ->
    findQuery(query)
  }
  val count = CallbackDataProvider.CountCallback<T, String> { query ->
    countQuery(query)
  }
  private val dataProvider = DataProvider.fromFilteringCallbacks(find, count)
          .withConfigurableFilter()
          
  var readButton: Button? = null
  val filtroEdt = TextField {
    val value = if (it.value.isNullOrBlank()) null else it.value
    dataProvider.setFilter(value)
    dataProvider.refreshAll()
  }
  
  init {
    items = emptyList()
    grid.addGlobalShortcutListener(ENTER) {
      if (!grid.selectedItems.isEmpty())
        if (updateButton.isVisible)
          updateButtonClicked()
        else
          readButtonClicked()
    }
    grid.addItemClickListener { e ->
      if (e.mouseEventDetails.isDoubleClick)
        if (!grid.asSingleSelect().isEmpty)
          if (updateButton.isVisible)
            updateButtonClicked()
          else
            readButtonClicked()
    }
    
    grid.dataProvider = dataProvider
    
    crudLayout.addFilterComponent(filtroEdt)
  }
  
  override fun initLayout() {
    findAllButton = Button("") { _ -> findAllButtonClicked() }
    findAllButton.description = "Refresh list"
    findAllButton.icon = VaadinIcons.REFRESH
    crudLayout.addToolbarComponent(findAllButton)
    
    addButton = Button("") { _ -> addButtonClicked() }
    addButton.description = "Add"
    addButton.icon = VaadinIcons.PLUS
    crudLayout.addToolbarComponent(addButton)
    
    updateButton = Button("") { _ -> updateButtonClicked() }
    updateButton.description = "Update"
    updateButton.icon = VaadinIcons.PENCIL
    crudLayout.addToolbarComponent(updateButton)
    
    readButton = Button("") { _ -> readButtonClicked() }
    readButton?.description = "Read"
    readButton?.icon = VaadinIcons.SEARCH
    crudLayout?.addToolbarComponent(readButton)
    
    deleteButton = Button("") { _ -> deleteButtonClicked() }
    deleteButton.description = "Delete"
    deleteButton.icon = VaadinIcons.TRASH
    crudLayout.addToolbarComponent(deleteButton)
    
    grid = Grid<T>(domainType)
    grid.setSizeFull()
    grid.addSelectionListener { _ -> gridSelectionChanged() }
    crudLayout.setMainComponent(grid)
    
    updateButtons()
    queryOnly = false
  }
  
  var queryOnly: Boolean = false
    set(value) {
      field = value
      
      findAllButton.isVisible = true
      addButton.isVisible = !value
      updateButton.isVisible = !value
      readButton?.isVisible = value
      deleteButton.isVisible = !value
    }
  
  var addOnly: Boolean = false
    set(value) {
      field = value
      
      findAllButton.isVisible = true
      addButton.isVisible = true
      updateButton.isVisible = !value
      readButton?.isVisible = value
      deleteButton.isVisible = !value
    }
  
  override fun updateButtons() {
    val rowSelected = !grid.asSingleSelect().isEmpty
    updateButton.isEnabled = rowSelected
    deleteButton.isEnabled = rowSelected
    readButton?.isEnabled = rowSelected
  }
  
  protected fun readButtonClicked() {
    val domainObject = grid.asSingleSelect().value
    showForm(CrudOperation.READ, domainObject, false, savedMessage) { _ ->
      try {
        grid.asSingleSelect().clear()
        refreshGrid()
        if (items.contains(domainObject)) {
          grid.asSingleSelect().value = domainObject
          // TODO: grid.scrollTo(updatedObject);
        }
      } catch (e1: CrudOperationException) {
        refreshGrid()
      } catch (e2: Exception) {
        refreshGrid()
        throw e2
      }
    }
  }
  
  override fun gridSelectionChanged() {
    updateButtons()
  }
  
  override fun refreshGrid() {
    grid.dataProvider.refreshAll()
  }
  
  fun findQuery(query: Query<T, String>): Stream<T> {
    val sorts = query.sortOrders.map {
      Sort(it.sorted, it.direction == SortDirection.DESCENDING)
    }
    return crudListener.findQuery(query.offset, query.limit, query.filter.orElse(""), sorts).stream()
  }
  
  fun countQuery(query: Query<T, String>): Int {
    return crudListener.countQuery(query.filter.orElse(""))
  }
}