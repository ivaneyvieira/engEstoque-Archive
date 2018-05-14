package br.com.engecopi.estoque.ui.views

import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.estoque.ui.title
import br.com.engecopi.estoque.viewmodel.ProdutoViewModel
import br.com.engecopi.estoque.viewmodel.ProdutoViewModel.ProdutoVo
import com.github.vok.karibudsl.AutoView
import com.github.vok.karibudsl.VAlign
import com.github.vok.karibudsl.addColumnFor
import com.github.vok.karibudsl.align
import com.github.vok.karibudsl.bind
import com.github.vok.karibudsl.button
import com.github.vok.karibudsl.comboBox
import com.github.vok.karibudsl.expandRatio
import com.github.vok.karibudsl.fillParent
import com.github.vok.karibudsl.grid
import com.github.vok.karibudsl.horizontalLayout
import com.github.vok.karibudsl.init
import com.github.vok.karibudsl.isMargin
import com.github.vok.karibudsl.label
import com.github.vok.karibudsl.textField
import com.github.vok.karibudsl.w
import com.vaadin.data.provider.ListDataProvider
import com.vaadin.icons.VaadinIcons
import com.vaadin.navigator.View
import com.vaadin.ui.ComboBox
import com.vaadin.ui.Grid
import com.vaadin.ui.Label
import com.vaadin.ui.TextField
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.renderers.NumberRenderer
import java.text.DecimalFormat

@AutoView
class ProdutoView : VerticalLayout(), View {
  var grid: Grid<Produto>? = null
  private val viewModel = ProdutoViewModel { viewModel ->
    grid?.dataProvider = ListDataProvider(viewModel.listaGrid)
    dialogProduto.apply {
      
      gradeProduto.setItems(viewModel.grades)
      viewModel.grades.firstOrNull()?.let {
        gradeProduto.value = it
      }
      
      descricaoProduto.value = viewModel.produtoVo.descricaoProduto
    }
  }
  
  val dialogProduto = DialogProduto()
  
  init {
    isMargin = true
    setSizeFull()
    title("Entrada de produtos")
    
    horizontalLayout {
      w = fillParent
      isMargin = false
      textField("Pesquisa") {
        w = fillParent
      }
    }
    horizontalLayout {
      button("Adionar Produto") {
        addClickListener {
          dialogProduto.binder.readBean(viewModel.produtoVo)
          dialogProduto.show()
        }
      }
      button("Remover Produto") {
        addClickListener {
        }
      }
    }
    grid = grid(Produto::class) {
      removeAllColumns()
      setSizeFull()
      expandRatio = 1.0f
      addColumnFor(Produto::codigo) {
        caption = "Código"
        
      }
      addColumnFor(Produto::nome) {
        expandRatio = 5
        caption = "Descrição"
      }
      addColumnFor(Produto::grade) {
        caption = "Grade"
      }
      addColumnFor(Produto::custo) {
        caption = "Custo"
        setRenderer(NumberRenderer(DecimalFormat("0.0000")))
        align = VAlign.Right
      }
    }
    viewModel.execPesquisa()
    
  }
  
  inner class DialogProduto : DialogPopup<ProdutoVo>("Produto", ProdutoVo::class) {
    val codigoProduto: TextField
    val descricaoProduto: Label
    val gradeProduto: ComboBox<String>
    
    init {
      codigoProduto = form.textField {
        icon = VaadinIcons.BARCODE
        caption = "Código"
        addValueChangeListener { e ->
          if (e.isUserOriginated) {
            viewModel.updateGrades(e.value)
          }
        }
        bind(binder).bind(ProdutoVo::codigoProduto)
      }
      
      descricaoProduto = form.label("Descrição")
      
      gradeProduto = form.comboBox<String> {
        icon = VaadinIcons.GRID
        caption = "Grade"
        isEmptySelectionAllowed = false
        isTextInputAllowed = false
        bind(binder).bind(ProdutoVo::gradeProduto)
      }
      
      addClickListenerOk {
        this.binder.writeBean(viewModel.produtoVo)
        viewModel.saveUpdateProduto()
      }
    }
  }
}


