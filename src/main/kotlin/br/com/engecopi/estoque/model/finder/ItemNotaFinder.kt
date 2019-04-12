package br.com.engecopi.estoque.model.finder

import br.com.engecopi.estoque.model.ItemNota
import br.com.engecopi.estoque.model.query.QItemNota
import io.ebean.Finder

open class ItemNotaFinder : Finder<Long, ItemNota>(ItemNota::class.java) {

  val alias = QItemNota._alias

  /**
   * Start a new typed query.
   */
  fun where(): QItemNota {
     return QItemNota()
  }

  /**
   * Start a new document store query.
   */
  fun text(): QItemNota {
     return QItemNota().text()
  }
}
