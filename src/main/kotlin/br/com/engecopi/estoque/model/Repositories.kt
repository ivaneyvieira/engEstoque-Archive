package br.com.engecopi.estoque.model

val viewProdutosLoc = ViewProdutoLoc.all()

fun updateViewProdutosLoc(){
  viewProdutosLoc.clear()
  viewProdutosLoc.addAll(ViewProdutoLoc.all())
}