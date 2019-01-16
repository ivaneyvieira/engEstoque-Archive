package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.ItemNota
import br.com.engecopi.estoque.model.Nota
import br.com.engecopi.estoque.model.RegistryUserInfo
import br.com.engecopi.estoque.model.StatusNota.INCLUIDA
import br.com.engecopi.estoque.model.TipoMov.SAIDA
import br.com.engecopi.estoque.model.query.QItemNota
import br.com.engecopi.framework.viewmodel.IView

class NFExpedicaoViewModel(view: IView) : NotaViewModel<NFExpedicaoVo>
                                            (view, NFExpedicaoVo::class, SAIDA,
                                             INCLUIDA, "") {
  override fun QItemNota.filtroStatus(): QItemNota {
    return status.eq(INCLUIDA)
  }

  override fun createVo() = NFExpedicaoVo().apply {
    this.usuario = RegistryUserInfo.usuarioDefault
  }

  fun processaKey(key: String) = exec {
    val notasSaci = Nota.findNotaSaidaPXA(key)
    if (notasSaci.isNotEmpty()) {
      val nota = Nota.createNota(notasSaci.firstOrNull())?.apply {
        sequencia = Nota.maxSequencia() + 1
        save()
      }
      if (nota == null)
        view.showError("Nota não encontrada")
      else {
        val itens = notasSaci.mapNotNull { notaSaci ->
          ItemNota.createItemNota(notaSaci, nota)?.apply {
            status = INCLUIDA
            save()
          }
        }
        if (itens.isEmpty())
          view.showError("Essa nota não possui itens com localização")
      }
    } else view.showError("Chave não encontrada")
  }
}

class NFExpedicaoVo : NotaVo(SAIDA, "")