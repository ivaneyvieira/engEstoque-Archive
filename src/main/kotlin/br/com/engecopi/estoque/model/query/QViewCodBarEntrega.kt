package br.com.engecopi.estoque.model.query

import br.com.engecopi.estoque.model.ViewCodBarEntrega
import io.ebean.EbeanServer
import io.ebean.typequery.PLong
import io.ebean.typequery.PString
import io.ebean.typequery.TQRootBean
import io.ebean.typequery.TypeQueryBean

/**
 * Query bean for ViewCodBarEntrega.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@TypeQueryBean
class QViewCodBarEntrega : TQRootBean<ViewCodBarEntrega, QViewCodBarEntrega> {

  companion object {
    /**
     * shared 'Alias' instance used to provide
     * properties to select and fetch clauses
     */
    val _alias = QViewCodBarEntrega(true)
  }

  lateinit var id: PLong<QViewCodBarEntrega>
  lateinit var codbar: PString<QViewCodBarEntrega>


  /**
   * Construct with a given EbeanServer.
   */
  constructor(server: EbeanServer) : super(ViewCodBarEntrega::class.java, server)

  /**
   * Construct using the default EbeanServer.
   */
  constructor() : super(ViewCodBarEntrega::class.java)

  /**
   * Construct for Alias.
   */
  private constructor(dummy: Boolean) : super(dummy)
}
