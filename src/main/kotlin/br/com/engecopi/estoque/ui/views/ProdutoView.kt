package br.com.engecopi.estoque.ui.views

import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.estoque.ui.title
import br.com.engecopi.estoque.viewmodel.ProdutoViewModel
import com.github.vok.karibudsl.AutoView
import com.github.vok.karibudsl.VAlign
import com.github.vok.karibudsl.addColumnFor
import com.github.vok.karibudsl.align
import com.github.vok.karibudsl.expandRatio
import com.github.vok.karibudsl.fillParent
import com.github.vok.karibudsl.grid
import com.github.vok.karibudsl.horizontalLayout
import com.github.vok.karibudsl.isMargin
import com.github.vok.karibudsl.textField
import com.github.vok.karibudsl.w
import com.vaadin.data.provider.ListDataProvider
import com.vaadin.navigator.View
import com.vaadin.ui.Grid
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.renderers.NumberRenderer
import java.text.DecimalFormat

@AutoView
class ProdutoView : VerticalLayout(), View {
  var grid: Grid<Produto>? = null
  val viewModel = ProdutoViewModel { viewModel ->
    grid?.setDataProvider(ListDataProvider(viewModel.listaGrid))
  }
  
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
    val dataProvider: ListDataProvider<Produto> = ListDataProvider(emptyList())
    grid = grid(dataProvider = dataProvider) {
      setSizeFull()
      expandRatio = 1.0f
      addColumnFor(Produto::codigo) {
        caption = "Código"
        
      }
      addColumnFor(Produto::nome) {
        caption = "Descrição"
      }
      addColumnFor(Produto::custo) {
        caption = "Custo"
        setRenderer(NumberRenderer(DecimalFormat("0.0000")))
        align = VAlign.Right
      }
    }
    viewModel.execPesquisa()
    
  }
  
}