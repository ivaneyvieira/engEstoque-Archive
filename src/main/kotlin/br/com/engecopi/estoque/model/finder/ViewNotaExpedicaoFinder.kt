package br.com.engecopi.estoque.model.finder

import br.com.engecopi.estoque.model.ViewNotaExpedicao
import br.com.engecopi.estoque.model.query.QViewNotaExpedicao
import io.ebean.Finder

open class ViewNotaExpedicaoFinder : Finder<Long, ViewNotaExpedicao>(ViewNotaExpedicao::class.java) {

  val alias = QViewNotaExpedicao._alias

  /**
   * Start a new typed query.
   */
  fun where(): QViewNotaExpedicao {
     return QViewNotaExpedicao()
  }

  /**
   * Start a new document store query.
   */
  fun text(): QViewNotaExpedicao {
     return QViewNotaExpedicao().text()
  }
}
