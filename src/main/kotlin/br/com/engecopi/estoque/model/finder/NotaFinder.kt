package br.com.engecopi.estoque.model.finder

import br.com.engecopi.estoque.model.Nota
import br.com.engecopi.estoque.model.query.QNota
import io.ebean.Finder
import io.ebean.Model.db

open class NotaFinder : Finder<Long, Nota>(Nota::class.java) {
  /**
   * Start a new typed query.
   */
  fun where(): QNota {
    return QNota()
  }

  /**
   * Start a new document store query.
   */
  fun text(): QNota {
    return QNota().text()
  }
}