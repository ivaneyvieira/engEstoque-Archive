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
     return QViewProdutoSaci()
  }

  /**
   * Start a new document store query.
   */
  fun text(): QViewProdutoSaci {
     return QViewProdutoSaci().text()
  }
}
