package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.Entrada
import br.com.engecopi.estoque.model.Entradas
import br.com.engecopi.estoque.model.ItemEntrada
import br.com.engecopi.estoque.model.ItensEntrada
import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.estoque.model.Produtos
import br.com.engecopi.saci.QuerySaci
import br.com.engecopi.saci.beans.NotaEntradaSaci
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime
import org.joda.time.LocalDate
import org.joda.time.LocalTime
import org.joda.time.format.DateTimeFormat
import java.math.BigDecimal

class EntradaViewModel(private val updateModel: (EntradaViewModel) -> Unit) {
  val listaGrid: MutableCollection<Entrada> = mutableListOf()
  val notaEntradaVo = NotaEntradaVo()
  
  fun processaNotaEntrada() = transaction {
    val notasEntrada = QuerySaci.querySaci.findNotaEntrada(
            storeno = notaEntradaVo.loja,
            nfname = notaEntradaVo.numero,
            invse = notaEntradaVo.serie
                                                          )
    val produtos = Produto.all().toList()
    val notasProduto = notasEntrada.filter { nota ->
      val produto = nota.prdno?.trim() ?: ""
      val grade = nota.grade ?: ""
      produtos.any { it.codigo == produto && it.grade == grade }
    }
    notasProduto.forEach { nota ->
      addNotaEntrada(nota)
    }
    execPesquisa()
  }
  
  fun execPesquisa() {
    transaction {
      val entradas = Entrada.all()
      listaGrid.clear()
      listaGrid.addAll(entradas)
    }
    updateModel(this)
  }
  
  private fun addNotaEntrada(nota: NotaEntradaSaci) {
    val dtf = DateTimeFormat.forPattern("HH:mm")
    val numero = "${nota.invno}"
    val entrada = Entrada.find { Entradas.numero eq numero }.firstOrNull() ?: Entrada.new {
      this.numero = numero
      this.loja = nota.storeno ?: 0
      this.data = LocalDate.now().toDateTime(LocalTime(0, 0))
      this.hora = dtf.print(DateTime.now())
    }
    addProdutoNotaEntrada(entrada, nota)
  }
  
  private fun addProdutoNotaEntrada(entrada: Entrada, nota: NotaEntradaSaci) {
    val codigo = nota.prdno ?: ""
    val grade = nota.grade ?: ""
    val produto = Produto.find { (Produtos.codigo eq codigo) and (Produtos.grade eq grade) }.firstOrNull()
    produto?.let { prd ->
      if (ItemEntrada.find { (ItensEntrada.notaEntrada eq entrada.id) and (ItensEntrada.produto eq prd.id) }.count() == 0)
        ItemEntrada.new {
          this.notaEntrada = entrada
          this.produto = prd
          this.quantidade = nota.quant ?: 0
          this.custo_unitario = nota.custo?.toBigDecimal() ?: BigDecimal.ZERO
        }
      nota.storeno?.let { loja -> prd.recalcula(loja) }
    }
  }
}

data class NotaEntradaVo(
        var numero: String = "",
        var serie: String = "",
        var loja: Int = 0
                        )