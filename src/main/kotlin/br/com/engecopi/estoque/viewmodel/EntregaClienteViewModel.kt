package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.ItemNota
import br.com.engecopi.estoque.model.RegistryUserInfo
import br.com.engecopi.estoque.model.RegistryUserInfo.abreviacaoDefault
import br.com.engecopi.estoque.model.StatusNota.CONFERIDA
import br.com.engecopi.estoque.model.StatusNota.ENTREGUE
import br.com.engecopi.estoque.model.StatusNota.INCLUIDA
import br.com.engecopi.estoque.model.TipoMov.SAIDA
import br.com.engecopi.estoque.model.ViewCodBarEntrega
import br.com.engecopi.estoque.model.query.QItemNota
import br.com.engecopi.framework.viewmodel.EViewModel
import br.com.engecopi.framework.viewmodel.IView

class EntregaClienteViewModel(view: IView)
  : NotaViewModel<EntregaClienteVo>(view, SAIDA, ENTREGUE, CONFERIDA, abreviacaoDefault) {
  override fun newBean(): EntregaClienteVo {
    return EntregaClienteVo()
  }

  override fun QItemNota.filtroStatus(): QItemNota {
    return status.`in`(CONFERIDA)
      .nota.usuario.isNotNull
      .nota.sequencia.ne(0)
  }

  override fun createVo() = EntregaClienteVo()

  fun processaKey(key: String) = execList {
    val item = ViewCodBarEntrega.findNota(key) ?: throw EViewModel("Produto não encontrado")

    if (item.status != CONFERIDA) {
      if (item.status == ENTREGUE)
        throw  EViewModel("Produto já foi entregue")
      else
        if (item.status == INCLUIDA)
          throw  EViewModel("Produto ainda não foi conferido")
    }
    item.status = ENTREGUE
    item.save()
    return@execList listOf(item)
  }

  fun notasConferidas(): List<EntregaClienteVo> {
    //TODO Refatorar
    return ItemNota.where()
      .status.eq(CONFERIDA)
      .findList()
      .map { it.toVO() }
  }
}

class EntregaClienteVo : NotaVo(SAIDA, "")