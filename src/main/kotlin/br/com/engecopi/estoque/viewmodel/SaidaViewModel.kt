package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.Nota
import br.com.engecopi.estoque.model.RegistryUserInfo.abreviacaoDefault
import br.com.engecopi.estoque.model.RegistryUserInfo.lojaDefault
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
    processaKeyBarcodeConferencia(key) ?: processaKeyBarcodeCliente(key)
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

  private fun processaKeyBarcodeConferencia(key: String): Nota? {
    val item = ViewCodBarConferencia.findNota(key) ?: return null
    if(item.abreviacao != abreviacaoDefault) throw EViewModel("Esta nota não pertence ao cd $abreviacaoDefault")
    return Nota.findSaida(item.numero)
  }

  private fun processaKeyBarcodeCliente(key: String): Nota? {
    val partes = key.split(" ")
    val loja = partes.getOrNull(0)?.toIntOrNull() ?: return null
    val nfno = partes.getOrNull(1) ?: return null
    val nfse = partes.getOrNull(2) ?: ""
    if(loja != lojaDefault.numero) return null
    val numero = if(nfse == "") nfno else "$nfno/$nfse"
    return processaKeyNumero(numero)
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
