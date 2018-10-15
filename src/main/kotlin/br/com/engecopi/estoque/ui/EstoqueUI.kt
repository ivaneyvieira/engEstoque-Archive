package br.com.engecopi.estoque.ui

import br.com.engecopi.estoque.model.LoginInfo
import br.com.engecopi.estoque.model.Loja
import br.com.engecopi.estoque.model.RegistryUserInfo
import br.com.engecopi.estoque.model.Usuario
import br.com.engecopi.estoque.ui.views.EntradaView
import br.com.engecopi.estoque.ui.views.EtiquetaView
import br.com.engecopi.estoque.ui.views.ProdutoView
import br.com.engecopi.estoque.ui.views.SaidaView
import br.com.engecopi.estoque.ui.views.UsuarioView
import br.com.engecopi.framework.ui.Session
import br.com.engecopi.utils.SystemUtils
import com.github.vok.karibudsl.autoViewProvider
import com.github.vok.karibudsl.comboBox
import com.github.vok.karibudsl.expandRatio
import com.github.vok.karibudsl.fillParent
import com.github.vok.karibudsl.onLeftClick
import com.github.vok.karibudsl.px
import com.github.vok.karibudsl.valoMenu
import com.github.vok.karibudsl.w
import com.vaadin.annotations.JavaScript
import com.vaadin.annotations.PreserveOnRefresh
import com.vaadin.annotations.Theme
import com.vaadin.annotations.Title
import com.vaadin.annotations.VaadinServletConfiguration
import com.vaadin.annotations.Viewport
import com.vaadin.icons.VaadinIcons
import com.vaadin.icons.VaadinIcons.INBOX
import com.vaadin.icons.VaadinIcons.OUT
import com.vaadin.icons.VaadinIcons.OUTBOX
import com.vaadin.icons.VaadinIcons.PACKAGE
import com.vaadin.icons.VaadinIcons.PAPERCLIP
import com.vaadin.icons.VaadinIcons.USER
import com.vaadin.navigator.Navigator
import com.vaadin.navigator.PushStateNavigation
import com.vaadin.navigator.ViewDisplay
import com.vaadin.server.Page
import com.vaadin.server.Responsive
import com.vaadin.server.VaadinRequest
import com.vaadin.server.VaadinService
import com.vaadin.server.VaadinServlet
import com.vaadin.shared.Position
import com.vaadin.shared.Position.TOP_CENTER
import com.vaadin.ui.ComboBox
import com.vaadin.ui.Notification
import com.vaadin.ui.Notification.Type.ERROR_MESSAGE
import com.vaadin.ui.UI
import com.vaadin.ui.themes.ValoTheme
import org.slf4j.LoggerFactory
import org.slf4j.bridge.SLF4JBridgeHandler
import java.rmi.registry.Registry
import javax.servlet.ServletContextEvent
import javax.servlet.ServletContextListener
import javax.servlet.annotation.WebListener
import javax.servlet.annotation.WebServlet
import javax.servlet.http.Cookie
import javax.swing.text.LabelView

private val log = LoggerFactory.getLogger(EstoqueUI::class.java)

@Theme("mytheme")
@Title("Controle de estoque")
@Viewport("width=device-width, initial-scale=1.0")
@JavaScript("https://code.jquery.com/jquery-2.1.4.min.js",
            "https://code.responsivevoice.org/responsivevoice.js")
@PushStateNavigation
@PreserveOnRefresh
class EstoqueUI : UI() {
  val title = "<h3>Estoque <strong>Engecopi</strong></h3>"
  val versao = SystemUtils.readFile("/versao.txt")
  lateinit var comboLocalizacao: ComboBox<String>
  var loginInfo: LoginInfo? = null
    set(value) {
      field = value
      updateContent("")
    }

  override fun init(request: VaadinRequest?) {
    RegistryUserInfo.register {
      EstoqueUI.current?.loginInfo
    }
    isResponsive = true
    updateContent(request?.contextPath ?: "")
  }

  private fun updateContent(contextPath: String) {
    //loginInfo = Session[LoginInfo::class]
    val info = loginInfo
    if (info == null) {
      content = LoginForm("$title <p align=\"right\">$versao</p>")
      navigator = null
    } else {
      content = null
      val user = info.usuario
      valoMenu {
        this.appTitle = title

        section("Login") {
          menuButton("Usuário:", badge = user.loginName)
          menuButton("Localizacao:", badge = info.abreviacao)
          menuButton("Loja:", badge = info.usuario.loja?.sigla ?: "")
          menuButton("Sair", icon = OUT) {
            onLeftClick {
              LoginService.logout()
            }
          }
        }


        section("Movimentação") {
          menuButton("Entrada", INBOX, view = EntradaView::class.java)
          menuButton("Saída", OUTBOX, view = SaidaView::class.java)
        }
        section("Consulta") {
          menuButton("Produtos", PACKAGE, view = ProdutoView::class.java)
          if (user.admin) {
            menuButton("Usuários", USER, view = UsuarioView::class.java)
            menuButton("Etiquetas", PAPERCLIP, view = EtiquetaView::class.java)
          }
        }
      }
      // Read more about navigators here: https://github.com/mvysny/karibu-dsl
      navigator = Navigator(this, content as ViewDisplay)
      navigator.addProvider(autoViewProvider)

      navigator.navigateTo(contextPath)
      setErrorHandler { e ->
        log.error("Erro não identificado ${e.throwable}", e.throwable)
        // when the exception occurs, show a nice notification
        Notification("Oops", "\n" +
                             "Ocorreu um erro e lamentamos muito isso. Já está trabalhando na correção!",
                     ERROR_MESSAGE)
          .apply {
            styleName += " " + ValoTheme.NOTIFICATION_CLOSABLE
            position = TOP_CENTER
            show(Page.getCurrent())
          }
      }
    }
  }

  companion object {
    val current
      get() = UI.getCurrent() as? EstoqueUI
  }
}

@WebListener
class Bootstrap : ServletContextListener {
  override fun contextDestroyed(sce: ServletContextEvent?) {
    log.info("Shutting down");
    log.info("Destroying VaadinOnKotlin")
    log.info("Shutdown complete")
  }

  override fun contextInitialized(sce: ServletContextEvent?) {
    log.info("Starting up")
  }
}

@WebServlet(urlPatterns = ["/*"], name = "MyUIServlet", asyncSupported = true)
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

fun UI.setCookie(nome: String, valor: String) {
  // Create a new cookie
  val myCookie = Cookie(nome, valor)
  // Make cookie expire in 2 minutes
  myCookie.maxAge = 60 * 60 * 24 * 5
  // Set the cookie path.
  myCookie.path = VaadinService.getCurrentRequest().contextPath
  // Save cookie
  VaadinService.getCurrentResponse().addCookie(myCookie)
}

fun UI.getCokies(name: String): String? {
  val cookie = VaadinService.getCurrentRequest().cookies.toList().firstOrNull { it.name == name }
  cookie?.let {
    setCookie(it.name, it.value)
  }
  return cookie?.value
}