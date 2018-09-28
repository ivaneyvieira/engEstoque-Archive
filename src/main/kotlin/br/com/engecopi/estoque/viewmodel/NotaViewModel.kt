package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.Etiqueta
import br.com.engecopi.estoque.model.ItemNota
import br.com.engecopi.estoque.model.Loja
import br.com.engecopi.estoque.model.Nota
import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.estoque.model.TipoMov
import br.com.engecopi.estoque.model.TipoMov.ENTRADA
import br.com.engecopi.estoque.model.TipoMov.SAIDA
import br.com.engecopi.estoque.model.TipoNota
import br.com.engecopi.estoque.model.TipoNota.OUTROS_E
import br.com.engecopi.estoque.model.Usuario
import br.com.engecopi.estoque.model.query.QItemNota
import br.com.engecopi.estoque.model.updateViewProdutosLoc
import br.com.engecopi.framework.viewmodel.CrudViewModel
import br.com.engecopi.framework.viewmodel.EViewModel
import br.com.engecopi.framework.viewmodel.EntityVo
import br.com.engecopi.framework.viewmodel.IView
import br.com.engecopi.saci.beans.NotaSaci
import br.com.engecopi.utils.localDate
import java.time.LocalDate
import kotlin.reflect.KClass

abstract class NotaViewModel<VO : NotaVo>(
  view: IView,
  val usuario: Usuario,
  classVO: KClass<VO>,
  val tipo: TipoMov
) : CrudViewModel<ItemNota, QItemNota, VO>(view, classVO) {
  init {
    Loja.setLojaDefault(usuario.loja?.numero ?: 0)
  }

  override fun update(bean: VO) {
    val nota = updateNota(bean)
    val produto = saveProduto(bean.produto)

    updateItemNota(bean, nota, produto)
  }

  override fun add(bean: VO) {
    val nota = insertNota(bean)
    val produtos = bean.produtos
    val usuario = bean.usuario ?: throw EViewModel("Usuário não encontrado")
    if (bean.notaSaci == null) {
      val produto = saveProduto(bean.produto)
      insertItemNota(nota, produto, bean.quantProduto ?: 0, usuario)
    } else {
      produtos.filter {
        it.selecionado
      }.forEach { produto ->
        produto.let { prd ->
          if (usuario.temProduto(prd.produto)) insertItemNota(
            nota, prd.produto, prd.quantidade, usuario
          )
        }
      }
    }
  }

  private fun insertItemNota(nota: Nota, produto: Produto?, quantProduto: Int, usuario: Usuario): ItemNota? {
    return if (quantProduto != 0) {
      if (Nota.existNumero(nota, produto)) {
        view
                .showWarning("O produto ${produto?.codigo} - ${produto?.descricao}. Já foi inserido na nota ${nota.numero}.")
        null
      } else {
        val item = ItemNota()
        item.apply {
          this.nota = nota
          this.produto = produto
          this.quantidade = quantProduto
          this.usuario = usuario
        }
        item.insert()
        item.produto?.recalculaSaldos()
        item
      }
    } else null
  }

  private fun updateItemNota(bean: VO, nota: Nota, produto: Produto?) {
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

  private fun updateNota(bean: VO): Nota {
    val nota: Nota = bean.nota ?: Nota()
    nota.apply {
      this.numero = if (bean.numeroNF.isNullOrBlank()) "${Nota.novoNumero()}"
      else bean.numeroNF ?: ""
      this.tipoMov = tipo
      this.tipoNota = bean.tipoNota
      this.loja = bean.lojaNF
      this.data = bean.dataNota
      this.observacao = bean.observacaoNota ?: ""
      this.rota = bean.rota ?: ""
      this.fornecedor = bean.fornecedor
      this.cliente = bean.cliente
    }

    nota.save()
    return nota
  }

  private fun insertNota(bean: VO): Nota {
    val nota: Nota = bean.nota ?: Nota()
    nota.apply {
      this.numero = if (bean.numeroNF.isNullOrBlank()) "${Nota.novoNumero()}"
      else bean.numeroNF ?: ""
      this.tipoMov = tipo
      this.tipoNota = bean.tipoNota
      this.loja = bean.lojaNF
      this.data = bean.dataNota
      this.observacao = bean.observacaoNota ?: ""
      this.rota = bean.rota ?: ""
      this.fornecedor = bean.fornecedor
      this.cliente = bean.cliente
    }
    nota.save()
    return nota
  }

  override val query: QItemNota
    get() {
      updateViewProdutosLoc()
      val query = ItemNota.where()
              .fetch("nota")
              .fetch("usuario")
              .fetch("produto")
              .fetch("produto.vproduto")
              .fetch("produto.viewProdutoLoc")
              .nota.tipoMov.eq(tipo)
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

  abstract fun createVo(): VO

  override fun ItemNota.toVO(): VO {
    val itemNota = this
    return createVo().apply {
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
    val idUser = this@NotaViewModel.usuario.loja?.id
    return nota.numero.eq(text).and().produto.viewProdutoLoc.localizacao.contains(text).produto.viewProdutoLoc.loja.id
            .eq(idUser).endAnd().produto.vproduto.codigo.contains(text).produto.vproduto.nome.contains(text)
  }

  override fun QItemNota.filterDate(date: LocalDate): QItemNota {
    return nota.data.eq(date)
  }

  override fun delete(bean: VO) {
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

abstract class NotaVo(val tipo: TipoMov) : EntityVo<ItemNota>() {
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
  val temGrid
    get() = (tipoNota != OUTROS_E) && (entityVo == null)
  val naoTemGrid
    get() = !temGrid
  var rota: String? = ""
  private val notaProdutoSaci: List<NotaSaci>
    get() = if (entityVo == null) when (tipo) {
      SAIDA   -> Nota.findNotaSaidaSaci(numeroNF, lojaNF)
      ENTRADA -> Nota.findNotaEntradaSaci(numeroNF, lojaNF)
    }
    else emptyList()
  val notaSaci
    get() = notaProdutoSaci.firstOrNull()
  val nota: Nota?
    get() = entityVo?.nota ?: when (tipo) {
      SAIDA   -> Nota.findSaida(numeroNF, lojaNF)
      ENTRADA -> Nota.findEntrada(numeroNF, lojaNF)
    }

  fun atualizaNota() {
    if (entityVo == null) {
      val nota = notaSaci?.let { nota ->
        tipoNota = TipoNota.values().find { it.toString() == nota.tipo } ?: OUTROS_E
        rota = nota.rota
      }
      if (nota == null) {
        tipoNota = OUTROS_E
        rota = ""
      }
      produtos.clear()
      produtos.addAll(notaProdutoSaci.mapNotNull { notaSaci ->
        val prd = Produto.findProduto(notaSaci.prdno, notaSaci.grade)
        if (usuario?.temProduto(prd) == true) ProdutoVO().apply {
          this.codigo = prd?.codigo ?: ""
          this.grade = prd?.grade ?: ""
          this.quantidade = notaSaci.quant ?: 0
        }
        else null
      })
    }
  }

  val dataNota: LocalDate
    get() = toEntity()?.dataNota ?: notaSaci?.date?.localDate() ?: LocalDate.now()
  val numeroInterno: Int
    get() = if (entityVo == null) notaSaci?.invno ?: 0
    else 0
  val fornecedor: String
    get() = entityVo?.nota?.fornecedor ?: notaSaci?.vendName ?: ""
  val cliente: String
    get() = entityVo?.nota?.cliente ?: notaSaci?.clienteName ?: ""
  var observacaoNota: String? = ""
  val produtoNota: List<Produto>
    get() {
      val nota = notaProdutoSaci
      val produtos = if (nota.isNotEmpty()) nota.mapNotNull { notaSaci ->
        Produto.findProduto(notaSaci.prdno, notaSaci.grade)
      }.filter { produto ->
        usuario?.temProduto(produto) ?: false
      }
      else Produto.all().filter { usuario?.temProduto(it) ?: false }
      return produtos.sortedBy { it.codigo + it.grade }
    }
  val quantidadeReadOnly
    get() = notaSaci != null
  val itemNota
    get() = toEntity()
  val produtos = ArrayList<ProdutoVO>()
  var produto: Produto? = null
    set(value) {
      field = value
      quantProduto = toEntity()?.quantidade ?: notaProdutoSaci.firstOrNull { neSaci ->
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

class ProdutoVO {
  var codigo: String = ""
  var grade: String = ""
  var quantidade: Int = 0
  var selecionado: Boolean = false
  val produto: Produto?
    get() = Produto.findProduto(codigo, grade)
  val descricaoProduto: String
    get() = produto?.descricao ?: ""
}
