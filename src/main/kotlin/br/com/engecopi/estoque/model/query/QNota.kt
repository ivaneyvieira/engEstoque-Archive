package br.com.engecopi.estoque.model.query

import br.com.engecopi.estoque.model.Nota
import br.com.engecopi.estoque.model.TipoMov
import br.com.engecopi.estoque.model.TipoNota
import br.com.engecopi.estoque.model.query.assoc.QAssocItemNota
import br.com.engecopi.estoque.model.query.assoc.QAssocLoja
import io.ebean.EbeanServer
import io.ebean.typequery.PEnum
import io.ebean.typequery.PInteger
import io.ebean.typequery.PLocalDate
import io.ebean.typequery.PLocalDateTime
import io.ebean.typequery.PLocalTime
import io.ebean.typequery.PLong
import io.ebean.typequery.PString
import io.ebean.typequery.TQRootBean
import io.ebean.typequery.TypeQueryBean

/**
 * Query bean for Nota.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@TypeQueryBean
class QNota : TQRootBean<Nota, QNota> {

  companion object {
    /**
     * shared 'Alias' instance used to provide
     * properties to select and fetch clauses
     */
    val _alias = QNota(true)
  }

  lateinit var id: PLong<QNota>
  lateinit var createdAt: PLocalDateTime<QNota>
  lateinit var updatedAt: PLocalDateTime<QNota>
  lateinit var version: PInteger<QNota>
  lateinit var numero: PString<QNota>
  lateinit var tipoMov: PEnum<QNota,TipoMov>
  lateinit var tipoNota: PEnum<QNota,TipoNota>
  lateinit var rota: PString<QNota>
  lateinit var data: PLocalDate<QNota>
  lateinit var hora: PLocalTime<QNota>
  lateinit var observacao: PString<QNota>
  lateinit var loja: QAssocLoja<QNota>
  lateinit var itensNota: QAssocItemNota<QNota>


  /**
   * Construct with a given EbeanServer.
   */
  constructor(server: EbeanServer) : super(Nota::class.java, server)

  /**
   * Construct using the default EbeanServer.
   */
  constructor() : super(Nota::class.java)

  /**
   * Construct for Alias.
   */
  private constructor(dummy: Boolean) : super(dummy)
}
