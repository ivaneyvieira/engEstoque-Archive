package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.finder.ItemNotaFinder
import br.com.engecopi.framework.model.BaseModel
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.persistence.Transient

@Entity
@Table(name = "itens_nota")
class ItemNota : BaseModel() {

  companion object Find : ItemNotaFinder()
  var quantidade: Int = 0
  @ManyToOne
  var produto: Produto? = null
  @ManyToOne
  var nota: Nota? = null
  @OneToMany(mappedBy = "itemNota")
  var movimentacoes: List<Movimentacao>? = null
  
  val nome: String?
    @Transient get() = produto?.nome
  
  val codigo: String?
    @Transient get() = produto?.codigo
  
  val grade: String?
    @Transient get() = produto?.grade
}