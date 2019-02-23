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
     return QViewProdutoLoc(db())
  }

  /**
   * Start a new document store query.
   */
  fun text(): QViewProdutoLoc {
     return QViewProdutoLoc(db()).text()
  }
}
