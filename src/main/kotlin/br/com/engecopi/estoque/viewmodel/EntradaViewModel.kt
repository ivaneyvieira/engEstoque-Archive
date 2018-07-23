package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.ItemNota
import br.com.engecopi.estoque.model.Loja
import br.com.engecopi.estoque.model.Nota
import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.estoque.model.TipoMov.ENTRADA
import br.com.engecopi.estoque.model.TipoNota
import br.com.engecopi.estoque.model.TipoNota.OUTROS_E
import br.com.engecopi.framework.viewmodel.CrudViewModel
import br.com.engecopi.framework.viewmodel.EViewModel
import br.com.engecopi.framework.viewmodel.IView
import br.com.engecopi.saci.beans.NotaEntradaSaci
import br.com.engecopi.saci.beans.ProdutoSaci
import br.com.engecopi.saci.saci
import br.com.engecopi.utils.localDate
import java.time.LocalDate

class EntradaViewModel(view: IView, val lojaDefault: Loja?) : CrudViewModel<EntradaVo>(view, EntradaVo::class) {
  override fun update(bean: EntradaVo) {
    val nota = saveNota(bean)
    
    val produto = saveProduto(bean)
    
    updateItemNota(bean, nota, produto)
  }
  
  override fun add(bean: EntradaVo) {
    val nota = saveNota(bean)
    
    val produto = saveProduto(bean)
    
    insertItemNota(bean, nota, produto)
  }
  
  private fun insertItemNota(
          bean: EntradaVo, nota: Nota,
          produto: Produto?
                            ): ItemNota {
    val item = bean.itemNota ?: ItemNota()
    item.apply {
      this.nota = nota
      this.produto = produto
      this.quantidade = bean.quantProduto ?: 0
    }
    item.insert()
    return item
  }
  
  private fun updateItemNota(
          bean: EntradaVo, nota: Nota,
          produto: Produto?
                            ) {
    bean.itemNota?.let { item ->
      item.apply {
        this.nota = nota
        this.produto = produto
        this.quantidade = bean.quantProduto ?: 0
      }
      item.update()
    }
  }
  
  private fun saveProduto(bean: EntradaVo): Produto? {
    bean.produtoSaci ?: throw EViewModel("Produto não encontrado no saci")
    val produto = bean.produto(bean.produtoSaci)
    return produto?.apply {
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
                this.tipoNota = itemNota?.nota?.tipoNota ?: OUTROS_E
                this.rota = itemNota?.nota?.rota
              }
            }
  }
  
  override fun delete(bean: EntradaVo) {
    bean.itemNota?.also { item ->
      item.delete()
    }
  }
  
  fun findLojas(loja: Loja?): List<Loja> = execList {
    loja?.let { listOf(it) } ?: Loja.all()
  }
  
  fun imprimir(itemNota: ItemNota) = execString {
    val template = itemNota.template
    val print = itemNota.printEtiqueta()
    val etiqueta = EtiquetaPrinter(template, print)
    etiqueta.print()
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
        val prdSaci = saci.findProduto(it.prdno ?: "")
                .firstOrNull { it.grade == grade }
        produto(prdSaci)?.let { prdSaci }
      }
    }
  
  var produtoSaci: ProdutoSaci? = null
  
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
  
  val saldo: Int
    get() = produto(produtoSaci)?.saldoLoja(lojaNF) ?: 0
  
  val nota: Nota?
    get() = Nota.findEntrada(numeroNF ?: "", lojaNF)
  
  val itemNota: ItemNota?
    get() = ItemNota.find(nota, produto(produtoSaci))
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