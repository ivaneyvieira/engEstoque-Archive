package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.finder.UsuarioFinder
import br.com.engecopi.estoque.ui.EstoqueUI.Companion.loja
import br.com.engecopi.framework.model.BaseModel
import br.com.engecopi.saci.saci
import io.ebean.annotation.Formula
import io.ebean.annotation.Index
import io.ebean.annotation.Length
import javax.persistence.CascadeType.MERGE
import javax.persistence.CascadeType.PERSIST
import javax.persistence.CascadeType.REFRESH
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.persistence.Transient
import javax.validation.constraints.Size

@Entity
@Table(name = "usuarios")
class Usuario : BaseModel() {
  @Size(max = 8)
  @Index(unique = true)
  var loginName: String = ""
  @Size(max = 30)
  var impressora: String = ""
  @ManyToOne(cascade = [PERSIST, MERGE, REFRESH])
  var loja: Loja? = null
  @Length(4000)
  var localizacaoes: String = ""
  @OneToMany(
    mappedBy = "usuario",
    cascade = [PERSIST, MERGE, REFRESH]
  )
  val itensNota: List<ItemNota>? = null
  var locais: List<String>
    get() = if (admin)
      ViewProdutoLoc.allAbreviacoes()
    else
      localizacaoes.split(",").asSequence().filter { it.isNotBlank() }.map { it.trim() }.toList()
    set(value) {
      if (!admin)
        localizacaoes = value.asSequence().sorted().joinToString()
    }

  fun usuarioSaci() = saci.findUser(loginName)

  val nome: String?
    @Transient get() = usuarioSaci()?.name
  @Formula(select = "(login_name = 'ADM' OR login_name = 'YASMINE')")
  var admin: Boolean = false

  fun temProduto(produto: Produto?): Boolean {
    if (admin || this.locais.isEmpty()) return true
    return ViewProdutoLoc.exists(loja, produto, locais)
  }

  val produtoLoc: List<Produto>
    get() {
      return locais.flatMap { loc ->
        ViewProdutoLoc.where()
                .loja.id.eq(loja?.id)
                .or().abreviacao.eq(loc).localizacao.eq(loc).endOr()
                .findList()
                .map { it.produto }
      }
    }

  companion object Find : UsuarioFinder() {
    fun findUsuario(loginName: String?): Usuario? {
      if (loginName.isNullOrBlank()) return null
      return where().loginName.eq(loginName).findOne()
    }

    fun nomeSaci(value: String): String {
      return saci.findUser(value)?.name ?: ""
    }
  }
}

