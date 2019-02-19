package br.com.engecopi.estoque.ui.views

import br.com.engecopi.estoque.model.RegistryUserInfo
import br.com.engecopi.estoque.viewmodel.NFExpedicaoViewModel
import br.com.engecopi.estoque.viewmodel.NFExpedicaoVo
import br.com.engecopi.estoque.viewmodel.SaidaVo
import br.com.engecopi.framework.ui.view.CrudLayoutView
import br.com.engecopi.framework.ui.view.GridCrudFlex
import br.com.engecopi.framework.ui.view.dateFormat
import br.com.engecopi.framework.ui.view.expand
import br.com.engecopi.framework.ui.view.grupo
import br.com.engecopi.framework.ui.view.row
import br.com.engecopi.framework.viewmodel.EntityVo
import br.com.engecopi.framework.viewmodel.ViewModel
import com.github.mvysny.karibudsl.v8.AutoView
import com.github.mvysny.karibudsl.v8.button
import com.github.mvysny.karibudsl.v8.dateField
import com.github.mvysny.karibudsl.v8.px
import com.github.mvysny.karibudsl.v8.refresh
import com.github.mvysny.karibudsl.v8.textField
import com.github.mvysny.karibudsl.v8.verticalLayout
import com.github.mvysny.karibudsl.v8.w
import com.vaadin.data.Binder
import com.vaadin.icons.VaadinIcons
import com.vaadin.ui.Button
import com.vaadin.ui.Image
import com.vaadin.ui.UI
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.renderers.TextRenderer
import com.vaadin.ui.themes.ValoTheme
import de.steinwedel.messagebox.ButtonOption
import de.steinwedel.messagebox.MessageBox
import org.vaadin.crudui.crud.CrudOperation

@AutoView("nf_expedicao")
class NFExpedicaoView : CrudLayoutView<NFExpedicaoVo, NFExpedicaoViewModel>() {
  val isAdmin
    get() = RegistryUserInfo.userDefaultIsAdmin

  override fun layoutForm(formLayout: VerticalLayout, operation: CrudOperation?, binder: Binder<NFExpedicaoVo>,
                          readOnly: Boolean) {
    formLayout.apply {
      w = (UI.getCurrent().page.browserWindowWidth * 0.8).toInt().px
      val nota = binder.bean
      grupo("Nota fiscal de saída") {
        verticalLayout {
          row {
            textField("Nota Fiscal") {
              expand = 2
              isReadOnly = true
              value = nota.numero
            }
            textField("Loja") {
              expand = 2
              isReadOnly = true
              value = nota.loja?.sigla
            }
            textField("Tipo") {
              expand = 2
              isReadOnly = true
              value = nota.tipoNota?.descricao
            }
            dateField("Data") {
              expand = 1
              isReadOnly = true
              value = nota.data
            }
            textField("Rota") {
              expand = 1
              isReadOnly = true
              value = nota.rota
            }
          }
          row {
            textField("Observação da nota fiscal") {
              expand = 1
              isReadOnly = true
              value = nota.observacao
            }
          }
        }
      }
    }
  }

  init {
    form("Nota Fiscal (Expedição)") {
      gridCrud(viewModel.crudClass.java) {
        addCustomToolBarComponent(btnImprimeTudo(this))
        addCustomToolBarComponent(btnLerChaveNota(this))
        setUpdateOperationVisible(false)
        setAddOperationVisible(false)
        setDeleteOperationVisible(RegistryUserInfo.usuarioDefault.admin)
        column(NFExpedicaoVo::numero) {
          caption = "Número NF"
          setSortProperty("numero")
        }
        grid.addComponentColumn { item ->
          val button = Button()
          print {
            val print = viewModel.imprimir(item)
            print
          }.extend(button)
          val impresso = item?.impresso ?: true
          button.isEnabled = impresso == false || isAdmin
          button.icon = VaadinIcons.PRINT
          button.addClickListener {
            val print = item?.impresso ?: true
            it.button.isEnabled = print == false || isAdmin
            refreshGrid()
          }
          button
        }.id = "btnPrint"
        column(NFExpedicaoVo::loja) {
          caption = "Loja NF"
          setRenderer({ loja -> loja?.sigla ?: "" }, TextRenderer())
        }
        column(NFExpedicaoVo::tipoNota) {
          caption = "TipoNota"
          setRenderer({ tipo -> tipo?.descricao ?: "" }, TextRenderer())
          setSortProperty("tipo_nota")
        }
        column(NFExpedicaoVo::lancamento) {
          caption = "Lançamento"
          dateFormat()
          setSortProperty("data", "hora")
        }
        column(NFExpedicaoVo::dataEmissao) {
          caption = "Emissao"
          dateFormat()
          setSortProperty("dataEmissao", "data", "hora")
        }
        column(NFExpedicaoVo::abreviacao) {
          caption = "Localização"
          setSortProperty("abreviacao")
        }
        column(NFExpedicaoVo::usuario) {
          caption = "Usuário"
          setRenderer({ it?.loginName ?: "" }, TextRenderer())
          setSortProperty("usuario.loginName")
        }
        column(NFExpedicaoVo::rota) {
          caption = "Rota"
        }
        column(NFExpedicaoVo::cliente) {
          caption = "Cliente"
          setSortProperty("cliente")
        }
      }
    }
  }

  override fun updateView(viewModel: ViewModel) {
  }

  override fun updateModel() {
  }

  override val viewModel: NFExpedicaoViewModel
    get() = NFExpedicaoViewModel(this)

  fun readString(msg: String, processaleitura: (String) -> Unit) {
    if (msg.isNotBlank()) {
      val textField = textField(msg) {
        this.w = 400.px
      }

      MessageBox.createQuestion()
        .withCaption("Leitura")
        .withIcon(Image().apply {
          icon = VaadinIcons.BARCODE
          focus()
        })
        .withMessage(textField)
        .withNoButton({ },
                      arrayOf(ButtonOption.caption("Cancela")))
        .withYesButton({ processaleitura(textField.value) },
                       arrayOf(ButtonOption.caption("Confirma"),
                               ButtonOption.style(ValoTheme.BUTTON_PRIMARY),
                               buttonDefault()))
        .withWidth("300px")
        .open().apply {
          textField.focus()
        }
    }
  }

  private fun buttonDefault(): ButtonOption {
    return ButtonOptionDefault()
  }

  private fun btnLerChaveNota(gridCrudFlex: GridCrudFlex<NFExpedicaoVo>): Button {
    return button("Ler Nota") {
      icon = VaadinIcons.BARCODE
      addClickListener {
        readString("Chave da nota fiscal") { key ->
          viewModel.processaKey(key)
          gridCrudFlex.grid.refresh()
        }
      }
    }
  }

  fun btnImprimeTudo(grid : GridCrudFlex<NFExpedicaoVo>) : Button{
    val button = Button("Imprime Etiquetas")
    button.let {
      it.icon = VaadinIcons.PRINT
      print {
        val print = viewModel.imprime()
        print
      }.extend(it)
      it.addClickListener{
        grid.refreshGrid()
      }
    }
    return button
  }
}