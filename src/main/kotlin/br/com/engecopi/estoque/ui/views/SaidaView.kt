package br.com.engecopi.estoque.ui.views

import br.com.engecopi.estoque.viewmodel.SaidaViewModel
import br.com.engecopi.estoque.viewmodel.SaidaVo
import br.com.engecopi.framework.ui.view.CrudLayoutView
import com.github.vok.karibudsl.AutoView
import com.vaadin.data.Binder
import com.vaadin.ui.VerticalLayout
import org.vaadin.crudui.crud.CrudOperation
import kotlin.reflect.KProperty

@AutoView
class SaidaView : CrudLayoutView<SaidaVo, SaidaViewModel>(){
  override fun fieldsRead(): List<KProperty<*>> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
  
  override fun layoutForm(
          formLayout: VerticalLayout, operation: CrudOperation?, binder: Binder<SaidaVo>, readOnly: Boolean
                         ) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
  
  override val viewModel = SaidaViewModel(this)
}

