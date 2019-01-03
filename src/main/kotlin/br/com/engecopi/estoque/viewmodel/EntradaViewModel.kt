package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.StatusNota.RECEBIDO
import br.com.engecopi.estoque.model.TipoMov.ENTRADA
import br.com.engecopi.estoque.model.Usuario
import br.com.engecopi.framework.viewmodel.IView

class EntradaViewModel(view: IView) : NotaViewModel<EntradaVo>(view, EntradaVo::class, ENTRADA, listOf(RECEBIDO)) {
  override fun createVo() = EntradaVo()
}

class EntradaVo : NotaVo(ENTRADA)
