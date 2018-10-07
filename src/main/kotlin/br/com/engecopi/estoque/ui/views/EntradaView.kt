package br.com.engecopi.estoque.ui.views

import br.com.engecopi.estoque.model.TipoNota
import br.com.engecopi.estoque.viewmodel.EntradaViewModel
import br.com.engecopi.estoque.viewmodel.EntradaVo
import br.com.engecopi.framework.ui.view.dateFormat
import br.com.engecopi.framework.ui.view.default
import br.com.engecopi.framework.ui.view.grupo
import br.com.engecopi.framework.ui.view.intFormat
import br.com.engecopi.framework.ui.view.integerField
import br.com.engecopi.framework.ui.view.row
import com.github.vok.karibudsl.AutoView
import com.github.vok.karibudsl.bind
import com.github.vok.karibudsl.comboBox
import com.github.vok.karibudsl.dateField
import com.github.vok.karibudsl.expandRatio
import com.github.vok.karibudsl.textField
import com.vaadin.data.Binder
import com.vaadin.icons.VaadinIcons
import com.vaadin.ui.Button
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.renderers.TextRenderer
import org.vaadin.crudui.crud.CrudOperation
import org.vaadin.crudui.crud.CrudOperation.ADD
import org.vaadin.crudui.crud.CrudOperation.UPDATE

@AutoView("")
class EntradaView : NotaView<EntradaVo, EntradaViewModel>() {
  override fun layoutForm(
    formLayout: VerticalLayout, operation: CrudOperation?, binder: Binder<EntradaVo>, readOnly: Boolean
  ) {
    if (operation == ADD) {
      binder.bean.lojaNF = lojaDefault
      binder.bean.usuario = usuario
    }
    formLayout.apply {
      grupo("Nota fiscal de entrada") {
        row {
          notaFiscalField(operation, binder)
          lojaField(operation, binder)
          comboBox<TipoNota>("Tipo") {
            expandRatio = 2f
            default { it.descricao }
            isReadOnly = true
            setItems(TipoNota.valuesEntrada())
            bind(binder).bind(EntradaVo::tipoNota)
          }
          textField("Rota") {
            expandRatio = 1f
            isReadOnly = true
            bind(binder).bind(EntradaVo::rota)
          }
        }
        row {
          textField("Observação") {
            expandRatio = 2f
            bind(binder).bind(EntradaVo::observacaoNota)
          }
        }
        row {
          dateField("Data") {
            expandRatio = 1f
            isReadOnly = true
            bind(binder).bind(EntradaVo::dataNota.name)
          }
          integerField("Número Interno") {
            expandRatio = 1f
            isReadOnly = true
            this.bind(binder).bind(EntradaVo::numeroInterno.name)
          }
          textField("Fornecedor") {
            expandRatio = 2f
            isReadOnly = true
            bind(binder).bind(EntradaVo::fornecedor.name)
          }
        }
      }

      grupo("Produto") {
        produtoField( operation, binder, "Entrada")
      }
    }
    if (!isAdmin && operation == UPDATE) binder.setReadOnly(true)
  }


  init {
    form("Entrada de produtos") {
      gridCrud(viewModel.crudClass.java) {
        addOnly = !isAdmin
        column(EntradaVo::numeroNF) {
          //isSortable = true
          caption = "Número NF"
          setSortProperty("nota.numero")
        }
        grid.addComponentColumn { item ->
          val button = Button()

          print {
            item.itemNota?.produto?.recalculaSaldos()
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
        column(EntradaVo::lojaNF) {
          caption = "Loja NF"
          setRenderer({ loja -> loja?.sigla ?: "" }, TextRenderer())
        }
        column(EntradaVo::dataNota) {
          caption = "Data Nota"
          dateFormat()

          setSortProperty("nota.data", "data", "hora")
        }
        column(EntradaVo::dataEmissao) {
          caption = "Emissao"
          dateFormat()
          setSortProperty("nota.dataEmissao", "data", "hora")
        }
        column(EntradaVo::quantProduto) {
          caption = "Quantidade"
          intFormat()
        }
        column(EntradaVo::codigo) {
          caption = "Código"
          setSortProperty("produto.codigo")
        }
        column(EntradaVo::descricaoProduto) {
          caption = "Descrição"
        }
        column(EntradaVo::grade) {
          caption = "Grade"
          setSortProperty("produto.grade")
        }
        column(EntradaVo::localizacao) {
          caption = "Local"
        }
        column(EntradaVo::usuario) {
          caption = "Usuário"
          setRenderer({ it?.loginName ?: "" }, TextRenderer())
          setSortProperty("usuario.loginName")
        }
        column(EntradaVo::rota) {
          caption = "Rota"
        }
        column(EntradaVo::fornecedor) {
          caption = "Fornecedor"
          setSortProperty("nota.fornecedor")
        }
      }
    }
  }

  override val viewModel: EntradaViewModel
    get() = EntradaViewModel(this, usuario)
}


