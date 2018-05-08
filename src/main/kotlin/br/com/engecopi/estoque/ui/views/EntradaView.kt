package br.com.engecopi.estoque.ui.views

import br.com.engecopi.estoque.ui.title
import com.github.vok.karibudsl.AutoView
import com.github.vok.karibudsl.fillParent
import com.github.vok.karibudsl.grid
import com.github.vok.karibudsl.horizontalLayout
import com.github.vok.karibudsl.isMargin
import com.github.vok.karibudsl.panel
import com.github.vok.karibudsl.textField
import com.github.vok.karibudsl.w
import com.vaadin.navigator.View
import com.vaadin.ui.VerticalLayout

@AutoView("")
class EntradaView : VerticalLayout(), View {
  init {
    isMargin = true
    title("Entrada de produtos")
    
    horizontalLayout {
      w = fillParent
      isMargin = false
      textField("Pesquisa") {
        w = fillParent
        
      }
  
    }
  }
}