package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.ItemNota
import br.com.engecopi.estoque.model.Label
import br.com.engecopi.estoque.model.Loja
import br.com.engecopi.estoque.model.Lote
import br.com.engecopi.estoque.model.Movimentacao
import br.com.engecopi.estoque.model.Nota
import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.estoque.model.TipoMov.ENTRADA
import br.com.engecopi.estoque.model.TipoNota
import br.com.engecopi.estoque.model.TipoNota.OUTROS_E
import br.com.engecopi.estoque.model.TipoProduto
import br.com.engecopi.estoque.ui.EstoqueUI.Companion.loja
import br.com.engecopi.framework.viewmodel.CrudViewModel
import br.com.engecopi.framework.viewmodel.EViewModel
import br.com.engecopi.framework.viewmodel.IView
import br.com.engecopi.saci.beans.NotaEntradaSaci
import br.com.engecopi.saci.beans.ProdutoSaci
import br.com.engecopi.saci.saci
import br.com.engecopi.utils.localDate
import java.time.LocalDate
import kotlin.math.ceil

class EntradaViewModel(view: IView, val lojaDefault: Loja?) : CrudViewModel<EntradaVo>(view, EntradaVo::class) {
  override fun update(bean: EntradaVo) {
    val nota = saveNota(bean)
    
    val produto = saveProduto(bean)
    
    updateItemNota(bean, nota, produto)
  }
  
  override fun add(bean: EntradaVo) {
    val nota = saveNota(bean)
    
    val produto = saveProduto(bean)
    
    val item = insertItemNota(bean, nota, produto)
    
    deleteMovimentacoes(item)
    
    geraMovimentacoes(bean, item)
  }
  
  private fun geraMovimentacoes(
          bean: EntradaVo,
          item: ItemNota
                               ) {
    val produto = item.produto
    var saldo = bean.quantProduto ?: 0
    val tamanho = bean.tamanho ?: 0
    val ultimoLote = bean.ultimoLote
    var sequencia = ultimoLote?.sequencia ?: 0
    val total = (ultimoLote?.total ?: 0) + ceil(saldo * 1f / tamanho).toInt()
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
    produto?.atualizaSaldo()
  }
  
  private fun deleteMovimentacoes(item: ItemNota) {
    item.movimentacoes?.forEach { mov ->
      mov.delete()
    }
  }
  
  private fun insertItemNota(
          bean: EntradaVo, nota: Nota,
          produto: Produto
                            ): ItemNota {
    val item = bean.itemNota ?: ItemNota()
    item.apply {
      this.nota = nota
      this.produto = produto
      this.quantidade = bean.quantProduto ?: 0
      this.tamanhoLote = bean.tamanho ?: 0
    }
    item.insert()
    return item
  }
  
  private fun updateItemNota(
          bean: EntradaVo, nota: Nota,
          produto: Produto
                            ) {
    bean.itemNota?.let { item ->
      item.apply {
        this.nota = nota
        this.produto = produto
        this.quantidade = bean.quantProduto ?: 0
        this.tamanhoLote = bean.tamanho ?: 0
      }
      item.update()
    }
  }
  
  private fun saveProduto(bean: EntradaVo): Produto {
    bean.produtoSaci ?: throw EViewModel("Produto não encontrado no saci")
    val produto = bean.produto(bean.produtoSaci) ?: Produto.createProduto(bean.produtoSaci)
    return produto.apply {
      tamanhoLote = bean.tamanho ?: 0
      label = Label.find(bean.tipoProduto)
      save()
    }
  }
  
  private fun saveNota(bean: EntradaVo): Nota {
    bean.notaEntradaSaci.firstOrNull() ?: throw EViewModel("Nota não encontrada no saci")
    val nota: Nota = bean.nota ?: Nota()
    nota.apply {
      this.numero = bean.numeroNF ?: ""
      this.tipoMov = ENTRADA
      this.loja = bean.lojaNF
      this.observacao = bean.observacaoNota ?: ""
      this.tipoNota = bean.tipoNota
      this.rota = bean.rota ?: ""
    }
    
    nota.save()
    return nota
  }
  
  override fun allBeans(): List<EntradaVo> {
    val query = ItemNota.where()
            .nota.tipoMov.eq(ENTRADA)
    val qyeryLoja = lojaDefault?.let { loja ->
      query.nota.loja.id.eq(loja.id)
    } ?: query
    return qyeryLoja
            .findList().map { itemNota ->
              EntradaVo().apply {
                val nota = itemNota.nota
                this.numeroNF = nota?.numero
                this.lojaNF = nota?.loja
                this.observacaoNota = nota?.observacao
                this.produtoSaci = itemNota.produto?.produtoSaci()
                this.tamanho = itemNota.produto?.tamanhoLote
                this.tipoProduto = itemNota.produto?.label?.tipo
                this.tipoNota = itemNota?.nota?.tipoNota ?: OUTROS_E
                this.rota = itemNota?.nota?.rota
              }
            }
  }
  
  override fun delete(bean: EntradaVo) {
    bean.itemNota?.also { item ->
      if (item.ultilmaMovimentacao) {
        item.movimentacoes?.forEach {
          it.delete()
        }
        item.delete()
      } else
        throw EViewModel("Não pode ser removido, por que essa não é a última movimentação do produto.")
    }
  }
  
  fun findLojas(loja: Loja?): List<Loja> = execList {
    loja?.let { listOf(it) } ?: Loja.all()
  }
  
  fun imprimirSeparado(itemNota: ItemNota) = execString {
    val template = itemNota.produto?.label?.layoutEntrada ?: ""
    val print = itemNota.printEtiqueta()
    val etiqueta = EtiquetaPrinter(template, print)
    etiqueta.printSeparado()
  }
  
  fun imprimirAgrupado(itemNota: ItemNota) = execString {
    val template = itemNota.produto?.label?.layoutEntrada ?: ""
    val print = itemNota.printEtiqueta()
    val etiqueta = EtiquetaPrinter(template, print)
    etiqueta.printAgrupado()
  }
}

class EntradaVo {
  var numeroNF: String? = ""
    set(value) {
      if (field != value) {
        field = value
        atualizaNota()
      }
    }
  var lojaNF: Loja? = null
    set(value) {
      if (field != value) {
        field = value
        atualizaNota()
      }
    }
  var tipoNota: TipoNota = OUTROS_E
  var rota: String? = ""
  
  val notaEntradaSaci: List<NotaEntradaSaci>
    get() = Nota.findNotaEntradaSaci(numeroNF, lojaNF)
  
  fun atualizaNota() {
    notaEntradaSaci.firstOrNull()?.let { nota ->
      tipoNota = TipoNota.values().find { it.toString() == nota.tipo } ?: OUTROS_E
      rota = nota.rota
    }
  }
  
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
  
  val produtoNota: List<ProdutoSaci>
    get() {
      return notaEntradaSaci.mapNotNull {
        val grade = it.grade ?: ""
        saci.findProduto(it.prdno ?: "")
                .firstOrNull { it.grade == grade }
      }
    }
  
  var produtoSaci: ProdutoSaci? = null
    set(value) {
      val mudou = field != value
      field = value
      if (tamanho == null || tamanho == 0 || mudou)
        tamanho = produto(value)?.tamanhoLote
      if (tipoProduto == null || mudou)
        tipoProduto = produto(produtoSaci)?.label?.tipo ?: TipoProduto.valueOf(produtoSaci?.tipo ?: "NORMAL")
    }
  
  val descricaoProduto: String
    get() = produtoSaci?.nome ?: ""
  
  val codigo: String
    get() = produtoSaci?.codigo ?: ""
  
  val grade: String
    get() = produtoSaci?.grade ?: ""
  
  val quantProduto: Int?
    get() = itemNota?.quantidade
            ?: notaEntradaSaci.firstOrNull { neSaci ->
              (neSaci.prdno ?: "") == (produtoSaci?.codigo ?: "") &&
              (neSaci.grade ?: "") == (produtoSaci?.grade ?: "")
            }?.quant
            ?: 0
  
  var tipoProduto: TipoProduto? = null
  
  var tamanho: Int? = 0
    get() {
      return if (tipoProduto?.loteUnitario != false)
        return 1
      else
        field
    }
  
  val tamanhoReadOnly: Boolean
    get() {
      return tipoProduto?.loteUnitario ?: true
    }
  
  val ultimoLote: Lote?
    get() = if (itemNota == null)
      produto(produtoSaci)?.ultimoLoteLoja(lojaNF)
    else
      itemNota?.ultimoLote()
  
  val sequencia: String
    get() {
      val lote = ultimoLote
      lote?.sequencia ?: return "1"
      return "${lote.sequencia + 1}"
    }
  
  val saldo: Int
    get() = produto(produtoSaci)?.saldoLoja(lojaNF) ?: 0
  
  val nota: Nota?
    get() = Nota.findEntrada(numeroNF ?: "", lojaNF)
  
  val itemNota: ItemNota?
    get() = ItemNota.find(nota, produto(produtoSaci))
  
  val movimentacao: List<MovimentacaoVO>
    get() {
      val sequencia = ultimoLote?.sequencia ?: 0
      val tamanhoLote = tamanho
      tamanhoLote ?: return emptyList()
      if (tamanhoLote == 0) return emptyList()
      val qtLote = (quantProduto ?: 0) / tamanhoLote
      val restoLote = (quantProduto ?: 0) % tamanhoLote
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
          this.quantidade = restoLote
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
  
  val descLote: String?
    get() {
      sequencia ?: return ""
      total ?: return ""
      return "$sequencia/$total"
    }
}