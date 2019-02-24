package br.com.engecopi.estoque.model.query.assoc

import br.com.engecopi.estoque.model.ViewCodBarConferencia
import io.ebean.typequery.PLong
import io.ebean.typequery.PString
import io.ebean.typequery.TQAssocBean
import io.ebean.typequery.TypeQueryBean

/**
 * Association query bean for AssocViewCodBarConferencia.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@TypeQueryBean
class QAssocViewCodBarConferencia<R>(name: String, root: R) : TQAssocBean<ViewCodBarConferencia,R>(name, root) {

  lateinit var id: PLong<R>
  lateinit var codbar: PString<R>

  // type safe fetch(properties) using varargs not supported yet ...
}
