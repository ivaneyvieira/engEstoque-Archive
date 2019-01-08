package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.TipoMov.SAIDA
import br.com.engecopi.estoque.model.Usuario
import br.com.engecopi.framework.viewmodel.IView

class SaidaViewModel(view: IView) : NotaViewModel<SaidaVo>(view, SaidaVo::class, SAIDA) {
  override fun createVo() = SaidaVo()
}

class SaidaVo : NotaVo(SAIDA)
