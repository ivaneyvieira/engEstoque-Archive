package br.com.engecopi.estoque.model.finder

import br.com.engecopi.estoque.model.Usuario
import br.com.engecopi.estoque.model.query.QUsuario
import io.ebean.Finder

open class UsuarioFinder : Finder<Long, Usuario>(Usuario::class.java) {

  val alias = QUsuario._alias

  /**
   * Start a new typed query.
   */
  fun where(): QUsuario {
     return QUsuario()
  }

  /**
   * Start a new document store query.
   */
  fun text(): QUsuario {
     return QUsuario().text()
  }
}
