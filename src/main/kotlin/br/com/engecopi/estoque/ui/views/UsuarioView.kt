package br.com.engecopi.estoque.ui.views

import br.com.engecopi.estoque.model.Loja
import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.estoque.ui.EstoqueUI
import br.com.engecopi.estoque.viewmodel.ProdutoViewModel
import br.com.engecopi.estoque.viewmodel.UsuarioCrudVo
import br.com.engecopi.estoque.viewmodel.UsuarioViewModel
import br.com.engecopi.framework.printer.printerSaci
import br.com.engecopi.framework.ui.view.CrudLayoutView
import br.com.engecopi.framework.ui.view.bindItens
import br.com.engecopi.framework.ui.view.reloadBinderOnChange
import br.com.engecopi.framework.ui.view.row
import br.com.engecopi.framework.ui.view.tokenField
import com.fo0.advancedtokenfield.model.Token
import com.github.vok.karibudsl.AutoView
import com.github.vok.karibudsl.bind
import com.github.vok.karibudsl.comboBox
import com.github.vok.karibudsl.expandRatio
import com.github.vok.karibudsl.textField
import com.github.vok.karibudsl.twinColSelect
import com.vaadin.data.Binder
import com.vaadin.data.provider.CallbackDataProvider
import com.vaadin.data.provider.DataProvider
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.renderers.TextRenderer
import org.vaadin.crudui.crud.CrudOperation
import org.vaadin.crudui.crud.CrudOperation.UPDATE

@AutoView
class UsuarioView : CrudLayoutView<UsuarioCrudVo, UsuarioViewModel>() {
  override val viewModel
    get() = UsuarioViewModel(this)
  val isAdmin
    get() = EstoqueUI.user?.isAdmin ?: false
  
  val produtoDataProvider = DataProvider.fromCallbacks<Produto>(
          { query -> viewModel.findProduto(query.offset, query.limit).stream() },
          { _ -> viewModel.countProduto() }
                                                               )
  
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
          reloadBinderOnChange(binder)
        }
        comboBox<String> {
          expandRatio = 1f
          caption = "Impressora"
          isEmptySelectionAllowed = false
          isTextInputAllowed = false
          setItems(printerSaci.printers.map { it.name })
          setItemCaptionGenerator { it }
          bind(binder).bind(UsuarioCrudVo::impressora)
        }
        tokenField("Localização") {
          expandRatio = 1f
          addTokens(binder.bean.locaisLoja.map { Token(it) })
        }
      }
    }
    if (!isAdmin && operation == UPDATE)
      binder.setReadOnly(true)
  }
  
  init {
    form("Usuários") {
      gridCrud(viewModel.crudClass.java) {
        setDeleteOperationVisible(EstoqueUI.user?.isAdmin ?: false)
        column(UsuarioCrudVo::loginName) {
          expandRatio = 1
          caption = "Usuário"
        }
        column(UsuarioCrudVo::nome) {
          expandRatio = 5
          caption = "Nome"
        }
        column(UsuarioCrudVo::loja) {
          expandRatio = 1
          caption = "Loja"
          setRenderer({ loja -> loja?.sigla ?: "Todas" }, TextRenderer())
        }
        column(UsuarioCrudVo::local) {
          expandRatio = 1
          caption = "Localização"
        }
        column(UsuarioCrudVo::impressora) {
          expandRatio = 1
          caption = "Impressora"
        }
      }
    }
  }
}
