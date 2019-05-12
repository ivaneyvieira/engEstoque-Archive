package br.com.engecopi.saci.beans

import br.com.engecopi.estoque.model.ItemNota
import br.com.engecopi.estoque.model.Nota
import br.com.engecopi.estoque.model.Nota.Find
import br.com.engecopi.estoque.model.ViewProdutoLoc

class NotaSaci(
  val rota: String?,
  val storeno: Int?,
  val numero: String?,
  val serie: String?,
  val date: Int?,
  val dt_emissao: Int?,
  val prdno: String?,
  val grade: String?,
  val quant: Int?,
  val vendName: String? = "",
  val clienteName: String? = "",
  val tipo: String?,
  val invno: Int?
<<<<<<< HEAD
) {
  fun isSave(): Boolean {
   return ItemNota.isSave(this)
  }

  fun numeroSerie(): String {
    numero ?: return ""
    return if(serie.isNullOrBlank()) numero
    else "$numero/$serie"
  }
}
=======
)
>>>>>>> dd0cbfadd54ad10a28f17046ede4ba69d92fbb43
