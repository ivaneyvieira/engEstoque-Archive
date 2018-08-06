package br.com.engecopi.estoque.model.query

import br.com.engecopi.estoque.model.ViewProduto
import br.com.engecopi.estoque.model.query.assoc.QAssocProduto
import io.ebean.EbeanServer
import io.ebean.typequery.PDouble
import io.ebean.typequery.PLong
import io.ebean.typequery.PString
import io.ebean.typequery.TQRootBean
import io.ebean.typequery.TypeQueryBean

/**
 * Query bean for ViewProduto.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@TypeQueryBean
class QViewProduto : TQRootBean<ViewProduto, QViewProduto> {

  companion object {
    /**
     * shared 'Alias' instance used to provide
     * properties to select and fetch clauses
     */
    val _alias = QViewProduto(true)
  }

  lateinit var id: PLong<QViewProduto>
  lateinit var codigo: PString<QViewProduto>
  lateinit var nome: PString<QViewProduto>
  lateinit var grade: PString<QViewProduto>
  lateinit var codebar: PString<QViewProduto>
  lateinit var custo: PDouble<QViewProduto>
  lateinit var unidade: PString<QViewProduto>
  lateinit var tipo: PString<QViewProduto>
  lateinit var produto: QAssocProduto<QViewProduto>


  /**
   * Construct with a given EbeanServer.
   */
  constructor(server: EbeanServer) : super(ViewProduto::class.java, server)

  /**
   * Construct using the default EbeanServer.
   */
  constructor() : super(ViewProduto::class.java)

  /**
   * Construct for Alias.
   */
  private constructor(dummy: Boolean) : super(dummy)
}
