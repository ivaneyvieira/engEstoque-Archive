package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.Entrada

class EntradaViewModel(private val updateModel: (EntradaViewModel) -> Unit) {
  fun processaNotaEntrada() {
        
  }
  
  val listaGrid: MutableCollection<Entrada> = mutableListOf()
  val notaEntradaVo = NotaEntradaVo()
}

data class NotaEntradaVo(
        var numero : String = "",
        var serie : String = "",
        var loja : Int = 0
                        )