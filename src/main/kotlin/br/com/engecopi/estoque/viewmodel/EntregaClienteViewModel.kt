package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.StatusNota.CONFERIDA
import br.com.engecopi.estoque.model.StatusNota.ENTREGUE
import br.com.engecopi.estoque.model.StatusNota.INCLUIDA
import br.com.engecopi.estoque.model.TipoMov.SAIDA
import br.com.engecopi.framework.viewmodel.IView

class EntregaClienteViewModel(view: IView) : NotaViewModel<EntregaClienteVo>(view, EntregaClienteVo::class, SAIDA,
                                                                             listOf(ENTREGUE, CONFERIDA)) {
  override fun createVo() = EntregaClienteVo()
  fun processaKey(key: String) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}

class EntregaClienteVo : NotaVo(SAIDA)