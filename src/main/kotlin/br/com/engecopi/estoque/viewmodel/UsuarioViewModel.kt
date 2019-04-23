package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.Loja
import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.estoque.model.Usuario
import br.com.engecopi.estoque.model.query.QUsuario
import br.com.engecopi.framework.viewmodel.CrudViewModel
import br.com.engecopi.framework.viewmodel.EntityVo
import br.com.engecopi.framework.viewmodel.IView

class UsuarioViewModel(view: IView) : CrudViewModel<Usuario, QUsuario, UsuarioCrudVo>(view, UsuarioCrudVo::class) {
  private val queryProduto get() = Produto.where()

  fun findProduto(offset: Int, limit: Int): List<Produto> {
    return queryProduto
      .setFirstRow(offset)
      .setMaxRows(limit)
      .findList()
  }

  fun countProduto(): Int {
    return queryProduto.findCount()
  }

  override fun update(bean: UsuarioCrudVo): UsuarioCrudVo {
    bean.entityVo?.let { usuario ->
      val loginName = bean.loginName ?: ""
      if (loginName.isNotBlank())
        usuario.loginName = loginName
      usuario.loja = bean.loja
      usuario.locais = bean.localizacaoes.toList()
      usuario.estoque = bean.estoque
      usuario.expedicao = bean.expedicao
      usuario.admin = bean.admin ?: false
      usuario.update()
    }
    return bean
  }

  override fun add(bean: UsuarioCrudVo): UsuarioCrudVo {
    val usuario = Usuario().apply {
      this.loginName = bean.loginName ?: ""
      this.loja = bean.loja
      this.locais = bean.localizacaoes.toList()
      this.estoque = bean.estoque
      this.expedicao = bean.expedicao
      this.admin = bean.admin ?: false
    }
    usuario.insert()
    return bean
  }

  override val query: QUsuario
    get() = Usuario.where().loginName.`in`(Usuario.findLoginUser())

  override fun Usuario.toVO(): UsuarioCrudVo {
    val usuario = this
    return UsuarioCrudVo().apply {
      entityVo = usuario
      this.loginName = usuario.loginName
      this.loja = usuario.loja
      this.localizacaoes = usuario.locais.toHashSet()
      this.estoque = usuario.estoque
      this.expedicao = usuario.expedicao
      this.admin = usuario.admin
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
      val sets = value?.findAbreviacores().orEmpty().toMutableSet()
      locaisLoja.addAll(sets)
    }
  val nome
    get() = Usuario.nomeSaci(loginName ?: "")
  var locaisLoja: MutableSet<String> = HashSet()
  var localizacaoes: Set<String> = HashSet()
  val localStr
    get() = localizacaoes.joinToString()
  var estoque: Boolean = true
  var expedicao: Boolean = false
  var admin: Boolean? = false
  val tipoUsuarioStr = if (estoque)
    if (expedicao) "Estoque/Expedição"
    else "Estoque"
  else if (expedicao) "Expedição"
  else ""
}
