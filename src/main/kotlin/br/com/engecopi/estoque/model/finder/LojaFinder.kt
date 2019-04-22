package br.com.engecopi.estoque.model.finder

import br.com.engecopi.estoque.model.Loja
import br.com.engecopi.estoque.model.query.QLoja
import io.ebean.Finder

open class LojaFinder : Finder<Long, Loja>(Loja::class.java) {

  val alias = QLoja._alias

  /**
   * Start a new typed query.
   */
  fun where(): QLoja {
     return QLoja()
  }

  /**
   * Start a new document store query.
   */
  fun text(): QLoja {
     return QLoja().text()
  }
}
