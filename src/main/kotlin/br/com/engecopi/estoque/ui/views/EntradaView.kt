package br.com.engecopi.estoque.ui.views

import br.com.engecopi.estoque.model.Loja
import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.estoque.viewmodel.EntradaViewModel
import br.com.engecopi.estoque.viewmodel.EntradaVo
import br.com.engecopi.estoque.viewmodel.MovimentacaoVO
import br.com.engecopi.framework.ui.view.CrudLayoutView
import br.com.engecopi.framework.ui.view.default
import br.com.engecopi.framework.ui.view.row
import com.github.vok.karibudsl.AutoView
import com.github.vok.karibudsl.bind
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
import com.vaadin.data.Binder.BindingBuilder
import com.vaadin.data.converter.StringToIntegerConverter
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.renderers.TextRenderer
import com.vaadin.ui.themes.ValoTheme
import org.vaadin.crudui.crud.CrudOperation
import java.time.LocalDate
import kotlin.reflect.KProperty

@AutoView("")
class EntradaView : CrudLayoutView<EntradaVo, EntradaViewModel>() {
  override fun fieldsRead(): List<KProperty<*>> {
    return listOf(EntradaVo::numeroNF, EntradaVo::lojaNF,
                  EntradaVo::dataNota, EntradaVo::fornecedor)
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
            }
            comboBox<Loja>("Loja") {
              expandRatio = 1f
              default({viewModel.findLojas()}, {it.sigla})
              bind(binder).bind(EntradaVo::lojaNF)
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
              bind(binder).bind(EntradaVo::dataNota)
            }
            textField("Número Interno") {
              expandRatio = 1f
              addStyleName(ValoTheme.TEXTFIELD_ALIGN_RIGHT)
              isReadOnly = true
              this.bind(binder)
                      .withConverter(StringToIntegerConverter(""))
                      .bind(EntradaVo::numeroInterno)
            }
            textField("Fornecedor") {
              expandRatio = 2f
              isReadOnly = true
              bind(binder).bind(EntradaVo::fornecedor)
            }
          }
        }
      }
      cssLayout("Produto") {
        w = 100.perc
        addStyleName(ValoTheme.LAYOUT_CARD)
        verticalLayout {
          row {
            comboBox<Produto>("Código") {
              expandRatio = 2f
              default({viewModel.findProdutoNota(binder.bean)}, {"${it.codigo} ${it.grade}".trim()})
              bind(binder).bind(EntradaVo::produto)
            }
            textField("Descrição") {
              expandRatio = 4f
              isReadOnly = true
              bind(binder).bind(EntradaVo::descricaoProduto)
            }
            textField("Quantidade") {
              expandRatio = 1f
              this.bind(binder)
                      .withConverter(StringToIntegerConverter("Quantidade inválida"))
                      .bind(EntradaVo::quantProduto)
            }
          }
          row {
            textField("Tamanho") {
              expandRatio = 1f
              this.bind(binder)
                      .withConverter(StringToIntegerConverter("Tamanho inválido"))
                      .bind(EntradaVo::tamanho)
            }
            textField("Sequencia") {
              expandRatio = 1f
              isReadOnly = true
              bind(binder).bind(EntradaVo::sequencia)
            }
            textField("Saldo") {
              expandRatio = 1f
              isReadOnly = true
              this.bind(binder)
                      .withConverter(StringToIntegerConverter(""))
                      .bind(EntradaVo::saldo)
            }
            label {
              expandRatio = 3f
            }
          }
        }
      }
      row {
        grid<MovimentacaoVO>() {
          h = 150.px
          caption = "Lotes"
        }
      }
    }
  }
  
  init {
    form("Entrada de produtos") {
      gridCrud(viewModel.crudClass.java) {
        column(EntradaVo::numeroNF) {
          expandRatio = 1
        }
        column(EntradaVo::lojaNF) {
          setRenderer({ loja -> loja?.sigla ?: "Todas" }, TextRenderer())
        }
      }
    }
  }
  
  override val viewModel: EntradaViewModel
    get() = EntradaViewModel(this)
}


