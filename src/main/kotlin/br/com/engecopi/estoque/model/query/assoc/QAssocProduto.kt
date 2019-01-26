package br.com.engecopi.estoque.model.query.assoc

import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.estoque.model.query.QProduto
import io.ebean.typequery.PInteger
import io.ebean.typequery.PLocalDate
import io.ebean.typequery.PLocalDateTime
import io.ebean.typequery.PLong
import io.ebean.typequery.PString
import io.ebean.typequery.TQAssocBean
import io.ebean.typequery.TQProperty
import io.ebean.typequery.TypeQueryBean

/**
 * Association query bean for AssocProduto.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@TypeQueryBean
class QAssocProduto<R>(name: String, root: R) : TQAssocBean<Produto,R>(name, root) {

  lateinit var id: PLong<R>
  lateinit var createdAt: PLocalDateTime<R>
  lateinit var updatedAt: PLocalDateTime<R>
  lateinit var version: PInteger<R>
  lateinit var codigo: PString<R>
  lateinit var grade: PString<R>
  lateinit var codebar: PString<R>
  lateinit var dataCadastro: PLocalDate<R>
  lateinit var itensNota: QAssocItemNota<R>
  lateinit var vproduto: QAssocViewProduto<R>
  lateinit var viewProdutoLoc: QAssocViewProdutoLoc<R>
  lateinit var localizacao: PString<R>
  lateinit var saldo_total: PInteger<R>

  // type safe fetch(properties) using varargs not supported yet ...
}
