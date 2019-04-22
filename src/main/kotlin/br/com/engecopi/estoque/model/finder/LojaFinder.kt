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
<<<<<<< HEAD
     return QLoja()
=======
    return QLoja()
>>>>>>> 9cbf9ae981c8aa2a499bc5822de1707096db26c9
  }

  /**
   * Start a new document store query.
   */
  fun text(): QLoja {
<<<<<<< HEAD
     return QLoja().text()
=======
    return QLoja().text()
>>>>>>> 9cbf9ae981c8aa2a499bc5822de1707096db26c9
  }
}
