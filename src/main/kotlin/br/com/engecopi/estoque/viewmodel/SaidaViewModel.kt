package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.ItemNota
import br.com.engecopi.estoque.model.Loja
import br.com.engecopi.estoque.model.Nota
import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.estoque.model.TipoMov.SAIDA
import br.com.engecopi.framework.viewmodel.ViewModel
import io.ebean.config.JsonConfig.DateTime
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime

class SaidaViewModel(val lojaDefault: Int, updateModel: (ViewModel) -> Unit) : ViewModel(updateModel) {
  val listaGrid: MutableCollection<Nota> = mutableListOf()
  val notaSaidaVo = NotaSaidaVo()
  val lojaSaida = Loja.findLojaUser(lojaDefault)
  
  override fun execUpdate() {
    exec {
      listaGrid.clear()
      listaGrid.addAll(Nota.findSaidas())
    }
  }
  
  fun novaNotaEntrada() {
    notaSaidaVo.numero = Nota.novoNumero()
    notaSaidaVo.loja = Loja.findLoja(lojaDefault)
    notaSaidaVo.itensSaida.clear()
    notaSaidaVo.addNewItem()
  }
  
  fun descricao(codigo: String): String = execString {
    Produto.findProdutos(codigo).firstOrNull()?.descricao ?: ""
  }
  
  fun grades(codigo: String): List<String> = execList {
    Produto.findProdutos(codigo).map { it.grade }
  }
  
  fun salvaSaida() = exec {
    val saidaNova = Nota().apply {
      this.numero = "${notaSaidaVo.numero}"
      this.loja = notaSaidaVo.loja
      this.tipoMov = SAIDA
    }
    saidaNova.save()
    notaSaidaVo.itensSaida.forEach { item ->
      val produto = Produto.findProduto(item.codigo, item.grade)
      produto?.let { prd ->
        ItemNota().apply {
          this.quantidade = item.quantidade
          this.nota = saidaNova
          this.produto = prd
        }.save()
      }
    }
  }
  
  fun removeSaida(saida: Nota) = exec {
    saida.itensNota?.forEach { it.delete() }
    saida.delete()
  }
}

data class NotaSaidaVo(
        var numero: Int = 0,
        var loja: Loja? = null,
        var observacao : String = ""
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