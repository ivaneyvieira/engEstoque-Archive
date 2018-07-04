package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.finder.MovimentacaoFinder
import br.com.engecopi.framework.model.BaseModel
import io.ebean.annotation.Index
import javax.persistence.CascadeType.ALL
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "movimentacoes")
@Index(unique=true, columnNames = ["lote_id", "item_nota_id"])
class Movimentacao : BaseModel() {
  var quantidade: Int = 0
  @ManyToOne(cascade = [ALL])
  var lote: Lote? = null
  @ManyToOne(cascade = [ALL])
  var itemNota: ItemNota? = null

  companion object Find : MovimentacaoFinder()
}
