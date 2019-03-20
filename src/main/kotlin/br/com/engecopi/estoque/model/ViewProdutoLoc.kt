package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.Repositories.findByAbreviacao
import br.com.engecopi.estoque.model.Repositories.findByProduto
import br.com.engecopi.estoque.model.finder.ViewProdutoLocFinder
import io.ebean.annotation.Cache
import io.ebean.annotation.View
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToOne

@Cache(enableQueryCache = false)
@Entity
@View(name = "t_loc_produtos")
class ViewProdutoLoc(@Id val id: String, val storeno: Int, val codigo: String, val grade: String,
                     val localizacao: String, val abreviacao: String,
                     @ManyToOne(cascade = [])
                     @JoinColumn(name = "produto_id") val produto: Produto,
                     @OneToOne(cascade = [])
                     @JoinColumn(name = "loja_id") val loja: Loja) {
  companion object Find: ViewProdutoLocFinder() {
    fun existsCache(produto: Produto?): Boolean {
      return Repositories.findByProduto(produto).count() > 0
    }

    fun produtosCache(): List<Produto> {
      return Repositories.findByLojaAbreviacao()
        .map {it.produto}
        .distinct()
    }

    fun findCache(produto: Produto?): List<ViewProdutoLoc> {
      produto ?: return emptyList()
      return findByProduto(produto)
    }

    fun localizacoesAbreviacaoCache(abreviacao: String): List<String> {
      return findByAbreviacao(abreviacao).map {it.localizacao}
        .distinct()
    }

    fun localizacoesProdutoCache(produto: Produto?): List<String> {
      produto ?: return emptyList()
      return findByProduto(produto).map {it.localizacao}
        .distinct()
    }

    fun localizacoesProduto(produto: Produto?)=
      where().produto.id.eq(produto?.id).findList().mapNotNull {it.localizacao}.distinct()

    fun abreviacoesProduto(produto: Produto?) =
      where().produto.id.eq(produto?.id).findList().mapNotNull {it.abreviacao}.distinct()
  }
}
