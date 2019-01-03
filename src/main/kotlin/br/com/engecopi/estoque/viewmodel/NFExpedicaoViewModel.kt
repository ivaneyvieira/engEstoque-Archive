package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.StatusNota.CONFERIDA
import br.com.engecopi.estoque.model.StatusNota.ENTREGUE
import br.com.engecopi.estoque.model.StatusNota.INCLUIDA
import br.com.engecopi.estoque.model.TipoMov.SAIDA
import br.com.engecopi.framework.viewmodel.IView

class NFExpedicaoViewModel(view: IView) : NotaViewModel<NFExpedicaoVo>(view, NFExpedicaoVo::class, SAIDA,
                                                                       listOf(INCLUIDA, CONFERIDA, ENTREGUE)) {
  override fun createVo() = NFExpedicaoVo()
  fun processaKey(key: String) {

  }
}

class NFExpedicaoVo : NotaVo(SAIDA)