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
<<<<<<< HEAD
     return QProduto()
=======
    return QProduto()
>>>>>>> 9cbf9ae981c8aa2a499bc5822de1707096db26c9
  }

  /**
   * Start a new document store query.
   */
  fun text(): QProduto {
<<<<<<< HEAD
     return QProduto().text()
=======
    return QProduto().text()
>>>>>>> 9cbf9ae981c8aa2a499bc5822de1707096db26c9
  }
}
