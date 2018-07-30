package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.finder.UsuarioFinder
import br.com.engecopi.framework.model.BaseModel
import br.com.engecopi.saci.saci
import io.ebean.annotation.Index
import javax.persistence.CascadeType.MERGE
import javax.persistence.CascadeType.PERSIST
import javax.persistence.CascadeType.REFRESH
import javax.persistence.CascadeType.REMOVE
import javax.persistence.Entity
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne
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
  @Size(max = 60)
  var localizacao: String? = ""
  @ManyToOne(cascade = [PERSIST, MERGE, REFRESH])
  var loja: Loja? = null
  @ManyToMany(cascade = [PERSIST, MERGE, REFRESH, REMOVE])
  @JoinTable(name = "usuarios_produtos")
  var produtos: MutableList<Produto>? = null
  
  fun usuarioSaci() = saci.findUser(loginName)
  
  val nome: String?
    @Transient get() = usuarioSaci()?.name
  
  val isAdmin
    @Transient get() = loginName == "ADM" || loginName == "YASMINE"
  
  fun temProduto(codigo: String?, grade: String?, localizacao: String? = null): Boolean {
    return if (codigo.isNullOrEmpty() || grade.isNullOrEmpty())
      false
    else {
      isAdmin
      || (this.localizacao == localizacao && !localizacao.isNullOrEmpty())
      || produtos.orEmpty().any { it.codigo == codigo && it.grade == grade }
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
