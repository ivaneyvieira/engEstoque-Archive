package br.com.engecopi.estoque.ui.views

import br.com.engecopi.estoque.model.TipoProduto
import br.com.engecopi.estoque.viewmodel.ProdutoViewModel
import br.com.engecopi.estoque.viewmodel.ProdutoVo
import br.com.engecopi.framework.ui.view.CrudLayoutView
import br.com.engecopi.framework.ui.view.bindItens
import br.com.engecopi.framework.ui.view.default
import br.com.engecopi.framework.ui.view.reloadBinderOnChange
import br.com.engecopi.framework.ui.view.row
import com.github.vok.karibudsl.AutoView
import com.github.vok.karibudsl.bind
import com.github.vok.karibudsl.comboBox
import com.github.vok.karibudsl.cssLayout
import com.github.vok.karibudsl.expandRatio
import com.github.vok.karibudsl.perc
import com.github.vok.karibudsl.textField
import com.github.vok.karibudsl.verticalLayout
import com.github.vok.karibudsl.w
import com.vaadin.data.Binder
import com.vaadin.data.converter.StringToIntegerConverter
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.themes.ValoTheme
import org.vaadin.crudui.crud.CrudOperation
import kotlin.reflect.KProperty

@AutoView
class ProdutoView : CrudLayoutView<ProdutoVo, ProdutoViewModel>() {
  /*
  override fun fieldsRead(): List<KProperty<*>> {
    return listOf(ProdutoVo::codigoProduto, ProdutoVo::descricaoProduto, ProdutoVo::gradeProduto)
  }
  */
  override fun layoutForm(
          formLayout: VerticalLayout,
          operation: CrudOperation?,
          binder: Binder<ProdutoVo>,
          readOnly: Boolean
                         ) {
    formLayout.apply {
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
              default{it}
              bindItens(binder) { produto -> produto.grades }
              
              bind(binder).bind(ProdutoVo::gradeProduto)
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
            }
            
            textField {
              expandRatio = 1f
              caption = "Tamanho do Lote"
              addStyleName(ValoTheme.TEXTFIELD_ALIGN_RIGHT)
              bind(binder)
                      .withConverter(StringToIntegerConverter("Valor inválido"))
                      .bind(ProdutoVo::tamanhoLote)
              
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
          caption = "Código"
        }
        column(ProdutoVo::descricaoProduto) {
          caption = "Descrição"
        }
        
        column(ProdutoVo::gradeProduto) {
          caption = "Grade"
        }
      }
    }
  }
  
  override val viewModel: ProdutoViewModel
    get() = ProdutoViewModel(this)
}


