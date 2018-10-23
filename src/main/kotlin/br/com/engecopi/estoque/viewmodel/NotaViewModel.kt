package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.Etiqueta
import br.com.engecopi.estoque.model.ItemNota
import br.com.engecopi.estoque.model.LocProduto
import br.com.engecopi.estoque.model.Loja
import br.com.engecopi.estoque.model.Nota
import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.estoque.model.RegistryUserInfo
import br.com.engecopi.estoque.model.RegistryUserInfo.abreviacaoDefault
import br.com.engecopi.estoque.model.RegistryUserInfo.lojaDefault
import br.com.engecopi.estoque.model.RegistryUserInfo.usuarioDefault
import br.com.engecopi.estoque.model.Repositories
import br.com.engecopi.estoque.model.Repositories.updateViewProdutosLoc
import br.com.engecopi.estoque.model.TipoMov
import br.com.engecopi.estoque.model.TipoMov.ENTRADA
import br.com.engecopi.estoque.model.TipoMov.SAIDA
import br.com.engecopi.estoque.model.TipoNota
import br.com.engecopi.estoque.model.TipoNota.OUTROS_E
import br.com.engecopi.estoque.model.Usuario
import br.com.engecopi.estoque.model.ViewProdutoLoc
import br.com.engecopi.estoque.model.findLocalizacao
import br.com.engecopi.estoque.model.query.QItemNota
import br.com.engecopi.framework.viewmodel.CrudViewModel
import br.com.engecopi.framework.viewmodel.EViewModel
import br.com.engecopi.framework.viewmodel.EntityVo
import br.com.engecopi.framework.viewmodel.IView
import br.com.engecopi.saci.beans.NotaSaci
import br.com.engecopi.utils.localDate
import java.time.LocalDate
import kotlin.reflect.KClass

abstract class NotaViewModel<VO : NotaVo>(view: IView, classVO: KClass<VO>, val tipo: TipoMov) :
  CrudViewModel<ItemNota, QItemNota, VO>(view, classVO) {
  override fun update(bean: VO) {
    if (bean.localizacao?.localizacao.isNullOrBlank())
      throw EViewModel("Não foi especificado a localização do item")
    val nota = updateNota(bean)
    val produto = saveProduto(bean.produto)

    updateItemNota(bean, nota, produto)
  }

  override fun add(bean: VO) {
    val nota = insertNota(bean)
    val produtos = bean.produtos
    val usuario = bean.usuario
    if (bean.notaSaci == null) {
      val produto = saveProduto(bean.produto)
      insertItemNota(nota, produto, bean.quantProduto ?: 0, usuario, bean.localizacao?.localizacao)
    } else {
      produtos.filter { it.selecionado && it.quantidade != 0 }
        .forEach { produto ->
          produto.let { prd ->
            if (usuario.temProduto(prd.produto)) insertItemNota(nota,
                                                                prd.produto,
                                                                prd.quantidade,
                                                                usuario,
                                                                prd.localizacao)
          }
        }
    }
  }

  private fun insertItemNota(nota: Nota, produto: Produto?, quantProduto: Int, usuario3: Usuario,
                             local: String?): ItemNota? {
    if (local.isNullOrBlank())
      throw EViewModel("Não foi especificado a localização do item")
    val saldoLocal = produto?.saldoLoja(local) ?: 0
    return if (quantProduto != 0) {
      when {
        Nota.existNumero(nota, produto, local)                         -> {
          val msg = "O produto ${produto?.codigo} - ${produto?.descricao}. Já foi inserido na nota ${nota.numero}."
          view.showWarning(msg)
          null
        }
        (saldoLocal + (nota.tipoMov.multiplicador * quantProduto)) < 0 -> {
          val msg = "Saldo insuficiente para o produto ${produto?.codigo} - ${produto?.descricao}."
          view.showWarning(msg)
          null
        }
        else                                                           -> {
          val item = ItemNota()
          item.apply {
            this.nota = nota
            this.produto = produto
            this.quantidade = quantProduto
            this.usuario = usuario3
            this.localizacao = local ?: ""
          }
          item.insert()
          item.produto?.recalculaSaldos(local)
          item
        }
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
      item.produto?.recalculaSaldos(bean.localizacao?.localizacao)
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
      this.dataEmissao = bean.dataEmissao
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
      this.dataEmissao = bean.dataEmissao
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
      Repositories.updateViewProdutosLoc()
      val query = ItemNota.where()
        .fetch("nota")
        .fetch("usuario")
        .fetch("produto")
        .fetch("produto.vproduto")
        .fetch("produto.viewProdutoLoc")
        .nota.tipoMov.eq(tipo)
      return query
        .nota.loja.id.eq(lojaDefault.id)
        .localizacao.startsWith(abreviacaoDefault)
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
      this.usuario = itemNota.usuario ?: usuarioDefault
      this.localizacao = this.produto?.makeLocProduto(itemNota.localizacao)

    }
  }

  override fun QItemNota.orderQuery(): QItemNota {
    return this.order().id.desc()
  }

  override fun QItemNota.filterString(text: String): QItemNota {
    val idLoja = RegistryUserInfo.lojaDefault.id
    return nota.numero.eq(text)
      .and()
      .produto.viewProdutoLoc.localizacao.contains(text)
      .produto.viewProdutoLoc.loja.id.eq(idLoja)
      .endAnd()
      .produto.vproduto.codigo.contains(text)
      .produto.vproduto.nome.contains(text)
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

  fun localizacaoes(): List<String> {
    return ViewProdutoLoc.localizacoes(abreviacaoDefault)
  }

  fun imprimir(itemNota: ItemNota?) = execString {
    val template = Etiqueta.template(itemNota?.tipoMov)
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

  var usuario: Usuario = RegistryUserInfo.usuarioDefault
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
    get() = if (entityVo == null)
      when (tipo) {
        SAIDA   -> Nota.findNotaSaidaSaci(numeroNF)
        ENTRADA -> Nota.findNotaEntradaSaci(numeroNF)
      }
    else emptyList()
  val notaSaci
    get() = notaProdutoSaci.firstOrNull()
  val nota: Nota?
    get() = entityVo?.nota ?: when (tipo) {
      SAIDA   -> Nota.findSaida(numeroNF)
      ENTRADA -> Nota.findEntrada(numeroNF)
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
      val produtosVo = notaProdutoSaci.flatMap { notaSaci ->
        val prd = Produto.findProduto(notaSaci.prdno, notaSaci.grade)
        val localizacoes = prd?.localizacoes().orEmpty().sorted()
        val ultimaLocalizacao = localizacoes.lastOrNull()
        val prdLocs: List<ProdutoVO> = if (tipoNota.tipoMov == SAIDA) {
          var quant = notaSaci.quant ?: 0
          val produtosLocais = localizacoes.asSequence().map { localizacao ->
            ProdutoVO(prd, tipoNota.tipoMov).apply {
              this.localizacao = localizacao
              val saldo = this.saldo
              if (quant > 0)
                if (quant > saldo) {
                  if (localizacao == ultimaLocalizacao) {
                    this.quantidade = quant
                    quant = 0
                  } else {
                    this.quantidade = saldo
                    quant = saldo - quant
                  }
                } else {
                  this.quantidade = quant
                  quant = 0
                }
              else
                this.quantidade = 0
            }
          }.toList()
          produtosLocais
        } else
          listOf(ProdutoVO(prd, tipoNota.tipoMov).apply {
            this.localizacao = if (localizacoes.size == 1) localizacoes[0] else ""
            this.quantidade = notaSaci.quant ?: 0
          })
        prdLocs
      }
      produtos.addAll(
        produtosVo.asSequence()
          .filter { it.quantidade != 0 }
          .sortedWith(compareBy(ProdutoVO::codigo,
                                ProdutoVO::grade,
                                ProdutoVO::localizacao))
          .toList())
    }
  }

  val dataNota: LocalDate
    get() = toEntity()?.dataNota ?: notaSaci?.date?.localDate() ?: LocalDate.now()
  val dataEmissao: LocalDate
    get() = toEntity()?.nota?.dataEmissao ?: notaSaci?.dt_emissao?.localDate() ?: LocalDate.now()
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
      if (entityVo != null)
        return emptyList()
      val nota = notaProdutoSaci
      val produtos = if (nota.isNotEmpty())
        nota.asSequence().mapNotNull { notaSaci ->
          Produto.findProduto(notaSaci.prdno, notaSaci.grade)
        }.filter { produto ->
          usuario.temProduto(produto)
        }.toList()
      else ViewProdutoLoc.produtos() // Produto.all().filter { usuario.temProduto(it) }
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
    get() = produto?.saldoLoja(localizacao?.localizacao) ?: 0
  var localizacao: LocProduto? = null
  val localizacaoProduto
    get() = produto?.sufixosLocalizacaoes().orEmpty()
}

class ProdutoVO(val produto: Produto?, val tipoMov: TipoMov) {
  val codigo: String = produto?.codigo ?: ""
  val grade: String = produto?.grade ?: ""
  var quantidade: Int = 0
  var selecionado: Boolean = false
  val saldoInsuficiente: Boolean
    get() = if (tipoMov == SAIDA)
      saldo < quantidade
    else
      false
  var localizacao: String = "" //TODO
  val saldo: Int
    get() = produto?.saldoLoja(localizacao) ?: 0
  val descricaoProduto: String
    get() = produto?.descricao ?: ""
}
