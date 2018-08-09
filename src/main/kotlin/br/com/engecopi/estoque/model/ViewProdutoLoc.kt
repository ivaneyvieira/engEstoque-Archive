package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.finder.ViewProdutoLocFinder
import io.ebean.annotation.View
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToOne

@Entity
@View(name = "v_loc_produtos")
class ViewProdutoLoc(
        @Id
        val id: String,
        val storeno: Int,
        val codigo: String,
        val grade: String,
        val localizacao: String,
        @ManyToOne(cascade = [])
        @JoinColumn(name = "produto_id")
        val produto: Produto,
        @OneToOne(cascade = [])
        @JoinColumn(name = "loja_id")
        val loja: Loja
                    ) {
  
  companion object Find : ViewProdutoLocFinder() {
    fun exists(loja: Loja?, produto: Produto, loc: String): Boolean {
      loja ?: return false
      return where().loja.id.eq(loja.id)
              .produto.id.eq(produto.id)
              .localizacao.eq(loc)
              .findOne() != null
    }
  }
}
