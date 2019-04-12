package br.com.engecopi.estoque.model.finder

import br.com.engecopi.estoque.model.ViewProduto
import br.com.engecopi.estoque.model.query.QViewProduto
import io.ebean.Finder
import io.ebean.Model.db

open class ViewProdutoFinder : Finder<Long, ViewProduto>(ViewProduto::class.java) {
  /**
   * Start a new typed query.
   */
  fun where(): QViewProduto {
    return QViewProduto(db())
  }

  /**
   * Start a new document store query.
   */
  fun text(): QViewProduto {
    return QViewProduto(db()).text()
  }
}