package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.finder.ViewProdutoLocFinder
import br.com.engecopi.estoque.viewmodel.ProdutoVo
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
    fun exists(loja: Loja?, produto: Produto?, locs: List<String>): Boolean {
      loja ?: return false
      produto ?: return false
      return where()
               .loja.id.eq(loja.id)
               .produto.id.eq(produto.id)
               .or()
               .abreviacao.isIn(locs)
               .localizacao.isIn(locs)
               .endOr()
               .findCount() > 0
    }

    fun find(loja: Loja?, produto: Produto?): List<ViewProdutoLoc> {
      loja ?: return emptyList()
      produto ?: return emptyList()
      return viewProdutosLoc.filter {
        it.loja.id == loja.id && it.produto.id == produto.id
      }
    }

    fun findAbreviacoresLoja(loja: Loja?): List<String> {
      loja ?: return emptyList()
      return viewProdutosLoc
        .asSequence()
        .filter { it.storeno == loja.numero }
        .map { it.abreviacao }
        .distinct()
        .toList()
    }

    fun localizacoes(produto: Produto?): List<String> {
      produto?: return emptyList()
      return viewProdutosLoc
        .asSequence()
        .filter{it.storeno == RegistryUserInfo.loja.numero && it.produto.id == produto.id}
        .distinct()
        .toList()
        .mapNotNull { it.localizacao }
    }
  }
}
