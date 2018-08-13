package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.Etiqueta
import br.com.engecopi.estoque.model.ItemNota
import br.com.engecopi.estoque.model.Loja
import br.com.engecopi.estoque.model.Nota
import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.estoque.model.TipoMov
import br.com.engecopi.estoque.model.TipoMov.ENTRADA
import br.com.engecopi.estoque.model.TipoNota
import br.com.engecopi.estoque.model.TipoNota.OUTROS_E
import br.com.engecopi.estoque.model.Usuario
import br.com.engecopi.estoque.model.query.QItemNota
import br.com.engecopi.framework.viewmodel.CrudViewModel
import br.com.engecopi.framework.viewmodel.EViewModel
import br.com.engecopi.framework.viewmodel.EntityVo
import br.com.engecopi.framework.viewmodel.IView
import br.com.engecopi.saci.beans.NotaEntradaSaci
import br.com.engecopi.utils.localDate
import java.time.LocalDate

class EntradaViewModel(view: IView, val usuario: Usuario?) :
        CrudViewModel<ItemNota, QItemNota, EntradaVo>(view, EntradaVo::class) {
  override fun update(bean: EntradaVo) {
    val nota = saveNota(bean)
    
    val produto = saveProduto(bean.produto)
    
    updateItemNota(bean, nota, produto)
  }
  
  override fun add(bean: EntradaVo) {
    val nota = saveNota(bean)
    val notasSaci = bean.notaEntradaSaci
    val usuario = bean.usuario ?: throw EViewModel("Usuário não encontrado")
    if (notasSaci.isEmpty()) {
      val produto = saveProduto(bean.produto)
      insertItemNota(nota, produto, bean.quantProduto ?: 0, usuario)
    }
    else {
      notasSaci.forEach { notaSaci ->
        val produto = Produto.findProduto(notaSaci.prdno, notaSaci.grade)
        produto?.let {
          if (usuario.temProduto(produto))
            insertItemNota(nota, produto, notaSaci.quant ?: 0, usuario)
        }
      }
    }
  }
  
  private fun insertItemNota(
          nota: Nota,
          produto: Produto,
          quantProduto: Int,
          usuario: Usuario
                            ): ItemNota? {
    return if (quantProduto != 0) {
      val item = ItemNota.find(nota, produto) ?: ItemNota()
      item.apply {
        this.nota = nota
        this.produto = produto
        this.quantidade = quantProduto
        this.usuario = usuario
      }
      item.save()
      item.produto?.recalculaSaldos()
      item
    }
    else
      null
  }
  
  private fun updateItemNota(
          bean: EntradaVo, nota: Nota,
          produto: Produto?
                            ) {
    bean.toEntity()?.let { item ->
      item.apply {
        this.nota = nota
        this.produto = produto
        this.quantidade = bean.quantProduto ?: 0
      }
      item.update()
      item.produto?.recalculaSaldos()
    }
  }
  
  private fun saveProduto(produto: Produto?): Produto {
    produto ?: throw EViewModel("Produto não encontrado no saci")
    
    return produto.apply {
      save()
    }
  }
  
  private fun saveNota(bean: EntradaVo): Nota {
    val nota: Nota = bean.notaEntrada ?: Nota()
    nota.apply {
      this.numero = if (bean.numeroNF.isNullOrBlank())
        "${Nota.novoNumero()}"
      else bean.numeroNF ?: ""
      this.tipoMov = TipoMov.ENTRADA
      this.tipoNota = bean.tipoNota
      this.loja = bean.lojaNF
      this.data = bean.dataNota
      this.observacao = bean.observacaoNota ?: ""
      this.rota = bean.rota ?: ""
    }
    
    nota.save()
    return nota
  }
  
  override val query: QItemNota
    get() {
      val query = ItemNota.where().nota.tipoMov.eq(ENTRADA)
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
  
  override fun ItemNota.toVO(): EntradaVo {
    val itemNota = this
    return EntradaVo().apply {
      entityVo = itemNota
      val nota = itemNota.nota
      this.numeroNF = nota?.numero
      this.lojaNF = nota?.loja
      this.observacaoNota = nota?.observacao
      this.produto = itemNota.produto
      this.tipoNota = itemNota.nota?.tipoNota ?: OUTROS_E
      this.rota = itemNota.nota?.rota
      this.usuario = itemNota.usuario
    }
  }
  
  override fun QItemNota.orderQuery(filter: String): QItemNota {
    return this.order().id.desc()
  }
  
  override fun QItemNota.filterString(text: String): QItemNota {
    val idUser = this@EntradaViewModel.usuario?.loja?.id
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
  
  override fun delete(bean: EntradaVo) {
    bean.toEntity()?.also { item ->
      item.delete()
    }
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

class EntradaVo : EntityVo<ItemNota>() {
  override fun findEntity(): ItemNota? {
    return ItemNota.find(nota, produto)
  }
  
  var usuario: Usuario? = null
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
  
  val notaEntrada: Nota?
    get() = Nota.findEntrada(numeroNF, lojaNF)
  
  fun atualizaNota() {
    notaEntradaSaci.firstOrNull()?.let { nota ->
      tipoNota = TipoNota.values().find { it.toString() == nota.tipo } ?: OUTROS_E
      rota = nota.rota
    }
  }
  
  val dataNota: LocalDate
    get() = notaEntradaSaci.firstOrNull()?.date?.localDate()
            ?: toEntity()?.data
            ?: LocalDate.now()
  
  val numeroInterno: Int
    get() = notaEntradaSaci.firstOrNull()?.invno ?: 0
  
  val fornecedor: String
    get() =  itemNota?.nota?.fornecedor ?: notaEntradaSaci.firstOrNull()?.vendName ?: ""
  
  var observacaoNota: String? = ""
  
  val produtoNota: List<Produto>
    get() {
      val nota = notaEntradaSaci
      return if (nota.isNotEmpty())
        nota.mapNotNull { notaSaci ->
          Produto.findProduto(notaSaci.prdno, notaSaci.grade)
        }.filter { produto ->
          usuario?.temProduto(produto) ?: false
        }
      else
        Produto.all().filter { usuario?.temProduto(it) ?: false }
    }
  
  var produto: Produto? = null
    set(value) {
      field = value
      quantProduto = toEntity()?.quantidade
              ?: notaEntradaSaci.firstOrNull { neSaci ->
        (neSaci.prdno ?: "") == (value?.codigo?.trim() ?: "") &&
        (neSaci.grade ?: "") == (value?.grade ?: "")
      }?.quant ?: 0
    }
  
  val descricaoProduto: String
    get() = produto?.descricao ?: ""
  
  val codigo: String
    get() = produto?.codigo ?: ""
  
  val grade: String
    get() = produto?.grade ?: ""
  
  var quantProduto: Int? = 0
  
  val saldo: Int
    get() = produto?.saldoLoja(lojaNF) ?: 0
  
  val localizacao
    get() = produto?.localizacao(lojaNF)
  
  val nota: Nota?
    get() = Nota.findEntrada(numeroNF ?: "", lojaNF)
  
  val itemNota
    get() = findEntity()
}
