package br.com.engecopi.estoque.model.finder

import br.com.engecopi.estoque.model.Movimentacao
import br.com.engecopi.estoque.model.query.QMovimentacao
import io.ebean.Finder
import io.ebean.Model.db

open class MovimentacaoFinder : Finder<Long, Movimentacao>(Movimentacao::class.java) {
  
  /**
   * Start a new typed query.
   */
  fun where(): QMovimentacao {
    return QMovimentacao(db())
  }
  
  /**
   * Start a new document store query.
   */
  fun text(): QMovimentacao {
    return QMovimentacao(db()).text()
  }
}