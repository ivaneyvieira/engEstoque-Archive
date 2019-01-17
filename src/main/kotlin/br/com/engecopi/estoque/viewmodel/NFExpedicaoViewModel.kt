package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.ItemNota
import br.com.engecopi.estoque.model.Nota
import br.com.engecopi.estoque.model.RegistryUserInfo
import br.com.engecopi.estoque.model.StatusNota.INCLUIDA
import br.com.engecopi.estoque.model.TipoMov.SAIDA
import br.com.engecopi.estoque.model.query.QItemNota
import br.com.engecopi.framework.viewmodel.EViewModel
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
        if(this.existe())
<<<<<<< HEAD
          view.showError("A Nota já existe")
=======
          throw EViewModel("Essa nota já está cadastrada")
>>>>>>> 338240fea4e483b8aceaf988f3545d630bb57533
        else {
          sequencia = Nota.maxSequencia() + 1
          save()
        }
      }
      if (nota == null)
        throw EViewModel("Nota não encontrada")
      else {
        val itens = notasSaci.mapNotNull { notaSaci ->
          ItemNota.createItemNota(notaSaci, nota)?.apply {
            status = INCLUIDA
            save()
          }
        }
        if (itens.isEmpty())
          throw EViewModel("Essa nota não possui itens com localização")
      }
    } else
      throw EViewModel("Chave não encontrada")
  }
}

class NFExpedicaoVo : NotaVo(SAIDA, "")