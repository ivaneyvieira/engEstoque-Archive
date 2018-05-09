package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.DB
import br.com.engecopi.estoque.model.Produto
import org.jetbrains.exposed.sql.transactions.transaction

class ProdutoViewModel(private val updateModel: (ProdutoViewModel) -> Unit) {
  
  var pesquisa: String = ""
  var listaGrid: List<Produto> = emptyList()
  
  fun execPesquisa() {
    DB.connect()
    transaction {
      listaGrid = Produto.all().filter { prd ->
        prd.nome.contains(pesquisa) || prd.codigo == pesquisa
      }
    }
    updateModel(this)
  }
}