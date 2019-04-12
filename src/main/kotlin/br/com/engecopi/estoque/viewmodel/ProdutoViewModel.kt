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
    bean.toEntity()?.let {produto ->
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
      val gradesSalvas = Produto.findProdutos(bean.codigoProduto).map {it.grade}
      if(bean.temGrade != true) {
        this.codigo = bean.codigoProduto.lpad(16, " ")
        this.grade = ""
        this.codebar = bean.codebar ?: ""
        this.save()
      } else bean.gradesProduto.filter {grade -> !gradesSalvas.contains(grade)}.forEach {grade ->
        this.codigo = bean.codigoProduto.lpad(16, " ")
        this.grade = grade
        this.codebar = bean.codebar ?: ""
        this.save()
      }
    }
  }

  override fun delete(bean: ProdutoVo) {
    Produto.findProdutos(bean.codigoProduto, bean.gradesProduto.toList()).forEach {it.delete()}
  }

  private fun QProduto.filtroUsuario(): QProduto {
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
      //gradesProduto = Produto.findGradesProduto(produto.codigo).toSet()
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
    return bean?.produto?.localizacoes().orEmpty().map { LocProduto(it) }
  }
}

class ProdutoVo : EntityVo<Produto>() {
  override fun findEntity(): Produto? {
    return Produto.findProdutos(codigoProduto).firstOrNull()
  }

  var lojaDefault: Loja? = null
  var codigoProduto: String? = ""
    set(value) {
      field = value
      if(entityVo == null) gradesProduto = Produto.findGradesProduto(value).toSet()
    }
  var gradesProduto: Set<String> = emptySet()
  val descricaoProduto: String?
    get() = produto?.descricao
  val descricaoProdutoSaci: String?
    get() = if(entityVo == null) ViewProdutoSaci.find(codigoProduto).firstOrNull()?.nome else entityVo?.descricao
  val grades
    get() = if(entityVo == null) ViewProdutoSaci.find(codigoProduto).mapNotNull {it.grade} else listOf(entityVo?.grade
                                                                                                       ?: "")
  val codebar: String?
    get() = produto?.codebar ?: ""
  val localizacao
    get() = produto?.localizacoes()
      .orEmpty().asSequence()
      .distinct().joinToString(" / ")
  val produto
    get() = toEntity()
  val temGrade get() = toEntity()?.temGrade
  val grade get() = produto?.grade ?: ""
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
      }.sortedWith(compareBy(ItemNota::localizacao, ItemNota::data, ItemNota::id)).toList()
    }
}
