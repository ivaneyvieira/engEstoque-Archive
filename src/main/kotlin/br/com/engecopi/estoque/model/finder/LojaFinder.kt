package br.com.engecopi.estoque.model.finder

import br.com.engecopi.estoque.model.Loja
import br.com.engecopi.estoque.model.query.QLoja
import io.ebean.Finder
import io.ebean.Model.db

open class LojaFinder : Finder<Long, Loja>(Loja::class.java) {
  
  /**
   * Start a new typed query.
   */
  fun where(): QLoja {
    return QLoja(db())
  }
  
  /**
   * Start a new document store query.
   */
  fun text(): QLoja {
    return QLoja(db()).text()
  }
}