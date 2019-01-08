package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.ItemNota
import br.com.engecopi.estoque.model.Nota
import br.com.engecopi.estoque.model.StatusNota.CONFERIDA
import br.com.engecopi.estoque.model.StatusNota.ENTREGUE
import br.com.engecopi.estoque.model.StatusNota.INCLUIDA
import br.com.engecopi.estoque.model.TipoMov.SAIDA
import br.com.engecopi.estoque.model.query.QItemNota
import br.com.engecopi.framework.viewmodel.EViewModel
import br.com.engecopi.framework.viewmodel.IView
import jdk.nashorn.internal.objects.NativeArray.forEach

class NFExpedicaoViewModel(view: IView) : NotaViewModel<NFExpedicaoVo>
                                            (view, NFExpedicaoVo::class, SAIDA,
                                             INCLUIDA) {
  override fun QItemNota.filtroStatus(): QItemNota {
    return status.eq(INCLUIDA)
  }

  override fun createVo() = NFExpedicaoVo()
  fun processaKey(key: String) = exec {
    val notasSaci = Nota.findNotaSaidaPXA(key)
    if (notasSaci.isNotEmpty()) {
      val nota = Nota.createNota(notasSaci.firstOrNull())?.apply {
        sequencia = Nota.maxSequencia() + 1
        save()
      }
      notasSaci.forEach { notaSaci ->
        ItemNota.createItemNota(notaSaci, nota)?.apply {
          status = INCLUIDA
          save()
        }
      }
    } else view.showError("Chave não encontrada")
  }
}

class NFExpedicaoVo : NotaVo(SAIDA)