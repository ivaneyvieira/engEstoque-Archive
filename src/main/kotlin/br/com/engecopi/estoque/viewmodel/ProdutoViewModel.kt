package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.Loja
import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.framework.viewmodel.ViewModel
import br.com.engecopi.saci.QuerySaci
import br.com.engecopi.saci.beans.ProdutoSaci
import io.ebean.config.JsonConfig.DateTime

class ProdutoViewModel(private val lojaDefault: Int, updateModel: (ViewModel) -> Unit) : ViewModel(updateModel) {
  var pesquisa: String = ""
  val listaGrid: MutableList<Produto> = mutableListOf()
  var grades: List<String> = emptyList()
  val produtoVo = ProdutoVo()
  val lojasSaldo: List<Loja> = exec {  Loja.lojaSaldo(lojaDefault) }.orEmpty()
  
  
  override fun execUpdate(): Unit = exec {
    listaGrid.clear()
    listaGrid.addAll(Produto.all().filter { prd ->
      prd.nome.contains(pesquisa) || prd.codigo == pesquisa
    })
  }
  
  fun updateGrades(codigo: String) = exec {
    val produtos = QuerySaci.querySaci.findProduto(codigo)
    produtoVo.codigoProduto = codigo.trim()
    produtoVo.descricaoProduto = produtos.firstOrNull()?.nome ?: "Produto não encontrado"
    
    val gradesSaci = produtos.mapNotNull { it.grade }.filter { it != "" }
    grades = if (gradesSaci.isNotEmpty())
      gradesSaci
    else
      listOf("")
  }
  
  fun saveUpdateProduto() = exec {
    val produto = Produto.find {
      (Produtos.codigo eq produtoVo.codigoProduto) and
              (Produtos.grade eq produtoVo.gradeProduto)
    }.firstOrNull()
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
  
  fun saldoProduto(prd: Produto?, loja: Int?) = transaction {
    prd?.saldos?.filter { it.loja == loja }?.map { it.quantidade }?.firstOrNull() ?: 0
  }
  
  fun validaDelete(produto: Produto): String = transaction {
    if (produto.saldos.sumBy { it.quantidade } > 0)
      "Este produto tem saldo"
    else ""
  }
  
  fun deleta(produto: Produto) = exec {
    produto.delete()
  }
  
  fun atualizaSaldo(produto: Produto) = exec {
    Saldo.lojas().forEach { loja ->
      produto.recalcula(loja)
    }
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