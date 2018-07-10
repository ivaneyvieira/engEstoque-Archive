package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.CodBarResult
import br.com.engecopi.estoque.model.ItemNota
import br.com.engecopi.estoque.model.Loja
import br.com.engecopi.estoque.model.Lote
import br.com.engecopi.estoque.model.Movimentacao
import br.com.engecopi.estoque.model.Nota
import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.estoque.model.TipoMov.SAIDA
import br.com.engecopi.framework.viewmodel.CrudViewModel
import br.com.engecopi.framework.viewmodel.IView
import br.com.engecopi.framework.viewmodel.EViewModel
import br.com.engecopi.utils.localDate
import java.time.LocalDate

class SaidaViewModel(view: IView) : CrudViewModel<SaidaVo>(view, SaidaVo::class) {
  override fun update(bean: SaidaVo) {
    val nota = saveNota(bean)
    
    val produto = bean.produto ?: throw EViewModel("Produto não encontrado no saci")
    
    val item = saveItemNota(bean, nota, produto)
    
    deleteMovimentacoes(item)
    
    geraMovimentacoes(bean, item)
  }
  
  override fun add(bean: SaidaVo) {
    val nota = saveNota(bean)
    
    val produto = bean.produto ?: throw EViewModel("Produto não encontrado no saci")
    
    val item = saveItemNota(bean, nota, produto)
    
    deleteMovimentacoes(item)
    
    geraMovimentacoes(bean, item)
  }
  
  private fun geraMovimentacoes(bean: SaidaVo, item: ItemNota) {
    val seq = bean.sequencia ?: throw  EViewModel("Lote não encontrado")
    val lote = Lote.find(item.nota?.loja, item.produto, seq) ?: throw  EViewModel("Lote não encontrado")
    val quantidade = bean.quantidade ?: throw  EViewModel("Quantidade não informada")
    if (quantidade > lote.saldo)
      throw  EViewModel("Saldo insuficiente no lote")
    else
      Movimentacao().apply {
        this.itemNota = item
        this.lote = lote
        this.quantidade = quantidade
      }.save()
  
    lote.atualizaSaldo()
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
      this.loja = bean.lojaNF
      this.observacao = bean.observacaoNota ?: ""
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
    return ItemNota.where().nota.tipoMov.eq(SAIDA)
            .findList().map { itemNota ->
              SaidaVo().apply {
                val nota = itemNota.nota
                this.numeroNota = nota?.numero
                this.lojaNF = nota?.loja
                this.observacaoNota = nota?.observacao
                this.produto = itemNota.produto
                this.quantidade = itemNota.quantidade
                this.sequencia = itemNota.movimentacoes?.firstOrNull()?.lote?.sequencia
                this.total = itemNota.movimentacoes?.firstOrNull()?.lote?.total
              }
            }
  }
  
  fun findLojas(): List<Loja> = execList {
    Loja.all()
  }
  
  fun findProdutos(): List<Produto> = execList {
    Produto.all()
  }
}

class SaidaVo {
  var numeroNota: String? = ""
  var lojaNF: Loja? = null
  
  val notaSaidaSaci
    get() = Nota.findNotaSaidaSaci(numeroNota, lojaNF)
  
  val dataNF: LocalDate?
    get() = notaSaidaSaci.firstOrNull()?.date?.localDate()
  
  val lojaTransf: Loja?
    get() = Loja.findLoja(notaSaidaSaci.firstOrNull()?.storenoT)
  
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
        sequencia = detalhe?.sequencia
        total = detalhe?.total
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
  
  var sequencia: Int? = null
  
  var total: Int? = null
  
  val sequenciaStr
    get() = detalheBarra?.let { detalhe ->
      "${detalhe.sequencia}/${detalhe.total}"
    } ?: ""
  
  val saldo: Int
    get() = produto?.saldoLoja(lojaNF, sequencia) ?: 0
}