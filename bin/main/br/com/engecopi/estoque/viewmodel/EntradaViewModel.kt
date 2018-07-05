package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.ItemNota
import br.com.engecopi.estoque.model.Loja
import br.com.engecopi.estoque.model.Lote
import br.com.engecopi.estoque.model.Movimentacao
import br.com.engecopi.estoque.model.Nota
import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.estoque.model.Produto.Find
import br.com.engecopi.estoque.model.TipoMov.ENTRADA
import br.com.engecopi.framework.model.ViewException
import br.com.engecopi.framework.viewmodel.CrudViewModel
import br.com.engecopi.framework.viewmodel.IView
import br.com.engecopi.saci.QuerySaci
import br.com.engecopi.saci.beans.NotaEntradaSaci
import br.com.engecopi.saci.beans.ProdutoSaci
import br.com.engecopi.utils.localDate
import java.time.LocalDate
import kotlin.math.ceil

class EntradaViewModel(view: IView) : CrudViewModel<EntradaVo>(view, EntradaVo::class) {
  override fun update(bean: EntradaVo) {
    val nota = saveNota(bean)
    
    val produto = saveProduto(bean)
    
    val item = saveItemNota(bean, nota, produto)
    
    deleteMovimentacoes(item)
    
    geraMovimentacoes(bean, item)
  }
  
  override fun add(bean: EntradaVo) {
    val nota = saveNota(bean)
    
    val produto = saveProduto(bean)
    
    val item = saveItemNota(bean, nota, produto)
    
    deleteMovimentacoes(item)
    
    geraMovimentacoes(bean, item)
  }
  
  private fun geraMovimentacoes(
          bean: EntradaVo,
          item: ItemNota
                               ) {
    val produto = item.produto
    var saldo = bean.quantProduto
    val tamanho = bean.tamanho ?: 0
    val ultimoLote = bean.lote
    var sequencia = ultimoLote?.sequencia ?: 0
    val total = ultimoLote?.total ?: 0+ceil(saldo * 1f / tamanho).toInt()
    while (saldo > 0) {
      val quant = if (saldo > tamanho) tamanho else saldo
      val lote = Lote()
      sequencia++
      lote.total = total
      lote.sequencia = sequencia
      lote.loja = bean.lojaNF
      lote.produto = produto
      lote.save()
      
      val movimentacao = Movimentacao()
      movimentacao.itemNota = item
      movimentacao.lote = lote
      movimentacao.quantidade = quant
      movimentacao.save()
      
      saldo -= tamanho
    }
  }
  
  private fun deleteMovimentacoes(item: ItemNota) {
    item.movimentacoes?.forEach { mov ->
      mov.lote?.delete()
      mov.delete()
    }
  }
  
  private fun saveItemNota(
          bean: EntradaVo, nota: Nota,
          produto: Produto
                          ): ItemNota {
    val item = bean.itemNota ?: ItemNota()
    item.apply {
      this.nota = nota
      this.produto = produto
      this.quantidade = bean.quantProduto
      this.tamanhoLote = bean.tamanho ?: 0
    }
    item.save()
    return item
  }
  
  private fun saveProduto(bean: EntradaVo): Produto {
    bean.produtoSaci ?: ViewException("Produto não encontrado no saci")
    val produto = bean.produto(bean.produtoSaci) ?: Produto.createProduto(bean.produtoSaci)
    return produto.apply {
      tamanhoLote = bean.tamanho ?: 0
      save()
    }
  }
  
  private fun saveNota(bean: EntradaVo): Nota {
    bean.notaEntradaSaci.firstOrNull() ?: ViewException("Nota não encontrada no saci")
    val nota: Nota = bean.nota ?: Nota()
    nota.apply {
      this.numero = bean.numeroNF ?: ""
      this.tipoMov = ENTRADA
      this.observacao = bean.observacaoNota ?: ""
    }
    
    nota.save()
    return nota
  }
  
  override fun allBeans(): List<EntradaVo> {
    return ItemNota.where().nota.tipoMov.eq(ENTRADA)
            .findList().map { itemNota ->
              EntradaVo().apply {
                val nota = itemNota.nota
                this.numeroNF = nota?.numero
                this.lojaNF = nota?.loja
                this.observacaoNota = nota?.observacao
                this.produtoSaci = itemNota.produto?.produtoSaci()
                this.tamanho = itemNota.produto?.tamanhoLote
              }
            }
  }
  
  override fun delete(bean: EntradaVo) {
    
    bean.itemNota?.let { item ->
      item.movimentacoes?.forEach {
        it.lote?.delete()
        it.delete()
      }
      item.delete()
    }
  }
  
  fun findLojas(): List<Loja> {
    return Loja.all()
  }
  
  fun findProdutoNota(entravaVo: EntradaVo?): List<ProdutoSaci> {
    return entravaVo?.notaEntradaSaci?.mapNotNull {
      val grade = it.grade ?: ""
      QuerySaci.querySaci.findProduto(it.prdno ?: "")
              .firstOrNull { it.grade == grade }
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
  
  fun produto(produtoSaci: ProdutoSaci?): Produto? {
    return produtoSaci?.let {
      Produto.findProduto(it.codigo ?: "", it.grade ?: "")
    }
  }
  
  var produtoSaci: ProdutoSaci? = null
    set(value) {
      field = value
      tamanho = produto(value)?.tamanhoLote
    }
  
  val descricaoProduto: String
    get() = produtoSaci?.nome ?: ""
  
  val quantProduto: Int
    get() = itemNota?.quantidade
            ?: notaEntradaSaci.firstOrNull { neSaci ->
              (neSaci.prdno ?: "") == (produtoSaci?.codigo ?: "") &&
              (neSaci.grade ?: "") == (produtoSaci?.grade ?: "")
            }?.quant
            ?: 0
  
  var tamanho: Int? = 0
  
  val lote: Lote?
    get() = produto(produtoSaci)?.ultimoLoteLoja(lojaNF)
  
  val sequencia: String
    get() {
      lote?.sequencia ?: return ""
      lote?.total ?: return ""
      return "${lote?.sequencia}/${lote?.total}"
    }
  
  val saldo: Int
    get() = produto(produtoSaci)?.saldoLoja(lojaNF) ?: 0
  
  val nota: Nota?
    get() = Nota.findEntrada(numeroNF ?: "", lojaNF)
  
  val itemNota: ItemNota?
    get() = ItemNota.where().nota.id.eq(nota?.id).produto.id
            .eq(produto(produtoSaci)?.id).findOne()
  
  val movimentacao: List<MovimentacaoVO>
    get() {
      val sequencia = lote?.sequencia ?: 0
      val tamanhoLote = tamanho
      tamanhoLote ?: return emptyList()
      if (tamanhoLote == 0) return emptyList()
      val qtLote = quantProduto / tamanhoLote
      val restoLote = quantProduto % tamanhoLote
      val totalLote = sequencia + qtLote + if (restoLote > 0) 1 else 0
      val lista = mutableListOf<MovimentacaoVO>()
      for (i in 1..qtLote) {
        val item = MovimentacaoVO().apply {
          this.sequencia = sequencia + i
          this.total = totalLote
          this.quantidade = tamanhoLote
        }
        lista.add(item)
      }
      if (restoLote > 0) {
        val item = MovimentacaoVO().apply {
          this.sequencia = totalLote
          this.total = totalLote
          this.quantidade = tamanhoLote
        }
        lista.add(item)
      }
      
      return lista
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