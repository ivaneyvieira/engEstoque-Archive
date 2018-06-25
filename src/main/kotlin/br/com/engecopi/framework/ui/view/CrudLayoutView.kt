package br.com.engecopi.framework.ui.view

import br.com.engecopi.framework.viewmodel.ViewModel
import com.github.vok.karibudsl.expandRatio
import com.github.vok.karibudsl.init
import com.vaadin.ui.HasComponents
import org.vaadin.crudui.crud.CrudOperation.ADD
import org.vaadin.crudui.crud.CrudOperation.DELETE
import org.vaadin.crudui.crud.CrudOperation.READ
import org.vaadin.crudui.crud.CrudOperation.UPDATE
import org.vaadin.crudui.crud.impl.GridCrud
import org.vaadin.crudui.form.impl.form.factory.VerticalCrudFormFactory
import org.vaadin.crudui.layout.impl.WindowBasedCrudLayout

abstract class CrudLayoutView<V : ViewModel> : LayoutView<V>() {
  abstract fun fieldsRead() : List<String>
  fun fieldsAdd() : List<String> = fieldsRead()
  fun fieldsUpdate() : List<String> = fieldsRead()
  fun fieldsDelete() : List<String> = fieldsRead()
  fun fieldsColumn() : List<String> = fieldsRead()
  
  
  override fun updateView(viewModel: ViewModel) {}
  
  override fun updateModel() {}
  fun <T> HasComponents.gridCrud(
          domainType: Class<T>,
          block: GridCrud<T>.() -> Unit = {}
                                ): GridCrud<T> {
    setSizeFull()
    
    val crudLayout = WindowBasedCrudLayout()
    crudLayout.defaults()
    val crudFormFactory = VerticalCrudFormFactory<T>(domainType)
    crudFormFactory.defaults()
    val gridCrud = GridCrud<T>(domainType, crudLayout, crudFormFactory/*, crudListener*/)
    gridCrud.defaults()
    return init(gridCrud){
          expandRatio = 1f
          block()
        }
  }
  
  private fun WindowBasedCrudLayout.defaults(){
    setWindowCaption(READ, "Consulta")
    setWindowCaption(ADD, "Adiciona")
    setWindowCaption(UPDATE, "Modifica")
    setWindowCaption(DELETE, "Apaga")
  }
  
  private fun <T> VerticalCrudFormFactory<T>.defaults(){
    setVisibleProperties(READ, *fieldsRead().toTypedArray())
    setVisibleProperties(ADD, *fieldsAdd().toTypedArray())
    setVisibleProperties(UPDATE, *fieldsUpdate().toTypedArray())
    setVisibleProperties(DELETE, *fieldsDelete().toTypedArray())
  
    setButtonCaption(READ, "Consulta")
    setButtonCaption(ADD, "Adiciona")
    setButtonCaption(UPDATE, "Modifica")
    setButtonCaption(DELETE, "Apaga")
    
    setCancelButtonCaption("Cancela")
    
  }

  private fun <T> GridCrud<T>.defaults() {
    grid.setColumns(*fieldsColumn().toTypedArray())
    setRowCountCaption("%d registro(s) encontrados")
    addButton.description = "Adiciona"
    findAllButton.description = "Atualiza"
    updateButton.description = "Modifica"
    deleteButton.description = "Apaga"
  }
}





