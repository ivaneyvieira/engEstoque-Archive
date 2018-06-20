package br.com.engecopi.estoque.model.query.assoc

import br.com.engecopi.estoque.model.ItemNota
import br.com.engecopi.estoque.model.query.QItemNota
import io.ebean.typequery.PInteger
import io.ebean.typequery.PLocalDateTime
import io.ebean.typequery.PLong
import io.ebean.typequery.TQAssocBean
import io.ebean.typequery.TQProperty
import io.ebean.typequery.TypeQueryBean

/**
 * Association query bean for AssocItemNota.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@TypeQueryBean
class QAssocItemNota<R>(name: String, root: R) : TQAssocBean<ItemNota,R>(name, root) {

  lateinit var id: PLong<R>
  lateinit var createdAt: PLocalDateTime<R>
  lateinit var updatedAt: PLocalDateTime<R>
  lateinit var version: PInteger<R>
  lateinit var quantidade: PInteger<R>
  lateinit var produto: QAssocProduto<R>
  lateinit var nota: QAssocNota<R>
  lateinit var movimentacoes: QAssocMovimentacao<R>

  // type safe fetch(properties) using varargs not supported yet ...
}
