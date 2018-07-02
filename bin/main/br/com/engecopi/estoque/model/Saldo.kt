package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.finder.SaldoFinder
import br.com.engecopi.framework.model.BaseModel
import io.ebean.annotation.Index
import javax.persistence.CascadeType.ALL
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "saldos")
@Index(unique=true, columnNames = ["produto_id", "loja_id"])
class Saldo : BaseModel() {
  @ManyToOne(cascade = [ALL])
  var produto: Produto? = null
  @ManyToOne(cascade = [ALL])
  var loja: Loja? = null
  var quantidade: Int = 0
  
  companion object Find : SaldoFinder()
}
