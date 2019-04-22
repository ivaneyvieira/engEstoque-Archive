package br.com.engecopi.estoque.model.finder

import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.estoque.model.query.QProduto
import io.ebean.Finder

open class ProdutoFinder : Finder<Long, Produto>(Produto::class.java) {

  val alias = QProduto._alias

  /**
   * Start a new typed query.
   */
  fun where(): QProduto {
     return QProduto()
  }

  /**
   * Start a new document store query.
   */
  fun text(): QProduto {
     return QProduto().text()
  }
}
