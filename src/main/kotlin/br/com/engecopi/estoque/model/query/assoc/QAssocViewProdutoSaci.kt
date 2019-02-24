package br.com.engecopi.estoque.model.query.assoc

import br.com.engecopi.estoque.model.ViewProdutoSaci
import io.ebean.typequery.PDouble
import io.ebean.typequery.PString
import io.ebean.typequery.TQAssocBean
import io.ebean.typequery.TypeQueryBean

/**
 * Association query bean for AssocViewProdutoSaci.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@TypeQueryBean
class QAssocViewProdutoSaci<R>(name: String, root: R) : TQAssocBean<ViewProdutoSaci,R>(name, root) {

  lateinit var id: PString<R>
  lateinit var codigo: PString<R>
  lateinit var nome: PString<R>
  lateinit var grade: PString<R>
  lateinit var codebar: PString<R>
  lateinit var custo: PDouble<R>
  lateinit var unidade: PString<R>
  lateinit var tipo: PString<R>

  // type safe fetch(properties) using varargs not supported yet ...
}
