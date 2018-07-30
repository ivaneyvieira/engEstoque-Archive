package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.Loja
import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.estoque.model.Usuario
import br.com.engecopi.estoque.model.query.QUsuario
import br.com.engecopi.framework.viewmodel.CrudViewModel
import br.com.engecopi.framework.viewmodel.IView
import br.com.engecopi.saci.saci

class UsuarioViewModel(view: IView) : CrudViewModel<Usuario, QUsuario, UsuarioCrudVo>(view, UsuarioCrudVo::class) {
  val queryProduto get() = Produto.where()
  
  fun findProduto(offset: Int, limit: Int) : List<Produto>{
    return queryProduto
            .setFirstRow(offset)
            .setMaxRows(limit)
            .findList()
  }
  
  fun countProduto() : Int {
    return queryProduto.findCount()
  }
  
  override fun update(bean: UsuarioCrudVo) {
    Usuario.findUsuario(bean.loginName ?: "")?.let { usuario ->
      usuario.loja = bean.loja
      usuario.impressora = bean.impressora ?: ""
      usuario.localizacao = bean.local
      usuario.update()
    }
  }
  
  override fun add(bean: UsuarioCrudVo) {
    val usuario = Usuario().apply {
      this.loginName = bean.loginName ?: ""
      this.loja = bean.loja
      this.impressora = bean.impressora ?: ""
      this.localizacao = bean.local
    }
    usuario.insert()
  }
  
  override val query: QUsuario
    get() = Usuario.where()
  
  override fun Usuario.toVO(): UsuarioCrudVo {
    val usuario = this
    return UsuarioCrudVo().apply {
      this.loginName = usuario.loginName
      this.loja = usuario.loja
      this.impressora = usuario.impressora
      this.local = usuario.localizacao
      //this.produtos = usuario.produtos.orEmpty().toSet()
    }
  }
  
  override fun QUsuario.filterString(text: String): QUsuario {
    return loginName.contains(text)
  }
  override fun delete(bean: UsuarioCrudVo) {
    Usuario.findUsuario(bean.loginName ?: "")?.delete()
  }
  
  val lojas = execList { Loja.all() }
  val produtos: List<Produto>
    get() = Produto.all()
}

class UsuarioCrudVo {
  var loginName: String? = ""
  var impressora: String? = ""
  
  var loja: Loja? = null
  
  val nome
    get() = Usuario.nomeSaci(loginName ?: "")
  
//  var produtos: Set<Produto>? = null
  
  var local: String? = null
  val locaisLoja
    get() = saci.findLocais(loja?.numero)
}
