package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.ItemNota
import br.com.engecopi.estoque.model.RegistryUserInfo
import br.com.engecopi.estoque.model.RegistryUserInfo.abreviacaoDefault
import br.com.engecopi.estoque.model.StatusNota.CONFERIDA
import br.com.engecopi.estoque.model.StatusNota.ENTREGUE
import br.com.engecopi.estoque.model.TipoMov.SAIDA
import br.com.engecopi.estoque.model.ViewCodBarConferencia
import br.com.engecopi.estoque.model.query.QItemNota
import br.com.engecopi.framework.viewmodel.EViewModel
import br.com.engecopi.framework.viewmodel.IView

class SaidaViewModel(view: IView) : NotaViewModel<SaidaVo>(view, SaidaVo::class, SAIDA,
                                                           ENTREGUE, abreviacaoDefault) {
  override fun QItemNota.filtroStatus(): QItemNota {
    return status.`in`(ENTREGUE, CONFERIDA)
  }

  override fun createVo() = SaidaVo()

  fun processaKey(key: String) = execValue {
    val item = ViewCodBarConferencia.findNota(key) ?: return@execValue null
    if (item.abreviacao == RegistryUserInfo.abreviacaoDefault)
      throw EViewModel("Código de barras inválido")
    return@execValue item.nota
  }

  fun confirmaProdutos(itens: List<ItemNota>) = exec {
    itens.forEach { itemNota ->
      itemNota.refresh()
      itemNota.status = CONFERIDA
      itemNota.impresso = false
      itemNota.update()
      itemNota.recalculaSaldos()
    }
  }
}

class SaidaVo : NotaVo(SAIDA, abreviacaoDefault)
