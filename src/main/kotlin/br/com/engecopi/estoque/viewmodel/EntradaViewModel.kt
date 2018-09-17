package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.TipoMov.ENTRADA
import br.com.engecopi.estoque.model.Usuario
import br.com.engecopi.framework.viewmodel.IView

class EntradaViewModel(view: IView, usuario: Usuario) : NotaViewModel<EntradaVo>(view, usuario, EntradaVo::class, ENTRADA) {
  override fun createVo() = EntradaVo(usuario)
}

class EntradaVo(usuario: Usuario) : NotaVo(ENTRADA, usuario)
