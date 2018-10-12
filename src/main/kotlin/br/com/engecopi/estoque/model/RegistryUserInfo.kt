package br.com.engecopi.estoque.model

object RegistryUserInfo {
  private var loginInfo: LoginInfo? = null

  fun register(loginInfo: LoginInfo) {
    if (this.loginInfo == null)
      this.loginInfo = loginInfo
    else
      throw Exception("As informações de login já forma registradas")
  }

  val usuario
    get() = loginInfo!!.usuario
  val abreviacao
    get() = loginInfo!!.abreviacao
  val loja
    get() = usuario.loja!!
}

data class LoginInfo(val usuario: Usuario, val abreviacao: String)
