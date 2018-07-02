package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.ItemNota
import br.com.engecopi.estoque.model.Loja
import br.com.engecopi.estoque.model.Lote
import br.com.engecopi.estoque.model.Nota
import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.estoque.model.TipoMov.ENTRADA
import br.com.engecopi.framework.viewmodel.CrudViewModel
import br.com.engecopi.framework.viewmodel.IView
import br.com.engecopi.saci.beans.NotaEntradaSaci
import br.com.engecopi.utils.localDate
import java.time.LocalDate

class EntradaViewModel(view: IView) : CrudViewModel<EntradaVo>(view, EntradaVo::class) {
  override fun update(bean: EntradaVo) {
  }
  
  override fun add(bean: EntradaVo) {
  }
  
  override fun allBeans(): List<EntradaVo> {
    return ItemNota.where().nota.tipoMov.eq(ENTRADA)
            .findList().map { itemNota ->
              EntradaVo().apply {
                val nota = itemNota.nota
                this.numeroNF = nota?.numero
                this.lojaNF = nota?.loja
                this.observacaoNota = nota?.observacao
                this.produto = itemNota.produto
                this.quantProduto = itemNota.movimentacoes?.sumBy { it.quantidade } ?: 0
                this.tamanho = itemNota.produto?.tamanhoLote
              }
            }
  }
  
  override fun delete(bean: EntradaVo) {
  }
  
  fun findLojas(): List<Loja> {
    return Loja.all()
  }
  
  fun findProdutoNota(entravaVo: EntradaVo): List<Produto> {
    return entravaVo.notaEntradaSaci.mapNotNull {
      Produto
              .findProduto(it.prdno ?: "", it.grade ?: "")
    }
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
    get() = notaEntradaSaci.firstOrNull { neSaci ->
      (neSaci.prdno ?: "") == (produto?.codigo ?: "") &&
      (neSaci.grade ?: "") == (produto?.grade ?: "")
    }?.quant ?: 0
  
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
      val nota = Nota.findEntrada(numeroNF ?: "", lojaNF) ?: return emptyList()
      val itemNota = ItemNota.where().nota.id.eq(nota.id).produto.id.eq(produto?.id).findOne() ?: return emptyList()
      return itemNota.movimentacoes?.map { mov ->
        MovimentacaoVO().apply {
          this.sequencia = mov.lote?.sequencia
          this.total = mov.lote?.total
          this.quantidade = mov.quantidade
        }
      } ?: emptyList()
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