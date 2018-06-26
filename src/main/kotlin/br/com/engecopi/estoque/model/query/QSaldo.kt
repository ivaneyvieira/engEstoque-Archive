package br.com.engecopi.estoque.model.query

import br.com.engecopi.estoque.model.Saldo
import br.com.engecopi.estoque.model.query.assoc.QAssocLoja
import br.com.engecopi.estoque.model.query.assoc.QAssocProduto
import io.ebean.EbeanServer
import io.ebean.typequery.PInteger
import io.ebean.typequery.PLocalDateTime
import io.ebean.typequery.PLong
import io.ebean.typequery.TQRootBean
import io.ebean.typequery.TypeQueryBean

/**
 * Query bean for Saldo.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@TypeQueryBean
class QSaldo : TQRootBean<Saldo, QSaldo> {

  companion object {
    /**
     * shared 'Alias' instance used to provide
     * properties to select and fetch clauses
     */
    val _alias = QSaldo(true)
  }

  lateinit var id: PLong<QSaldo>
  lateinit var createdAt: PLocalDateTime<QSaldo>
  lateinit var updatedAt: PLocalDateTime<QSaldo>
  lateinit var version: PInteger<QSaldo>
  lateinit var produto: QAssocProduto<QSaldo>
  lateinit var loja: QAssocLoja<QSaldo>
  lateinit var quantidade: PInteger<QSaldo>


  /**
   * Construct with a given EbeanServer.
   */
  constructor(server: EbeanServer) : super(Saldo::class.java, server)

  /**
   * Construct using the default EbeanServer.
   */
  constructor() : super(Saldo::class.java)

  /**
   * Construct for Alias.
   */
  private constructor(dummy: Boolean) : super(dummy)
}
