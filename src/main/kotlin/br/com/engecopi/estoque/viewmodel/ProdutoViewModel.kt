package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.estoque.model.Produtos
import br.com.engecopi.estoque.model.Saldo
import br.com.engecopi.saci.QuerySaci
import br.com.engecopi.saci.beans.ProdutoSaci
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime

class ProdutoViewModel(val lojaDefault : Int, val updateModel: (ProdutoViewModel) -> Unit) {
  var pesquisa: String = ""
  val listaGrid: MutableList<Produto> = mutableListOf()
  var grades: List<String> = emptyList()
  val produtoVo = ProdutoVo()
  
  fun lojasSaldo() = transaction { Saldo.lojas().filter { it == lojaDefault || lojaDefault == 0 } }
  
  fun execPesquisa() {
    transaction {
      listaGrid.clear()
      listaGrid.addAll(Produto.all().filter { prd ->
        prd.nome.contains(pesquisa) || prd.codigo == pesquisa
      })
    }
    updateModel(this)
  }
  
  fun updateGrades(codigo: String) {
    val produtos = QuerySaci.querySaci.findProduto(codigo)
    produtoVo.codigoProduto = codigo.trim()
    produtoVo.descricaoProduto = produtos.firstOrNull()?.nome ?: "Produto não encontrado"
    
    val gradesSaci = produtos.mapNotNull { it.grade }.filter { it != "" }
    grades = if (gradesSaci.isNotEmpty())
      gradesSaci
    else
      listOf("")
    updateModel(this)
  }
  
  fun saveUpdateProduto() {
    transaction {
      val produto = Produto.find { (Produtos.codigo eq produtoVo.codigoProduto) and
              (Produtos.grade eq produtoVo.gradeProduto) }.firstOrNull()
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
  
  fun saldoProduto(prd: Produto?, loja: Int?) = transaction {
    prd?.saldos?.filter { it.loja == loja }?.map { it.quantidade }?.firstOrNull() ?: 0
  }
  
  fun validaDelete(produto: Produto): String = transaction {
    if (produto.saldos.sumBy { it.quantidade } > 0)
      "Este produto tem saldo"
    else ""
  }
  
  fun deleta(produto: Produto) = transaction {
    produto.delete()
    execPesquisa()
  }
  
  fun atualizaSaldo(produto: Produto) = transaction{
    Saldo.lojas().forEach {loja->
      produto.recalcula(loja)
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