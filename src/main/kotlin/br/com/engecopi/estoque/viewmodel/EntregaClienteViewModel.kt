package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.estoque.model.StatusNota.CONFERIDA
import br.com.engecopi.estoque.model.StatusNota.ENTREGUE
import br.com.engecopi.estoque.model.StatusNota.INCLUIDA
import br.com.engecopi.estoque.model.TipoMov.SAIDA
import br.com.engecopi.estoque.model.ViewCodBarEntrega
import br.com.engecopi.estoque.model.query.QItemNota
import br.com.engecopi.framework.viewmodel.EViewModel
import br.com.engecopi.framework.viewmodel.IView

class EntregaClienteViewModel(view: IView) : NotaViewModel<EntregaClienteVo>(view, EntregaClienteVo::class, SAIDA,
                                                                             ENTREGUE, "") {
  override fun QItemNota.filtroStatus(): QItemNota {
    return status.`in`(ENTREGUE, CONFERIDA)
      .nota.usuario.isNotNull
  }

  override fun createVo() = EntregaClienteVo()
  fun processaKey(key: String)= exec {
    val item = ViewCodBarEntrega.findNota(key) ?: throw EViewModel("Produto não encontrado")
    if(item.status != CONFERIDA){
      if(item.status == ENTREGUE)
        throw  EViewModel("Produto já foi entregue")
      else
        if(item.status == INCLUIDA)
          throw  EViewModel("Produto ainda não foi conferido")
    }
    item.status = ENTREGUE
    item.save()
  }
}

class EntregaClienteVo : NotaVo(SAIDA, "")