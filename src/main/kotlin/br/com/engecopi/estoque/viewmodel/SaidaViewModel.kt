package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.ItemNota
import br.com.engecopi.estoque.model.Loja
import br.com.engecopi.estoque.model.Nota
import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.estoque.model.TipoMov.SAIDA
import br.com.engecopi.framework.viewmodel.CrudViewModel
import br.com.engecopi.framework.viewmodel.IView
import br.com.engecopi.framework.viewmodel.ViewModel
import io.ebean.config.JsonConfig.DateTime
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime

class SaidaViewModel(view: IView) : CrudViewModel<SaidaVo>(view, SaidaVo::class) {
  override fun update(bean: SaidaVo) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
  
  override fun add(bean: SaidaVo) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
  
  override fun delete(bean: SaidaVo) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
  
  override fun allBeans(): List<SaidaVo> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}

class SaidaVo {
  var numeroNota: String? = ""
  var lojaNF: Loja? = null
  
  val notaSaida = Nota.findSaida(numeroNota, lojaNF)
  
  val dataNF : LocalDate? = Nota?.
}