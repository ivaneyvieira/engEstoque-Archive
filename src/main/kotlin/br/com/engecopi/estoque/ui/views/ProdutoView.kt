package br.com.engecopi.estoque.ui.views

import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.estoque.ui.LoginService
import br.com.engecopi.estoque.ui.title
import br.com.engecopi.estoque.viewmodel.ProdutoViewModel
import br.com.engecopi.estoque.viewmodel.ProdutoViewModel.ProdutoVo
import br.com.engecopi.framework.ui.view.DialogPopup
import br.com.engecopi.framework.ui.view.LayoutView
import com.github.vok.karibudsl.AutoView
import com.github.vok.karibudsl.ModifierKey.Ctrl
import com.github.vok.karibudsl.VAlign
import com.github.vok.karibudsl.addColumnFor
import com.github.vok.karibudsl.align
import com.github.vok.karibudsl.bind
import com.github.vok.karibudsl.button
import com.github.vok.karibudsl.clickShortcut
import com.github.vok.karibudsl.comboBox
import com.github.vok.karibudsl.expandRatio
import com.github.vok.karibudsl.fillParent
import com.github.vok.karibudsl.grid
import com.github.vok.karibudsl.horizontalLayout
import com.github.vok.karibudsl.isMargin
import com.github.vok.karibudsl.textField
import com.github.vok.karibudsl.w
import com.sun.javafx.webkit.theme.Renderer.setRenderer
import com.vaadin.data.provider.ListDataProvider
import com.vaadin.event.ShortcutAction.KeyCode.DELETE
import com.vaadin.event.ShortcutAction.KeyCode.INSERT
import com.vaadin.ui.ComboBox
import com.vaadin.ui.Grid
import com.vaadin.ui.Notification
import com.vaadin.ui.TextField
import com.vaadin.ui.renderers.NumberRenderer
import java.text.DecimalFormat

@AutoView
class ProdutoView : LayoutView<ProdutoViewModel>() {
  val lojaDefault = LoginService.currentUser?.storeno ?: 0
  var grid: Grid<Produto>? = null
  override val viewModel = ProdutoViewModel(lojaDefault) { viewModel ->
    viewModel as ProdutoViewModel
    grid?.apply {
      dataProvider.refreshAll()
      setDataProvider(dataProvider)
    }
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
    title("Produtos")
    
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
          val produto = grid?.selectedItems?.firstOrNull()
          if (produto == null) {
            Notification.show("Não há produto selecionado",
                              Notification.Type.WARNING_MESSAGE
                             )
          } else {
            val msg = viewModel.validaDelete(produto)
            if (msg == "")
              viewModel.deleta(produto)
            else
              Notification.show(msg,
                                Notification.Type.WARNING_MESSAGE
                               )
          }
        }
      }
      button("Atualiza Saldo") {
        addClickListener {
          val produto = grid?.selectedItems?.firstOrNull()
          produto?.let { viewModel.atualizaSaldo(produto) }
          ?: Notification.show("Não há produto selecionado",
                               Notification.Type.WARNING_MESSAGE)
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
          // setRenderer(NumberRenderer(DecimalFormat("0")))
          //  align = VAlign.Right
        }
      }
    }
    viewModel.updateModel()
  }
  
  inner class DialogProduto : DialogPopup<ProdutoVo>("Produto", ProdutoVo::class) {
    val codigoProduto: TextField
    val descricaoProduto: TextField
    val gradeProduto: ComboBox<String>
    
    init {
      codigoProduto = form.textField {
        caption = "Código"
        addValueChangeListener { e ->
          if (e.isUserOriginated) {
            viewModel.updateGrades(e.value)
          }
        }
        bind(binder).bind(ProdutoVo::codigoProduto)
      }
      
      descricaoProduto = form.textField("Descrição") {
        isReadOnly = true
      }
      
      gradeProduto = form.comboBox {
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


