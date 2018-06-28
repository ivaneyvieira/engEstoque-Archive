package br.com.engecopi.estoque.model.query

import br.com.engecopi.estoque.model.Lote
import br.com.engecopi.estoque.model.query.assoc.QAssocLoja
import br.com.engecopi.estoque.model.query.assoc.QAssocMovimentacao
import br.com.engecopi.estoque.model.query.assoc.QAssocProduto
import io.ebean.EbeanServer
import io.ebean.typequery.PInteger
import io.ebean.typequery.PLocalDateTime
import io.ebean.typequery.PLong
import io.ebean.typequery.TQRootBean
import io.ebean.typequery.TypeQueryBean

/**
 * Query bean for Lote.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@TypeQueryBean
class QLote : TQRootBean<Lote, QLote> {

  companion object {
    /**
     * shared 'Alias' instance used to provide
     * properties to select and fetch clauses
     */
    val _alias = QLote(true)
  }

  lateinit var id: PLong<QLote>
  lateinit var createdAt: PLocalDateTime<QLote>
  lateinit var updatedAt: PLocalDateTime<QLote>
  lateinit var version: PInteger<QLote>
  lateinit var sequencia: PInteger<QLote>
  lateinit var total: PInteger<QLote>
  lateinit var produto: QAssocProduto<QLote>
  lateinit var loja: QAssocLoja<QLote>
  lateinit var movimentacoes: QAssocMovimentacao<QLote>


  /**
   * Construct with a given EbeanServer.
   */
  constructor(server: EbeanServer) : super(Lote::class.java, server)

  /**
   * Construct using the default EbeanServer.
   */
  constructor() : super(Lote::class.java)

  /**
   * Construct for Alias.
   */
  private constructor(dummy: Boolean) : super(dummy)
}
