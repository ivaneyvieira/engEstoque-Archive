package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.RegistryUserInfo.abreviacaoDefault
import br.com.engecopi.estoque.model.RegistryUserInfo.lojaDefault

object Repositories {
  private var viewProdutosLoc = newViewProdutosLoc()
  private lateinit var viewProdutosLocProdutoKey: Map<ProdutoKey, List<ViewProdutoLoc>>
  private lateinit var viewProdutosLocLojaAbreviacaoKey: Map<LojaAbreviacaoKey, List<ViewProdutoLoc>>
  private lateinit var viewProdutosLocLojaKey: Map<Int, List<ViewProdutoLoc>>
  private lateinit var viewProdutosLocAbreviacaoKey: Map<String, List<ViewProdutoLoc>>

  fun updateViewProdutosLoc() {
    viewProdutosLoc = newViewProdutosLoc()
  }


  private fun newViewProdutosLoc(): List<ViewProdutoLoc> {
    val list = ViewProdutoLoc.all()
    viewProdutosLocProdutoKey = list.groupBy { ProdutoKey(it.produto.id, it.storeno, it.abreviacao) }
    viewProdutosLocLojaAbreviacaoKey = list.groupBy { LojaAbreviacaoKey(it.storeno, it.abreviacao) }
    viewProdutosLocLojaKey = list.groupBy { it.storeno }
    viewProdutosLocAbreviacaoKey = list.groupBy { it.abreviacao }
    return list
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