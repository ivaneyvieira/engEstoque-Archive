package br.com.engecopi.estoque.model.query

import br.com.engecopi.estoque.model.Movimentacao
import br.com.engecopi.estoque.model.query.assoc.QAssocItemNota
import br.com.engecopi.estoque.model.query.assoc.QAssocLote
import io.ebean.EbeanServer
import io.ebean.typequery.PInteger
import io.ebean.typequery.PLocalDateTime
import io.ebean.typequery.PLong
import io.ebean.typequery.TQRootBean
import io.ebean.typequery.TypeQueryBean

/**
 * Query bean for Movimentacao.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@TypeQueryBean
class QMovimentacao : TQRootBean<Movimentacao, QMovimentacao> {

  companion object {
    /**
     * shared 'Alias' instance used to provide
     * properties to select and fetch clauses
     */
    val _alias = QMovimentacao(true)
  }

  lateinit var id: PLong<QMovimentacao>
  lateinit var createdAt: PLocalDateTime<QMovimentacao>
  lateinit var updatedAt: PLocalDateTime<QMovimentacao>
  lateinit var version: PInteger<QMovimentacao>
  lateinit var quantidade: PInteger<QMovimentacao>
  lateinit var saldo: PInteger<QMovimentacao>
  lateinit var lote: QAssocLote<QMovimentacao>
  lateinit var itemNota: QAssocItemNota<QMovimentacao>


  /**
   * Construct with a given EbeanServer.
   */
  constructor(server: EbeanServer) : super(Movimentacao::class.java, server)

  /**
   * Construct using the default EbeanServer.
   */
  constructor() : super(Movimentacao::class.java)

  /**
   * Construct for Alias.
   */
  private constructor(dummy: Boolean) : super(dummy)
}
