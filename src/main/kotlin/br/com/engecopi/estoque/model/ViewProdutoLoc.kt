package br.com.engecopi.estoque.model

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
    fun exists(loja: Loja?, produto: Produto?, locs: List<String>): Boolean {
      loja ?: return false
      produto ?: return false
      return where().loja.id.eq(loja.id)
              .produto.id.eq(produto.id)
<<<<<<< HEAD
              .or().abreviacao.isIn(locs).localizacao.isIn(locs).endOr()
=======
              .or().abreviacao.isIn(locs)
              .localizacao.isIn(locs)
              .endOr()
>>>>>>> 9921615b309041e2f7b5d78986e65ab94b1ae22a
              .findCount() > 0
    }

    fun find(usuario: Usuario, produto: Produto?): ViewProdutoLoc? {
      val loja = usuario.loja ?: return null
      produto ?: return null
      return viewProdutosLoc.firstOrNull {
        it.loja.id == loja.id && it.produto.id == produto.id
                && usuario.locais.contains(it.abreviacao)
      }
    }

    fun allAbreviacoes(): List<String> {
      return viewProdutosLoc.mapNotNull { it.abreviacao }
    }
  }
}
