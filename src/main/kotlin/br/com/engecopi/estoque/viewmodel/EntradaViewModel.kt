package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.ItemNota
import br.com.engecopi.estoque.model.Loja
import br.com.engecopi.estoque.model.Nota
import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.estoque.model.TipoMov.ENTRADA
import br.com.engecopi.framework.viewmodel.ViewModel
import br.com.engecopi.saci.QuerySaci
import br.com.engecopi.saci.beans.NotaEntradaSaci

class EntradaViewModel(val lojaDefault: Int, updateModel: (ViewModel) -> Unit) : ViewModel(updateModel) {
  
  val listaGrid: MutableCollection<Nota> = mutableListOf()
  val notaEntradaVo = NotaEntradaVo().apply {
    loja = Loja.findLoja(lojaDefault)
  }
  
  fun processaNotaEntrada() = exec {
    val notasEntrada = QuerySaci.querySaci.findNotaEntrada(
            storeno = notaEntradaVo.loja?.numero ?: 0,
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
  }
  
  override fun execUpdate() {
    val entradas = Nota.findEntradas(lojaDefault)
    listaGrid.clear()
    listaGrid.addAll(entradas)
  }
  
  private fun addNotaEntrada(nota: NotaEntradaSaci) {
    val numero = "${nota.invno}"
    val entrada = Nota.findEntrada(numero) ?: Nota().apply {
      this.numero = numero
      this.loja = Loja.findLoja(nota.storeno)
      this.tipoMov = ENTRADA
    }
    addProdutoNotaEntrada(entrada, nota)
  }
  
  private fun addProdutoNotaEntrada(entrada: Nota, nota: NotaEntradaSaci) {
    val codigo = nota.prdno ?: ""
    val grade = nota.grade ?: ""
    val produto = Produto.findProduto(codigo, grade)
    produto?.let { prd ->
      val item = entrada.findItem(prd) ?: ItemNota().apply {
        this.produto = prd
        this.nota = entrada
      }
      
      item.quantidade = nota.quant ?: 0
      
      item.save()
    }
  }
  
  fun removeEntrada(entrada: Nota) = exec {
    entrada.itensNota?.forEach { it.delete() }
    entrada.delete()
  }
}

data class NotaEntradaVo(
        var numero: String = "",
        var serie: String = "",
        var loja: Loja? = null
                        )