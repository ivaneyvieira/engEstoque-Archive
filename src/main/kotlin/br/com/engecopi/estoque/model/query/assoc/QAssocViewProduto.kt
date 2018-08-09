package br.com.engecopi.estoque.model.query.assoc

import br.com.engecopi.estoque.model.ViewProduto
import br.com.engecopi.estoque.model.query.QViewProduto
import io.ebean.typequery.PDouble
import io.ebean.typequery.PLong
import io.ebean.typequery.PString
import io.ebean.typequery.TQAssocBean
import io.ebean.typequery.TQProperty
import io.ebean.typequery.TypeQueryBean

/**
 * Association query bean for AssocViewProduto.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@TypeQueryBean
class QAssocViewProduto<R>(name: String, root: R) : TQAssocBean<ViewProduto,R>(name, root) {

  lateinit var id: PLong<R>
  lateinit var codigo: PString<R>
  lateinit var nome: PString<R>
  lateinit var grade: PString<R>
  lateinit var codebar: PString<R>
  lateinit var custo: PDouble<R>
  lateinit var unidade: PString<R>
  lateinit var tipo: PString<R>
  lateinit var produto: QAssocProduto<R>

  // type safe fetch(properties) using varargs not supported yet ...
}