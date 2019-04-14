package br.com.engecopi.estoque.model.finder

import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.estoque.model.query.QProduto
import io.ebean.Finder
import io.ebean.Model.db

open class ProdutoFinder : Finder<Long, Produto>(Produto::class.java) {
  /**
   * Start a new typed query.
   */
  fun where(): QProduto {
    return QProduto(db())
  }

  /**
   * Start a new document store query.
   */
  fun text(): QProduto {
    return QProduto(db()).text()
  }
}