package br.com.engecopi.estoque.model.finder

import br.com.engecopi.estoque.model.Nota
import br.com.engecopi.estoque.model.query.QNota
import io.ebean.Finder

open class NotaFinder : Finder<Long, Nota>(Nota::class.java) {

  val alias = QNota._alias

  /**
   * Start a new typed query.
   */
  fun where(): QNota {
<<<<<<< HEAD
     return QNota()
=======
    return QNota()
>>>>>>> 9cbf9ae981c8aa2a499bc5822de1707096db26c9
  }

  /**
   * Start a new document store query.
   */
  fun text(): QNota {
<<<<<<< HEAD
     return QNota().text()
=======
    return QNota().text()
>>>>>>> 9cbf9ae981c8aa2a499bc5822de1707096db26c9
  }
}
