package br.com.engecopi.estoque.model.query

import br.com.engecopi.estoque.model.Label
import br.com.engecopi.estoque.model.TipoProduto
import io.ebean.EbeanServer
import io.ebean.typequery.PEnum
import io.ebean.typequery.PInteger
import io.ebean.typequery.PLocalDateTime
import io.ebean.typequery.PLong
import io.ebean.typequery.PString
import io.ebean.typequery.TQRootBean
import io.ebean.typequery.TypeQueryBean

/**
 * Query bean for Label.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@TypeQueryBean
class QLabel : TQRootBean<Label, QLabel> {

  companion object {
    /**
     * shared 'Alias' instance used to provide
     * properties to select and fetch clauses
     */
    val _alias = QLabel(true)
  }

  lateinit var id: PLong<QLabel>
  lateinit var createdAt: PLocalDateTime<QLabel>
  lateinit var updatedAt: PLocalDateTime<QLabel>
  lateinit var version: PInteger<QLabel>
  lateinit var tipo: PEnum<QLabel,TipoProduto>
  lateinit var layoutEntrada: PString<QLabel>
  lateinit var layoutSaida: PString<QLabel>


  /**
   * Construct with a given EbeanServer.
   */
  constructor(server: EbeanServer) : super(Label::class.java, server)

  /**
   * Construct using the default EbeanServer.
   */
  constructor() : super(Label::class.java)

  /**
   * Construct for Alias.
   */
  private constructor(dummy: Boolean) : super(dummy)
}
