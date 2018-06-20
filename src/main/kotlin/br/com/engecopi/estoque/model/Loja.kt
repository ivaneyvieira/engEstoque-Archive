package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.finder.LojaFinder
import br.com.engecopi.framework.model.BaseModel
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "lojas")
class Loja : BaseModel() {
  var numero: Int = 0
  @OneToMany(mappedBy = "loja")
  val notas: List<Nota>? = null
  @OneToMany(mappedBy = "loja")
  val usuarios: List<Usuario>? = null
  
  companion object Find : LojaFinder() {
    fun findLoja(storeno: Int?): Loja? {
      TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
  
    fun lojaSaldo(lojaDefault: Int): List<Loja> {
      TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
  }
  
}
