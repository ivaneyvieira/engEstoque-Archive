package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.finder.UsuarioFinder
import br.com.engecopi.framework.model.BaseModel
import br.com.engecopi.saci.saci
import io.ebean.annotation.Formula
import io.ebean.annotation.Index
import javax.persistence.CascadeType.MERGE
import javax.persistence.CascadeType.PERSIST
import javax.persistence.CascadeType.REFRESH
import javax.persistence.Entity
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
  
  fun usuarioSaci() = saci.findUser(loginName)
  
  val nome: String?
    @Transient get() = usuarioSaci()?.name
  
  @Formula(select = "(login_name = 'ADM' OR login_name = 'YASMINE')")
  var isAdmin: Boolean = false
  
  fun temProduto(produto: Produto?): Boolean {
    if(isAdmin || this.localizacao.isNullOrBlank()) return true
    produto?: return false
    return saci.findLoc(storeno = loja?.numero,
                        prdno = produto.codigo,
                        grade = produto.grade)
            .isNotEmpty()
  }
  
  val produtoLoc: List<Produto>
    get() {
      loja ?: return emptyList()
      localizacao ?: return emptyList()
      return saci.findLoc(loja?.numero, localizacao)
              .mapNotNull { prdloc ->
                Produto.findProduto(prdloc.codigo, prdloc.grade) ?: Produto.createProduto(prdloc.codigo, prdloc.grade)
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

