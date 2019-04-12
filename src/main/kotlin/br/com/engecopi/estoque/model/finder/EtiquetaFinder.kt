package br.com.engecopi.estoque.model.finder

import br.com.engecopi.estoque.model.Etiqueta
import br.com.engecopi.estoque.model.query.QEtiqueta
import io.ebean.Finder

open class EtiquetaFinder : Finder<Long, Etiqueta>(Etiqueta::class.java) {

  val alias = QEtiqueta._alias

  /**
   * Start a new typed query.
   */
  fun where(): QEtiqueta {
     return QEtiqueta()
  }

  /**
   * Start a new document store query.
   */
  fun text(): QEtiqueta {
     return QEtiqueta().text()
  }
}
