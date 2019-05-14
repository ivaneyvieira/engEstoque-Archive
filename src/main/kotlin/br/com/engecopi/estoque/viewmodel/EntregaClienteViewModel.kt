package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.ItemNota
import br.com.engecopi.estoque.model.RegistryUserInfo.abreviacaoDefault
import br.com.engecopi.estoque.model.RegistryUserInfo.usuarioDefault
import br.com.engecopi.estoque.model.StatusNota.CONFERIDA
import br.com.engecopi.estoque.model.StatusNota.ENTREGUE
import br.com.engecopi.estoque.model.StatusNota.ENT_LOJA
import br.com.engecopi.estoque.model.StatusNota.INCLUIDA
import br.com.engecopi.estoque.model.TipoMov.SAIDA
import br.com.engecopi.estoque.model.ViewCodBarCliente
import br.com.engecopi.estoque.model.ViewCodBarConferencia
import br.com.engecopi.estoque.model.ViewCodBarEntrega
import br.com.engecopi.estoque.model.query.QItemNota
import br.com.engecopi.framework.viewmodel.EViewModel
import br.com.engecopi.framework.viewmodel.IView

class EntregaClienteViewModel(view: IView): NotaViewModel<EntregaClienteVo>(view, SAIDA, ENTREGUE, CONFERIDA, "") {
  override fun newBean(): EntregaClienteVo {
    return EntregaClienteVo()
  }

  override fun QItemNota.filtroStatus(): QItemNota {
    return status.`in`(CONFERIDA)
      .nota.usuario.isNotNull.nota.sequencia.ne(0)
      .let {q ->
        if(usuarioDefault.isEstoqueExpedicao) q.localizacao.startsWith(abreviacaoDefault)
        else q
      }
  }

  override fun createVo() = EntregaClienteVo()

  fun processaKey(key: String) = execList {
    val itens = findItens(key)
    if(itens.isEmpty()) throw EViewModel("Produto não encontrado")
    itens.forEach {item ->
      val codigoProduto = item.produto?.codigo?.trim() ?: ""
      if(item.status == ENTREGUE || item.status == ENT_LOJA) showWarning("Produto $codigoProduto já foi entregue")
      else if(item.status == INCLUIDA) showWarning("Produto $codigoProduto ainda não foi conferido")
      else if(item.status == CONFERIDA) {
        item.status = ENTREGUE
        item.save()
      }
    }
    return@execList itens
  }

  private fun findItens(key: String): List<ItemNota> {
    val itemUnico = ViewCodBarEntrega.findNota(key)
    return if(itemUnico == null) {
      val itensConferencia = ViewCodBarConferencia.findKeyItemNota(key)
      if(itensConferencia.isEmpty())
        ViewCodBarCliente.findKeyItemNota(key, abreviacaoDefault, CONFERIDA)
        else itensConferencia
    }
    else listOf(itemUnico)
  }

  fun notasConferidas(): List<EntregaClienteVo> {
    //TODO Refatorar
    return ItemNota.where()
      .status.eq(CONFERIDA)
      .findList()
      .map {it.toVO()}
  }
}

class EntregaClienteVo: NotaVo(SAIDA, "")