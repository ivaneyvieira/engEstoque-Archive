package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.Entrada

class EntradaViewModel(private val updateModel: (EntradaViewModel) -> Unit) {
  val listaGrid: MutableCollection<Entrada> = mutableListOf()
}