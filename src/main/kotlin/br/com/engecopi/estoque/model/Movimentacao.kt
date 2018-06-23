package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.finder.MovimentacaoFinder
import br.com.engecopi.framework.model.BaseModel
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "movimentacoes")
class Movimentacao : BaseModel() {
  var quantidade: Int = 0
  var saldo: Int = 0
  @ManyToOne
  var lote: Lote? = null
  @ManyToOne
  var itemNota: ItemNota? = null

  companion object Find : MovimentacaoFinder()
}
