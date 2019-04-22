package br.com.engecopi.estoque.model.finder

import br.com.engecopi.estoque.model.ViewProdutoSaci
import br.com.engecopi.estoque.model.query.QViewProdutoSaci
import io.ebean.Finder

open class ViewProdutoSaciFinder : Finder<String, ViewProdutoSaci>(ViewProdutoSaci::class.java) {

  val alias = QViewProdutoSaci._alias

  /**
   * Start a new typed query.
   */
  fun where(): QViewProdutoSaci {
<<<<<<< HEAD
     return QViewProdutoSaci()
=======
    return QViewProdutoSaci()
>>>>>>> 9cbf9ae981c8aa2a499bc5822de1707096db26c9
  }

  /**
   * Start a new document store query.
   */
  fun text(): QViewProdutoSaci {
<<<<<<< HEAD
     return QViewProdutoSaci().text()
=======
    return QViewProdutoSaci().text()
>>>>>>> 9cbf9ae981c8aa2a499bc5822de1707096db26c9
  }
}
