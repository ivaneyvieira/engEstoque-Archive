package br.com.engecopi.estoque.ui.views

import br.com.engecopi.framework.printer.ZPLPreview
import br.com.engecopi.utils.SystemUtils
import br.com.engecopi.utils.makeResource
import com.github.vok.karibudsl.h
import com.github.vok.karibudsl.label
import com.github.vok.karibudsl.panel
import com.github.vok.karibudsl.perc
import com.github.vok.karibudsl.px
import com.github.vok.karibudsl.textArea
import com.github.vok.karibudsl.verticalLayout
import com.github.vok.karibudsl.w
import com.vaadin.ui.UI
import com.vaadin.ui.Window
import com.vaadin.ui.themes.ValoTheme

class DialogEtiqueta(caption: String, val text: String) : Window(caption) {
  val zpls = (0..5).mapNotNull {  SystemUtils.resize(ZPLPreview.createImage(text, it),
                               600, 600)}
  
  init {
    isResizable = false
    isModal = true
    w = 50.perc
    h = 80.perc
    verticalLayout {
      textArea("Layout") {
        w = 100.perc
        h = 300.px
        isReadOnly = true
        value = text
      }
      zpls.forEach {zpl->
        label {
          icon = zpl.makeResource()
        }
      }
    }
  }
  
  fun show() {
    center()
    UI.getCurrent().addWindow(this)
  }
}