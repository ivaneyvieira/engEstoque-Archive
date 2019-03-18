package br.com.engecopi.estoque.model.query

import br.com.engecopi.estoque.model.ViewCodBarCliente
import io.ebean.EbeanServer
import io.ebean.typequery.PInteger
import io.ebean.typequery.PLong
import io.ebean.typequery.PString
import io.ebean.typequery.TQRootBean
import io.ebean.typequery.TypeQueryBean

/**
 * Query bean for ViewCodBarCliente.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@TypeQueryBean
class QViewCodBarCliente : TQRootBean<ViewCodBarCliente, QViewCodBarCliente> {

  companion object {
    /**
     * shared 'Alias' instance used to provide
     * properties to select and fetch clauses
     */
    val _alias = QViewCodBarCliente(true)
  }

  lateinit var id: PLong<QViewCodBarCliente>
  lateinit var codbar: PString<QViewCodBarCliente>
  lateinit var storeno: PInteger<QViewCodBarCliente>
  lateinit var numero: PString<QViewCodBarCliente>
  lateinit var sequencia: PInteger<QViewCodBarCliente>


  /**
   * Construct with a given EbeanServer.
   */
  constructor(server: EbeanServer) : super(ViewCodBarCliente::class.java, server)

  /**
   * Construct using the default EbeanServer.
   */
  constructor() : super(ViewCodBarCliente::class.java)

  /**
   * Construct for Alias.
   */
  private constructor(dummy: Boolean) : super(dummy)
}
