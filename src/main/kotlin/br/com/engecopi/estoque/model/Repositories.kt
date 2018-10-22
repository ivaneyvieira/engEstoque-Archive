package br.com.engecopi.estoque.model

object Repositories {
  var viewProdutosLoc = newViewProdutosLoc()

  fun updateViewProdutosLoc() {
    viewProdutosLoc = newViewProdutosLoc()
  }

  private fun newViewProdutosLoc(): List<ViewProdutoLoc> {
    return ViewProdutoLoc.all().toList()
  }
}