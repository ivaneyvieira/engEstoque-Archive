package br.com.engecopi.estoque.ui.views

import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.estoque.ui.title
import br.com.engecopi.estoque.viewmodel.ProdutoViewModel
import com.github.vok.karibudsl.AutoView
import com.github.vok.karibudsl.VAlign
import com.github.vok.karibudsl.addColumnFor
import com.github.vok.karibudsl.align
import com.github.vok.karibudsl.bind
import com.github.vok.karibudsl.button
import com.github.vok.karibudsl.expandRatio
import com.github.vok.karibudsl.fillParent
import com.github.vok.karibudsl.grid
import com.github.vok.karibudsl.horizontalLayout
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
  val viewModel = ProdutoViewModel { viewModel ->
    grid?.dataProvider = ListDataProvider(viewModel.listaGrid)
    gradeProduto?.let {
      gradeProduto.setItems(viewModel.grades)
      viewModel.grades.firstOrNull()?.let {
        gradeProduto.value = it
      }
    }
    descricaoProduto?.value = viewModel.descricaoProduto
  }
  
  val codigoProduto = TextField().apply {
    icon = VaadinIcons.BARCODE
    caption = "Código"
    addValueChangeListener { e ->
      if (e.isUserOriginated) {
        viewModel.updateGrades(e.value)
      }
    }
  }
  
  val descricaoProduto: Label? = label {
    caption = "Descrição"
  }
  
  val gradeProduto: ComboBox<String>? = ComboBox<String>().apply {
    icon = VaadinIcons.GRID
    caption = "Grade"
    isEmptySelectionAllowed = false
    isTextInputAllowed = false
  }
  
  val dialogProduto = DialogPopup("Produto", ProdutoVo::class) {
    form.addComponent(codigoProduto)
    form.addComponent(descricaoProduto)
    form.addComponent(gradeProduto)
    codigoProduto.bind(binder).bind(ProdutoVo::codigoProduto)
    gradeProduto?.bind(binder)?.bind(ProdutoVo::gradeProduto)
    addClickListenerOk{
      val produto = bean
    }
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
    horizontalLayout {
      button("Adionar Produto") {
        addClickListener {
          val prd = ProdutoVo()
          dialogProduto.bean = prd
          dialogProduto.show()
        }
      }
      button("Remover Produto") {
      
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

data class ProdutoVo(
        var codigoProduto: String = "19",
        var gradeProduto: String = ""
                    )
