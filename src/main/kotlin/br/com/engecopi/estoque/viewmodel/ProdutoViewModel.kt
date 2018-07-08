package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.Loja
import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.estoque.model.TipoProduto
import br.com.engecopi.estoque.model.TipoProduto.PECA
import br.com.engecopi.framework.viewmodel.IView
import br.com.engecopi.framework.viewmodel.ViewModel
import br.com.engecopi.saci.beans.ProdutoSaci
import br.com.engecopi.saci.saci

class ProdutoViewModel(private val lojaDefault: Int, view: IView) : ViewModel(view) {
  var pesquisa: String = ""
  val listaGrid: MutableList<Produto> = mutableListOf()
  var grades: List<String> = emptyList()
  val produtoVo = ProdutoVo()
  val lojasSaldo: List<Loja> = execList { Loja.lojaSaldo(lojaDefault) }
  
  override fun execUpdate() = exec {
    listaGrid.clear()
    listaGrid.addAll(Produto.where().findList().filter { prd ->
      val descricao = prd.descricao ?: ""
      descricao.contains(pesquisa) || prd.codigo == pesquisa
    })
  }
  
  fun updateGrades(codigo: String) = exec {
    val produtos = saci.findProduto(codigo)
    produtos.firstOrNull()?.let { produto ->
      produtoVo.codigoProduto = codigo.trim()
      produtoVo.descricaoProduto = produto.nome ?: "Produto não encontrado"
      produto.tipo?.let { tipo ->
        produtoVo.tipo = TipoProduto.valueOf(tipo)
      }
    }
    
    val gradesSaci = produtos.mapNotNull { it.grade }.filter { it != "" }
    grades = if (gradesSaci.isNotEmpty())
      gradesSaci
    else
      listOf("")
  }
  
  fun saveUpdateProduto() = exec {
    val produto = Produto.findProduto(produtoVo.codigoProduto, produtoVo.gradeProduto)
                  ?: Produto.createProduto(produtoVo.codigoProduto, produtoVo.gradeProduto)
    
    produto.tipo = produtoVo.tipo
    produto.tamanhoLote = produtoVo.tamanhoLote
    
    produto.save()
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
          var tipo: TipoProduto = PECA,
          var tamanhoLote: Int = 0,
          var produtos: List<ProdutoSaci> = emptyList()
                      ) {
    val codebar: String
      get() = produtos.firstOrNull { it.grade == gradeProduto }?.codebar ?: ""
  }
}