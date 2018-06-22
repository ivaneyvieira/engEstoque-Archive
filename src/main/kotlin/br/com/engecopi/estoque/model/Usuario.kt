package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.finder.UsuarioFinder
import br.com.engecopi.framework.model.BaseModel
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "usuarios")
class Usuario: BaseModel() {
  var numero : Int = 0
  @ManyToOne
  var loja : Loja? = null
  
  companion object Find : UsuarioFinder()
}
