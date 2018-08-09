package br.com.engecopi.estoque.model.query.assoc

import br.com.engecopi.estoque.model.ViewProdutoLoc
import br.com.engecopi.estoque.model.query.QViewProdutoLoc
import io.ebean.typequery.PInteger
import io.ebean.typequery.PString
import io.ebean.typequery.TQAssocBean
import io.ebean.typequery.TQProperty
import io.ebean.typequery.TypeQueryBean

/**
 * Association query bean for AssocViewProdutoLoc.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@TypeQueryBean
class QAssocViewProdutoLoc<R>(name: String, root: R) : TQAssocBean<ViewProdutoLoc,R>(name, root) {

  lateinit var id: PString<R>
  lateinit var storeno: PInteger<R>
  lateinit var codigo: PString<R>
  lateinit var grade: PString<R>
  lateinit var localizacao: PString<R>
  lateinit var produto: QAssocProduto<R>
  lateinit var loja: QAssocLoja<R>

  // type safe fetch(properties) using varargs not supported yet ...
}
