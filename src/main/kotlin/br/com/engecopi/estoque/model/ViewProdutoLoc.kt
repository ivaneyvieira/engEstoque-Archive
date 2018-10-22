package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.RegistryUserInfo.abreviacaoDefault
import br.com.engecopi.estoque.model.RegistryUserInfo.lojaDefault
import br.com.engecopi.estoque.model.Repositories.viewProdutosLoc
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
@View(name = "v_loc_produtos")
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
      val abreviacao = RegistryUserInfo.abreviacaoDefault
      val lojaId = RegistryUserInfo.lojaDefault.id
      val produtoId = produto?.id ?: return false
      return Repositories.viewProdutosLoc.any {
        it.loja.id == lojaId && it.produto.id == produtoId && it.abreviacao == abreviacao
      }
    }

    fun produtos(): List<Produto> {
      val abreviacao = RegistryUserInfo.abreviacaoDefault
      val lojaId = RegistryUserInfo.lojaDefault.id

      return Repositories.viewProdutosLoc.asSequence().filter {
        it.loja.id == lojaId  && it.abreviacao == abreviacao
      }.map { it.produto }.distinct().toList()
    }

    fun find(produto: Produto?): List<ViewProdutoLoc> {
      val loja = RegistryUserInfo.lojaDefault
      produto ?: return emptyList()
      return viewProdutosLoc.filter {
        it.loja.id == loja.id && it.produto.id == produto.id
      }
    }

    fun localizacoes(abreviacao : String): List<String> {
      return viewProdutosLoc
        .asSequence()
        .filter{ it.storeno == lojaDefault.numero && it.abreviacao == abreviacao}
        .mapNotNull { it.localizacao }.distinct()
        .toList()
    }

    fun localizacoes(produto: Produto?): List<String> {
      produto?: return emptyList()
      val abreviacao = abreviacaoDefault
      val ret =  viewProdutosLoc
        .asSequence()
        .filter{ it.storeno == lojaDefault.numero && it.produto.id == produto.id && it.abreviacao == abreviacao}
        .mapNotNull { it.localizacao }
        .toList()
      return ret.distinct()
    }
  }
}
