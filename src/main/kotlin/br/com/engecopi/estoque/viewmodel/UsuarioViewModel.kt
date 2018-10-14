package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.Loja
import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.estoque.model.Usuario
import br.com.engecopi.estoque.model.ViewProdutoLoc
import br.com.engecopi.estoque.model.query.QUsuario
import br.com.engecopi.framework.viewmodel.CrudViewModel
import br.com.engecopi.framework.viewmodel.EntityVo
import br.com.engecopi.framework.viewmodel.IView

class UsuarioViewModel(view: IView) : CrudViewModel<Usuario, QUsuario, UsuarioCrudVo>(view, UsuarioCrudVo::class) {
  val queryProduto get() = Produto.where()

  fun findProduto(offset: Int, limit: Int): List<Produto> {
    return queryProduto
      .setFirstRow(offset)
      .setMaxRows(limit)
      .findList()
  }

  fun countProduto(): Int {
    return queryProduto.findCount()
  }

  override fun update(bean: UsuarioCrudVo) {
    bean.entityVo?.let { usuario ->
      val loginName = bean.loginName ?: ""
      if (loginName.isNotBlank())
        usuario.loginName = loginName
      usuario.loja = bean.loja
      usuario.locais = bean.localizacaoes.toList()
      usuario.update()
    }
  }

  override fun add(bean: UsuarioCrudVo) {
    val usuario = Usuario().apply {
      this.loginName = bean.loginName ?: ""
      this.loja = bean.loja
      this.locais = bean.localizacaoes.toList()
    }
    usuario.insert()
  }

  override val query: QUsuario
    get() = Usuario.where()

  override fun Usuario.toVO(): UsuarioCrudVo {
    val usuario = this
    return UsuarioCrudVo().apply {
      entityVo = usuario
      this.loginName = usuario.loginName
      this.loja = usuario.loja
      this.localizacaoes = usuario.locais.toHashSet()
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

class UsuarioCrudVo : EntityVo<Usuario>() {
  override fun findEntity(): Usuario? {
    return Usuario.findUsuario(loginName)
  }

  var loginName: String? = ""
  var loja: Loja? = null
    set(value) {
      field = value
      locaisLoja.clear()
      val sets = ViewProdutoLoc.findAbreviacoresLoja().toMutableSet()
      locaisLoja.addAll(sets)
    }
  val nome
    get() = Usuario.nomeSaci(loginName ?: "")
  var locaisLoja: MutableSet<String> = HashSet()
  var localizacaoes: Set<String> = HashSet()
  val localStr
    get() = localizacaoes.joinToString()
}
