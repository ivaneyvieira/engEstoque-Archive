package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.ItemNota
import br.com.engecopi.estoque.model.Nota
import br.com.engecopi.estoque.model.StatusNota.CONFERIDA
import br.com.engecopi.estoque.model.StatusNota.ENTREGUE
import br.com.engecopi.estoque.model.StatusNota.ENTREGUE_LOJA
import br.com.engecopi.estoque.model.StatusNota.INCLUIDA
import br.com.engecopi.estoque.model.TipoMov.SAIDA
import br.com.engecopi.estoque.model.ViewCodBarEntrega
import br.com.engecopi.estoque.model.query.QItemNota
import br.com.engecopi.framework.viewmodel.EViewModel
import br.com.engecopi.framework.viewmodel.IView

class EntregaClienteEditorViewModel(view: IView)
  : NotaViewModel<EntregaClienteVo>(view, SAIDA, ENTREGUE, "") {
  override fun newBean(): EntregaClienteVo {
    return EntregaClienteVo()
  }

  override fun QItemNota.filtroStatus(): QItemNota {
    return status.`in`(ENTREGUE, ENTREGUE_LOJA)
      .nota.usuario.isNotNull
      .nota.sequencia.ne(0)
  }

  override fun createVo() = EntregaClienteVo()

  fun notasConferidas(): List<EntregaClienteVo> {
    //TODO Refatorar
    return ItemNota.where()
      .status.eq(CONFERIDA)
      .findList()
      .map { it.toVO() }
  }
}

