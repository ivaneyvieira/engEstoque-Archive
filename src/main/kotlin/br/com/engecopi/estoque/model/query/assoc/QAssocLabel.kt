package br.com.engecopi.estoque.model.query.assoc

import br.com.engecopi.estoque.model.Label
import br.com.engecopi.estoque.model.TipoProduto
import br.com.engecopi.estoque.model.query.QLabel
import io.ebean.typequery.PEnum
import io.ebean.typequery.PInteger
import io.ebean.typequery.PLocalDateTime
import io.ebean.typequery.PLong
import io.ebean.typequery.PString
import io.ebean.typequery.TQAssocBean
import io.ebean.typequery.TQProperty
import io.ebean.typequery.TypeQueryBean

/**
 * Association query bean for AssocLabel.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@TypeQueryBean
class QAssocLabel<R>(name: String, root: R) : TQAssocBean<Label,R>(name, root) {

  lateinit var id: PLong<R>
  lateinit var createdAt: PLocalDateTime<R>
  lateinit var updatedAt: PLocalDateTime<R>
  lateinit var version: PInteger<R>
  lateinit var tipo: PEnum<R,TipoProduto>
  lateinit var layout: PString<R>

  // type safe fetch(properties) using varargs not supported yet ...
}
