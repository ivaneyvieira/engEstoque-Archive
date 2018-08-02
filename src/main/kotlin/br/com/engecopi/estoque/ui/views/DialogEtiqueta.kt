package br.com.engecopi.estoque.ui.views

import br.com.engecopi.framework.printer.ZPLPreview
import br.com.engecopi.utils.SystemUtils
import br.com.engecopi.utils.makeResource
import com.github.vok.karibudsl.button
import com.github.vok.karibudsl.label
import com.github.vok.karibudsl.perc
import com.github.vok.karibudsl.verticalLayout
import com.github.vok.karibudsl.w
import com.vaadin.server.VaadinRequest
import com.vaadin.server.VaadinSession
import com.vaadin.shared.ui.ContentMode
import com.vaadin.ui.JavaScript
import com.vaadin.ui.UI

class DialogEtiqueta : UI() {
  override fun init(request: VaadinRequest?) {
    
    val text = request?.getParameter("textLayout")
    content = verticalLayout {
      button("Imprimir") {
        addClickListener {
          JavaScript.getCurrent().execute("print();")
        }
      }
      label {
        w = 100.perc
        value = text ?: ""
        contentMode = ContentMode.PREFORMATTED
      }
      zpls(text).forEach { zpl ->
        label {
          icon = zpl.makeResource()
        }
      }
    }
  }
  
  fun zpls(text: String?) = (0..5).mapNotNull {
    SystemUtils.resize(ZPLPreview.createImage(text ?: "", it),
                       600, 600)
  }
}