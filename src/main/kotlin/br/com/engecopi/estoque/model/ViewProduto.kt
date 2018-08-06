package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.finder.ViewProdutoFinder
import br.com.engecopi.estoque.model.query.QViewProduto
import io.ebean.Model
import io.ebean.Model.db
import io.ebean.annotation.Cache
import io.ebean.annotation.View
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne

@Cache(enableQueryCache = true)
@Entity
@View(name = "view_produtos", dependentTables = ["produtos"])
class ViewProduto {

  @Id
  @Column(name = "produto_id")
  var id: Long? = null
  
  var codigo: String? = null
  var nome: String? = null
  var grade: String? = null
  var codebar: String? = null
  var custo: Double? = null
  var unidade: String? = null
  var tipo: String? = null
  
  @OneToOne(cascade = [])
  @JoinColumn(name = "produto_id")
  var produto: Produto? = null
  
  companion object Find : ViewProdutoFinder()
}
