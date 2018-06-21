package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.Loja
import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.framework.viewmodel.ViewModel
import br.com.engecopi.saci.QuerySaci
import br.com.engecopi.saci.beans.ProdutoSaci
import com.vaadin.ui.Notification.Type

class ProdutoViewModel(private val lojaDefault: Int, updateModel: (ViewModel) -> Unit) : ViewModel(updateModel) {
  var pesquisa: String = ""
  val listaGrid: MutableList<Produto> = mutableListOf()
  var grades: List<String> = emptyList()
  val produtoVo = ProdutoVo()
  val lojasSaldo: List<Loja> = execList { Loja.lojaSaldo(lojaDefault) }
  
  override fun execUpdate() = exec {
    listaGrid.clear()
    listaGrid.addAll(Produto.all().filter { prd ->
      val descricao = prd.descricao ?: ""
      descricao.contains(pesquisa) || prd.codigo == pesquisa
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
    val produto = Produto.findProduto(produtoVo.codigoProduto, produtoVo.gradeProduto)
    if (produto == null) {
      Produto().apply {
        codigo = produtoVo.codigoProduto
        grade = produtoVo.gradeProduto
        codebar = produtoVo.codebar
      }
    } else {
      produto.grade = produtoVo.gradeProduto
      produto.codebar = produtoVo.codebar
    }
  }
  
  fun saldoProduto(prd: Produto, loja: Loja) = execValue {
    prd.saldoLoja(loja)
  }
  
  fun validaDelete(produto: Produto): String = execValue {
    if (produto.saldoTotal() > 0)
      "Este produto tem saldo"
    else ""
  } ?: ""
  
  fun deleta(produto: Produto) = exec {
    produto.delete()
  }
  
  fun atualizaSaldo(produto: Produto) {
    produto.atualizaSaldo()
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