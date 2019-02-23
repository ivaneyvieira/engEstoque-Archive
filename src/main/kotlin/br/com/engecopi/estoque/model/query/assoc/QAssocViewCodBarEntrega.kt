package br.com.engecopi.estoque.model.query.assoc

import br.com.engecopi.estoque.model.ViewCodBarEntrega
import br.com.engecopi.estoque.model.query.QViewCodBarEntrega
import io.ebean.typequery.PLong
import io.ebean.typequery.PString
import io.ebean.typequery.TQAssocBean
import io.ebean.typequery.TQProperty
import io.ebean.typequery.TypeQueryBean

/**
 * Association query bean for AssocViewCodBarEntrega.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@TypeQueryBean
class QAssocViewCodBarEntrega<R>(name: String, root: R) : TQAssocBean<ViewCodBarEntrega,R>(name, root) {

  lateinit var id: PLong<R>
  lateinit var codbar: PString<R>

  // type safe fetch(properties) using varargs not supported yet ...
}
