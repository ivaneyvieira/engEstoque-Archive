package br.com.engecopi.estoque.ui.views

import br.com.engecopi.estoque.model.ItemNota
import br.com.engecopi.estoque.model.Nota
import br.com.engecopi.estoque.model.StatusNota.CONFERIDA
import br.com.engecopi.estoque.model.TipoNota
import br.com.engecopi.estoque.viewmodel.EntregaClienteViewModel
import br.com.engecopi.estoque.viewmodel.EntregaClienteVo
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
import com.vaadin.icons.VaadinIcons
import com.vaadin.ui.Button
import com.vaadin.ui.TextField
import com.vaadin.ui.UI
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.renderers.TextRenderer
import de.steinwedel.messagebox.ButtonOption
import org.vaadin.crudui.crud.CrudOperation
import org.vaadin.crudui.crud.CrudOperation.ADD
import org.vaadin.crudui.crud.CrudOperation.UPDATE

@AutoView("entrega_cliente")
class EntregaClienteView : NotaView<EntregaClienteVo, EntregaClienteViewModel>() {
  override fun layoutForm(
    formLayout: VerticalLayout,
    operation: CrudOperation?,
    binder: Binder<EntregaClienteVo>,
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
              bind(binder).bind(EntregaClienteVo::tipoNota)
            }
            dateField("Data") {
              expand = 1
              isReadOnly = true
              bind(binder).bind(EntregaClienteVo::dataNota.name)
            }
            textField("Rota") {
              expand = 1
              isReadOnly = true
              bind(binder).bind(EntregaClienteVo::rota)
            }
          }
          row {
            textField("Observação da nota fiscal") {
              expand = 1
              bind(binder).bind(EntregaClienteVo::observacaoNota)
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

  override val viewModel: EntregaClienteViewModel = EntregaClienteViewModel(this)

  init {
    form("Entrega ao Cliente") {
      gridCrud(viewModel.crudClass.java) {
       /* addCustomToolBarComponent(btnImprimeTudo(this))*/
        addCustomToolBarComponent(btnLerChaveNota())
        reloadOnly = !isAdmin
        column(EntregaClienteVo::numeroNF) {
          caption = "Número NF"
          setSortProperty("nota.numero")
        }
       /* grid.addComponentColumn { item ->
          val button = Button()
          print {
            item.itemNota?.recalculaSaldos()
            return@print viewModel.imprimir(item.itemNota)
          }.extend(button)
          val impresso = item?.entityVo?.impresso ?: true
          button.isEnabled = impresso == false || isAdmin
          button.icon = VaadinIcons.PRINT
          button.addClickListener {
            val print = item?.entityVo?.impresso ?: true
            it.button.isEnabled = print == false || isAdmin
            refreshGrid()
          }
          return@addComponentColumn button
        }.id = "btnPrint"*/
        column(EntregaClienteVo::lojaNF) {
          caption = "Loja NF"
          setRenderer({ loja -> loja?.sigla ?: "" }, TextRenderer())
        }
        column(EntregaClienteVo::tipoNotaDescricao) {
          caption = "TipoNota"
          setSortProperty("nota.tipo_nota")
        }
        column(EntregaClienteVo::lancamento) {
          caption = "Lançamento"
          dateFormat()
          setSortProperty("nota.lancamento", "data", "hora")
        }
        column(EntregaClienteVo::dataEmissao) {
          caption = "Emissao"
          dateFormat()
          setSortProperty("nota.dataEmissao", "data", "hora")
        }
        column(EntregaClienteVo::quantProduto) {
          caption = "Quantidade"
          intFormat()
        }
        column(EntregaClienteVo::codigo) {
          caption = "Código"
          setSortProperty("produto.codigo")
        }
        column(EntregaClienteVo::descricaoProduto) {
          caption = "Descrição"
        }
        column(EntregaClienteVo::grade) {
          caption = "Grade"
          setSortProperty("produto.grade")
        }
        column(EntregaClienteVo::localizacao) {
          caption = "Localização"
          setRenderer({ it?.toString() }, TextRenderer())
        }
        column(EntregaClienteVo::usuario) {
          caption = "Usuário"
          setRenderer({ it?.loginName ?: "" }, TextRenderer())
          setSortProperty("usuario.loginName")
        }
        column(EntregaClienteVo::rotaDescricao) {
          caption = "Rota"
        }
        column(EntregaClienteVo::cliente) {
          caption = "Cliente"
          setSortProperty("nota.cliente")
        }
        val itens = viewModel.notasConferidas()
          .groupBy { it.numeroNF }
          .entries
          .sortedBy { entry ->
            entry.value
              .map { it.entityVo?.id ?: 0 }
              .max()
          }.mapNotNull { it.key }

        grid.setStyleGenerator { saida ->
          if (saida.status == CONFERIDA) {
            val numero = saida.numeroNF
            val index = itens.indexOf(numero)
            if (index % 2 == 0)
              "pendente"
            else
              "pendente2"
          } else null
        }
      }
    }
  }

  fun readString(msg: String, processaleitura: (Nota?, String) -> ItemNota?) {
    if (msg.isNotBlank()) {
      val textField = TextField(msg).apply {
        this.w = 400.px
      }

      dlgCodigoBarras(textField, processaleitura)
    }
  }

  private fun buttonDefault(): ButtonOption {
    return ButtonOptionDefault()
  }

  private fun btnLerChaveNota(): Button {
    return button("Ler Nota") {
      icon = VaadinIcons.BARCODE
      addClickListener {
        readString("Chave da nota fiscal") { nota, key ->
          viewModel.processaKey(nota, key)
        }
      }
    }
  }
}