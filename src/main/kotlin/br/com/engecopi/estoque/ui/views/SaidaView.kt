package br.com.engecopi.estoque.ui.views

import br.com.engecopi.estoque.model.TipoNota
import br.com.engecopi.estoque.viewmodel.SaidaViewModel
import br.com.engecopi.estoque.viewmodel.SaidaVo
import br.com.engecopi.framework.ui.view.GridCrudFlex
import br.com.engecopi.framework.ui.view.dateFormat
import br.com.engecopi.framework.ui.view.default
import br.com.engecopi.framework.ui.view.expand
import br.com.engecopi.framework.ui.view.grupo
import br.com.engecopi.framework.ui.view.intFormat
import br.com.engecopi.framework.ui.view.row
import com.github.mvysny.karibudsl.v8.AutoView
import com.github.mvysny.karibudsl.v8.bind
import com.github.mvysny.karibudsl.v8.button
import com.github.mvysny.karibudsl.v8.comboBox
import com.github.mvysny.karibudsl.v8.dateField
import com.github.mvysny.karibudsl.v8.px
import com.github.mvysny.karibudsl.v8.textField
import com.github.mvysny.karibudsl.v8.verticalLayout
import com.github.mvysny.karibudsl.v8.w
import com.vaadin.data.Binder
import com.vaadin.event.ShortcutAction.KeyCode
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
import org.vaadin.crudui.crud.CrudOperation.ADD
import org.vaadin.crudui.crud.CrudOperation.UPDATE

@AutoView
class SaidaView : NotaView<SaidaVo, SaidaViewModel>() {
  override fun layoutForm(
    formLayout: VerticalLayout,
    operation: CrudOperation?,
    binder: Binder<SaidaVo>,
    readOnly: Boolean
                         ) {
    if (operation == ADD) {
      binder.bean.lojaNF = lojaDefault
      binder.bean.usuario = usuario
    }
    formLayout.apply {
      w = (UI.getCurrent().page.browserWindowWidth * 0.8).toInt().px

      grupo("Nota fiscal de saída") {
        verticalLayout {
          row {
            notaFiscalField(operation, binder)
            lojaField(operation, binder)
            comboBox<TipoNota>("Tipo") {
              expand = 2
              default { it.descricao }
              isReadOnly = true
              setItems(TipoNota.valuesSaida())
              bind(binder).bind(SaidaVo::tipoNota)
            }
            dateField("Data") {
              expand = 1
              isReadOnly = true
              bind(binder).bind(SaidaVo::dataNota.name)
            }
            textField("Rota") {
              expand = 1
              isReadOnly = true
              bind(binder).bind(SaidaVo::rota)
            }
          }
          row {
            textField("Observação da nota fiscal") {
              expand = 1
              bind(binder).bind(SaidaVo::observacaoNota)
            }
          }
        }
      }

      grupo("Produto") {
        produtoField(operation, binder, "Saída")
      }
    }
    if (!isAdmin && operation == UPDATE)
      binder.setReadOnly(true)
  }

  override val viewModel: SaidaViewModel = SaidaViewModel(this)

  init {
    form("Saída de produtos") {
      gridCrud(viewModel.crudClass.java) {
        addCustomToolBarComponent(btnImprimeTudo(this))
        addCustomToolBarComponent(btnLerChaveNota(this))
        addOnly = !isAdmin
        column(SaidaVo::numeroNF) {
          caption = "Número NF"
          setSortProperty("nota.numero")
        }
        grid.addComponentColumn { item ->
          val button = Button()
          print {
            item.itemNota?.recalculaSaldos()
            val print = viewModel.imprimir(item.itemNota)
            print
          }.extend(button)
          val impresso = item?.entityVo?.impresso ?: true
          button.isEnabled = impresso == false || isAdmin
          button.icon = VaadinIcons.PRINT
          button.addClickListener {
            val print = item?.entityVo?.impresso ?: true
            it.button.isEnabled = print == false || isAdmin
            refreshGrid()
          }
          button
        }.id = "btnPrint"
        column(SaidaVo::lojaNF) {
          caption = "Loja NF"
          setRenderer({ loja -> loja?.sigla ?: "" }, TextRenderer())
        }
        column(SaidaVo::tipoNotaDescricao) {
          caption = "TipoNota"
          setSortProperty("nota.tipo_nota")
        }
        column(SaidaVo::dataNota) {
          caption = "Data"
          dateFormat()
          setSortProperty("nota.data", "data", "hora")
        }
        column(SaidaVo::dataEmissao) {
          caption = "Emissao"
          dateFormat()
          setSortProperty("nota.dataEmissao", "data", "hora")
        }
        column(SaidaVo::quantProduto) {
          caption = "Quantidade"
          intFormat()
        }
        column(SaidaVo::codigo) {
          caption = "Código"
          setSortProperty("produto.codigo")
        }
        column(SaidaVo::descricaoProduto) {
          caption = "Descrição"
        }
        column(SaidaVo::grade) {
          caption = "Grade"
          setSortProperty("produto.grade")
        }
        column(SaidaVo::localizacao) {
          caption = "Localização"
          setRenderer({ it?.sufixo }, TextRenderer())
        }
        column(SaidaVo::usuario) {
          caption = "Usuário"
          setRenderer({ it?.loginName ?: "" }, TextRenderer())
          setSortProperty("usuario.loginName")
        }
        column(SaidaVo::rotaDescricao) {
          caption = "Rota"
        }
        column(SaidaVo::cliente) {
          caption = "Cliente"
          setSortProperty("nota.cliente")
        }
      }
    }
  }

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

  private fun btnLerChaveNota(gridCrudFlex: GridCrudFlex<SaidaVo>): Button {
    return button("Ler Nota") {
      icon = VaadinIcons.BARCODE
      addClickListener {
        readString("Chave da nota fiscal") { key ->
          viewModel.processaKey(key)
        }
      }
    }
  }
}

class ButtonOptionDefault : ButtonOption() {
  override fun apply(messageBox: MessageBox?, button: Button?) {
    button?.setClickShortcut(KeyCode.ENTER)
  }
}

