package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.TipoMov.SAIDA
import br.com.engecopi.framework.viewmodel.IView

class NFExpedicaoViewModel(view: IView) : NotaViewModel<NFExpedicaoVo>(view, NFExpedicaoVo::class, SAIDA) {
  override fun createVo() = NFExpedicaoVo()
  fun processaKey(key: String) {

  }
}

class NFExpedicaoVo : NotaVo(SAIDA)