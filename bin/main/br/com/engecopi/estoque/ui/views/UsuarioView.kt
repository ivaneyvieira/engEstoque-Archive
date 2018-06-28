package br.com.engecopi.estoque.ui.views

import br.com.engecopi.estoque.model.Loja
import br.com.engecopi.estoque.viewmodel.UsuarioCrudVo
import br.com.engecopi.estoque.viewmodel.UsuarioViewModel
import br.com.engecopi.framework.ui.view.CrudLayoutView
import br.com.engecopi.framework.ui.view.row
import com.github.vok.karibudsl.AutoView
import com.github.vok.karibudsl.bind
import com.github.vok.karibudsl.comboBox
import com.github.vok.karibudsl.expandRatio
import com.github.vok.karibudsl.textField
import com.vaadin.data.Binder
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.renderers.TextRenderer
import org.vaadin.crudui.crud.CrudOperation

@AutoView
class UsuarioView : CrudLayoutView<UsuarioCrudVo, UsuarioViewModel>() {
  override val viewModel = UsuarioViewModel(this)
  
  override fun fieldsRead() = listOf(UsuarioCrudVo::loginName, UsuarioCrudVo::nome, UsuarioCrudVo::loja)
  
  override fun layoutForm(
          formLayout: VerticalLayout,
          operation: CrudOperation?,
          binder: Binder<UsuarioCrudVo>,
          readOnly: Boolean
                         ) {
    formLayout.apply {
      row {
        textField {
          expandRatio = 1f
          caption = "Login Saci"
          bind(binder).bind(UsuarioCrudVo::loginName)
          addValueChangeListener {
            binder.readBean(binder.bean)
          }
        }
        textField {
          expandRatio = 4f
          caption = "Nome"
          isReadOnly = true
          bind(binder).bind(UsuarioCrudVo::nome.name)
        }
      }
      row {
        comboBox<Loja> {
          expandRatio = 1f
          caption = "Loja"
          isEmptySelectionAllowed = true
          isTextInputAllowed = false
          this.emptySelectionCaption = "Todas"
          setItems(viewModel.lojas)
          setItemCaptionGenerator { it.sigla }
          bind(binder).bind(UsuarioCrudVo::loja)
        }
      }
    }
  }
  
  init {
    form("Usuários") {
      gridCrud(viewModel.crudClass.java) {
        column(UsuarioCrudVo::nome){
          expandRatio = 1
        }
        column(UsuarioCrudVo::loja) {
          setRenderer({ loja -> loja?.sigla ?: "Todas" }, TextRenderer())
        }
      }
    }
  }
}
