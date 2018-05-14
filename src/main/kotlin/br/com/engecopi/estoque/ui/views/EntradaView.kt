package br.com.engecopi.estoque.ui.views

import br.com.engecopi.estoque.model.Entrada
import br.com.engecopi.estoque.ui.title
import br.com.engecopi.estoque.viewmodel.EntradaViewModel
import com.github.vok.karibudsl.AutoView
import com.github.vok.karibudsl.ModifierKey.Ctrl
import com.github.vok.karibudsl.button
import com.github.vok.karibudsl.clickShortcut
import com.github.vok.karibudsl.fillParent
import com.github.vok.karibudsl.grid
import com.github.vok.karibudsl.horizontalLayout
import com.github.vok.karibudsl.isMargin
import com.github.vok.karibudsl.textField
import com.github.vok.karibudsl.w
import com.vaadin.data.provider.ListDataProvider
import com.vaadin.event.ShortcutAction.KeyCode
import com.vaadin.navigator.View
import com.vaadin.ui.Grid
import com.vaadin.ui.VerticalLayout

@AutoView("")
class EntradaView : VerticalLayout(), View {
  val viewModel = EntradaViewModel {
  
  }
  val grid: Grid<Entrada>
  
  init {
    isMargin = true
    title("Entrada de produtos")
    
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
        clickShortcut = Ctrl + KeyCode.INSERT
        addClickListener {
        }
      }
      button("Remover Produto") {
        clickShortcut = Ctrl + KeyCode.DELETE
        addClickListener {
        }
      }
    }
    grid = grid(Entrada::class) {
      dataProvider = ListDataProvider(viewModel.listaGrid)
      removeAllColumns()
      setSizeFull()
    }
  }
}