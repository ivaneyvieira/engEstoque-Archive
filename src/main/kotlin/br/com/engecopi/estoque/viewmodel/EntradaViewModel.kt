package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.Loja
import br.com.engecopi.estoque.model.Lote
import br.com.engecopi.estoque.model.Nota
import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.framework.viewmodel.CrudViewModel
import br.com.engecopi.framework.viewmodel.IView
import br.com.engecopi.framework.viewmodel.ViewModel
import br.com.engecopi.saci.beans.NotaEntradaSaci
import br.com.engecopi.utils.localDate
import java.time.LocalDate

class EntradaViewModel(view: IView) : CrudViewModel<EntradaVo>(view, EntradaVo::class) {
  override fun update() {
  
  }
  
  override fun add() {
  
  }
  
  override fun findAll(): List<EntradaVo> {
    return emptyList()
  }
  
  override fun delete() {
  
  }
  
  fun findLojas(): List<Loja>? {
    return Loja.all()
  }
}

class EntradaVo {
  var numeroNF: String? = ""
  var lojaNF: Loja? = null
  
  val notaEntradaSaci: List<NotaEntradaSaci>
    get() = Nota.findNotaEntradaSaci(numeroNF, lojaNF)
  
  var dataNota: LocalDate? = null
    get() = notaEntradaSaci.firstOrNull()?.date?.localDate()
  
  var numeroInterno: Int = 0
    get() = notaEntradaSaci.firstOrNull()?.invno ?: 0
  
  var fornecedor: String = ""
    get() = notaEntradaSaci.firstOrNull()?.vendName ?: ""
  
  var observacaoNota: String? = ""
  
  var produto: Produto? = null
    set(value) {
      field = value
      tamanho = value?.tamanhoLote
    }
  
  var descricaoProduto: String = ""
    get() = produto?.descricao ?: ""
  
  var quantProduto: Int = 0
    get() = notaEntradaSaci.filter { neSaci ->
      (neSaci.prdno ?: "") == (produto?.codigo ?: "") &&
      (neSaci.grade ?: "") == (produto?.grade ?: "")
    }.firstOrNull()?.quant ?: 0
  
  var tamanho: Int? = 0
  
  var lote: Lote? = null
    get() = produto?.ultimoLoteLoja(lojaNF)
  
  var sequencia: String = ""
    get() {
      lote?.sequencia ?: return ""
      lote?.total ?: return ""
      return "${lote?.sequencia}/${lote?.total}"
    }
  
  var saldo: Int = 0
    get() = produto?.saldoLoja(lojaNF) ?: 0
  
  var movimentacao: List<MovimentacaoVO> = emptyList()
    get() {
      return emptyList()
    }
}

class MovimentacaoVO {
  var sequencia: Int? = 0
  var total: Int? = 0
  var quantidade: Int? = 0
  
  val descLote: String
    get() {
      sequencia ?: return ""
      total ?: return ""
      return "$sequencia/$total"
    }
}