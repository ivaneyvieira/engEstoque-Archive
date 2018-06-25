package br.com.engecopi.estoque.ui.views

import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.estoque.model.TipoProduto
import br.com.engecopi.estoque.model.TipoProduto.BOBINA
import br.com.engecopi.estoque.ui.LoginService
import br.com.engecopi.estoque.ui.title
import br.com.engecopi.estoque.viewmodel.ProdutoViewModel
import br.com.engecopi.estoque.viewmodel.ProdutoViewModel.ProdutoVo
import br.com.engecopi.framework.ui.view.DialogPopup
import br.com.engecopi.framework.ui.view.LayoutView
import br.com.engecopi.framework.viewmodel.ViewModel
import com.github.vok.karibudsl.AutoView
import com.github.vok.karibudsl.ModifierKey.Ctrl
import com.github.vok.karibudsl.VAlign
import com.github.vok.karibudsl.addColumnFor
import com.github.vok.karibudsl.align
import com.github.vok.karibudsl.alignment
import com.github.vok.karibudsl.bind
import com.github.vok.karibudsl.button
import com.github.vok.karibudsl.clickShortcut
import com.github.vok.karibudsl.comboBox
import com.github.vok.karibudsl.expandRatio
import com.github.vok.karibudsl.fillParent
import com.github.vok.karibudsl.grid
import com.github.vok.karibudsl.horizontalLayout
import com.github.vok.karibudsl.isMargin
import com.github.vok.karibudsl.perc
import com.github.vok.karibudsl.px
import com.github.vok.karibudsl.textField
import com.github.vok.karibudsl.w
import com.vaadin.data.converter.StringToIntegerConverter
import com.vaadin.data.provider.ListDataProvider
import com.vaadin.event.ShortcutAction.KeyCode.DELETE
import com.vaadin.event.ShortcutAction.KeyCode.INSERT
import com.vaadin.ui.ComboBox
import com.vaadin.ui.Grid
import com.vaadin.ui.TextField
import com.vaadin.ui.renderers.NumberRenderer
import com.vaadin.ui.themes.ValoTheme
import java.text.DecimalFormat

@AutoView
class ProdutoView : LayoutView<ProdutoViewModel>() {
  private val lojaDefault = LoginService.currentUser?.storeno ?: 0
  override val viewModel = ProdutoViewModel(lojaDefault, this)
  var grid: Grid<Produto>? = null
  
  val dialogProduto = DialogProduto()
  
  init {
    form("Produtos") {
      horizontalLayout {
        isVisible = false
        w = fillParent
        isMargin = false
        textField("Pesquisa") {
          w = fillParent
        }
      }
      horizontalLayout {
        button("Adionar Produto") {
          clickShortcut = Ctrl + INSERT
          addClickListener {
            dialogProduto.binder.readBean(viewModel.produtoVo)
            dialogProduto.show()
          }
        }
        button("Remover Produto") {
          clickShortcut = Ctrl + DELETE
          addClickListener {
            grid?.actionSelected("Não há produto selecionado") { produto ->
              val msg = viewModel.validaDelete(produto)
              if (msg == "")
                viewModel.deleta(produto)
              else
                showWarning(msg)
            }
          }
        }
        button("Atualiza Saldo") {
          addClickListener {
            grid?.actionSelected("Não há produto selecionado") { produto ->
              viewModel.atualizaSaldo(produto)
            }
          }
        }
      }
      grid = grid(Produto::class) {
        dataProvider = ListDataProvider(viewModel.listaGrid)
        removeAllColumns()
        setSizeFull()
        expandRatio = 1.0f
        addColumnFor(Produto::codigo) {
          caption = "Código"
        }
        addColumnFor(Produto::descricao) {
          expandRatio = 5
          caption = "Descrição"
        }
        addColumnFor(Produto::grade) {
          caption = "Grade"
        }
        
        viewModel.lojasSaldo.forEach { loja ->
          addColumn { prd -> viewModel.saldoProduto(prd, loja) }.apply {
            caption = "Loja $loja"
            setRenderer(NumberRenderer(DecimalFormat("0")))
            align = VAlign.Right
          }
        }
      }
    }
  }
  
  inner class DialogProduto : DialogPopup<ProdutoVo>("Produto", ProdutoVo::class) {
    
    var descricaoProduto: TextField? = null
    
    var gradeProduto: ComboBox<String>? = null
    
    var tipoProduto: ComboBox<TipoProduto>? = null
    
    init {
      form.apply {
        row {
          textField {
            expandRatio = 1f
            caption = "Código"
            addValueChangeListener { e ->
              if (e.isUserOriginated) {
                viewModel.updateGrades(e.value)
              }
            }
            bind(binder).bind(ProdutoVo::codigoProduto)
          }
          
          descricaoProduto = textField("Descrição") {
            expandRatio = 3f
            isReadOnly = true
          }
          gradeProduto = comboBox {
            expandRatio = 1f
            caption = "Grade"
            isEmptySelectionAllowed = false
            isTextInputAllowed = false
            bind(binder).bind(ProdutoVo::gradeProduto)
          }
        }
        row {
          tipoProduto = comboBox<TipoProduto> {
            expandRatio = 1f
            caption = "Tipo"
            isEmptySelectionAllowed = false
            isTextInputAllowed = false
            setItems(TipoProduto.values().toList())
            setItemCaptionGenerator { it.descricao }
            bind(binder).bind(ProdutoVo::tipo)
          }
          
          textField {
            expandRatio = 1f
            caption = "Quant Lote"
            addStyleName(ValoTheme.TEXTFIELD_ALIGN_RIGHT)
            bind(binder).withConverter(StringToIntegerConverter("Valor inválido"))
                    .bind(ProdutoVo::quant_lote)
            
          }
          textField {
            expandRatio = 1f
            caption = "Quant Bobina"
            addStyleName(ValoTheme.TEXTFIELD_ALIGN_RIGHT)
            bind(binder).withConverter(StringToIntegerConverter("Valor inválido"))
                    .bind(ProdutoVo::quant_bobina)
            
          }
        }
      }
      
      addClickListenerOk {
        this.binder.writeBean(viewModel.produtoVo)
        viewModel.saveUpdateProduto()
      }
    }
  }
  
  override fun updateModel() {
  }
  
  override fun updateView(viewModel: ViewModel) {
    viewModel as ProdutoViewModel
    grid?.apply {
      dataProvider.refreshAll()
      setDataProvider(dataProvider)
    }
    dialogProduto.apply {
      gradeProduto?.setItems(viewModel.grades)
      viewModel.grades.firstOrNull()?.let {
        gradeProduto?.value = it
      }
      
      tipoProduto?.value = viewModel.produtoVo.tipo
      
      descricaoProduto?.value = viewModel.produtoVo.descricaoProduto
    }
  }
}


