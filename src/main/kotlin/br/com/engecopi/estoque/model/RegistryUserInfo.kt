package br.com.engecopi.estoque.model

import br.com.engecopi.framework.model.Transaction

object RegistryUserInfo {
  const val LOJA_FIELD = "LOJA_DEFAULT"
  const val USER_FIELD = "USER_DEFAULT"
  const val ABREV_FIELD = "ABREV_DEFAULT"

  private var loginInfo: () -> LoginInfo? = { null }

  fun register(loginInfo: () -> LoginInfo?) {
    this.loginInfo = loginInfo
    Transaction.variable(LOJA_FIELD, "${loja.numero}")
    Transaction.variable(USER_FIELD, "${usuario.id}")
    Transaction.variable(ABREV_FIELD, abreviacao)
  }

  val usuario
    get() = loginInfo()!!.usuario
  val abreviacao
    get() = loginInfo()!!.abreviacao
  val loja
    get() = usuario.loja!!
}

data class LoginInfo(val usuario: Usuario, val abreviacao: String)
