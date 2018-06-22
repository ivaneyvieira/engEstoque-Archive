package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.finder.SaldoFinder
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "saldos")
class Saldo {
  @ManyToOne
  var produto : Produto? = null
  @ManyToOne
  var loja : Loja? = null
  var quantidade : Int = 0
  
  companion object Find : SaldoFinder()
}
