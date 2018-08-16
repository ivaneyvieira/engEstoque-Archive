package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.finder.ViewProdutoLocFinder
import io.ebean.annotation.Cache
import io.ebean.annotation.View
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToOne

@Cache(enableQueryCache = true)
@Entity
@View(name = "v_loc_produtos")
class ViewProdutoLoc(
        @Id
        val id: String,
        val storeno: Int,
        val codigo: String,
        val grade: String,
        val localizacao: String,
        val abreviacao : String,
        @ManyToOne(cascade = [])
        @JoinColumn(name = "produto_id")
        val produto: Produto,
        @OneToOne(cascade = [])
        @JoinColumn(name = "loja_id")
        val loja: Loja
                    ) {
  
  companion object Find : ViewProdutoLocFinder() {
    fun exists(loja: Loja?, produto: Produto, locs: List<String>): Boolean {
      loja ?: return false
      return where().loja.id.eq(loja.id)
              .produto.id.eq(produto.id)
              .or().abreviacao.isIn(locs).localizacao.isIn(locs).endOr()
              .findOne() != null
    }
  
    fun find(loja: Loja?, produto: Produto?): ViewProdutoLoc? {
      loja ?: return null
      produto ?: return null
      return where().loja.id.eq(loja.id)
              .produto.id.eq(produto.id)
              .findOne()
    }
  }
}
