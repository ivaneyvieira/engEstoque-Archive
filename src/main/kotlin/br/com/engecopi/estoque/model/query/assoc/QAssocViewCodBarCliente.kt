package br.com.engecopi.estoque.model.query.assoc

import br.com.engecopi.estoque.model.ViewCodBarCliente
import br.com.engecopi.estoque.model.query.QViewCodBarCliente
import io.ebean.typequery.PInteger
import io.ebean.typequery.PLong
import io.ebean.typequery.PString
import io.ebean.typequery.TQAssocBean
import io.ebean.typequery.TQProperty
import io.ebean.typequery.TypeQueryBean

/**
 * Association query bean for AssocViewCodBarCliente.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@TypeQueryBean
class QAssocViewCodBarCliente<R>(name: String, root: R) : TQAssocBean<ViewCodBarCliente,R>(name, root) {

  lateinit var id: PLong<R>
  lateinit var codbar: PString<R>
  lateinit var storeno: PInteger<R>
  lateinit var numero: PString<R>
  lateinit var sequencia: PInteger<R>

  // type safe fetch(properties) using varargs not supported yet ...
}
