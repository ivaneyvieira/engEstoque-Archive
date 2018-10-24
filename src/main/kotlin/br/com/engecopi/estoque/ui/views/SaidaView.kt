package br.com.engecopi.estoque.ui.views

import br.com.engecopi.estoque.model.TipoNota
import br.com.engecopi.estoque.viewmodel.SaidaViewModel
import br.com.engecopi.estoque.viewmodel.SaidaVo
import br.com.engecopi.framework.ui.view.dateFormat
import br.com.engecopi.framework.ui.view.default
import br.com.engecopi.framework.ui.view.expand
import br.com.engecopi.framework.ui.view.grupo
import br.com.engecopi.framework.ui.view.intFormat
import br.com.engecopi.framework.ui.view.row
import com.github.vok.karibudsl.AutoView
import com.github.vok.karibudsl.bind
import com.github.vok.karibudsl.comboBox
import com.github.vok.karibudsl.dateField
import com.github.vok.karibudsl.expandRatio
import com.github.vok.karibudsl.px
import com.github.vok.karibudsl.textField
import com.github.vok.karibudsl.verticalLayout
import com.github.vok.karibudsl.w
import com.vaadin.data.Binder
import com.vaadin.icons.VaadinIcons
import com.vaadin.ui.Button
import com.vaadin.ui.UI
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.renderers.TextRenderer
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
        produtoField( operation, binder, "Saída")
      }
    }
    if (!isAdmin && operation == UPDATE)
      binder.setReadOnly(true)
  }

  init {
    form("Expedição") {
      gridCrud(viewModel.crudClass.java) {
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
        }
        column(SaidaVo::lojaNF) {
          caption = "Loja NF"
          setRenderer({ loja -> loja?.sigla ?: "" }, TextRenderer())
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
        column(SaidaVo::rota) {
          caption = "Rota"
        }
        column(SaidaVo::cliente) {
          caption = "Cliente"
          setSortProperty("nota.cliente")
        }
      }
    }
  }

  override val viewModel
    get() = SaidaViewModel(this)
}

