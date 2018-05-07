package br.com.engecopi.estoque.ui

import com.github.vok.karibudsl.valoMenu
import com.vaadin.annotations.JavaScript
import com.vaadin.annotations.Theme
import com.vaadin.annotations.Title
import com.vaadin.annotations.Viewport
import com.vaadin.navigator.PushStateNavigation
import com.vaadin.server.VaadinRequest
import com.vaadin.ui.UI

@Theme("mytheme")
@Title("Controle de estoque")
@Viewport("width=device-width, initial-scale=1.0")
@JavaScript("https://code.jquery.com/jquery-2.1.4.min.js", "https://code.responsivevoice.org/responsivevoice.js")
@PushStateNavigation
class EstoqueUI : UI() {
  override fun init(request: VaadinRequest?) {
    val content = valoMenu {
      appTitle = "<h3>Karibu-DSL <strong>Sample App</strong></h3>"
      userMenu {
        item("John Doe", ClassResource("profilepic300px.jpg")) {
          item("Edit Profile")
          item("Preferences")
          addSeparator()
          item("Sign Out")
        }
      }
      menuButton("Welcome", VaadinIcons.MENU, "3", WelcomeView::class.java)
      menuButton("Common UI Elements", VaadinIcons.NOTEBOOK, view = CommonElementsView::class.java)
      section("Components", "5")
      menuButton("Labels", VaadinIcons.TEXT_LABEL, view = Labels::class.java)
      menuButton("Buttons & Links", VaadinIcons.BUTTON, view = ButtonsAndLinks::class.java)
      menuButton("Text Fields", VaadinIcons.TEXT_INPUT, view = TextFields::class.java)
      menuButton("Date Fields", VaadinIcons.DATE_INPUT, view = DateFields::class.java)
      menuButton("Combo Boxes", VaadinIcons.DROP, view = ComboBoxes::class.java)
      menuButton("Selects", VaadinIcons.SELECT, view = NativeSelects::class.java)
      menuButton("Check Boxes & Option Groups", VaadinIcons.CHECK, view = CheckBoxes::class.java)
      menuButton("Sliders", VaadinIcons.SLIDER, view = Sliders::class.java)
      menuButton("Color Pickers", VaadinIcons.PAINTBRUSH, view = ColorPickers::class.java)
      menuButton("Menu Bars", VaadinIcons.MENU, view = MenuBars::class.java)
      menuButton("Trees", VaadinIcons.FILE_TREE, view = Trees::class.java)
      section("Containers", "4")
      menuButton("Panels", VaadinIcons.PANEL, view = Panels::class.java)
      menuButton("Split Panels", VaadinIcons.ROAD_SPLIT, view = SplitPanels::class.java)
      menuButton("Tabs", VaadinIcons.TAB, "123", Tabsheets::class.java)
      menuButton("Accordions", VaadinIcons.ACCORDION_MENU, view = Accordions::class.java)
      menuButton("Popup Views", VaadinIcons.MODAL, view = PopupViews::class.java)
      section("Other", "1")
      menuButton("Form Demo", VaadinIcons.FORM, view = FormView::class.java)
    }
  
    // Read more about navigators here: https://github.com/mvysny/karibu-dsl
    navigator = Navigator(this, content as ViewDisplay)
    navigator.addProvider(autoViewProvider)
    setErrorHandler { e ->
      log.error("Vaadin UI uncaught exception ${e.throwable}", e.throwable)
      // when the exception occurs, show a nice notification
      Notification("Oops", "An error occurred, and we are really sorry about that. Already working on the fix!", Notification.Type.ERROR_MESSAGE).apply {
        styleName += " " + ValoTheme.NOTIFICATION_CLOSABLE
        position = Position.TOP_CENTER
        show(Page.getCurrent())
      }
    }
  }
  
  companion object {
    @JvmStatic
    private val log = LoggerFactory.getLogger(MyUI::class.java)
  }
}

@WebServlet(urlPatterns = arrayOf("/*"), name = "MyUIServlet", asyncSupported = true)
@VaadinServletConfiguration(ui = MyUI::class, productionMode = false)
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
  addStyleNames(ValoTheme.LABEL_H1, ValoTheme.LABEL_COLORED)
}