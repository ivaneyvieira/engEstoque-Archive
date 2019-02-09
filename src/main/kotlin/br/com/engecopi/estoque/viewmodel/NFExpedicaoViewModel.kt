package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.ItemNota
import br.com.engecopi.estoque.model.Loja
import br.com.engecopi.estoque.model.Nota
import br.com.engecopi.estoque.model.RegistryUserInfo
import br.com.engecopi.estoque.model.StatusNota.INCLUIDA
import br.com.engecopi.estoque.model.TipoMov
import br.com.engecopi.estoque.model.TipoMov.ENTRADA
import br.com.engecopi.estoque.model.TipoNota
import br.com.engecopi.estoque.model.Usuario
import br.com.engecopi.estoque.model.ViewNotaExpedicao
import br.com.engecopi.estoque.model.query.QViewNotaExpedicao
import br.com.engecopi.estoque.ui.log
import br.com.engecopi.framework.viewmodel.CrudViewModel
import br.com.engecopi.framework.viewmodel.EViewModel
import br.com.engecopi.framework.viewmodel.EntityVo
import br.com.engecopi.framework.viewmodel.IView
import java.time.LocalDate
import java.time.LocalTime

class NFExpedicaoViewModel(view: IView) : CrudViewModel<ViewNotaExpedicao, QViewNotaExpedicao, NFExpedicaoVo>
                                            (view, NFExpedicaoVo::class) {
  override fun update(bean: NFExpedicaoVo) {
    log.error("Atualização não permitida")
  }

  override fun add(bean: NFExpedicaoVo) {
    log.error("Inserssão não permitida")
  }

  override fun delete(bean: NFExpedicaoVo) {
    val nota = bean.findEntity() ?: return
    val saida = Nota.findSaida(nota.numero)
    ItemNota.where()
      .nota.equalTo(saida)
      .localizacao.startsWith(bean.abreviacao)
      .findList()
      .forEach { it.delete() }
  }

  override val query: QViewNotaExpedicao
    get() = ViewNotaExpedicao.where()

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
      hora = bean.hora
      observacao = bean.observacao
      loja = bean.loja
      sequencia = bean.sequencia
      usuario = bean.usuario
      abreviacao = bean.abreviacao
    }
  }

  fun processaKey(key: String) = exec {
    val notasSaci = Nota.findNotaSaidaPXA(key)
    if (notasSaci.isNotEmpty()) {
      val nota = Nota.createNota(notasSaci.firstOrNull())?.apply {
        if (this.existe())
          view.showWarning("Essa nota já está cadastrada")
        else {
          val serie = numero.split("/").getOrNull(1) ?: ""
          sequencia = Nota.maxSequencia(serie) + 1
          usuario = RegistryUserInfo.usuarioDefault
          save()
        }
      }
      if (nota == null)
        throw EViewModel("Nota não encontrada")
      else {
        val itens = notasSaci.mapNotNull { notaSaci ->
          val item = ItemNota.find(notaSaci) ?: ItemNota.createItemNota(notaSaci, nota)
          return@mapNotNull item?.apply {
            status = INCLUIDA
            usuario = RegistryUserInfo.usuarioDefault
            save()
          }
        }
        if (itens.isEmpty())
          throw EViewModel("Essa nota não possui itens com localização")
      }
    } else
      throw EViewModel("Chave não encontrada")
  }

  fun imprimir(itemNota: ItemNota?) : String {
    itemNota ?: return ""
    val template = itemNota.template
    val print = itemNota.printEtiqueta()
    itemNota.let {
      it.refresh()
      it.impresso = true
      it.update()
    }
    return print.print(template)
  }

  fun imprimir(nota: NFExpedicaoVo?): String {
    val itens = nota?.findEntity()?.findItens() ?: emptyList()
    return itens.map{imprimir(it)}.distinct().joinToString(separator = "\n")
  }

  fun imprime(): String {
    val itens = ItemNota.where()
      .impresso.eq(false)
      .status.eq(INCLUIDA)
      .findList()
    return itens.map{imprimir(it)}.distinct().joinToString(separator = "\n")
  }
}

class NFExpedicaoVo : EntityVo<ViewNotaExpedicao>() {
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
  var hora: LocalTime = LocalTime.now()
  var observacao: String = ""
  var loja: Loja? = null
  var sequencia: Int = 0
  var usuario: Usuario? = null
  var abreviacao: String? = ""
  var impresso: Boolean = false
}