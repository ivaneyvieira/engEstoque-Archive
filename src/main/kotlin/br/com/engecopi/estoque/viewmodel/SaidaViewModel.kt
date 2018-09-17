package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.TipoMov.SAIDA
import br.com.engecopi.estoque.model.Usuario
import br.com.engecopi.framework.viewmodel.IView

class SaidaViewModel(view: IView, usuario: Usuario) : NotaViewModel<SaidaVo>(view, usuario, SaidaVo::class, SAIDA) {
  override fun createVo() = SaidaVo(usuario)
}

class SaidaVo(usuario: Usuario) : NotaVo(SAIDA, usuario)
