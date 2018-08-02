package br.com.engecopi.estoque.ui.views

import br.com.engecopi.framework.printer.ZPLPreview
import br.com.engecopi.utils.SystemUtils
import br.com.engecopi.utils.makeResource
import com.github.vok.karibudsl.button
import com.github.vok.karibudsl.label
import com.github.vok.karibudsl.link
import com.github.vok.karibudsl.perc
import com.github.vok.karibudsl.verticalLayout
import com.github.vok.karibudsl.w
import com.vaadin.annotations.Theme
import com.vaadin.server.VaadinRequest
import com.vaadin.server.VaadinSession
import com.vaadin.shared.ui.ContentMode
import com.vaadin.ui.Button
import com.vaadin.ui.JavaScript
import com.vaadin.ui.Label
import com.vaadin.ui.Link
import com.vaadin.ui.UI
import java.util.concurrent.*

@Theme("xxx")
class DialogEtiqueta : UI() {
  var button : Link? = null
  var codeZpl : Label? = null
  var imgZpl : Label? = null
  override fun init(request: VaadinRequest?) {
    
    val text = request?.getParameter("textLayout")
    content = verticalLayout {
     button = link("Imprimir") {
        addClickListener {
          button?.isVisible = false
          codeZpl?.isVisible = true
          imgZpl?.isVisible = false
          JavaScript.getCurrent().execute("print();")
        }
        addStyleName("noprint")
      }
      codeZpl = label {
        w = 100.perc
        value = text ?: ""
        contentMode = ContentMode.PREFORMATTED
        isVisible = false
      }
      imgZpl = zpls(text)?.let { zpl ->
        label {
          icon = zpl.makeResource()
          addStyleName(".noprint")
        }
      }
    }
  }
  
  fun zpls(text: String?) : ByteArray? {
    return SystemUtils.resize(ZPLPreview.createImage(text ?: "", 0),
                       600, 600)
  }
}