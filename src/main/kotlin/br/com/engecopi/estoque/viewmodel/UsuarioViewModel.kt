package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.Loja
import br.com.engecopi.framework.viewmodel.CrudViewModel
import br.com.engecopi.framework.viewmodel.IView

class UsuarioViewModel(view : IView) : CrudViewModel<UsuarioCrudVo>(view, UsuarioCrudVo::class) {

}

class UsuarioCrudVo{
  var codigo : String = ""
  var loja : Loja? = null
  var nome : String = ""
}