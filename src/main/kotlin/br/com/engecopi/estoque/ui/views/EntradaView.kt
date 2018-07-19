package br.com.engecopi.estoque.ui.views

import br.com.engecopi.estoque.model.Loja
import br.com.engecopi.estoque.model.TipoNota
import br.com.engecopi.estoque.model.TipoProduto
import br.com.engecopi.estoque.ui.EstoqueUI
import br.com.engecopi.estoque.viewmodel.EntradaViewModel
import br.com.engecopi.estoque.viewmodel.EntradaVo
import br.com.engecopi.estoque.viewmodel.MovimentacaoVO
import br.com.engecopi.framework.ui.view.CrudLayoutView
import br.com.engecopi.framework.ui.view.bindItens
import br.com.engecopi.framework.ui.view.bindReadOnly
import br.com.engecopi.framework.ui.view.dateFormat
import br.com.engecopi.framework.ui.view.default
import br.com.engecopi.framework.ui.view.grupo
import br.com.engecopi.framework.ui.view.intFormat
import br.com.engecopi.framework.ui.view.integerField
import br.com.engecopi.framework.ui.view.reloadBinderOnChange
import br.com.engecopi.framework.ui.view.row
import br.com.engecopi.saci.beans.ProdutoSaci
import com.github.vok.karibudsl.AutoView
import com.github.vok.karibudsl.addColumnFor
import com.github.vok.karibudsl.bind
import com.github.vok.karibudsl.comboBox
import com.github.vok.karibudsl.dateField
import com.github.vok.karibudsl.expandRatio
import com.github.vok.karibudsl.grid
import com.github.vok.karibudsl.h
import com.github.vok.karibudsl.px
import com.github.vok.karibudsl.textField
import com.vaadin.data.Binder
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.renderers.TextRenderer
import com.vaadin.ui.themes.ValoTheme
import org.vaadin.crudui.crud.CrudOperation
import org.vaadin.crudui.crud.CrudOperation.ADD
import org.vaadin.crudui.crud.CrudOperation.UPDATE
import kotlin.reflect.full.declaredMemberProperties

@AutoView("")
class EntradaView : CrudLayoutView<EntradaVo, EntradaViewModel>() {
  val lojaDefault
    get() = EstoqueUI.loja
  val isAdmin
    get() = EstoqueUI.user?.isAdmin ?: false
  
  override fun layoutForm(
          formLayout: VerticalLayout,
          operation: CrudOperation?,
          binder: Binder<EntradaVo>,
          readOnly: Boolean
                         ) {
    if (operation == ADD)
      binder.bean.lojaNF = lojaDefault
    formLayout.apply {
      grupo("Nota fiscal de entrada") {
        row {
          textField("Nota Fiscal") {
            expandRatio = 1f
            isReadOnly = operation != ADD
            bind(binder).bind(EntradaVo::numeroNF)
            EntradaVo::class.declaredMemberProperties
            reloadBinderOnChange(binder)
          }
          comboBox<Loja>("Loja") {
            expandRatio = 1f
            isReadOnly = operation != ADD
            default { it.sigla }
            
            setItems(viewModel.findLojas(lojaDefault))
            
            bind(binder).bind(EntradaVo::lojaNF)
            reloadBinderOnChange(binder)
          }
          comboBox<TipoNota>("Tipo") {
            expandRatio = 1f
            default { it.descricao }
            isReadOnly = true
            setItems(TipoNota.valuesEntrada())
            bind(binder).bind(EntradaVo::tipoNota)
          }
          textField("Rota") {
            expandRatio = 1f
            isReadOnly = true
            bind(binder).bind(EntradaVo::rota)
          }
        }
        row {
          textField("Observação") {
            expandRatio = 2f
            bind(binder).bind(EntradaVo::observacaoNota)
          }
        }
        row {
          dateField("Data") {
            expandRatio = 1f
            isReadOnly = true
            bind(binder).bind(EntradaVo::dataNota.name)
          }
          integerField("Número Interno") {
            expandRatio = 1f
            isReadOnly = true
            this.bind(binder)
                    .bind(EntradaVo::numeroInterno.name)
          }
          textField("Fornecedor") {
            expandRatio = 2f
            isReadOnly = true
            bind(binder).bind(EntradaVo::fornecedor.name)
          }
        }
        
      }
      
      grupo("Produto") {
        row {
          comboBox<ProdutoSaci>("Código") {
            expandRatio = 2f
            isReadOnly = operation != ADD
            default { "${it.codigo} ${it.grade}".trim() }
            
            bindItens(binder, EntradaVo::produtoNota)
            bind(binder).bind(EntradaVo::produtoSaci)
            reloadBinderOnChange(binder)
          }
          textField("Descrição") {
            expandRatio = 5f
            isReadOnly = true
            bind(binder).bind(EntradaVo::descricaoProduto.name)
          }
          textField("Grade") {
            expandRatio = 1f
            isReadOnly = true
            bind(binder).bind(EntradaVo::grade.name)
          }
        }
        row {
          comboBox<TipoProduto>("Tipo do Produto") {
            expandRatio = 1f
            isReadOnly = operation != ADD
            isEmptySelectionAllowed = false
            isTextInputAllowed = false
            setItems(TipoProduto.values().toList())
            setItemCaptionGenerator { it.descricao }
            
            bind(binder).bind(EntradaVo::tipoProduto)
            reloadBinderOnChange(binder)
          }
          integerField("Lote com") {
            expandRatio = 1f
            bindReadOnly(binder, EntradaVo::tamanhoReadOnly) {
              if (operation != ADD)
                this.isReadOnly = true
            }
            
            this.bind(binder)
                    .bind(EntradaVo::tamanho)
            addStyleName(ValoTheme.TEXTFIELD_ALIGN_RIGHT)
            reloadBinderOnChange(binder)
          }
          textField("Seq Inicial") {
            expandRatio = 1f
            isReadOnly = true
            bind(binder).bind(EntradaVo::sequencia.name)
          }
          integerField("Qtd Entrada") {
            expandRatio = 1f
            isReadOnly = operation != ADD
            this.bind(binder)
                    .bind(EntradaVo::quantProduto.name)
          }
        }
      }
      
      row {
        grid(MovimentacaoVO::class) {
          h = 150.px
          caption = "Lotes"
          bindItens(binder, EntradaVo::movimentacao)
          removeAllColumns()
          addColumnFor(MovimentacaoVO::descLote) {
            caption = "Seq. do Lote"
          }
          addColumnFor(MovimentacaoVO::quantidade) {
            caption = "Qtd. por Lote"
            intFormat()
          }
        }
      }
    }
    if(!isAdmin && operation == UPDATE)
      binder.setReadOnly(true)
  }
  
  init {
    form("Entrada de produtos") {
      gridCrud(viewModel.crudClass.java) {
        setDeleteOperationVisible(isAdmin)
        column(EntradaVo::numeroNF) {
          caption = "Número NF"
        }
        column(EntradaVo::lojaNF) {
          caption = "Loja NF"
          setRenderer({ loja -> loja?.sigla ?: "" }, TextRenderer())
        }
        column(EntradaVo::dataNota) {
          caption = "Data Nota"
          dateFormat()
        }
        column(EntradaVo::fornecedor) {
          caption = "Fornecedor"
        }
        column(EntradaVo::codigo) {
          caption = "Código"
        }
        column(EntradaVo::descricaoProduto) {
          caption = "Descrição"
        }
        column(EntradaVo::grade) {
          caption = "Grade"
        }
        column(EntradaVo::quantProduto) {
          caption = "Quantidade"
          intFormat()
        }
      }
    }
  }
  
  override val viewModel: EntradaViewModel
    get() = EntradaViewModel(this, lojaDefault)
}


