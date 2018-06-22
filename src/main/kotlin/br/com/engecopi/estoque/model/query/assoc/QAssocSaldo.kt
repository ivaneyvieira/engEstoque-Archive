package br.com.engecopi.estoque.model.query.assoc

import br.com.engecopi.estoque.model.Saldo
import br.com.engecopi.estoque.model.query.QSaldo
import io.ebean.typequery.PInteger
import io.ebean.typequery.TQAssocBean
import io.ebean.typequery.TQProperty
import io.ebean.typequery.TypeQueryBean

/**
 * Association query bean for AssocSaldo.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@TypeQueryBean
class QAssocSaldo<R>(name: String, root: R) : TQAssocBean<Saldo,R>(name, root) {

  lateinit var produto: QAssocProduto<R>
  lateinit var loja: QAssocLoja<R>
  lateinit var quantidade: PInteger<R>

  // type safe fetch(properties) using varargs not supported yet ...
}
