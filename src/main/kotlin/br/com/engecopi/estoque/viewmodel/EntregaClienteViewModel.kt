package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.ItemNota
import br.com.engecopi.estoque.model.Nota
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
      .nota.sequencia.ne(0)
  }

  override fun createVo() = EntregaClienteVo()

  fun processaKey(nota: Nota?, key: String) = execValue {
    val item = ViewCodBarEntrega.findNota(key) ?: throw EViewModel("Produto não encontrado")

    if (nota != null && nota.id != item.nota?.id)
      throw EViewModel("Este produto não pertence a nota ${nota.numero}")
    if (item.status != CONFERIDA) {
      if (item.status == ENTREGUE)
        throw  EViewModel("Produto já foi entregue")
      else
        if (item.status == INCLUIDA)
          throw  EViewModel("Produto ainda não foi conferido")
    }
    item.status = ENTREGUE
    item.save()
    return@execValue item
  }

  fun notasConferidas(): List<EntregaClienteVo> {
    return ItemNota.where()
      .status.eq(CONFERIDA)
      .findList()
      .map { it.toVO() }
  }
}

class EntregaClienteVo : NotaVo(SAIDA, "")