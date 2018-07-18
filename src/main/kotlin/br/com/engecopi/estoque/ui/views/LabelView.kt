package br.com.engecopi.estoque.ui.views

import br.com.engecopi.estoque.model.TipoProduto
import br.com.engecopi.estoque.ui.EstoqueUI
import br.com.engecopi.estoque.viewmodel.LabelViewModel
import br.com.engecopi.estoque.viewmodel.LabelVo
import br.com.engecopi.framework.ui.view.CrudLayoutView
import br.com.engecopi.framework.ui.view.row
import com.github.vok.karibudsl.AutoView
import com.github.vok.karibudsl.bind
import com.github.vok.karibudsl.comboBox
import com.github.vok.karibudsl.expandRatio
import com.github.vok.karibudsl.h
import com.github.vok.karibudsl.label
import com.github.vok.karibudsl.px
import com.github.vok.karibudsl.textArea
import com.github.vok.karibudsl.w
import com.vaadin.data.Binder
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.renderers.TextRenderer
import org.vaadin.crudui.crud.CrudOperation

@AutoView
class LabelView : CrudLayoutView<LabelVo, LabelViewModel>() {
  override fun layoutForm(
          formLayout: VerticalLayout,
          operation: CrudOperation?,
          binder: Binder<LabelVo>,
          readOnly: Boolean
                         ) {
    formLayout.apply {
      w = 900.px
      val szLabel = 400
      row {
        comboBox<TipoProduto>("Tipo") {
          expandRatio = 1f
          isEmptySelectionAllowed = false
          isTextInputAllowed = false
          setItems(TipoProduto.values().toList())
          setItemCaptionGenerator { it.descricao }
          
          bind(binder).bind(LabelVo::tipoProduto)
        }
        label {
          expandRatio = 4f
        }
      }
      row {
        textArea("Layout Entrada") {
          h = szLabel.px
          expandRatio = 2f
          isWordWrap = false
          addStyleName("mono")
          bind(binder).bind(LabelVo::layoutEntrada)
        }
        textArea("Layout Saída") {
          h = szLabel.px
          expandRatio = 2f
          isWordWrap = false
          addStyleName("mono")
          bind(binder).bind(LabelVo::layoutSaida)
        }
      }
    }
  }
  
  init {
    form("Entrada de produtos") {
      gridCrud(viewModel.crudClass.java) {
        setDeleteOperationVisible(EstoqueUI.user?.isAdmin ?: false)
        column(LabelVo::tipoProduto) {
          caption = "Tipo de Produto"
          setRenderer({ it?.descricao ?: "" }, TextRenderer())
        }
      }
    }
  }
  
  override val viewModel: LabelViewModel
    get() = LabelViewModel(this)
}