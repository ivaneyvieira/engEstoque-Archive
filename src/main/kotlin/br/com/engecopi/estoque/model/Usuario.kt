package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.finder.UsuarioFinder
import br.com.engecopi.framework.model.BaseModel
import br.com.engecopi.saci.QuerySaci
import javax.persistence.CascadeType.ALL
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.persistence.Transient
import javax.validation.constraints.Size

@Entity
@Table(name = "usuarios")
class Usuario : BaseModel() {
  @Size(max = 8)
  var loginName: String = ""
  @ManyToOne(cascade = [ALL])
  var loja: Loja? = null
  
  fun usuarioSaci() = QuerySaci.querySaci.findUser(loginName)
  
  val nome: String?
    @Transient get() = usuarioSaci()?.name
  
  companion object Find : UsuarioFinder() {
    fun findUsuario(loginName: String): Usuario? {
      return where().loginName.eq(loginName).findOne()
    }
    
    fun nomeSaci(value: String): String {
      return QuerySaci.querySaci.findUser(value)?.name ?: ""
    }
  }
}
