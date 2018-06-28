package br.com.engecopi.estoque.model.finder

import br.com.engecopi.estoque.model.Lote
import br.com.engecopi.estoque.model.query.QLote
import io.ebean.Finder

open class LoteFinder : Finder<Long, Lote>(Lote::class.java) {

  val alias = QLote._alias

  /**
   * Start a new typed query.
   */
  fun where(): QLote {
     return QLote(db())
  }

  /**
   * Start a new document store query.
   */
  fun text(): QLote {
     return QLote(db()).text()
  }
}
