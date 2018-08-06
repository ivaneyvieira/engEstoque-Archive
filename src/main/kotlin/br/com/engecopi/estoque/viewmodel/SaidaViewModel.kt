package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.Etiqueta
import br.com.engecopi.estoque.model.ItemNota
import br.com.engecopi.estoque.model.Loja
import br.com.engecopi.estoque.model.Nota
import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.estoque.model.TipoMov.SAIDA
import br.com.engecopi.estoque.model.TipoNota
import br.com.engecopi.estoque.model.TipoNota.OUTROS_S
import br.com.engecopi.estoque.model.Usuario
import br.com.engecopi.estoque.model.query.QItemNota
import br.com.engecopi.framework.viewmodel.CrudViewModel
import br.com.engecopi.framework.viewmodel.EViewModel
import br.com.engecopi.framework.viewmodel.IView
import br.com.engecopi.saci.saci
import br.com.engecopi.utils.localDate
import java.time.LocalDate

class SaidaViewModel(view: IView, val usuario: Usuario?) :
        CrudViewModel<ItemNota, QItemNota, SaidaVo>(view, SaidaVo::class) {
  override fun update(bean: SaidaVo) {
    val nota = saveNota(bean)
    
    val produto = bean.produto ?: throw EViewModel("Produto não encontrado no saci")
    
    updateItemNota(bean, nota, produto)
  }
  
  override fun add(bean: SaidaVo) {
    val nota = saveNota(bean)
    
    val notasSaida = bean.notaSaidaSaci
    val usuario = bean.usuario ?: throw EViewModel("Usuário não encontrado")
    if (notasSaida.isEmpty()) {
      val produto = bean.produto ?: throw EViewModel("Produto não encontrado no saci")
      addItemNota(nota, produto, bean.quantidade ?: 0, usuario)
    }
    else {
      notasSaida.forEach { notaSaida ->
        val produto = Produto.findProduto(notaSaida.prdno, notaSaida.grade)
        if (produto != null && usuario.temProduto(produto))
          addItemNota(nota, produto, notaSaida.quant ?: 0, usuario)
      }
    }
  }
  
  private fun addItemNota(
          nota: Nota,
          produto: Produto,
          quantidade: Int,
          usuario: Usuario
                         ): ItemNota {
    val item = ItemNota.find(nota, produto)?: ItemNota()
    item.apply {
      this.nota = nota
      this.produto = produto
      this.quantidade = quantidade
      this.usuario = usuario
    }
    item.save()
    item.produto?.recalculaSaldos()
    return item
  }
  
  private fun updateItemNota(
          bean: SaidaVo, nota: Nota,
          produto: Produto
                            ) {
    ItemNota.find(nota, produto)?.let { item ->
      item.apply {
        this.nota = nota
        this.produto = produto
        this.quantidade = bean.quantidade ?: 0
      }
      item.save()
      item.produto?.recalculaSaldos()
    }
  }
  
  private fun saveNota(bean: SaidaVo): Nota {
    val nota: Nota = bean.notaSaida ?: Nota()
    nota.apply {
      this.numero = if (bean.numeroNota.isNullOrBlank())
        "${Nota.novoNumero()}"
      else bean.numeroNota ?: ""
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
      item.delete()
    }
  }
  
  override val query: QItemNota
    get() {
      val query = ItemNota.where().nota.tipoMov.eq(SAIDA)
      return usuario?.let { u ->
        query.let { q ->
          val loja = u.loja
          if (loja == null) q
          else q.nota.loja.id.eq(loja.id)
        }.let { q ->
          if (u.isAdmin) q
          else q.usuario.id.eq(usuario.id)
        }
      } ?: query
    }
  
  override fun ItemNota.toVO(): SaidaVo {
    val itemNota = this
    return SaidaVo().apply {
      val nota = itemNota.nota
      this.numeroNota = nota?.numero
      this.lojaNF = nota?.loja
      this.observacaoNota = nota?.observacao
      this.produto = itemNota.produto
      this.quantidade = itemNota.quantidade
      this.tipoNota = itemNota.nota?.tipoNota ?: OUTROS_S
      this.usuario = itemNota.usuario
    }
  }
  
  override fun QItemNota.filterString(text: String): QItemNota {
    return nota.numero.eq(text)
  }
  
  override fun QItemNota.filterDate(date: LocalDate): QItemNota {
    return nota.data.eq(date)
  }
  
  fun findLojas(loja: Loja?): List<Loja> = execList {
    loja?.let { listOf(it) } ?: Loja.all()
  }
  
  fun imprimir(itemNota: ItemNota?) = execString {
    val template = Etiqueta.where().tipoMov.eq(itemNota?.tipoMov).findOne()?.template
    val print = itemNota?.printEtiqueta()
    print?.print(template ?: "") ?: ""
  }
}

class SaidaVo {
  var usuario: Usuario? = null
  
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
  val clienteName
    get() = notaSaidaSaci.firstOrNull()?.clienteName ?: observacaoNota
  
  fun atualizaNota() {
    notaSaidaSaci.firstOrNull()?.let { nota ->
      tipoNota = TipoNota.values().find { it.toString() == nota.tipo } ?: OUTROS_S
      rota = nota.rota
    }
  }
  
  val listaProdutos: List<Produto>
    get() {
      val notaSaci = notaSaidaSaci
      val lista = if (notaSaci.isEmpty())
        Produto.all()
      else
        notaSaci.mapNotNull { Produto.findProduto(it.prdno, it.grade) }
      return lista.filter { usuario?.temProduto(it) ?: false }
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
    }
  
  val localizacao
    get() = saci.findLocStr(lojaNF?.numero, produto?.codigo, produto?.grade)
  
  var produto: Produto? = null
    set(value) {
      notaSaidaSaci
              .find {
                it.prdno == value?.codigo?.trim()
                && it.grade == value?.grade
              }?.let { prd ->
                quantidade = prd.quant
              }
      
      field = value
    }
  
  val descricaoProduto: String?
    get() = produto?.descricao
  
  val codigo: String?
    get() = produto?.codigo
  
  val grade: String?
    get() = produto?.grade
  
  val quantidadeReadOnly
    get() = !notaSaidaSaci.isEmpty()
  
  var quantidade: Int? = 0
  
  val itemNota: ItemNota?
    get() = ItemNota.find(notaSaida, produto)
}
