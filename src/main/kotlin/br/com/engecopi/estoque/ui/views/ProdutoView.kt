package br.com.engecopi.estoque.ui.views

import br.com.engecopi.estoque.model.ItemNota
import br.com.engecopi.estoque.model.Lote
import br.com.engecopi.estoque.model.TipoProduto
import br.com.engecopi.estoque.viewmodel.ProdutoViewModel
import br.com.engecopi.estoque.viewmodel.ProdutoVo
import br.com.engecopi.framework.ui.view.CrudLayoutView
import br.com.engecopi.framework.ui.view.bindItens
import br.com.engecopi.framework.ui.view.bindReadOnly
import br.com.engecopi.framework.ui.view.default
import br.com.engecopi.framework.ui.view.intFormat
import br.com.engecopi.framework.ui.view.integerField
import br.com.engecopi.framework.ui.view.reloadBinderOnChange
import br.com.engecopi.framework.ui.view.row
import com.github.vok.karibudsl.AutoView
import com.github.vok.karibudsl.VAlign
import com.github.vok.karibudsl.addColumnFor
import com.github.vok.karibudsl.align
import com.github.vok.karibudsl.bind
import com.github.vok.karibudsl.comboBox
import com.github.vok.karibudsl.cssLayout
import com.github.vok.karibudsl.expandRatio
import com.github.vok.karibudsl.grid
import com.github.vok.karibudsl.h
import com.github.vok.karibudsl.perc
import com.github.vok.karibudsl.px
import com.github.vok.karibudsl.textField
import com.github.vok.karibudsl.verticalLayout
import com.github.vok.karibudsl.w
import com.vaadin.data.Binder
import com.vaadin.data.converter.StringToIntegerConverter
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.renderers.LocalDateRenderer
import com.vaadin.ui.renderers.NumberRenderer
import com.vaadin.ui.renderers.TextRenderer
import com.vaadin.ui.themes.ValoTheme
import org.vaadin.crudui.crud.CrudOperation
import java.text.DecimalFormat

@AutoView
class ProdutoView : CrudLayoutView<ProdutoVo, ProdutoViewModel>() {
  override fun layoutForm(
          formLayout: VerticalLayout,
          operation: CrudOperation?,
          binder: Binder<ProdutoVo>,
          readOnly: Boolean
                         ) {
    formLayout.apply {
      w = 800.px
      h = 300.px
      cssLayout("Produtos") {
        w = 100.perc
        addStyleName(ValoTheme.LAYOUT_CARD)
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
            comboBox<TipoProduto> {
              expandRatio = 1f
              caption = "Tipo"
              isEmptySelectionAllowed = false
              isTextInputAllowed = false
              setItems(TipoProduto.values().toList())
              setItemCaptionGenerator { it.descricao }
              
              bind(binder).bind(ProdutoVo::tipo)
              reloadBinderOnChange(binder)
            }
            
            integerField {
              expandRatio = 1f
              caption = "Tamanho do Lote"
              addStyleName(ValoTheme.TEXTFIELD_ALIGN_RIGHT)
              bindReadOnly(binder, ProdutoVo::tamanhoReadOnly)
              bind(binder)
                      .bind(ProdutoVo::tamanhoLote)
            }
          }
          row {
            grid(Lote::class, "Lotes") {
              expandRatio = 1f
              removeAllColumns()
              addColumnFor(Lote::sequenciaStr) {
                this.isSortable= false
                caption = "Sequencia"
              }
              addColumnFor(Lote::saldo) {
                this.isSortable= false
                caption = "Saldo"
                setRenderer(NumberRenderer(DecimalFormat("0")))
                align = VAlign.Right
              }
              bindItens(binder, ProdutoVo::lotes)
            }
            grid(ItemNota::class, "Notas") {
              expandRatio = 2f
              removeAllColumns()
              addColumnFor(ItemNota::numeroNota) {
                this.isSortable= false
                caption = "Nota"
              }
              addColumnFor(ItemNota::dataNota) {
                this.isSortable= false
                caption = "Data"
                setRenderer(LocalDateRenderer("dd/MM/yy"))
              }
              addColumnFor(ItemNota::tipoMov) {
                this.isSortable= false
                caption = "Tipo"
                setRenderer({ it.toString() }, TextRenderer())
              }
              addColumnFor(ItemNota::rota){
                this.isSortable= false
                caption = "Rota"
              }
              addColumnFor(ItemNota::quantidadeUnitaria) {
                this.isSortable= false
                caption = "Quant."
                setRenderer(NumberRenderer(DecimalFormat("0")))
                align = VAlign.Right
              }
              addColumnFor(ItemNota::saldoTransient) {
                this.isSortable= false
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
  }
  
  init {
    form("Entrada de produtos") {
      gridCrud(viewModel.crudClass.java) {
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
        column(ProdutoVo::tipo) {
          expandRatio = 1
          caption = "Tipo"
          setRenderer({ tipo -> tipo?.descricao }, TextRenderer())
        }
        column(ProdutoVo::tamanhoLote) {
          expandRatio = 1
          caption = "Tamanho do Lote"
          intFormat()
        }
      }
    }
  }
  
  override val viewModel: ProdutoViewModel
    get() = ProdutoViewModel(this)
}


