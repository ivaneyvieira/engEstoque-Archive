package br.com.engecopi.estoque.ui.views

import br.com.engecopi.estoque.viewmodel.UsuarioCrudVo
import br.com.engecopi.estoque.viewmodel.UsuarioViewModel
import br.com.engecopi.framework.ui.view.CrudLayoutView
import com.github.vok.karibudsl.AutoView

@AutoView
class UsuarioView : CrudLayoutView<UsuarioViewModel>() {
  
  override val viewModel = UsuarioViewModel(this)
  
  override fun fieldsRead() = listOf(UsuarioCrudVo::codigo, UsuarioCrudVo::nome, UsuarioCrudVo::loja)
  
  init {
    form("Usuários") {
      gridCrud(viewModel.crudClass.java)
    }
  }
}
