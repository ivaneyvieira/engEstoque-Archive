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
import br.com.engecopi.estoque.model.updateViewProdutosLoc
import br.com.engecopi.framework.viewmodel.CrudViewModel
import br.com.engecopi.framework.viewmodel.EViewModel
import br.com.engecopi.framework.viewmodel.EntityVo
import br.com.engecopi.framework.viewmodel.IView
import br.com.engecopi.saci.beans.NotaEntradaSaci
import br.com.engecopi.utils.localDate
import java.time.LocalDate

class EntradaViewModel(view: IView, val usuario: Usuario) : CrudViewModel<ItemNota, QItemNota, EntradaVo>(
        view, EntradaVo::class
) {
  init {
    Loja.setLojaDefault(usuario.loja?.numero ?: 0)
  }

  override fun update(bean: EntradaVo) {
    val nota = updateNota(bean)
    val produto = saveProduto(bean.produto)

    updateItemNota(bean, nota, produto)
  }

  override fun add(bean: EntradaVo) {
    val nota = insertNota(bean)
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
          if (usuario.temProduto(produto)) insertItemNota(nota, produto, notaSaci.quant ?: 0, usuario)
        }
      }
    }
  }

  private fun insertItemNota(
          nota: Nota, produto: Produto, quantProduto: Int, usuario: Usuario
  ): ItemNota? {
    return if (quantProduto != 0) {
      if (Nota.existNumero(nota, produto)) {
        view.showWarning("O produto ${produto.codigo} - ${produto.descricao}. Já foi inserido na nota ${nota.numero}.")
        null
      }
      else {
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
    }
    else null
  }

  private fun updateItemNota(
          bean: EntradaVo, nota: Nota, produto: Produto?
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

  private fun updateNota(bean: EntradaVo): Nota {
    val nota: Nota = bean.notaEntrada ?: Nota()
    nota.apply {
      this.numero = if (bean.numeroNF.isNullOrBlank()) "${Nota.novoNumero()}"
      else bean.numeroNF ?: ""
      this.tipoMov = TipoMov.ENTRADA
      this.tipoNota = bean.tipoNota
      this.loja = bean.lojaNF
      this.data = bean.dataNota
      this.observacao = bean.observacaoNota ?: ""
      this.rota = bean.rota ?: ""
      this.fornecedor = bean.fornecedor
    }

    nota.save()
    return nota
  }

  private fun insertNota(bean: EntradaVo): Nota {
    val nota: Nota = bean.notaEntrada ?: Nota()
    nota.apply {
      this.numero = if (bean.numeroNF.isNullOrBlank()) "${Nota.novoNumero()}"
      else bean.numeroNF ?: ""
      this.tipoMov = TipoMov.ENTRADA
      this.tipoNota = bean.tipoNota
      this.loja = bean.lojaNF
      this.data = bean.dataNota
      this.observacao = bean.observacaoNota ?: ""
      this.rota = bean.rota ?: ""
      this.fornecedor = bean.fornecedor
    }
    nota.save()
    return nota
  }

  override val query: QItemNota
    get() {
      updateViewProdutosLoc()
      val query =
              ItemNota.where().fetch("nota").fetch("usuario").fetch("produto").fetch("produto.vproduto")
                      .fetch("produto.viewProdutoLoc").nota.tipoMov.eq(ENTRADA)
      return usuario.let { u ->
        val loja = u.loja
        query.let { q ->
          if (loja == null) q
          else q.nota.loja.id.eq(loja.id)
        }.let { q ->
          if (u.admin) q
          else q.or().produto.viewProdutoLoc.localizacao.isIn(usuario.locais).produto.viewProdutoLoc.abreviacao.isIn(
                  usuario.locais
          ).endOr().produto.viewProdutoLoc.loja.id.eq(loja?.id)
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

  override fun QItemNota.orderQuery(): QItemNota {
    return this.order().id.desc()
  }

  override fun QItemNota.filterString(text: String): QItemNota {
    val idUser = this@EntradaViewModel.usuario.loja?.id
    return nota.numero.eq(text).and().produto.viewProdutoLoc.localizacao.contains(text).produto.viewProdutoLoc.loja.id
            .eq(idUser).endAnd().produto.vproduto.codigo.contains(text).produto.vproduto.nome.contains(text)
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
    itemNota?.let {
      it.impresso = true
      it.save()
    }
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
    get() = if (entityVo == null) Nota.findNotaEntradaSaci(numeroNF, lojaNF)
    else emptyList()
  val notaEntrada: Nota?
    get() = entityVo?.nota ?: Nota.findEntrada(numeroNF, lojaNF)

  fun atualizaNota() {
    if (entityVo == null) {
      notaEntradaSaci.firstOrNull()?.let { nota ->
        tipoNota = TipoNota.values().find { it.toString() == nota.tipo } ?: OUTROS_E
        rota = nota.rota
      }
    }
  }

  val dataNota: LocalDate
    get() = toEntity()?.dataNota ?: notaEntradaSaci.firstOrNull()?.date?.localDate() ?: LocalDate.now()
  val numeroInterno: Int
    get() = if (entityVo == null) notaEntradaSaci.firstOrNull()?.invno ?: 0
    else 0
  val fornecedor: String
    get() = entityVo?.nota?.fornecedor ?: notaEntradaSaci.firstOrNull()?.vendName ?: ""
  var observacaoNota: String? = ""
  val produtoNota: List<Produto>
    get() {
      val nota = notaEntradaSaci
      val produtos = if (nota.isNotEmpty()) nota.mapNotNull { notaSaci ->
        Produto.findProduto(notaSaci.prdno, notaSaci.grade)
      }.filter { produto ->
        usuario?.temProduto(produto) ?: false
      }
      else Produto.all().filter { usuario?.temProduto(it) ?: false }
      return produtos.sortedBy { it.codigo + it.grade }
    }
  val nota: Nota?
    get() = entityVo?.nota ?: Nota.findEntrada(numeroNF ?: "", lojaNF)
  val itemNota
    get() = toEntity()
  val produtos = ArrayList<ProdutoVOEntrada>()
  var produto: Produto? = null
    set(value) {
      field = value
      quantProduto = toEntity()?.quantidade ?: notaEntradaSaci.firstOrNull { neSaci ->
        (neSaci.prdno ?: "") == (value?.codigo?.trim() ?: "") && (neSaci.grade ?: "") == (value?.grade ?: "")
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
}

class ProdutoVOEntrada {
  var nota: Nota? = null
  var codigo: String = ""
  var descricao: String = ""
  var grade: String = ""
  var quantidade: Int = 0
  var selecionado: Boolean = true
  val produto: Produto?
    get() = Produto.findProduto(codigo, grade)
  val descricaoProduto: String
    get() = produto?.descricao ?: ""
  var quantProduto: Int? = 0
  val saldo: Int
    get() = produto?.saldoLoja(nota?.loja) ?: 0
  val localizacao
    get() = produto?.localizacao(nota?.loja)
}
