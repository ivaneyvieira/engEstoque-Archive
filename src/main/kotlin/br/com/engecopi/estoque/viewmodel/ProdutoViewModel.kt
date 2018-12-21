package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.ItemNota
import br.com.engecopi.estoque.model.LocProduto
import br.com.engecopi.estoque.model.Loja
import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.estoque.model.RegistryUserInfo.abreviacaoDefault
import br.com.engecopi.estoque.model.RegistryUserInfo.lojaDefault
import br.com.engecopi.estoque.model.RegistryUserInfo.usuarioDefault
import br.com.engecopi.estoque.model.Repositories
import br.com.engecopi.estoque.model.TipoNota
import br.com.engecopi.estoque.model.ViewProdutoLoc
import br.com.engecopi.estoque.model.ViewProdutoSaci
import br.com.engecopi.estoque.model.query.QProduto
import br.com.engecopi.framework.viewmodel.CrudViewModel
import br.com.engecopi.framework.viewmodel.EntityVo
import br.com.engecopi.framework.viewmodel.IView
import br.com.engecopi.utils.lpad
import java.time.LocalDate

class ProdutoViewModel(view: IView) :
  CrudViewModel<Produto, QProduto, ProdutoVo>(view, ProdutoVo::class) {
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
    return this
      .viewProdutoLoc.localizacao.startsWith(abreviacaoDefault)
      .viewProdutoLoc.loja.id.eq(lojaDefault.id)
  }

  override val query: QProduto
    get() {
      Repositories.updateViewProdutosLoc()
      return Produto
        .where()
        .filtroUsuario()
    }

  override fun Produto.toVO(): ProdutoVo {
    val produto = this
    return ProdutoVo().apply {
      entityVo = produto
      codigoProduto = produto.codigo.trim()
      gradeProduto = produto.grade
      lojaDefault = usuarioDefault.loja
    }
  }

  override fun QProduto.filterString(text: String): QProduto {
    return codigo.contains(text)
      .codebar.eq(text)
      .vproduto.nome.contains(text)
      .grade.contains(text)
      .localizacao.contains(text)
  }

  fun localizacoes(bean: ProdutoVo?): List<LocProduto> {
    return bean?.produto?.sufixosLocalizacaoes().orEmpty()
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
    get() = produto?.descricao
  val descricaoProdutoSaci: String?
    get() = ViewProdutoSaci.find(codigoProduto).firstOrNull()?.nome
  val grades
    get() = ViewProdutoSaci.find(codigoProduto).mapNotNull { it.grade }
  val codebar: String?
    get() = produto?.codebar ?: ""
  val localizacao
    get() = produto?.sufixosLocalizacaoes()
      .orEmpty().asSequence()
      .map { it.localizacao }.distinct().joinToString(" / ")
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
  var filtroLocalizacao: LocProduto? = null
  val itensNota: List<ItemNota>
    get() {
      produto?.recalculaSaldos()

      return produto?.findItensNota().orEmpty().asSequence().filter {
        (lojaDefault?.let { lDef -> it.nota?.loja?.id == lDef.id } ?: true)
        &&
        (filtroDI?.let { di -> (it.nota?.data?.isAfter(di) ?: true) || (it.nota?.data?.isEqual(di) ?: true) } ?: true)
        &&
        (filtroDF?.let { df -> (it.nota?.data?.isBefore(df) ?: true) || (it.nota?.data?.isEqual(df) ?: true) } ?: true)
        &&
        (filtroTipo?.let { t -> it.nota?.tipoNota == t } ?: true)
        &&
        (filtroLocalizacao?.let { loc -> it.localizacao == loc.localizacao } ?: true)
      }.sortedWith(compareBy(ItemNota::localizacao, ItemNota::dataNota, ItemNota::id)).toList()
    }
}
