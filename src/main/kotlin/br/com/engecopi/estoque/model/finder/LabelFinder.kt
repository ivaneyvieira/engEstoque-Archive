package br.com.engecopi.estoque.model.finder

import br.com.engecopi.estoque.model.Label
import br.com.engecopi.estoque.model.query.QLabel
import io.ebean.Finder

open class LabelFinder : Finder<Long, Label>(Label::class.java) {
  
  /**
   * Start a new typed query.
   */
  fun where(): QLabel {
    return QLabel(db())
  }
  
  /**
   * Start a new document store query.
   */
  fun text(): QLabel {
    return QLabel(db()).text()
  }
}