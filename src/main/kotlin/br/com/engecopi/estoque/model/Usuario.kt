package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.finder.UsuarioFinder
import br.com.engecopi.framework.model.BaseModel
import br.com.engecopi.saci.saci
import io.ebean.annotation.DbArray
import io.ebean.annotation.Formula
import io.ebean.annotation.Index
import io.ebean.annotation.Length
import javax.persistence.CascadeType
import javax.persistence.CascadeType.MERGE
import javax.persistence.CascadeType.REMOVE
import javax.persistence.CascadeType.PERSIST
import javax.persistence.CascadeType.REFRESH
import javax.persistence.Entity
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
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
  @Length(100)
  var localizacaoes: String = ""
  @OneToMany(mappedBy = "usuario",
             cascade = [PERSIST, MERGE, REFRESH])
  val itensNota: List<ItemNota>? = null
  
  var locais: List<String>
    get() = localizacaoes.split(",").filter { it.isNotBlank() }.map { it.trim() }.toList()
    set(value) {
      localizacaoes = value.sorted().joinToString()
    }
  
  fun usuarioSaci() = saci.findUser(loginName)
  
  val nome: String?
    @Transient get() = usuarioSaci()?.name
  
  @Formula(select = "(login_name = 'ADM' OR login_name = 'YASMINE')")
  var isAdmin: Boolean = false
  
  fun temProduto(produto: Produto?): Boolean {
    produto ?: return false
    if (isAdmin || this.locais.isEmpty()) return true
    return locais.any { loc -> ViewProdutoLoc.exists(loja, produto, loc) }
  }
  
  val produtoLoc: List<Produto>
    get() {
      return locais.flatMap { loc ->
        ViewProdutoLoc.where()
                .loja.id.eq(loja?.id)
                .localizacao.eq(loc)
                .findList()
                .map { it.produto }
      }
    }
  
  companion object Find : UsuarioFinder() {
    fun findUsuario(loginName: String): Usuario? {
      return where().loginName.eq(loginName).findOne()
    }
    
    fun nomeSaci(value: String): String {
      return saci.findUser(value)?.name ?: ""
    }
  }
}

