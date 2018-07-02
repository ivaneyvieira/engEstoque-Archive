package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.Loja
import br.com.engecopi.estoque.model.Usuario
import br.com.engecopi.framework.viewmodel.CrudViewModel
import br.com.engecopi.framework.viewmodel.IView

class UsuarioViewModel(view: IView) : CrudViewModel<UsuarioCrudVo>(view, UsuarioCrudVo::class) {
  override fun update(bean: UsuarioCrudVo) {
    Usuario.findUsuario(bean.loginName ?: "")?.let { usuario ->
      usuario.loja = bean.loja
      usuario.update()
    }
  }
  
  override fun add(bean: UsuarioCrudVo) {
    val usuario = Usuario().apply {
      this.loginName = bean.loginName ?: ""
      this.loja = bean.loja
    }
    usuario.insert()
  }
  
  override fun allBeans(): List<UsuarioCrudVo> {
    return Usuario.all().map { usuario ->
      UsuarioCrudVo().apply {
        this.loginName = usuario.loginName
        this.loja = usuario.loja
      }
    }
  }
  
  override fun delete(bean: UsuarioCrudVo) {
    Usuario.findUsuario(bean.loginName ?: "")?.delete()
  }
  
  val lojas = execList { Loja.all() }
}

class UsuarioCrudVo {
  var loginName: String? = ""
  
  var loja: Loja? = null
  
  val nome
    get() = Usuario.nomeSaci(loginName ?: "")
}
