package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.ItemSaida
import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.estoque.model.Produtos
import br.com.engecopi.estoque.model.Saida
import br.com.engecopi.framework.viewmodel.ViewModel
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime
import org.joda.time.LocalDate
import org.joda.time.LocalTime
import org.joda.time.format.DateTimeFormat
import java.math.BigDecimal

class SaidaViewModel(val lojaDefault: Int, updateModel: (ViewModel) -> Unit) : ViewModel(updateModel) {
  val listaGrid: MutableCollection<Saida> = mutableListOf()
  val notaSaidaVo = NotaSaidaVo()
  
  override fun execUpdate() {
    transaction {
      listaGrid.clear()
      listaGrid.addAll(Saida.all())
    }
  }
  
  fun novaNotaEntrada() {
    notaSaidaVo.numero = Saida.novoNumero()
    notaSaidaVo.loja = lojaDefault
    notaSaidaVo.itensSaida.clear()
    notaSaidaVo.addNewItem()
  }
  
  fun descricao(codigo: String?): String = transaction {
    codigo?.let { Produto.find { Produtos.codigo eq it }.firstOrNull()?.nome ?: "" } ?: ""
  }
  
  fun grades(codigo: String?): List<String> = transaction {
    codigo?.let { Produto.find { Produtos.codigo eq it }.map { it.grade } } ?: emptyList()
  }
  
  fun salvaSaida() = exec {
    val dtf = DateTimeFormat.forPattern("HH:mm")
    val saidaNova = Saida.new {
      this.numero = Saida.novoNumero()
      this.loja = notaSaidaVo.loja
      this.data = LocalDate.now().toDateTime(LocalTime(0, 0))
      this.hora = dtf.print(DateTime.now())
    }
    notaSaidaVo.itensSaida.forEach { item ->
      val produto = Produto.find { (Produtos.codigo eq item.codigo) and (Produtos.grade eq item.grade) }.firstOrNull()
      produto?.let { prd ->
        ItemSaida.new {
          this.quantidade = item.quantidade
          this.custo_unitario = BigDecimal.ZERO
          this.notaSaida = saidaNova
          this.produto = prd
        }
        prd.recalcula(notaSaidaVo.loja)
      }
    }
  }
  
  fun removeSaida(saida: Saida) = exec {
    saida.itens.forEach { it.delete() }
    saida.delete()
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
        b.id = i + 1
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
        var grade: String = "",
        var quantidade: Int = 0
                          )