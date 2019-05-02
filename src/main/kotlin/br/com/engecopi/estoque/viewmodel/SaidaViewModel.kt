package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.ItemNota
import br.com.engecopi.estoque.model.Nota
import br.com.engecopi.estoque.model.RegistryUserInfo
import br.com.engecopi.estoque.model.RegistryUserInfo.abreviacaoDefault
import br.com.engecopi.estoque.model.StatusNota
import br.com.engecopi.estoque.model.StatusNota.CONFERIDA
import br.com.engecopi.estoque.model.StatusNota.ENTREGUE
import br.com.engecopi.estoque.model.TipoMov.SAIDA
import br.com.engecopi.estoque.model.ViewCodBarConferencia
import br.com.engecopi.estoque.model.query.QItemNota
import br.com.engecopi.framework.viewmodel.EViewModel
import br.com.engecopi.framework.viewmodel.IView

class SaidaViewModel(view: IView): NotaViewModel<SaidaVo>(view, SAIDA,
                                                          ENTREGUE, abreviacaoDefault) {
  override fun newBean(): SaidaVo {
    return SaidaVo()
  }

  override fun QItemNota.filtroStatus(): QItemNota {
    return status.`in`(ENTREGUE, CONFERIDA)
  }

  override fun add(bean: SaidaVo) {
    //Não faze nada
  }

  override fun createVo() = SaidaVo()

  fun processaKey(key: String) = execValue {
    val item = ViewCodBarConferencia.findNota(key) ?: return@execValue null
    if (item.abreviacao != abreviacaoDefault)
      throw EViewModel("Código de barras inválido")
    return@execValue Nota.findSaida(item.numero)
  }

  fun confirmaProdutos(itens: List<ItemNota>, situacao : StatusNota) = exec {
    itens.forEach { itemNota ->
      itemNota.refresh()
      itemNota.status = situacao
      itemNota.impresso = false
      itemNota.usuario = RegistryUserInfo.usuarioDefault
      itemNota.update()
      itemNota.recalculaSaldos()
    }
  }
}

class SaidaVo : NotaVo(SAIDA, abreviacaoDefault)
