package br.com.engecopi.estoque.ui.views

import br.com.engecopi.estoque.ui.EstoqueUI
import br.com.engecopi.estoque.viewmodel.EtiquetaViewModel
import br.com.engecopi.estoque.viewmodel.EtiquetaVo
import br.com.engecopi.estoque.viewmodel.UsuarioCrudVo
import br.com.engecopi.framework.ui.view.CrudLayoutView
import br.com.engecopi.framework.ui.view.row
import com.github.vok.karibudsl.AutoView
import com.github.vok.karibudsl.bind
import com.github.vok.karibudsl.expandRatio
import com.github.vok.karibudsl.h
import com.github.vok.karibudsl.perc
import com.github.vok.karibudsl.px
import com.github.vok.karibudsl.textArea
import com.github.vok.karibudsl.textField
import com.github.vok.karibudsl.w
import com.vaadin.data.Binder
import com.vaadin.ui.VerticalLayout
import org.vaadin.crudui.crud.CrudOperation

@AutoView
class EtiquetaView : CrudLayoutView<EtiquetaVo, EtiquetaViewModel>() {
  override fun layoutForm(
          formLayout: VerticalLayout,
          operation: CrudOperation?,
          binder: Binder<EtiquetaVo>,
          readOnly: Boolean
                         ) {
    formLayout.apply {
      w = 600.px
      h = 600.px
      row {
        textField("Título") {
          expandRatio = 1f
          bind(binder).bind(EtiquetaVo::titulo)
        }
      }
      row{
        textArea("Template") {
          h = 400.px
          expandRatio = 1f
          bind(binder).bind(EtiquetaVo::template)
        }
      }
    }
  }
  
  init {
    form("Etiquetas") {
      gridCrud(viewModel.crudClass.java) {
        setDeleteOperationVisible(EstoqueUI.user?.isAdmin ?: false)
        column(EtiquetaVo::titulo) {
          expandRatio = 1
          caption = "Título"
        }
      }
    }
  }
  
  override val viewModel: EtiquetaViewModel
    get() = EtiquetaViewModel(this)
}