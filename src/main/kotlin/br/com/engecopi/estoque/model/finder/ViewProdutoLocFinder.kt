package br.com.engecopi.estoque.model.finder

import br.com.engecopi.estoque.model.ViewProdutoLoc
import br.com.engecopi.estoque.model.query.QViewProdutoLoc
import io.ebean.Finder

open class ViewProdutoLocFinder : Finder<String, ViewProdutoLoc>(ViewProdutoLoc::class.java) {

  val alias = QViewProdutoLoc._alias

  /**
   * Start a new typed query.
   */
  fun where(): QViewProdutoLoc {
<<<<<<< HEAD
     return QViewProdutoLoc()
=======
    return QViewProdutoLoc()
>>>>>>> 9cbf9ae981c8aa2a499bc5822de1707096db26c9
  }

  /**
   * Start a new document store query.
   */
  fun text(): QViewProdutoLoc {
<<<<<<< HEAD
     return QViewProdutoLoc().text()
=======
    return QViewProdutoLoc().text()
>>>>>>> 9cbf9ae981c8aa2a499bc5822de1707096db26c9
  }
}
