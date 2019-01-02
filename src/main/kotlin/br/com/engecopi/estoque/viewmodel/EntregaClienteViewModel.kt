package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.TipoMov.SAIDA
import br.com.engecopi.framework.viewmodel.IView

class EntregaClienteViewModel(view: IView) : NotaViewModel<EntregaClienteVo>(view, EntregaClienteVo::class, SAIDA) {
  override fun createVo() = EntregaClienteVo()
  fun processaKey(key: String) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}

class EntregaClienteVo : NotaVo(SAIDA)