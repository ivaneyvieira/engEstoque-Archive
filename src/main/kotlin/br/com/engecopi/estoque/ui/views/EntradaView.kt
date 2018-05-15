package br.com.engecopi.estoque.ui.views

import br.com.engecopi.estoque.model.Entrada
import br.com.engecopi.estoque.ui.title
import br.com.engecopi.estoque.viewmodel.EntradaViewModel
import br.com.engecopi.estoque.viewmodel.NotaEntradaVo
import com.github.vok.karibudsl.AutoView
import com.github.vok.karibudsl.ModifierKey.Ctrl
import com.github.vok.karibudsl.addColumnFor
import com.github.vok.karibudsl.bind
import com.github.vok.karibudsl.button
import com.github.vok.karibudsl.clickShortcut
import com.github.vok.karibudsl.expandRatio
import com.github.vok.karibudsl.fillParent
import com.github.vok.karibudsl.grid
import com.github.vok.karibudsl.horizontalLayout
import com.github.vok.karibudsl.isMargin
import com.github.vok.karibudsl.textField
import com.github.vok.karibudsl.w
import com.vaadin.data.converter.StringToIntegerConverter
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
  
  val dialogNotaEntrada = DialogNotaEntrada()
  
  init {
    isMargin = true
    setSizeFull()
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
      button("Carregar Nota de Entrada") {
        clickShortcut = Ctrl + KeyCode.INSERT
        addClickListener {
        }
      }
      button("Remover Remover Nota de Entrada") {
        clickShortcut = Ctrl + KeyCode.DELETE
        addClickListener {
        }
      }
    }
    grid = grid(Entrada::class) {
      dataProvider = ListDataProvider(viewModel.listaGrid)
      removeAllColumns()
      setSizeFull()
      expandRatio = 1.0f
      addColumnFor(Entrada::numero) {
        caption = "Número"
      }
      addColumnFor(Entrada::loja) {
        caption = "Loja"
      }
      addColumnFor(Entrada::data) {
        caption = "Data"
        // setRenderer(DateTimeRenderer("dd/MM/yyyy"))
      }
      addColumnFor(Entrada::hora) {
        caption = "Hora"
        //  setRenderer(DateTimeRenderer("hh:mm"))
      }
    }
  }
  
  inner class DialogNotaEntrada : DialogPopup<NotaEntradaVo>("Pesquisa Nota de Entrada", NotaEntradaVo::class) {
    val numero = form.textField("Número") {
      bind(binder).bind(NotaEntradaVo::numero)
    }
    val serie = form.textField("Série") {
      bind(binder).bind(NotaEntradaVo::serie)
    }
    val loja = form.textField("Loja") {
      bind(binder)
              .withConverter(StringToIntegerConverter("A loja deve ser numérica"))
              .bind(NotaEntradaVo::loja)
    }
    
    init {
      addClickListenerOk {
        this.binder.writeBean(viewModel.notaEntradaVo)
        viewModel.processaNotaEntrada()
      }
    }
    
  }
  
}