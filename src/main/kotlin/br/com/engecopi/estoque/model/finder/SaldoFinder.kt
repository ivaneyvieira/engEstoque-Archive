package br.com.engecopi.estoque.model.finder

import br.com.engecopi.estoque.model.Saldo
import br.com.engecopi.estoque.model.Usuario
import br.com.engecopi.estoque.model.query.QSaldo
import br.com.engecopi.estoque.model.query.QUsuario
import io.ebean.Finder

open class SaldoFinder: Finder<Long, Saldo>(Saldo::class.java) {
  
  val alias = QSaldo._alias
  
  /**
   * Start a new typed query.
   */
  fun where(): QSaldo {
    return QSaldo(db())
  }
  
  /**
   * Start a new document store query.
   */
  fun text(): QSaldo {
    return QSaldo(db()).text()
  }
}