package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.ItemNota
import br.com.engecopi.estoque.model.Loja
import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.estoque.model.RegistryUserInfo.loja
import br.com.engecopi.estoque.model.TipoNota
import br.com.engecopi.estoque.model.Usuario
import br.com.engecopi.estoque.model.ViewProdutoLoc
import br.com.engecopi.estoque.model.ViewProdutoSaci
import br.com.engecopi.estoque.model.query.QProduto
import br.com.engecopi.framework.viewmodel.CrudViewModel
import br.com.engecopi.framework.viewmodel.EntityVo
import br.com.engecopi.framework.viewmodel.IView
import br.com.engecopi.utils.lpad
import java.time.LocalDate

class ProdutoViewModel(view: IView, val usuario: Usuario) :
  CrudViewModel<Produto, QProduto, ProdutoVo>(
    view,
    ProdutoVo::class
  ) {

  override fun update(bean: ProdutoVo) {
    Produto.findProduto(
      bean.codigoProduto,
      bean.gradeProduto
    )?.let { produto ->
      produto.codigo = bean.codigoProduto.lpad(
        16,
        " "
      )
      produto.codebar = bean.codebar ?: ""
      produto.update()
    }
  }

  override fun add(bean: ProdutoVo) {
    Produto().apply {
      this.codigo = bean.codigoProduto.lpad(
        16,
        " "
      )
      this.grade = bean.gradeProduto ?: ""
      this.codebar = bean.codebar ?: ""
      this.insert()
    }
  }

  override fun delete(bean: ProdutoVo) {
    Produto.findProduto(
      bean.codigoProduto,
      bean.gradeProduto
    )?.let { produto ->
      produto.delete()
    }
  }

  fun QProduto.filtroUsuario(): QProduto {
    return usuario.let { u ->
      if (u.admin || u.localizacaoes.isEmpty())
        this
      else
        this.or()
          .viewProdutoLoc.localizacao.isIn(usuario.locais)
          .viewProdutoLoc.abreviacao.isIn(usuario.locais)
          .endOr()
          .viewProdutoLoc.loja.id.eq(loja.id)
    } ?: this
  }

  override val query: QProduto
    get() = Produto
      .where()
      .filtroUsuario()

  override fun Produto.toVO(): ProdutoVo {
    val produto = this
    return ProdutoVo().apply {
      entityVo = produto
      codigoProduto = produto.codigo.trim()
      gradeProduto = produto.grade
      lojaDefault = usuario.loja
    }
  }

  override fun QProduto.filterString(text: String): QProduto {
    return codigo.contains(text)
      .codebar.eq(text)
      .vproduto.nome.contains(text)
      .grade.contains(text)
      .localizacao.contains(text)
  }

  fun localizacoes(bean: ProdutoVo?): List<String> {
    return ViewProdutoLoc.localizacoes(bean?.produto)
  }
}

class ProdutoVo : EntityVo<Produto>() {
  override fun findEntity(): Produto? {
    return Produto.findProduto(
      codigoProduto,
      gradeProduto
    )
  }

  var lojaDefault: Loja? = null
  var codigoProduto: String? = ""
  var gradeProduto: String? = ""
  val descricaoProduto: String?
    get() = produto?.descricao
  val descricaoProdutoSaci: String?
    get() = ViewProdutoSaci.find(codigoProduto).firstOrNull()?.nome
  val grades
    get() = ViewProdutoSaci.find(codigoProduto).mapNotNull { it.grade }
  val codebar: String?
    get() = produto?.codebar ?: ""
  val localizacao get() = produto?.localizacao ?: ""
  val produto
    get() = toEntity()
  val saldo
    get() = produto?.saldo_total ?: 0
  val comprimento: Int?
    get() = produto?.vproduto?.comp
  val lagura: Int?
    get() = produto?.vproduto?.larg
  val altura: Int?
    get() = produto?.vproduto?.alt
  val cubagem: Double?
    get() = produto?.vproduto?.cubagem
  var filtroDI: LocalDate? = null
  var filtroDF: LocalDate? = null
  var filtroTipo: TipoNota? = null
  var filtroLocalizacao: String? = null
  val itensNota: List<ItemNota>
    get() {
      produto?.recalculaSaldos()

      return produto?.finItensNota().orEmpty().filter {
        (lojaDefault?.let { lDef -> it.nota?.loja?.id == lDef.id } ?: true)
        &&
        (filtroDI?.let { di -> (it.nota?.data?.isAfter(di) ?: true) || (it.nota?.data?.isEqual(di) ?: true) } ?: true)
        &&
        (filtroDF?.let { df -> (it.nota?.data?.isBefore(df) ?: true) || (it.nota?.data?.isEqual(df) ?: true) } ?: true)
        &&
        (filtroTipo?.let { t -> it.nota?.tipoNota == t } ?: true)
        &&
        (filtroLocalizacao?.let { loc -> it.localizacao == loc } ?: true)
      }
    }
}
