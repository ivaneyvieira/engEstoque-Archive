package br.com.engecopi.estoque.model.query

import br.com.engecopi.estoque.model.Usuario
import br.com.engecopi.estoque.model.query.assoc.QAssocLoja
import io.ebean.EbeanServer
import io.ebean.typequery.PInteger
import io.ebean.typequery.PLocalDateTime
import io.ebean.typequery.PLong
import io.ebean.typequery.TQRootBean
import io.ebean.typequery.TypeQueryBean

/**
 * Query bean for Usuario.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@TypeQueryBean
class QUsuario : TQRootBean<Usuario, QUsuario> {

  companion object {
    /**
     * shared 'Alias' instance used to provide
     * properties to select and fetch clauses
     */
    val _alias = QUsuario(true)
  }

  lateinit var id: PLong<QUsuario>
  lateinit var createdAt: PLocalDateTime<QUsuario>
  lateinit var updatedAt: PLocalDateTime<QUsuario>
  lateinit var version: PInteger<QUsuario>
  lateinit var numero: PInteger<QUsuario>
  lateinit var loja: QAssocLoja<QUsuario>


  /**
   * Construct with a given EbeanServer.
   */
  constructor(server: EbeanServer) : super(Usuario::class.java, server)

  /**
   * Construct using the default EbeanServer.
   */
  constructor() : super(Usuario::class.java)

  /**
   * Construct for Alias.
   */
  private constructor(dummy: Boolean) : super(dummy)
}