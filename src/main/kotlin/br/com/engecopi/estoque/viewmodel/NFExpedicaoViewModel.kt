package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.Etiqueta
import br.com.engecopi.estoque.model.ItemNota
import br.com.engecopi.estoque.model.Loja
import br.com.engecopi.estoque.model.Nota
import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.estoque.model.RegistryUserInfo
import br.com.engecopi.estoque.model.StatusNota.INCLUIDA
import br.com.engecopi.estoque.model.TipoMov
import br.com.engecopi.estoque.model.TipoMov.ENTRADA
import br.com.engecopi.estoque.model.TipoNota
import br.com.engecopi.estoque.model.Usuario
import br.com.engecopi.estoque.model.ViewNotaExpedicao
import br.com.engecopi.estoque.model.ViewProdutoLoc
import br.com.engecopi.estoque.model.query.QViewNotaExpedicao
import br.com.engecopi.estoque.ui.log
import br.com.engecopi.framework.viewmodel.CrudViewModel
import br.com.engecopi.framework.viewmodel.EViewModel
import br.com.engecopi.framework.viewmodel.EntityVo
import br.com.engecopi.framework.viewmodel.IView
import java.time.LocalDate
import java.time.LocalTime

class NFExpedicaoViewModel(view: IView): CrudViewModel<ViewNotaExpedicao, QViewNotaExpedicao, NFExpedicaoVo>(view) {
  override fun newBean(): NFExpedicaoVo {
    return NFExpedicaoVo()
  }

  override fun update(bean: NFExpedicaoVo): NFExpedicaoVo {
    log?.error("Atualização não permitida")
    return bean
  }

  override fun add(bean: NFExpedicaoVo): NFExpedicaoVo {
    log?.error("Inserssão não permitida")
    return bean
  }

  override fun delete(bean: NFExpedicaoVo) {
    val nota = bean.findEntity() ?: return
    val saida = Nota.findSaida(nota.numero)
    //TODO Refatorar
    ItemNota.where()
      .nota.equalTo(saida)
      .localizacao.startsWith(bean.abreviacao)
      .delete()
  }

  override val query: QViewNotaExpedicao
    get() = ViewNotaExpedicao.where()

  override fun QViewNotaExpedicao.orderQuery(): QViewNotaExpedicao {
    return this.order()
      .lancamento.desc()
      .id.desc()
  }

  override fun ViewNotaExpedicao.toVO(): NFExpedicaoVo {
    val bean = this
    return NFExpedicaoVo().apply {
      numero = bean.numero
      tipoMov = bean.tipoMov
      tipoNota = bean.tipoNota
      rota = bean.rota
      fornecedor = bean.fornecedor
      cliente = bean.cliente
      data = bean.data
      dataEmissao = bean.dataEmissao
      lancamento = bean.lancamento
      hora = bean.hora
      observacao = bean.observacao
      loja = bean.loja
      sequencia = bean.sequencia
      usuario = bean.usuario
      abreviacao = bean.abreviacao
    }
  }

  fun processaKey(key: String, abreviacoes: List<String>) = execValue {
    val notasSaci = findNotaSaidaKey(key)
    if(notasSaci.isNotEmpty()) {
      val loja = RegistryUserInfo.lojaDefault.numero
      val lojaSaci = notasSaci.firstOrNull()?.storeno ?: 0
      if(loja != lojaSaci) throw EViewModel("Esta nota pertence a loja $lojaSaci")
      val nota = Nota.createNota(notasSaci.firstOrNull())
        ?.apply {
          if(this.existe()) throw EViewModel("Essa nota já está cadastrada")
          else {
            val serie = numero.split("/").getOrNull(1) ?: ""
            sequencia = Nota.maxSequencia(serie) + 1
            usuario = RegistryUserInfo.usuarioDefault
            save()
          }
        }
      if(nota == null) throw EViewModel("Nota não encontrada")
      else {
        val itens = notasSaci.mapNotNull {notaSaci ->
          val item = ItemNota.find(notaSaci) ?: ItemNota.createItemNota(notaSaci, nota)
          val abreviacao = item?.abreviacao
          return@mapNotNull if(abreviacoes.contains(abreviacao)) item?.apply {
            status = INCLUIDA
            impresso = false
            usuario = RegistryUserInfo.usuarioDefault
            save()
          }
          else null
        }

        if(itens.isEmpty()) throw EViewModel("Essa nota não possui itens com localização")

        return@execValue nota
      }
    } else throw EViewModel("Chave não encontrada")
  }

  private fun imprimir(itemNota: ItemNota?, template: String): String {
    itemNota ?: return ""
    val print = itemNota.printEtiqueta()
    itemNota.let {
      it.refresh()
      it.impresso = true
      it.update()
    }
    return print.print(template)
  }

  fun imprimir(nota: Nota?): String {
    nota ?: return ""
    nota.refresh()
    val templates = Etiqueta.templates(INCLUIDA)
    val itens = nota.itensNota ?: emptyList()

    return templates.joinToString(separator = "\n") {template ->
      itens.map {imprimir(it, template)}
        .distinct()
        .joinToString(separator = "\n")
    }
  }

  fun imprimir(nota: NFExpedicaoVo?): String {
    val templates = Etiqueta.templates(INCLUIDA)
    val itens = nota?.findEntity()?.findItens() ?: emptyList()

    return templates.joinToString(separator = "\n") {template ->
      itens.map {imprimir(it, template)}
        .distinct()
        .joinToString(separator = "\n")
    }
  }

  fun imprimir(itemNota: ItemNota?): String {
    itemNota ?: return ""
    val templates = itemNota.templates

    return templates.joinToString(separator = "\n") {template ->
      imprimir(itemNota, template)
    }
  }

  fun imprime() = execString {
    val templates = Etiqueta.templates(INCLUIDA)
    //TODO Refatorar
    val itens = ItemNota.where()
      .impresso.eq(false)
      .status.eq(INCLUIDA)
      .findList()
    templates.joinToString(separator = "\n") {template ->
      itens.map {imprimir(it, template)}
        .distinct()
        .joinToString(separator = "\n")
    }
  }

  fun findNotaSaidaKey(key: String) = execList {
    val notaSaci = when {
      key.length == 44    -> Nota.findNotaSaidaKey(key)
      key.endsWith("/1")  -> Nota.findNotaSaidaSaci(key)
      key.endsWith("/5")  -> Nota.findNotaSaidaSaci(key)
      key.endsWith("/10") -> Nota.findNotaSaidaSaci(key)
      else                -> throw EViewModel("Chave não encontrada")
    }
    if(notaSaci.isEmpty()) throw EViewModel("Chave não encontrada")
    else notaSaci
  }

  fun findLoja(storeno: Int?): Loja? = Loja.findLoja(storeno)

  fun abreviacoes(prdno: String?, grade: String?): List<String> {
    val produto = Produto.findProduto(prdno, grade) ?: return emptyList()
    return ViewProdutoLoc.abreviacoesProduto(produto)
  }
}

class NFExpedicaoVo: EntityVo<ViewNotaExpedicao>() {
  override fun findEntity(): ViewNotaExpedicao? {
    return ViewNotaExpedicao.findSaida(numero, abreviacao)
  }

  var numero: String = ""
  var tipoMov: TipoMov = ENTRADA
  var tipoNota: TipoNota? = null
  var rota: String = ""
  var fornecedor: String = ""
  var cliente: String = ""
  var data: LocalDate = LocalDate.now()
  var dataEmissao: LocalDate = LocalDate.now()
  var lancamento: LocalDate = LocalDate.now()
  var hora: LocalTime = LocalTime.now()
  var observacao: String = ""
  var loja: Loja? = null
  var sequencia: Int = 0
  var usuario: Usuario? = null
  var abreviacao: String? = ""
  var impresso: Boolean = false
}