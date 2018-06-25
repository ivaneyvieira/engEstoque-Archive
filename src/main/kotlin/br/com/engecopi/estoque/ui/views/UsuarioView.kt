package br.com.engecopi.estoque.ui.views

import br.com.engecopi.estoque.viewmodel.UsuarioCrudVo
import br.com.engecopi.estoque.viewmodel.UsuarioViewModel
import br.com.engecopi.framework.ui.view.CrudLayoutView
import com.github.vok.karibudsl.AutoView

@AutoView
class UsuarioView : CrudLayoutView<UsuarioViewModel>() {
  
  override val viewModel = UsuarioViewModel(this)

  override fun fieldsRead(): List<String> {
    return listOf(UsuarioCrudVo::codigo.name, UsuarioCrudVo::nome.name, UsuarioCrudVo::loja.name)
  }
  
  init {
    form("Usuários") {}
    gridCrud(viewModel.crudClass.java) {
    
    }
  }
}
