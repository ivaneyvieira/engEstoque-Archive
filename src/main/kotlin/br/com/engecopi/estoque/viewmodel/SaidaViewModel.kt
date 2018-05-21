package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.estoque.model.Produtos
import br.com.engecopi.estoque.model.Saida
import org.jetbrains.exposed.sql.transactions.transaction

class SaidaViewModel(private val updateModel: (SaidaViewModel) -> Unit) {
  val listaGrid: MutableCollection<Saida> = mutableListOf()
  val notaSaidaVo = NotaSaidaVo()
  
  fun execPesquisa() = transaction {
    listaGrid.clear()
    listaGrid.addAll(Saida.all())
    updateModel(this@SaidaViewModel)
  }
  
  fun novaNotaEntrada() {
    notaSaidaVo.numero = Saida.novoNumero()
    notaSaidaVo.loja = 0
    notaSaidaVo.itensSaida.clear()
    notaSaidaVo.addNewItem()
  }
  
  fun descricao(codigo: String?): String = transaction {
    codigo?.let { Produto.find { Produtos.codigo eq it }.firstOrNull()?.nome ?: "" } ?: ""
  }
  
  fun grades(codigo: String?): List<String> = transaction {
    codigo?.let { Produto.find { Produtos.codigo eq it }.map { it.grade } } ?: emptyList()
  }
}

data class NotaSaidaVo(
        var numero: Int = 0,
        var loja: Int = 0
                      ) {
  val itensSaida = mutableListOf<NotaSaidaItemVo>()
  
  fun addNewItem() {
val nova = mutableListOf<NotaSaidaItemVo>()
    itensSaida.forEachIndexed { i, b ->
      if (b.codigo != "")
        b.id = i+1
      nova.add(b.copy())
    }
    itensSaida.clear()
    itensSaida.addAll(nova)
    if (itensSaida.none { it.id == 0 }) {
      itensSaida.add(NotaSaidaItemVo())
    }
  }
}

data class NotaSaidaItemVo(
        var id: Int = 0,
        var codigo: String = "",
        var descricao: String = "",
        var grade: String? = "",
        var quantidade: Int = 0
                     )