package br.com.engecopi.estoque.model.finder

import br.com.engecopi.estoque.model.ViewCodBarEntrega
import br.com.engecopi.estoque.model.query.QViewCodBarEntrega
import io.ebean.Finder

open class ViewCodBarEntregaFinder : Finder<Long, ViewCodBarEntrega>(ViewCodBarEntrega::class.java) {

  val alias = QViewCodBarEntrega._alias

  /**
   * Start a new typed query.
   */
  fun where(): QViewCodBarEntrega {
     return QViewCodBarEntrega(db())
  }

  /**
   * Start a new document store query.
   */
  fun text(): QViewCodBarEntrega {
     return QViewCodBarEntrega(db()).text()
  }
}
