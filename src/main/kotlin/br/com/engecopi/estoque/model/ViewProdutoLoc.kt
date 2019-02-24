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
class ViewProdutoLoc(
  @Id
  val id: String,
  val storeno: Int,
  val codigo: String,
  val grade: String,
  val localizacao: String,
  val abreviacao: String,
  @ManyToOne(cascade = [])
  @JoinColumn(name = "produto_id")
  val produto: Produto,
  @OneToOne(cascade = [])
  @JoinColumn(name = "loja_id")
  val loja: Loja
                    ) {
  companion object Find : ViewProdutoLocFinder() {
    fun exists(produto: Produto?): Boolean {
      return Repositories.findByProduto(produto).count() > 0
    }

    fun produtos(): List<Produto> {
      return Repositories.findByLojaAbreviacao()
        .asSequence()
        .map { it.produto }
        .distinct()
        .toList()
    }

    fun find(produto: Produto?): List<ViewProdutoLoc> {
      produto ?: return emptyList()
      return findByProduto(produto)
    }

    fun localizacoes(abreviacao: String): List<String> {
      return findByAbreviacao(abreviacao)
        .asSequence()
        .mapNotNull { it.localizacao }.distinct()
        .toList()
    }

    fun localizacoes(produto: Produto?): List<String> {
      produto ?: return emptyList()
      return findByProduto(produto)
        .asSequence()
        .mapNotNull { it.localizacao }.distinct()
        .toList()
    }
  }
}
