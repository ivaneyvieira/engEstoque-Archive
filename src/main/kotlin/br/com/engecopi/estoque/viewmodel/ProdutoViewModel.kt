package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.DB
import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.estoque.model.Produtos
import br.com.engecopi.saci.QuerySaci
import org.jetbrains.exposed.sql.transactions.transaction

class ProdutoViewModel(private val updateModel: (ProdutoViewModel) -> Unit) {
  val todasGrades = "TODAS"
  var pesquisa: String = ""
  var listaGrid: List<Produto> = emptyList()
  var grades: List<String> = emptyList()
  var descricaoProduto = ""
  
  fun execPesquisa() {
    DB.connect()
    transaction {
      listaGrid = Produto.all().filter { prd ->
        prd.nome.contains(pesquisa) || prd.codigo == pesquisa
      }
    }
    updateModel(this)
  }
  
  fun updateGrades(codigo: String) {
    val produtos = QuerySaci.querySaci.findProduto(codigo)
    descricaoProduto = produtos.firstOrNull()?.nome ?: ""
    val gradesSaci = produtos.mapNotNull { it.grade }
    if (gradesSaci.isNotEmpty())
      grades = listOf(todasGrades) + gradesSaci
    updateModel(this)
  }
  
  fun saveUpdateProduto(produto : Produto){
    produto.codigo = ""
    Produto.new{
    
    }
  }
  
}