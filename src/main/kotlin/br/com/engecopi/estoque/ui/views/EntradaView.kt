package br.com.engecopi.estoque.ui.views

import br.com.engecopi.estoque.model.Loja
import br.com.engecopi.estoque.viewmodel.EntradaViewModel
import br.com.engecopi.estoque.viewmodel.EntradaVo
import br.com.engecopi.estoque.viewmodel.MovimentacaoVO
import br.com.engecopi.framework.ui.view.CrudLayoutView
import br.com.engecopi.framework.ui.view.bindItens
import br.com.engecopi.framework.ui.view.dateFormat
import br.com.engecopi.framework.ui.view.default
import br.com.engecopi.framework.ui.view.intFormat
import br.com.engecopi.framework.ui.view.reload
import br.com.engecopi.framework.ui.view.reloadBinderOnChange
import br.com.engecopi.framework.ui.view.row
import br.com.engecopi.saci.beans.ProdutoSaci
import com.github.vok.karibudsl.AutoView
import com.github.vok.karibudsl.VAlign
import com.github.vok.karibudsl.addColumnFor
import com.github.vok.karibudsl.align
import com.github.vok.karibudsl.bind
import com.github.vok.karibudsl.column
import com.github.vok.karibudsl.comboBox
import com.github.vok.karibudsl.cssLayout
import com.github.vok.karibudsl.dateField
import com.github.vok.karibudsl.expandRatio
import com.github.vok.karibudsl.grid
import com.github.vok.karibudsl.h
import com.github.vok.karibudsl.label
import com.github.vok.karibudsl.perc
import com.github.vok.karibudsl.px
import com.github.vok.karibudsl.textField
import com.github.vok.karibudsl.verticalLayout
import com.github.vok.karibudsl.w
import com.vaadin.data.Binder
import com.vaadin.data.converter.StringToIntegerConverter
import com.vaadin.data.provider.DataProvider
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.renderers.DateRenderer
import com.vaadin.ui.renderers.LocalDateRenderer
import com.vaadin.ui.renderers.NumberRenderer
import com.vaadin.ui.renderers.TextRenderer
import com.vaadin.ui.themes.ValoTheme
import org.vaadin.crudui.crud.CrudOperation
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import kotlin.reflect.KProperty

@AutoView("")
class EntradaView : CrudLayoutView<EntradaVo, EntradaViewModel>() {
  override fun fieldsRead(): List<KProperty<*>> {
    return listOf(EntradaVo::numeroNF, EntradaVo::lojaNF,
                  EntradaVo::dataNota, EntradaVo::fornecedor,
                  EntradaVo::codigo, EntradaVo::descricaoProduto, EntradaVo::grade,
                  EntradaVo::quantProduto)
  }
  
  override fun layoutForm(
          formLayout: VerticalLayout,
          operation: CrudOperation?,
          binder: Binder<EntradaVo>,
          readOnly: Boolean
                         ) {
    formLayout.apply {
      cssLayout("Nota fiscal de entrada") {
        w = 100.perc
        addStyleName(ValoTheme.LAYOUT_CARD)
        verticalLayout {
          row {
            textField("Nota Fiscal") {
              expandRatio = 1f
              bind(binder).bind(EntradaVo::numeroNF)
              reloadBinderOnChange(binder)
            }
            comboBox<Loja>("Loja") {
              expandRatio = 1f
              default { it.sigla }
              setItems(viewModel.findLojas())
              bind(binder).bind(EntradaVo::lojaNF)
              reloadBinderOnChange(binder)
            }
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
            textField("Número Interno") {
              expandRatio = 1f
              addStyleName(ValoTheme.TEXTFIELD_ALIGN_RIGHT)
              isReadOnly = true
              this.bind(binder)
                      .withConverter(StringToIntegerConverter(""))
                      .bind(EntradaVo::numeroInterno.name)
            }
            textField("Fornecedor") {
              expandRatio = 2f
              isReadOnly = true
              bind(binder).bind(EntradaVo::fornecedor.name)
            }
          }
        }
      }
      cssLayout("Produto") {
        w = 100.perc
        addStyleName(ValoTheme.LAYOUT_CARD)
        verticalLayout {
          row {
            comboBox<ProdutoSaci>("Código") {
              expandRatio = 2f
              default { "${it.codigo} ${it.grade}".trim() }
              bindItens(binder) { entrada -> viewModel.findProdutoNota(entrada) }
              bind(binder).bind(EntradaVo::produtoSaci)
              reloadBinderOnChange(binder)
            }
            textField("Descrição") {
              expandRatio = 4f
              isReadOnly = true
              bind(binder).bind(EntradaVo::descricaoProduto.name)
            }
            textField("Quantidade") {
              expandRatio = 1f
              this.bind(binder)
                      .withConverter(StringToIntegerConverter("Quantidade inválida"))
                      .bind(EntradaVo::quantProduto.name)
            }
          }
          row {
            textField("Tamanho") {
              expandRatio = 1f
              this.bind(binder)
                      .withConverter(StringToIntegerConverter("Tamanho inválido"))
                      .bind(EntradaVo::tamanho)
              reloadBinderOnChange(binder)
            }
            textField("Sequencia") {
              expandRatio = 1f
              isReadOnly = true
              bind(binder).bind(EntradaVo::sequencia.name)
            }
            textField("Saldo") {
              expandRatio = 1f
              isReadOnly = true
              this.bind(binder)
                      .withConverter(StringToIntegerConverter(""))
                      .bind(EntradaVo::saldo.name)
            }
            label {
              expandRatio = 3f
            }
          }
        }
      }
      row {
        grid(MovimentacaoVO::class) {
          h = 150.px
          caption = "Lotes"
          addStyleName(ValoTheme.TABLE_COMPACT)
          bindItens(binder) { entrada ->
            entrada.movimentacao
          }
          removeAllColumns()
          addColumnFor(MovimentacaoVO::descLote)
          addColumnFor(MovimentacaoVO::quantidade)
        }
      }
    }
  }
  
  init {
    form("Entrada de produtos") {
      gridCrud(viewModel.crudClass.java) {
        column(EntradaVo::numeroNF) {
          caption = "Número NF"
          //expandRatio = 1
        }
        column(EntradaVo::lojaNF) {
          caption = "Loja NF"
          setRenderer({ loja -> loja?.sigla ?: "Todas" }, TextRenderer())
        }
        column(EntradaVo::dataNota){
          caption = "Data Nota"
          dateFormat()
        }
        column(EntradaVo::fornecedor){
          caption = "Fornecedor"
        }
        column(EntradaVo::codigo){
          caption = "Código"
        }
        column(EntradaVo::descricaoProduto){
          caption = "Descrição"
        }
        column(EntradaVo::grade){
          caption = "Grade"
        }
        column(EntradaVo::quantProduto){
          caption = "Quantidade"
          intFormat()
        }
      }
    }
  }
  
  override val viewModel: EntradaViewModel
    get() = EntradaViewModel(this)
}


