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
<<<<<<< HEAD
     return QUsuario()
=======
    return QUsuario()
>>>>>>> 9cbf9ae981c8aa2a499bc5822de1707096db26c9
  }

  /**
   * Start a new document store query.
   */
  fun text(): QUsuario {
<<<<<<< HEAD
     return QUsuario().text()
=======
    return QUsuario().text()
>>>>>>> 9cbf9ae981c8aa2a499bc5822de1707096db26c9
  }
}
