package br.com.engecopi.estoque.ui.views

import br.com.engecopi.estoque.model.Loja
import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.estoque.model.RegistryUserInfo
import br.com.engecopi.estoque.model.TipoMov
import br.com.engecopi.estoque.model.TipoUsuario
import br.com.engecopi.estoque.model.TipoUsuario.ESTOQUE
import br.com.engecopi.estoque.viewmodel.UsuarioCrudVo
import br.com.engecopi.estoque.viewmodel.UsuarioViewModel
import br.com.engecopi.framework.ui.view.CrudLayoutView
import br.com.engecopi.framework.ui.view.bindItensSet
import br.com.engecopi.framework.ui.view.expand
import br.com.engecopi.framework.ui.view.reloadBinderOnChange
import br.com.engecopi.framework.ui.view.row
import com.github.mvysny.karibudsl.v8.AutoView
import com.github.mvysny.karibudsl.v8.bind
import com.github.mvysny.karibudsl.v8.comboBox
import com.github.mvysny.karibudsl.v8.textField
import com.github.mvysny.karibudsl.v8.twinColSelect
import com.vaadin.data.Binder
import com.vaadin.data.provider.DataProvider
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.renderers.TextRenderer
import org.vaadin.crudui.crud.CrudOperation
import org.vaadin.crudui.crud.CrudOperation.UPDATE

@AutoView
class UsuarioView : CrudLayoutView<UsuarioCrudVo, UsuarioViewModel>() {
  override val viewModel
    get() = UsuarioViewModel(this)
  val isAdmin = RegistryUserInfo.usuarioDefault.admin
  val produtoDataProvider = DataProvider.fromCallbacks<Produto>(
    { query -> viewModel.findProduto(query.offset, query.limit).stream() },
    { viewModel.countProduto() }
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
          expand = 1
          caption = "Login Saci"
          isReadOnly = isAdmin == false
          bind(binder).bind(UsuarioCrudVo::loginName)
          addValueChangeListener {
            binder.readBean(binder.bean)
          }
        }
        textField {
          expand = 4
          caption = "Nome"
          isReadOnly = true
          bind(binder).bind(UsuarioCrudVo::nome.name)
        }
      }
      row {
        comboBox<Loja> {
          expand = 1
          caption = "Loja"
          isEmptySelectionAllowed = true
          isTextInputAllowed = false
          this.emptySelectionCaption = "Todas"
          setItems(viewModel.lojas)
          setItemCaptionGenerator { it.sigla }
          bind(binder).bind(UsuarioCrudVo::loja)
          reloadBinderOnChange(binder)
        }
        comboBox<TipoUsuario> {
          expand = 1
          caption = "Loja"
          isEmptySelectionAllowed = false
          isTextInputAllowed = false
          //this.emptySelectionCaption = "Todas"
          setItems(TipoUsuario.values().toList())
          setItemCaptionGenerator { it.descricao }
          bind(binder).bind(UsuarioCrudVo::tipoUsuario)
          reloadBinderOnChange(binder)
        }
      }
      row {
        twinColSelect<String>("Localizações") {
          bindItensSet(binder, UsuarioCrudVo::locaisLoja.name)
          bind(binder).bind(UsuarioCrudVo::localizacaoes)
        }
      }
    }
    if (!isAdmin && operation == UPDATE)
      binder.setReadOnly(true)
  }

  init {
    form("Usuários") {
      gridCrud(viewModel.crudClass.java) {
        setDeleteOperationVisible(RegistryUserInfo.usuarioDefault.admin)
        column(UsuarioCrudVo::loginName) {
          expandRatio = 1
          caption = "Usuário"
          setSortProperty("loginName")
        }
        column(UsuarioCrudVo::nome) {
          expandRatio = 5
          caption = "Nome"
        }
        column(UsuarioCrudVo::tipoUsuario) {
          expandRatio = 1
          caption = "Tipo de Usuário"
          setRenderer({ user -> user?.descricao ?: "N/C" }, TextRenderer())
        }
        column(UsuarioCrudVo::loja) {
          expandRatio = 1
          caption = "Loja"
          setRenderer({ loja -> loja?.sigla ?: "Todas" }, TextRenderer())
        }
        column(UsuarioCrudVo::localStr) {
          expandRatio = 1
          caption = "Localização"
          setSortProperty("localizacaoesDefault")
        }
      }
    }
  }
}