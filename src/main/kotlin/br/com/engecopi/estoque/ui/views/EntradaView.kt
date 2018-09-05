package br.com.engecopi.estoque.ui.views

import br.com.engecopi.estoque.model.Loja
import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.estoque.model.TipoNota
import br.com.engecopi.estoque.model.TipoNota.OUTROS_E
import br.com.engecopi.estoque.ui.EstoqueUI
import br.com.engecopi.estoque.viewmodel.EntradaViewModel
import br.com.engecopi.estoque.viewmodel.EntradaVo
import br.com.engecopi.estoque.viewmodel.ProdutoVOEntrada
import br.com.engecopi.estoque.viewmodel.ProdutoVo
import br.com.engecopi.framework.ui.view.CrudLayoutView
import br.com.engecopi.framework.ui.view.bindItens
import br.com.engecopi.framework.ui.view.bindVisible
import br.com.engecopi.framework.ui.view.dateFormat
import br.com.engecopi.framework.ui.view.default
import br.com.engecopi.framework.ui.view.grupo
import br.com.engecopi.framework.ui.view.intFormat
import br.com.engecopi.framework.ui.view.integerField
import br.com.engecopi.framework.ui.view.reloadBinderOnChange
import br.com.engecopi.framework.ui.view.row
import com.github.vok.karibudsl.AutoView
import com.github.vok.karibudsl.addColumnFor
import com.github.vok.karibudsl.bind
import com.github.vok.karibudsl.column
import com.github.vok.karibudsl.comboBox
import com.github.vok.karibudsl.dateField
import com.github.vok.karibudsl.expandRatio
import com.github.vok.karibudsl.getAll
import com.github.vok.karibudsl.grid
import com.github.vok.karibudsl.h
import com.github.vok.karibudsl.isMultiSelect
import com.github.vok.karibudsl.px
import com.github.vok.karibudsl.refresh
import com.github.vok.karibudsl.textField
import com.vaadin.data.Binder
import com.vaadin.icons.VaadinIcons
import com.vaadin.ui.Button
import com.vaadin.ui.Grid.SelectionMode.MULTI
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.renderers.TextRenderer
import org.vaadin.crudui.crud.CrudOperation
import org.vaadin.crudui.crud.CrudOperation.ADD
import org.vaadin.crudui.crud.CrudOperation.UPDATE

@AutoView("")
class EntradaView : CrudLayoutView<EntradaVo, EntradaViewModel>() {
  val lojaDefault
    get() = EstoqueUI.loja
  val usuario = EstoqueUI.user!!
  val isAdmin = usuario.admin

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
          textField("Nota Fiscal") {
            expandRatio = 2f
            isReadOnly = operation != ADD
            bind(binder).bind(EntradaVo::numeroNF)
            reloadBinderOnChange(binder)
          }
          comboBox<Loja>("Loja") {
            expandRatio = 2f
            isReadOnly = operation != ADD
            default { it.sigla }

            setItems(viewModel.findLojas(lojaDefault))

            bind(binder).bind(EntradaVo::lojaNF)
            reloadBinderOnChange(binder)
          }
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
        row {
          this.bindVisible(binder, EntradaVo::naoTemGrid)
          comboBox<Produto>("Código") {
            expandRatio = 2f
            isReadOnly = operation != ADD
            default { "${it.codigo.trim()} ${it.grade}".trim() }
            isTextInputAllowed = true
            bindItens(binder, EntradaVo::produtoNota)
            bind(binder).bind(EntradaVo::produto)
            reloadBinderOnChange(binder)
          }
          textField("Descrição") {
            expandRatio = 5f
            isReadOnly = true
            bind(binder).bind(EntradaVo::descricaoProduto.name)
          }
          textField("Grade") {
            expandRatio = 1f
            isReadOnly = true
            bind(binder).bind(EntradaVo::grade.name)
          }
          integerField("Qtd Entrada") {
            expandRatio = 1f
            isReadOnly = (isAdmin == false) && (operation != ADD)
            this.bind(binder).bind(EntradaVo::quantProduto.name)
          }
        }
        row {
          this.bindVisible(binder, EntradaVo::temGrid)

          grid(ProdutoVOEntrada::class) {
            expandRatio = 2f
            this.h = 200.px
            removeAllColumns()
            val selectionModel = setSelectionMode(MULTI)
            selectionModel.addSelectionListener { select ->
              if (select.isUserOriginated) {
                this.dataProvider.getAll().forEach {
                  it.selecionado = false
                }
                select.allSelectedItems.forEach {
                  it.selecionado = true
                }
              }
            }
            addColumnFor(ProdutoVOEntrada::codigo) {
              expandRatio = 1
              caption = "Código"
            }
            addColumnFor(ProdutoVOEntrada::descricaoProduto) {
              expandRatio = 5
              caption = "Descrição"
            }
            addColumnFor(ProdutoVOEntrada::grade) {
              expandRatio = 1
              caption = "Grade"
            }
            addColumnFor(ProdutoVOEntrada::quantidade) {
              expandRatio = 1
              caption = "Qtd Entrada"
            }
            bindItens(binder, EntradaVo::produtos)
          }
        }
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


