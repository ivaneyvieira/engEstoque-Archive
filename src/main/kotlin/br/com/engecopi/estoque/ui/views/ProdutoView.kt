package br.com.engecopi.estoque.ui.views

import br.com.engecopi.estoque.model.ItemNota
import br.com.engecopi.estoque.model.RegistryUserInfo
import br.com.engecopi.estoque.model.TipoNota
import br.com.engecopi.estoque.viewmodel.ProdutoViewModel
import br.com.engecopi.estoque.viewmodel.ProdutoVo
import br.com.engecopi.framework.ui.view.CrudLayoutView
import br.com.engecopi.framework.ui.view.bindItens
import br.com.engecopi.framework.ui.view.default
import br.com.engecopi.framework.ui.view.grupo
import br.com.engecopi.framework.ui.view.reloadBinderOnChange
import br.com.engecopi.framework.ui.view.row
import com.github.vok.karibudsl.AutoView
import com.github.vok.karibudsl.VAlign
import com.github.vok.karibudsl.addColumnFor
import com.github.vok.karibudsl.align
import com.github.vok.karibudsl.bind
import com.github.vok.karibudsl.comboBox
import com.github.vok.karibudsl.dateField
import com.github.vok.karibudsl.expandRatio
import com.github.vok.karibudsl.grid
import com.github.vok.karibudsl.h
import com.github.vok.karibudsl.px
import com.github.vok.karibudsl.textField
import com.github.vok.karibudsl.w
import com.vaadin.data.Binder
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.renderers.LocalDateRenderer
import com.vaadin.ui.renderers.NumberRenderer
import com.vaadin.ui.renderers.TextRenderer
import org.vaadin.crudui.crud.CrudOperation
import org.vaadin.crudui.crud.CrudOperation.UPDATE
import java.text.DecimalFormat

@AutoView
class ProdutoView : CrudLayoutView<ProdutoVo, ProdutoViewModel>() {
  override fun layoutForm(
    formLayout: VerticalLayout,
    operation: CrudOperation?,
    binder: Binder<ProdutoVo>,
    readOnly: Boolean
                         ) {
    binder.bean.lojaDefault = RegistryUserInfo.loja
    formLayout.apply {
      w = 800.px
      h = 300.px
      grupo("Produtos") {
        row {
          textField {
            expandRatio = 1f
            caption = "Código"
            bind(binder).bind(ProdutoVo::codigoProduto)
            reloadBinderOnChange(binder)
          }
          textField("Descrição") {
            expandRatio = 3f
            isReadOnly = true
            bind(binder).bind(ProdutoVo::descricaoProdutoSaci.name)
          }
          comboBox<String> {
            expandRatio = 1f
            caption = "Grade"
            default { it }
            bind(binder).bind(ProdutoVo::gradeProduto)
            bindItens(binder, "grades")
            reloadBinderOnChange(binder)
          }
        }
      }
      grupo("Notas") {
        row {
          dateField("Data Inicial") {
            id = "filtro"
            value = null
            bind(binder).bind(ProdutoVo::filtroDI)
            reloadBinderOnChange(binder)
          }
          dateField("Data Final") {
            id = "filtro"
            value = null
            bind(binder).bind(ProdutoVo::filtroDF)
            reloadBinderOnChange(binder)
          }
          comboBox<TipoNota>("Tipo") {
            default { it.descricao2 }
            id = "filtro"
            setItems(TipoNota.values().toList())
            isEmptySelectionAllowed = true
            emptySelectionCaption = "Todos"
            value = null
            bind(binder).bind(ProdutoVo::filtroTipo)
            reloadBinderOnChange(binder)
          }
          comboBox<String>("Local") {
            default { it }
            id = "filtro"
            val itens = viewModel.localizacoes(binder.bean)
            setItems(itens)
            bind(binder).bind(ProdutoVo::filtroLocalizacao)
            value = itens.firstOrNull()
            reloadBinderOnChange(binder)
          }
        }
        row {
          grid(ItemNota::class) {
            expandRatio = 2f
            removeAllColumns()
            addColumnFor(ItemNota::numeroNota) {
              this.isSortable = false
              caption = "Nota"
            }
            addColumnFor(ItemNota::dataNota) {
              this.isSortable = false
              caption = "Data"
              setRenderer(LocalDateRenderer("dd/MM/yy"))
            }
            addColumnFor(ItemNota::tipoNota) {
              this.isSortable = false
              caption = "Tipo"
              setRenderer({ it?.descricao ?: "" }, TextRenderer())
            }
            addColumnFor(ItemNota::rota) {
              this.isSortable = false
              caption = "Rota"
            }
            addColumnFor(ItemNota::quantidadeSaldo) {
              this.isSortable = false
              caption = "Quantidade"
              setRenderer(NumberRenderer(DecimalFormat("0")))
              align = VAlign.Right
            }
            addColumnFor(ItemNota::saldo) {
              this.isSortable = false
              caption = "Saldo"
              setRenderer(NumberRenderer(DecimalFormat("0")))
              align = VAlign.Right
            }
            bindItens(binder, "itensNota")
          }
        }
      }
    }
    if (!RegistryUserInfo.usuario.admin && operation == UPDATE)
      binder.setReadOnly(true)
  }

  init {
    form("Entrada de produtos") {
      gridCrud(viewModel.crudClass.java) {
        queryOnly = !RegistryUserInfo.usuario.admin
        column(ProdutoVo::codigoProduto) {
          expandRatio = 1
          caption = "Código"
          setSortProperty("codigo")
        }
        column(ProdutoVo::descricaoProduto) {
          expandRatio = 5
          caption = "Descrição"
          setSortProperty("vproduto.nome")
        }
        column(ProdutoVo::gradeProduto) {
          expandRatio = 1
          caption = "Grade"
          setSortProperty("grade")
        }
        column(ProdutoVo::localizacao) {
          expandRatio = 1
          caption = "Localização"
          setSortProperty("localizacao")
        }
        column(ProdutoVo::saldo) {
          expandRatio = 1
          caption = "Saldo"
          setSortProperty("saldo_total")
          setRenderer(NumberRenderer(DecimalFormat("0")))
          align = VAlign.Right
        }
        column(ProdutoVo::comprimento) {
          expandRatio = 1
          caption = "Comprimento"
          setSortProperty("vproduto.comp")
          setRenderer(NumberRenderer(DecimalFormat("0")))
          align = VAlign.Right
        }
        column(ProdutoVo::lagura) {
          expandRatio = 1
          caption = "Largura"
          setSortProperty("vproduto.larg")
          setRenderer(NumberRenderer(DecimalFormat("0")))
          align = VAlign.Right
        }
        column(ProdutoVo::altura) {
          expandRatio = 1
          caption = "Altura"
          setSortProperty("vproduto.alt")
          setRenderer(NumberRenderer(DecimalFormat("0")))
          align = VAlign.Right
        }
        column(ProdutoVo::cubagem) {
          expandRatio = 1
          caption = "Cubagem"
          setSortProperty("vproduto.cubagem")
          setRenderer(NumberRenderer(DecimalFormat("0.000000")))
          align = VAlign.Right
        }
      }
    }
  }

  override val viewModel: ProdutoViewModel
    get() = ProdutoViewModel(this)
}


