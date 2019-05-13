package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.ItemNota
import br.com.engecopi.estoque.model.Nota
import br.com.engecopi.estoque.model.RegistryUserInfo
import br.com.engecopi.estoque.model.RegistryUserInfo.abreviacaoDefault
import br.com.engecopi.estoque.model.RegistryUserInfo.usuarioDefault
import br.com.engecopi.estoque.model.StatusNota
import br.com.engecopi.estoque.model.StatusNota.CONFERIDA
import br.com.engecopi.estoque.model.StatusNota.ENTREGUE
import br.com.engecopi.estoque.model.TipoMov.SAIDA
import br.com.engecopi.estoque.model.ViewCodBarConferencia
import br.com.engecopi.estoque.model.query.QItemNota
import br.com.engecopi.framework.viewmodel.EViewModel
import br.com.engecopi.framework.viewmodel.IView

class SaidaViewModel(view: IView): NotaViewModel<SaidaVo>(view, SAIDA, ENTREGUE, CONFERIDA, abreviacaoDefault) {
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
    processaKeyBarcode(key) ?: processaKeyNumero(key)
  }

  private fun processaKeyNumero(key: String): Nota? {
    val notasSaci =
      Nota.findNotaSaidaSaci(key)
        .filter {loc ->
          loc.localizacaoes()
            .any {it.abreviacao == abreviacaoDefault}
        }
    val notaSaci = notasSaci.firstOrNull()
    return if(usuarioDefault.isTipoCompativel(notaSaci?.tipoNota())) Nota.findSaida(notaSaci?.numeroSerie())
                                                                     ?: Nota.createNotaItens(notasSaci)
    else null
  }

  private fun processaKeyBarcode(key: String): Nota? {
    val item = ViewCodBarConferencia.findNota(key) ?: return null
    if(item.abreviacao != abreviacaoDefault) throw EViewModel("Código de barras inválido")
    return Nota.findSaida(item.numero)
  }

  fun confirmaProdutos(itens: List<ProdutoVO>, situacao: StatusNota) = exec {
    itens.forEach {produtoVO ->
      produtoVO.value?.run {
        refresh()
        status = situacao
        impresso = false
        usuario = usuarioDefault
        localizacao = produtoVO.localizacao?.localizacao ?: ""
        update()
        recalculaSaldos()
      }
    }
  }
}

class SaidaVo: NotaVo(SAIDA, abreviacaoDefault)
