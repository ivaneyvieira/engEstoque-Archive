package br.com.engecopi.estoque.ui

import br.com.engecopi.estoque.ui.views.EntradaView
import br.com.engecopi.estoque.ui.views.ProdutoView
import br.com.engecopi.estoque.ui.views.SaidaView
import com.github.vok.karibudsl.autoViewProvider
import com.github.vok.karibudsl.fillParent
import com.github.vok.karibudsl.label
import com.github.vok.karibudsl.valoMenu
import com.github.vok.karibudsl.w
import com.vaadin.annotations.JavaScript
import com.vaadin.annotations.Theme
import com.vaadin.annotations.Title
import com.vaadin.annotations.VaadinServletConfiguration
import com.vaadin.annotations.Viewport
import com.vaadin.icons.VaadinIcons
import com.vaadin.navigator.Navigator
import com.vaadin.navigator.PushStateNavigation
import com.vaadin.navigator.ViewDisplay
import com.vaadin.server.Page
import com.vaadin.server.VaadinRequest
import com.vaadin.server.VaadinServlet
import com.vaadin.shared.Position
import com.vaadin.ui.HasComponents
import com.vaadin.ui.Notification
import com.vaadin.ui.Notification.Type.ERROR_MESSAGE
import com.vaadin.ui.UI
import com.vaadin.ui.themes.ValoTheme
import org.slf4j.LoggerFactory
import org.slf4j.bridge.SLF4JBridgeHandler
import javax.servlet.annotation.WebServlet

@Theme("mytheme")
@Title("Controle de estoque")
@Viewport("width=device-width, initial-scale=1.0")
@JavaScript("https://code.jquery.com/jquery-2.1.4.min.js",
            "https://code.responsivevoice.org/responsivevoice.js")
@PushStateNavigation
class EstoqueUI : UI() {
  override fun init(request: VaadinRequest?) {
    val content = valoMenu {
      appTitle = "<h3>Estoque <strong>Engecopi</strong></h3>"
      
      section("Movimentacao")
      menuButton("Entrada", VaadinIcons.INBOX, view = EntradaView::class.java)
      menuButton("Saida", VaadinIcons.OUTBOX, view = SaidaView::class.java)
      section("Consulta")
      menuButton("Produtos", VaadinIcons.PACKAGE, view = ProdutoView::class.java)
    }
    
    // Read more about navigators here: https://github.com/mvysny/karibu-dsl
    navigator = Navigator(this, content as ViewDisplay)
    navigator.addProvider(autoViewProvider)
    setErrorHandler { e ->
      log.error("Erro não identificado ${e.throwable}", e.throwable)
      // when the exception occurs, show a nice notification
      Notification("Oops", "\n" +
                           "Ocorreu um erro e lamentamos muito isso. Já está trabalhando na correção!",
                   ERROR_MESSAGE)
              .apply {
                styleName += " " + ValoTheme.NOTIFICATION_CLOSABLE
                position = Position.TOP_CENTER
                show(Page.getCurrent())
              }
    }
  }
  
  companion object {
    @JvmStatic
    private val log = LoggerFactory.getLogger(EstoqueUI::class.java)
  }
}

@WebServlet(urlPatterns = arrayOf("/*"), name = "MyUIServlet", asyncSupported = true)
@VaadinServletConfiguration(ui = EstoqueUI::class, productionMode = false)
class MyUIServlet : VaadinServlet() {
  companion object {
    init {
      // Vaadin logs into java.util.logging. Redirect that, so that all logging goes through slf4j.
      SLF4JBridgeHandler.removeHandlersForRootLogger()
      SLF4JBridgeHandler.install()
    }
  }
}

fun HasComponents.title(title: String) = label(title) {
  w = fillParent
  addStyleNames(ValoTheme.LABEL_H2, ValoTheme.LABEL_COLORED)
}