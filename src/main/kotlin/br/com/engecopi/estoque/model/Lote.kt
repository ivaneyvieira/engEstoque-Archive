package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.finder.LoteFinder
import br.com.engecopi.framework.model.BaseModel
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "lotes")
class Lote: BaseModel() {
  var sequencia : Int = 0
  var total : Int = 0
  @ManyToOne
  var produto : Produto? = null
  @OneToMany(mappedBy = "lote")
  val movimentacoes: List<Movimentacao>? = null
}
