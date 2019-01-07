package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.Nota
import br.com.engecopi.estoque.model.StatusNota.CONFERIDA
import br.com.engecopi.estoque.model.StatusNota.ENTREGUE
import br.com.engecopi.estoque.model.StatusNota.INCLUIDA
import br.com.engecopi.estoque.model.TipoMov.SAIDA
import br.com.engecopi.estoque.model.query.QItemNota
import br.com.engecopi.framework.viewmodel.IView

class NFExpedicaoViewModel(view: IView) : NotaViewModel<NFExpedicaoVo>
                                            (view, NFExpedicaoVo::class, SAIDA,
                                             INCLUIDA) {
  override fun QItemNota.filtroStatus(): QItemNota {
    return status.eq(INCLUIDA)
  }

  override fun createVo() = NFExpedicaoVo()
  fun processaKey(key: String) {
    Nota.findNotaSaidaPXA(key).forEach { notaSaci ->
      Nota
    }
  }
}

class NFExpedicaoVo : NotaVo(SAIDA)