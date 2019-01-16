package br.com.engecopi.estoque.model.query

import br.com.engecopi.estoque.model.ViewCodBarConferencia
import io.ebean.EbeanServer
import io.ebean.typequery.PLong
import io.ebean.typequery.PString
import io.ebean.typequery.TQRootBean
import io.ebean.typequery.TypeQueryBean

/**
 * Query bean for ViewCodBarConferencia.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@TypeQueryBean
class QViewCodBarConferencia : TQRootBean<ViewCodBarConferencia, QViewCodBarConferencia> {

  companion object {
    /**
     * shared 'Alias' instance used to provide
     * properties to select and fetch clauses
     */
    val _alias = QViewCodBarConferencia(true)
  }

  lateinit var id: PLong<QViewCodBarConferencia>
  lateinit var codbar: PString<QViewCodBarConferencia>


  /**
   * Construct with a given EbeanServer.
   */
  constructor(server: EbeanServer) : super(ViewCodBarConferencia::class.java, server)

  /**
   * Construct using the default EbeanServer.
   */
  constructor() : super(ViewCodBarConferencia::class.java)

  /**
   * Construct for Alias.
   */
  private constructor(dummy: Boolean) : super(dummy)
}
