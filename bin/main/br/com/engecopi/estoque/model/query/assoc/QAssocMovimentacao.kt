package br.com.engecopi.estoque.model.query.assoc

import br.com.engecopi.estoque.model.Movimentacao
import br.com.engecopi.estoque.model.query.QMovimentacao
import io.ebean.typequery.PInteger
import io.ebean.typequery.PLocalDateTime
import io.ebean.typequery.PLong
import io.ebean.typequery.TQAssocBean
import io.ebean.typequery.TQProperty
import io.ebean.typequery.TypeQueryBean

/**
 * Association query bean for AssocMovimentacao.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@TypeQueryBean
class QAssocMovimentacao<R>(name: String, root: R) : TQAssocBean<Movimentacao,R>(name, root) {

  lateinit var id: PLong<R>
  lateinit var createdAt: PLocalDateTime<R>
  lateinit var updatedAt: PLocalDateTime<R>
  lateinit var version: PInteger<R>
  lateinit var quantidade: PInteger<R>
  lateinit var lote: QAssocLote<R>
  lateinit var itemNota: QAssocItemNota<R>

  // type safe fetch(properties) using varargs not supported yet ...
}
