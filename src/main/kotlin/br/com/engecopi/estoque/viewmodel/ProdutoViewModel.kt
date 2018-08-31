package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.ItemNota
import br.com.engecopi.estoque.model.Loja
import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.estoque.model.TipoNota
import br.com.engecopi.estoque.model.Usuario
import br.com.engecopi.estoque.model.ViewProdutoSaci
import br.com.engecopi.estoque.model.query.QProduto
import br.com.engecopi.estoque.ui.EstoqueUI.Companion.loja
import br.com.engecopi.framework.viewmodel.CrudViewModel
import br.com.engecopi.framework.viewmodel.EntityVo
import br.com.engecopi.framework.viewmodel.IView
import br.com.engecopi.utils.lpad
import java.time.LocalDate

class ProdutoViewModel(view: IView, val usuario: Usuario?) :
        CrudViewModel<Produto, QProduto, ProdutoVo>(view, ProdutoVo::class) {
  
  init {
    Loja.setLojaDefault(usuario?.loja?.numero ?: 0)
  }
  
  override fun update(bean: ProdutoVo) {
    Produto.findProduto(bean.codigoProduto, bean.gradeProduto)?.let { produto ->
      produto.codigo = bean.codigoProduto.lpad(16, " ")
      produto.codebar = bean.codebar ?: ""
      produto.update()
    }
  }
  
  override fun add(bean: ProdutoVo) {
    Produto().apply {
      this.codigo = bean.codigoProduto.lpad(16, " ")
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
      if (u.admin || u.localizacaoes.isEmpty())
        this
      else
        this.or()
                .viewProdutoLoc.localizacao.isIn(usuario.locais)
                .viewProdutoLoc.abreviacao.isIn(usuario.locais)
                .endOr()
                .viewProdutoLoc.loja.id.eq(loja?.id)
    } ?: this
  }
  
  override val query: QProduto
    get() = Produto
            .where()
            .fetch("viewProdutoLoc")
            .filtroUsuario()
  
  override fun Produto.toVO(): ProdutoVo {
    val produto = this
    return ProdutoVo().apply {
      entityVo = produto
      codigoProduto = produto.codigo.trim()
      gradeProduto = produto.grade
      lojaDefault = usuario?.loja
    }
  }
  
  override fun QProduto.filterString(text: String): QProduto {
    return codigo.contains(text)
            .codebar.eq(text)
            .vproduto.nome.contains(text)
            .grade.contains(text)
            .viewProdutoLoc.localizacao.contains(text)
  }
}

class ProdutoVo : EntityVo<Produto>() {
  override fun findEntity(): Produto? {
    return Produto.findProduto(codigoProduto, gradeProduto)
  }
  
  var lojaDefault: Loja? = null
  var codigoProduto: String? = ""
  var gradeProduto: String? = ""
  
  val descricaoProduto: String?
    get() = Produto.findProduto(codigoProduto, gradeProduto)?.descricao
  
  val descricaoProdutoSaci: String?
    get() = ViewProdutoSaci.find(codigoProduto).firstOrNull()?.nome
  
  val grades
    get() = ViewProdutoSaci.find(codigoProduto).mapNotNull { it.grade }
  
  val codebar: String?
    get() = Produto.findProduto(codigoProduto, gradeProduto)?.codebar ?: ""
  
  val localizacao get() = produto?.localizacao ?: ""
  
  val produto
    get() = findEntity()
  
  val saldo
    get() = entityVo?.saldo_total ?: 0
  
  var filtroDI: LocalDate? = null
  var filtroDF: LocalDate? = null
  var filtroTipo: TipoNota? = null
  
  val itensNota: List<ItemNota>
    get() {
      produto?.recalculaSaldos()
      val itens = produto?.finItensNota().orEmpty().filter {
        (lojaDefault?.let { lDef ->
          it.nota?.loja?.id == lDef.id
        } ?: true)
        &&
        (filtroDI?.let { di ->
          (it.nota?.data?.isAfter(di) ?: true) ||
          (it.nota?.data?.isEqual(di) ?: true)
        } ?: true)
        &&
        (filtroDF?.let { df ->
          (it.nota?.data?.isBefore(df) ?: true) ||
          (it.nota?.data?.isEqual(df) ?: true)
        } ?: true)
        &&
        (filtroTipo?.let { t ->
          it.nota?.tipoNota == t
        } ?: true)
      }
      
      return itens
    }
}
