package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.ItemNota
import br.com.engecopi.estoque.model.Loja
import br.com.engecopi.estoque.model.Lote
import br.com.engecopi.estoque.model.Movimentacao
import br.com.engecopi.estoque.model.Nota
import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.estoque.model.TipoMov.ENTRADA
import br.com.engecopi.framework.model.ViewException
import br.com.engecopi.framework.viewmodel.CrudViewModel
import br.com.engecopi.framework.viewmodel.IView
import br.com.engecopi.saci.beans.NotaEntradaSaci
import br.com.engecopi.utils.localDate
import java.time.LocalDate
import kotlin.math.ceil

class EntradaViewModel(view: IView) : CrudViewModel<EntradaVo>(view, EntradaVo::class) {
  override fun update(bean: EntradaVo) {
  }
  
  override fun add(bean: EntradaVo) {
    bean.notaEntradaSaci.firstOrNull() ?: ViewException("Nota não encontrada no saci")
    val nota: Nota = bean.nota ?: Nota()
    nota.apply {
      this.numero = bean.numeroNF ?: ""
      this.tipoMov = ENTRADA
      this.observacao = bean.observacaoNota ?: ""
    }
    
    nota.save()
    
    bean.produto ?: ViewException("Produto não encontrada no saci")
    bean.produto?.tamanhoLote = bean.tamanho ?: 0
    bean.produto?.save()
    
    val item = bean.itemNota ?: ItemNota()
    item.apply {
      this.nota = nota
      this.produto = produto
      this.quantidade = bean.quantProduto
      this.tamanhoLote = bean.tamanho ?: 0
    }
    item.save()
    
    item.movimentacoes?.forEach { mov ->
      mov.lote?.delete()
      mov.delete()
    }
    
    var saldo = bean.quantProduto
    val tamanho = bean.tamanho ?: 0
    val ultimoLote = bean.lote
    var sequencia = ultimoLote?.sequencia ?: 0
    val total = ultimoLote?.total ?: 0 + ceil(saldo*1f/tamanho).toInt()
    while(saldo > 0){
    val quant = if(saldo > tamanho) tamanho else saldo
      val lote = Lote()
      sequencia++
      lote.total = total
      lote.sequencia = sequencia
      lote.loja = bean.lojaNF
      lote.produto = bean.produto
      lote.save()
      
      val movimentacao = Movimentacao()
      movimentacao.itemNota = item
      movimentacao.lote = lote
      movimentacao.quantidade = quant
      movimentacao.saldo = quant
      movimentacao.save()
  
      saldo -= tamanho
    }
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
                this.tamanho = itemNota.produto?.tamanhoLote
              }
            }
  }
  
  override fun delete(bean: EntradaVo) {
  }
  
  fun findLojas(): List<Loja> {
    return Loja.all()
  }
  
  fun findProdutoNota(entravaVo: EntradaVo?): List<Produto> {
    return entravaVo?.notaEntradaSaci?.mapNotNull {
      Produto.findProduto(it.prdno ?: "", it.grade ?: "")
    }.orEmpty()
  }
}

class EntradaVo {
  var numeroNF: String? = ""
  var lojaNF: Loja? = null
  
  val notaEntradaSaci: List<NotaEntradaSaci>
    get() = Nota.findNotaEntradaSaci(numeroNF, lojaNF)
  
  val dataNota: LocalDate?
    get() = notaEntradaSaci.firstOrNull()?.date?.localDate()
  
  val numeroInterno: Int
    get() = notaEntradaSaci.firstOrNull()?.invno ?: 0
  
  val fornecedor: String
    get() = notaEntradaSaci.firstOrNull()?.vendName ?: ""
  
  var observacaoNota: String? = ""
  
  var produto: Produto? = null
    set(value) {
      field = value
      tamanho = value?.tamanhoLote
    }
  
  val descricaoProduto: String
    get() = produto?.descricao ?: ""
  
  val quantProduto: Int
    get() = itemNota?.quantidade
            ?: notaEntradaSaci.firstOrNull { neSaci ->
              (neSaci.prdno ?: "") == (produto?.codigo ?: "") &&
              (neSaci.grade ?: "") == (produto?.grade ?: "")
            }?.quant
            ?: 0
  
  var tamanho: Int? = 0
  
  val lote: Lote?
    get() = produto?.ultimoLoteLoja(lojaNF)
  
  val sequencia: String
    get() {
      lote?.sequencia ?: return ""
      lote?.total ?: return ""
      return "${lote?.sequencia}/${lote?.total}"
    }
  
  val saldo: Int
    get() = produto?.saldoLoja(lojaNF) ?: 0
  
  val nota: Nota?
    get() = Nota.findEntrada(numeroNF ?: "", lojaNF)
  
  val itemNota: ItemNota?
    get() = ItemNota.where().nota.id.eq(nota?.id).produto.id.eq(produto?.id).findOne()
  
  val movimentacao: List<MovimentacaoVO>
    get() {
      return itemNota?.movimentacoes?.map { mov ->
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