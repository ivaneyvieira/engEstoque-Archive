package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.ItemNota
import br.com.engecopi.estoque.model.Loja
import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.estoque.model.TipoNota
import br.com.engecopi.estoque.model.Usuario
import br.com.engecopi.estoque.model.query.QProduto
import br.com.engecopi.framework.viewmodel.CrudViewModel
import br.com.engecopi.framework.viewmodel.IView
import br.com.engecopi.saci.saci
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ProdutoViewModel(view: IView, val usuario: Usuario?) :
        CrudViewModel<Produto, QProduto, ProdutoVo>(view, ProdutoVo::class) {
  override fun update(bean: ProdutoVo) {
    Produto.findProduto(bean.codigoProduto, bean.gradeProduto)?.let { produto ->
      produto.codebar = bean.codebar ?: ""
      produto.update()
    }
  }
  
  override fun add(bean: ProdutoVo) {
    Produto().apply {
      this.codigo = bean.codigoProduto ?: ""
      this.grade = bean.gradeProduto ?: ""
      this.codebar = bean.codebar ?: ""
      this.insert()
    }
  }
  
  override fun delete(bean: ProdutoVo) {
    Produto.findProduto(bean.codigoProduto, bean.gradeProduto)?.let { produto ->
      produto.delete()
    }
  }
  
  fun QProduto.filtroUsuario(): QProduto {
    return usuario?.let { u ->
      if (u.isAdmin || u.localizacao.isNullOrBlank())
        this
      else
        itensNota.usuario.id.eq(u.id)
    } ?: this
  }
  
  override fun countQuery(filter: String): Int = execInt {
    query.filterBlank(filter)
            .findList()
            .filter { usuario?.temProduto(it) ?: false }
            .size
  }
  
  override fun findQuery(offset: Int, limit: Int, filter: String): List<ProdutoVo> = execList {
    query.filterBlank(filter)
            .setFirstRow(offset)
            .setMaxRows(limit)
            .findList()
            .filter { usuario?.temProduto(it) ?: false }
            .map { it.toVO() }
  }
  
  override val query: QProduto
    get() = Produto.where().filtroUsuario()
  
  override fun Produto.toVO(): ProdutoVo {
    val produto = this
    return ProdutoVo().apply {
      codigoProduto = produto.codigo
      gradeProduto = produto.grade
      lojaDefault = usuario?.loja
    }
  }
  
  override fun QProduto.filterString(text: String): QProduto {
    return codigo.eq(text)
            .codebar.eq(text)
  }
}

class ProdutoVo {
  var lojaDefault: Loja? = null
  var codigoProduto: String? = ""
  var gradeProduto: String? = ""
  val produtosSaci
    get() = saci.findProduto(codigoProduto ?: "")
  val descricaoProduto: String?
    get() = produtosSaci.firstOrNull()?.nome ?: ""
  val grades
    get() = produtosSaci.mapNotNull { it.grade }
  
  val codebar: String?
    get() = produtosSaci.firstOrNull { it.grade == gradeProduto }?.codebar ?: ""
  
  val localizacao
    get() = saci.findLocStr(lojaDefault?.numero, produto?.codigo, produto?.grade)
  
  
  val produto: Produto?
    get() = Produto.findProduto(codigoProduto, gradeProduto)
  
  var filtroDI: LocalDate? = null
  var filtroDF: LocalDate? = null
  var filtroTipo: TipoNota? = null
  
  val itensNota: List<ItemNota>
    get() {
      val itens = produto?.itensNota.orEmpty().filter {
        (lojaDefault?.let { lDef ->
          it.nota?.loja?.id == lDef.id
        } ?: true)
        &&
        (filtroDI?.let {di->
          (it.nota?.data?.isAfter(di) ?: true) ||
          (it.nota?.data?.isEqual(di)?: true)
        }?: true)
        &&
        (filtroDF?.let {df->
          (it.nota?.data?.isBefore(df) ?: true) ||
          (it.nota?.data?.isEqual(df)?: true)
        }?: true)
        &&
        (filtroTipo?.let {t->
          it.nota?.tipoNota == t ?: true
        }?: true)
      }
      var saldo = 0
      itens.forEach { item ->
        saldo += item.quantidadeSaldo
        item.saldoTransient = saldo
      }
      return itens
    }
}
