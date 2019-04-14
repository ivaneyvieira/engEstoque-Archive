package br.com.engecopi.estoque.model.finder

import br.com.engecopi.estoque.model.Usuario
import br.com.engecopi.estoque.model.query.QUsuario
import io.ebean.Finder
import io.ebean.Model.db

open class UsuarioFinder : Finder<Long, Usuario>(Usuario::class.java) {
  /**
   * Start a new typed query.
   */
  fun where(): QUsuario {
    return QUsuario(db())
  }

  /**
   * Start a new document store query.
   */
  fun text(): QUsuario {
    return QUsuario(db()).text()
  }
}