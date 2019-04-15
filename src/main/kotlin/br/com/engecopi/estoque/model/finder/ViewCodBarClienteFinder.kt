package br.com.engecopi.estoque.model.finder

import br.com.engecopi.estoque.model.ViewCodBarCliente
import br.com.engecopi.estoque.model.query.QViewCodBarCliente
import io.ebean.Finder

open class ViewCodBarClienteFinder : Finder<Long, ViewCodBarCliente>(ViewCodBarCliente::class.java) {

  val alias = QViewCodBarCliente._alias

  /**
   * Start a new typed query.
   */
  fun where(): QViewCodBarCliente {
     return QViewCodBarCliente()
  }

  /**
   * Start a new document store query.
   */
  fun text(): QViewCodBarCliente {
     return QViewCodBarCliente().text()
  }
}
