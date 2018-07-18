package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.CodBarResult
import br.com.engecopi.estoque.model.ItemNota
import br.com.engecopi.estoque.model.Loja
import br.com.engecopi.estoque.model.Lote
import br.com.engecopi.estoque.model.Movimentacao
import br.com.engecopi.estoque.model.Nota
import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.estoque.model.TipoMov.SAIDA
import br.com.engecopi.estoque.model.TipoNota
import br.com.engecopi.estoque.model.TipoNota.OUTROS_S
import br.com.engecopi.framework.viewmodel.CrudViewModel
import br.com.engecopi.framework.viewmodel.EViewModel
import br.com.engecopi.framework.viewmodel.IView
import br.com.engecopi.utils.localDate
import java.time.LocalDate

class SaidaViewModel(view: IView, val lojaDefault: Loja?) : CrudViewModel<SaidaVo>(view, SaidaVo::class) {
  override fun update(bean: SaidaVo) {
    val nota = saveNota(bean)
    
    val produto = bean.produto ?: throw EViewModel("Produto não encontrado no saci")
    
    val item = saveItemNota(bean, nota, produto)
    
    //deleteMovimentacoes(item)
    
    //geraMovimentacoes(bean, item)
  }
  
  override fun add(bean: SaidaVo) {
    val nota = saveNota(bean)
    
    val produto = bean.produto ?: throw EViewModel("Produto não encontrado no saci")
    
    val item = saveItemNota(bean, nota, produto)
    
    deleteMovimentacoes(item)
    
    geraMovimentacoes(bean, item)
  }
  
  private fun geraMovimentacoes(bean: SaidaVo, item: ItemNota) {
    bean.lotesQuant.forEach { loteQuant ->
      Movimentacao().apply {
        this.itemNota = item
        this.lote = loteQuant.lote
        this.quantidade = loteQuant.quant
      }.save()
      loteQuant.lote.atualizaSaldo()
    }
  }
  
  private fun deleteMovimentacoes(item: ItemNota) {
    item.movimentacoes?.forEach { mov ->
      mov.delete()
    }
  }
  
  private fun saveItemNota(
          bean: SaidaVo, nota: Nota,
          produto: Produto
                          ): ItemNota {
    val item = ItemNota.find(nota, produto) ?: ItemNota()
    item.apply {
      this.nota = nota
      this.produto = produto
      this.quantidade = bean.quantidade ?: 0
      this.tamanhoLote = bean.quantidade ?: 0
    }
    item.save()
    return item
  }
  
  private fun saveNota(bean: SaidaVo): Nota {
    val nota: Nota = bean.notaSaida ?: Nota()
    nota.apply {
      this.numero = if (bean.numeroNota.isNullOrBlank())
        "${Nota.novoNumero()}" else bean.numeroNota ?: ""
      this.tipoMov = SAIDA
      this.tipoNota = bean.tipoNota
      this.loja = bean.lojaNF
      this.observacao = bean.observacaoNota ?: ""
      this.rota = bean.rota ?: ""
    }
    
    nota.save()
    return nota
  }
  
  override fun delete(bean: SaidaVo) {
    ItemNota.find(bean.notaSaida, bean.produto)?.also { item ->
      item.movimentacoes?.forEach {
        it.delete()
      }
      item.delete()
    }
  }
  
  override fun allBeans(): List<SaidaVo> {
    val query = ItemNota.where().nota.tipoMov.eq(SAIDA)
    val qyeryLoja = lojaDefault?.let { loja ->
      query.nota.loja.id.eq(loja.id)
    } ?: query
    return qyeryLoja
            .findList().map { itemNota ->
              SaidaVo().apply {
                val nota = itemNota.nota
                this.numeroNota = nota?.numero
                this.lojaNF = nota?.loja
                this.observacaoNota = nota?.observacao
                this.produto = itemNota.produto
                this.quantidade = itemNota.quantidade
                this.loteInicial = itemNota.loteInicial()
                this.tipoNota = itemNota?.nota?.tipoNota ?: OUTROS_S
              }
            }
  }
  
  fun findLojas(loja: Loja?): List<Loja> = execList {
    loja?.let { listOf(it) } ?: Loja.all()
  }
  
  fun findProdutos(): List<Produto> = execList {
    Produto.all()
  }
}

class SaidaVo {
  var numeroNota: String? = ""
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
  var tipoNota: TipoNota = OUTROS_S
  var rota: String? = ""
  
  fun atualizaNota() {
    notaSaidaSaci.firstOrNull()?.let { nota ->
      tipoNota = TipoNota.values().find { it.toString() == nota.tipo } ?: OUTROS_S
      rota = nota.rota
    }
  }
  
  val notaSaidaSaci
    get() = Nota.findNotaSaidaSaci(numeroNota, lojaNF)
  
  val dataNF: LocalDate
    get() = notaSaidaSaci.firstOrNull()?.date?.localDate() ?: LocalDate.now()
  
  val notaSaida: Nota?
    get() = Nota.findSaida(numeroNota, lojaNF)
  
  var observacaoNota: String? = ""
  
  var codigoBarra: String? = ""
    set(value) {
      field = value
      val prdno = value?.split(" ")?.getOrNull(0)
      val prdTipo = Produto.findProdutos(prdno).firstOrNull()
      detalheBarra = prdTipo?.label?.tipo?.processaCodBar?.processaCodBar(value ?: "")
      detalheBarra.also { detalhe ->
        produto = Produto.findProduto(detalhe?.prdno, detalhe?.grade)
        val sequencia = detalhe?.sequencia
        loteInicial = lotes.find { it.sequencia == sequencia }
      }
    }
  
  var detalheBarra: CodBarResult? = null
  
  var produto: Produto? = null
  
  val descricaoProduto: String?
    get() = produto?.descricao
  
  val codigo: String?
    get() = produto?.codigo
  
  val grade: String?
    get() = produto?.grade
  
  var quantidade: Int? = 0
  
  val saldo: Int
    get() = lotesQuant.sumBy { it.quant }
  
  val lotes: List<Lote>
    get() = Lote.findSequencia(lojaNF, produto)
  
  var loteInicial: Lote? = null
  
  val loteFinal: Lote?
    get() = lotesQuant.lastOrNull()?.lote
  
  val loteFinalStr: String?
    get() = loteFinal?.sequenciaStr
  
  val quantidadeDisponivel: Int?
    get() = if (controlePorLote)
      lotesQuant.size
    else
      lotesQuant.sumBy { it.quant }
  
  val lotesQuant: List<LoteQuant>
    get() {
      val lotes = Lote.findSequencia(lojaNF, produto, loteInicial?.sequencia ?: 0)
      val quant = quantidade ?: 0
      return if (controlePorLote) {
        lotes.take(quant).map { LoteQuant(it, it.saldo) }
      } else {
        var saldo = quant
        val list = mutableListOf<LoteQuant>()
        lotes.forEach { lote ->
          if (saldo > 0) {
            if (saldo >= lote.saldo) {
              list.add(LoteQuant(lote, lote.saldo))
              saldo -= lote.saldo
            } else {
              list.add(LoteQuant(lote, saldo))
              saldo = 0
            }
          }
        }
        list
      }
    }
  
  val controlePorLote
    get() = produto?.label?.tipo?.controlePorLote ?: false
  
  val quantidadeCaption: String
    get() {
      return if (controlePorLote)
        "Qtd Saída (lote)"
      else
        "Qtd Saída"
    }
}

data class LoteQuant(val lote: Lote, val quant: Int)