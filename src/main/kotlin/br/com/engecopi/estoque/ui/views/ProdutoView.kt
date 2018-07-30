package br.com.engecopi.estoque.ui.views

import br.com.engecopi.estoque.model.ItemNota
import br.com.engecopi.estoque.ui.EstoqueUI
import br.com.engecopi.estoque.viewmodel.ProdutoViewModel
import br.com.engecopi.estoque.viewmodel.ProdutoVo
import br.com.engecopi.framework.ui.view.CrudLayoutView
import br.com.engecopi.framework.ui.view.bindItens
import br.com.engecopi.framework.ui.view.default
import br.com.engecopi.framework.ui.view.grupo
import br.com.engecopi.framework.ui.view.reloadBinderOnChange
import br.com.engecopi.framework.ui.view.row
import com.github.vok.karibudsl.AutoView
import com.github.vok.karibudsl.VAlign
import com.github.vok.karibudsl.addColumnFor
import com.github.vok.karibudsl.align
import com.github.vok.karibudsl.bind
import com.github.vok.karibudsl.comboBox
import com.github.vok.karibudsl.expandRatio
import com.github.vok.karibudsl.grid
import com.github.vok.karibudsl.h
import com.github.vok.karibudsl.px
import com.github.vok.karibudsl.textField
import com.github.vok.karibudsl.verticalLayout
import com.github.vok.karibudsl.w
import com.vaadin.data.Binder
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.renderers.LocalDateRenderer
import com.vaadin.ui.renderers.NumberRenderer
import com.vaadin.ui.renderers.TextRenderer
import org.vaadin.crudui.crud.CrudOperation
import org.vaadin.crudui.crud.CrudOperation.UPDATE
import java.text.DecimalFormat

@AutoView
class ProdutoView : CrudLayoutView<ProdutoVo, ProdutoViewModel>() {
  val lojaDefault
    get() = EstoqueUI.loja
  val isAdmin
    get() = EstoqueUI.user?.isAdmin ?: false
  override fun layoutForm(
          formLayout: VerticalLayout,
          operation: CrudOperation?,
          binder: Binder<ProdutoVo>,
          readOnly: Boolean
                         ) {
    binder.bean.lojaDefault = lojaDefault
    formLayout.apply {
      w = 800.px
      h = 300.px
      grupo("Produtos") {
        verticalLayout {
          row {
            textField {
              expandRatio = 1f
              caption = "Código"
              bind(binder).bind(ProdutoVo::codigoProduto)
              reloadBinderOnChange(binder)
            }
            textField("Descrição") {
              expandRatio = 3f
              isReadOnly = true
              bind(binder).bind(ProdutoVo::descricaoProduto.name)
            }
            comboBox<String> {
              expandRatio = 1f
              caption = "Grade"
              default { it }
              bind(binder).bind(ProdutoVo::gradeProduto)
              bindItens(binder, ProdutoVo::grades)
              reloadBinderOnChange(binder)
            }
          }
          row {
            grid(ItemNota::class, "Notas") {
              expandRatio = 2f
              removeAllColumns()
              addColumnFor(ItemNota::numeroNota) {
                this.isSortable = false
                caption = "Nota"
              }
              addColumnFor(ItemNota::dataNota) {
                this.isSortable = false
                caption = "Data"
                setRenderer(LocalDateRenderer("dd/MM/yy"))
              }
              addColumnFor(ItemNota::tipoNota) {
                this.isSortable = false
                caption = "Tipo"
                setRenderer({ it?.descricao ?: "" }, TextRenderer())
              }
              addColumnFor(ItemNota::rota) {
                this.isSortable = false
                caption = "Rota"
              }
              addColumnFor(ItemNota::quantidadeSaldo) {
                this.isSortable = false
                caption = "Quantidade"
                setRenderer(NumberRenderer(DecimalFormat("0")))
                align = VAlign.Right
              }
              addColumnFor(ItemNota::saldoTransient) {
                this.isSortable = false
                caption = "Saldo"
                setRenderer(NumberRenderer(DecimalFormat("0")))
                align = VAlign.Right
              }
              bindItens(binder, ProdutoVo::itensNota)
            }
          }
        }
      }
    }
    if(!isAdmin && operation == UPDATE)
      binder.setReadOnly(true)
  }
  
  init {
    form("Entrada de produtos") {
      gridCrud(viewModel.crudClass.java) {
        queryOnly = !isAdmin
        column(ProdutoVo::codigoProduto) {
          expandRatio = 1
          caption = "Código"
        }
        column(ProdutoVo::descricaoProduto) {
          expandRatio = 5
          caption = "Descrição"
        }
        column(ProdutoVo::gradeProduto) {
          expandRatio = 1
          caption = "Grade"
        }
        column(ProdutoVo::localizacao) {
          expandRatio = 1
          caption = "Local"
        }
      }
    }
  }
  
  override val viewModel: ProdutoViewModel
    get() = ProdutoViewModel(this, EstoqueUI.user)
}


