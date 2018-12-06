package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.RegistryUserInfo.abreviacaoDefault
import br.com.engecopi.estoque.model.RegistryUserInfo.lojaDefault
import io.ebean.Ebean
import java.time.LocalTime
import io.ebean.Ebean.getServerCacheManager
import io.ebean.cache.ServerCacheManager

object Repositories {
  private var time = 0
  private lateinit var viewProdutosLocProdutoKey: Map<ProdutoKey, List<ViewProdutoLoc>>
  private lateinit var viewProdutosLocLojaAbreviacaoKey: Map<LojaAbreviacaoKey, List<ViewProdutoLoc>>
  private lateinit var viewProdutosLocLojaKey: Map<Int, List<ViewProdutoLoc>>
  private lateinit var viewProdutosLocAbreviacaoKey: Map<String, List<ViewProdutoLoc>>

  init {
    newViewProdutosLoc()
  }

  fun updateViewProdutosLoc() {
    newViewProdutosLoc()
  }

  private fun newViewProdutosLoc() {
    val agora = LocalTime.now().toSecondOfDay()
    if ((agora - time) > 5) {
      val serverCacheManager = Ebean.getServerCacheManager()
      serverCacheManager.clear(ViewProdutoLoc::class.java)
      val list = ViewProdutoLoc.all()
      viewProdutosLocProdutoKey = list.groupBy { ProdutoKey(it.produto.id, it.storeno, it.abreviacao) }
      viewProdutosLocLojaAbreviacaoKey = list.groupBy { LojaAbreviacaoKey(it.storeno, it.abreviacao) }
      viewProdutosLocLojaKey = list.groupBy { it.storeno }
      viewProdutosLocAbreviacaoKey = list.groupBy { it.abreviacao }

      time = LocalTime.now().toSecondOfDay()
    }
  }

  fun findByProduto(produto: Produto?): List<ViewProdutoLoc> {
    produto ?: return emptyList()
    return viewProdutosLocProdutoKey[ProdutoKey(produtoId = produto.id, storeno = lojaDefault.numero,
                                                abreviacao = abreviacaoDefault)].orEmpty()
  }

  fun findByLoja(loja: Loja?): List<ViewProdutoLoc> {
    loja ?: return emptyList()
    return viewProdutosLocLojaKey[loja.numero]
      .orEmpty()
  }

  fun findByAbreviacao(abreviacao: String): List<ViewProdutoLoc> {
    return viewProdutosLocAbreviacaoKey[abreviacao]
      .orEmpty()
  }

  fun findByLojaAbreviacao(storeno: Int = RegistryUserInfo.lojaDefault.numero,
                           abreviacao: String = RegistryUserInfo.abreviacaoDefault): List<ViewProdutoLoc> {
    return viewProdutosLocLojaAbreviacaoKey[LojaAbreviacaoKey(storeno = storeno, abreviacao = abreviacao)].orEmpty()
  }
}

data class ProdutoKey(
  val produtoId: Long,
  val storeno: Int,
  val abreviacao: String
                     )

data class LojaAbreviacaoKey(
  val storeno: Int,
  val abreviacao: String
                            )