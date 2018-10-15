package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.RegistryUserInfo.loja
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
    fun exists(produto: Produto?, locs: List<String>): Boolean {
      val loja = RegistryUserInfo.loja
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

    fun find(produto: Produto?): List<ViewProdutoLoc> {
      val loja = RegistryUserInfo.loja
      produto ?: return emptyList()
      return viewProdutosLoc.filter {
        it.loja.id == loja.id && it.produto.id == produto.id
      }
    }

    fun localizacoes(produto: Produto?): List<String> {
      val loja = RegistryUserInfo.loja
      produto?: return emptyList()
      return viewProdutosLoc
        .asSequence()
        .filter{it.storeno == loja.numero && it.produto.id == produto.id}
        .distinct()
        .toList()
        .mapNotNull { it.localizacao }
    }
  }
}
