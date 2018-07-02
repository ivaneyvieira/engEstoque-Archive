package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.finder.LoteFinder
import br.com.engecopi.framework.model.BaseModel
import io.ebean.annotation.Index
import javax.persistence.CascadeType.ALL
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "lotes")
class Lote : BaseModel() {
  @Index(unique=true)
  var sequencia: Int = 0
  var total: Int = 0
  @ManyToOne(cascade = [ALL])
  var produto: Produto? = null
  @ManyToOne(cascade = [ALL])
  var loja: Loja? = null
  @OneToMany(mappedBy = "lote", cascade = [ALL])
  val movimentacoes: List<Movimentacao>? = null
  
  companion object Find : LoteFinder()
}
