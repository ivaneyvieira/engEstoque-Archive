package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.TipoUsuario.EXPEDICAO
import br.com.engecopi.framework.model.Transaction

object RegistryUserInfo {
  const val LOJA_FIELD = "LOJA_DEFAULT"
  const val USER_FIELD = "USER_DEFAULT"
  const val ABREV_FIELD = "ABREV_DEFAULT"
  private var loginInfo: () -> LoginInfo? = { null }

  fun register(loginInfoReg: () -> LoginInfo?) {
    this.loginInfo = loginInfoReg
  }

  private val info : LoginInfo
  get() {
    val info = loginInfo()
    if (info == null) {
      Transaction.variable(LOJA_FIELD, "NULL")
      Transaction.variable(USER_FIELD, "NULL")
      Transaction.variable(ABREV_FIELD, "NULL")
    } else {
      Transaction.variable(LOJA_FIELD, "${info.usuario.loja?.numero}")
      Transaction.variable(USER_FIELD, "${info.usuario.id}")
      Transaction.variable(ABREV_FIELD, "'${info.abreviacao}'")
    }
    return info!!
  }

  val usuarioDefault
    get() = info.usuario
  val abreviacaoDefault
    get() =  if(info.usuario.tipoUsuario == EXPEDICAO) "" else info.abreviacao
  val lojaDefault
    get() = usuarioDefault.loja!!
  val userDefaultIsAdmin
    get() = usuarioDefault.admin
}

data class LoginInfo(val usuario: Usuario, val abreviacao: String)
