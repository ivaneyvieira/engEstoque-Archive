package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.DB
import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.estoque.model.Produtos
import br.com.engecopi.saci.QuerySaci
import br.com.engecopi.saci.beans.ProdutoSaci
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime

class ProdutoViewModel(private val updateModel: (ProdutoViewModel) -> Unit) {
  val todasGrades = "TODAS"
  var pesquisa: String = ""
  var listaGrid: List<Produto> = emptyList()
  var grades: List<String> = emptyList()
  val produtoVo = ProdutoVo()
  
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
    produtoVo.codigoProduto = codigo.trim()
    produtoVo.descricaoProduto = produtos.firstOrNull()?.nome ?: "Produto não encontrado"
    
    val gradesSaci = produtos.mapNotNull { it.grade }.filter { it != "" }
    grades = if (gradesSaci.isNotEmpty())
      listOf(todasGrades) + gradesSaci
    else
      listOf("")
    updateModel(this)
  }
  
  fun saveUpdateProduto() {
    transaction {
      val produto = Produto.find { Produtos.codigo eq produtoVo.codigoProduto }.firstOrNull()
      if (produto == null) {
        Produto.new {
          codigo = produtoVo.codigoProduto
          grade = produtoVo.gradeProduto
          codebar = produtoVo.codebar
          data_cadastro = DateTime.now()
        }
      } else {
        produto.grade = produtoVo.gradeProduto
        produto.codebar = produtoVo.codebar
      }
    }
    execPesquisa()
  }
  
  data class ProdutoVo(
          var codigoProduto: String = "",
          var gradeProduto: String = "",
          var descricaoProduto: String = "",
          var produtos: List<ProdutoSaci> = emptyList()
                      ) {
    val codebar: String
      get() = produtos.firstOrNull { it.grade == gradeProduto }?.codebar ?: ""
  }
}