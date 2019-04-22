package br.com.engecopi.estoque.model.finder

import br.com.engecopi.estoque.model.ViewProduto
import br.com.engecopi.estoque.model.query.QViewProduto
import io.ebean.Finder

open class ViewProdutoFinder : Finder<Long, ViewProduto>(ViewProduto::class.java) {

  val alias = QViewProduto._alias

  /**
   * Start a new typed query.
   */
  fun where(): QViewProduto {
<<<<<<< HEAD
     return QViewProduto()
=======
    return QViewProduto()
>>>>>>> 9cbf9ae981c8aa2a499bc5822de1707096db26c9
  }

  /**
   * Start a new document store query.
   */
  fun text(): QViewProduto {
<<<<<<< HEAD
     return QViewProduto().text()
=======
    return QViewProduto().text()
>>>>>>> 9cbf9ae981c8aa2a499bc5822de1707096db26c9
  }
}
