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
import br.com.engecopi.estoque.model.updateViewProdutosLoc
import br.com.engecopi.estoque.ui.EstoqueUI.Companion.loja
import br.com.engecopi.framework.viewmodel.CrudViewModel
import br.com.engecopi.framework.viewmodel.EViewModel
import br.com.engecopi.framework.viewmodel.EntityVo
import br.com.engecopi.framework.viewmodel.IView
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
                         ): ItemNota? {
    
    val saldo = produto.saldoLoja(nota.loja) - quantidade
    return when {
      saldo < 0       -> {
        view.showWarning("O produto ${produto.codigo} - ${produto.descricao}. Está com saldo insuficiente.")
        null
      }
      quantidade == 0 -> {
        view.showWarning("O produto ${produto.codigo} - ${produto.descricao}. Está com quantidade zerada.")
        null
      }
      else            -> {
        val item = ItemNota.find(nota, produto) ?: ItemNota()
        item.apply {
          this.nota = nota
          this.produto = produto
          this.quantidade = quantidade
          this.usuario = usuario
        }
        item.save()
        item.produto?.recalculaSaldos()
        item
      }
    }
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
      this.cliente = bean.clienteName ?: ""
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
      updateViewProdutosLoc()
      val query = ItemNota
              .where()
              .fetch("nota")
              .fetch("produto")
              .fetch("produto.vproduto")
              .fetch("produto.viewProdutoLoc")
              .nota.tipoMov.eq(SAIDA)
      return usuario?.let { u ->
        val loja = u.loja
        query.let { q ->
          if (loja == null) q
          else q.nota.loja.id.eq(loja.id)
        }.let { q ->
          if (u.admin) q
          else q.or()
                  .produto.viewProdutoLoc.localizacao.isIn(usuario.locais)
                  .produto.viewProdutoLoc.abreviacao.isIn(usuario.locais)
                  .endOr()
                  .produto.viewProdutoLoc.loja.id.eq(loja?.id)
        }
      } ?: query
    }
  
  override fun ItemNota.toVO(): SaidaVo {
    val itemNota = this
    return SaidaVo().apply {
      entityVo = itemNota
      val nota = itemNota.nota
      this.numeroNota = nota?.numero
      this.lojaNF = nota?.loja
      this.observacaoNota = nota?.observacao
      this.produto = itemNota.produto
      this.quantidade = itemNota.quantidade
      this.rota = itemNota.rota
      this.tipoNota = itemNota.nota?.tipoNota ?: OUTROS_S
      this.usuario = itemNota.usuario
    }
  }
  
  override fun QItemNota.orderQuery(): QItemNota {
    return this.order().id.desc()
  }
  
  override fun QItemNota.filterString(text: String): QItemNota {
    val idUser = this@SaidaViewModel.usuario?.loja?.id
    return nota.numero.eq(text)
            .and()
            .produto.viewProdutoLoc.localizacao.contains(text)
            .produto.viewProdutoLoc.loja.id.eq(idUser)
            .endAnd()
            .produto.vproduto.codigo.contains(text)
            .produto.vproduto.nome.contains(text)
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
    itemNota?.let {
      it.impresso = true
      it.save()
    }
    print?.print(template ?: "") ?: ""
  }
}

class SaidaVo : EntityVo<ItemNota>() {
  override fun findEntity(): ItemNota? {
    return ItemNota.find(notaSaida, produto)
  }
  
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
    get() = itemNota?.nota?.cliente ?: notaSaidaSaci.firstOrNull()?.clienteName ?: observacaoNota
  
  fun atualizaNota() {
    if (itemNota == null)
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
      return lista.filter { usuario?.temProduto(it) ?: false }.sortedBy { it.codigo + it.grade }
    }
  
  val notaSaidaSaci
    get() = Nota.findNotaSaidaSaci(numeroNota, lojaNF)
  
  val dataNF: LocalDate
    get() = entityVo?.dataNota ?: notaSaidaSaci.firstOrNull()?.date?.localDate() ?: LocalDate.now()
  
  val notaSaida: Nota?
    get() = entityVo?.nota ?: Nota.findSaida(numeroNota, lojaNF)
  
  var observacaoNota: String? = ""
  
  val localizacao
    get() = produto?.localizacao(lojaNF)
  
  var produto: Produto? = null
    set(value) {
      if (entityVo == null)
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
    get() = toEntity()
}
