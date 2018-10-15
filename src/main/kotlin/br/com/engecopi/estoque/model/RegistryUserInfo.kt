package br.com.engecopi.estoque.model

import br.com.engecopi.framework.model.Transaction

object RegistryUserInfo {
  const val LOJA_FIELD = "LOJA_DEFAULT"
  const val USER_FIELD = "USER_DEFAULT"
  const val ABREV_FIELD = "ABREV_DEFAULT"

  private var loginInfo: () -> LoginInfo? = { null }

  fun register(loginInfo: () -> LoginInfo?) {
    this.loginInfo = loginInfo
    if(this.loginInfo() == null) {
      Transaction.variable(LOJA_FIELD, "NULL")
      Transaction.variable(USER_FIELD, "NULL")
      Transaction.variable(ABREV_FIELD, "NULL")
    }else{
      Transaction.variable(LOJA_FIELD, "${loja.numero}")
      Transaction.variable(USER_FIELD, "${usuario.id}")
      Transaction.variable(ABREV_FIELD, "'$abreviacao'")
    }
  }

  val usuario
    get() = loginInfo()!!.usuario
  val abreviacao
    get() = loginInfo()!!.abreviacao
  val loja
    get() = usuario.loja!!
  val admin
    get() = usuario.admin
}

data class LoginInfo(val usuario: Usuario, val abreviacao: String)
