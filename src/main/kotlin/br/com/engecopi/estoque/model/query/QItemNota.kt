package br.com.engecopi.estoque.model.query

import br.com.engecopi.estoque.model.ItemNota
import br.com.engecopi.estoque.model.query.assoc.QAssocEtiqueta
import br.com.engecopi.estoque.model.query.assoc.QAssocNota
import br.com.engecopi.estoque.model.query.assoc.QAssocProduto
import br.com.engecopi.estoque.model.query.assoc.QAssocUsuario
import io.ebean.EbeanServer
import io.ebean.typequery.PInteger
import io.ebean.typequery.PLocalDate
import io.ebean.typequery.PLocalDateTime
import io.ebean.typequery.PLocalTime
import io.ebean.typequery.PLong
import io.ebean.typequery.TQRootBean
import io.ebean.typequery.TypeQueryBean

/**
 * Query bean for ItemNota.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@TypeQueryBean
class QItemNota : TQRootBean<ItemNota, QItemNota> {

  companion object {
    /**
     * shared 'Alias' instance used to provide
     * properties to select and fetch clauses
     */
    val _alias = QItemNota(true)
  }

  lateinit var id: PLong<QItemNota>
  lateinit var createdAt: PLocalDateTime<QItemNota>
  lateinit var updatedAt: PLocalDateTime<QItemNota>
  lateinit var version: PInteger<QItemNota>
  lateinit var data: PLocalDate<QItemNota>
  lateinit var hora: PLocalTime<QItemNota>
  lateinit var quantidade: PInteger<QItemNota>
  lateinit var produto: QAssocProduto<QItemNota>
  lateinit var nota: QAssocNota<QItemNota>
  lateinit var etiqueta: QAssocEtiqueta<QItemNota>
  lateinit var usuario: QAssocUsuario<QItemNota>


  /**
   * Construct with a given EbeanServer.
   */
  constructor(server: EbeanServer) : super(ItemNota::class.java, server)

  /**
   * Construct using the default EbeanServer.
   */
  constructor() : super(ItemNota::class.java)

  /**
   * Construct for Alias.
   */
  private constructor(dummy: Boolean) : super(dummy)
}
