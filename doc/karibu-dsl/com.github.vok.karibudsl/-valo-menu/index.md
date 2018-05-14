[com.github.vok.karibudsl](../index.md) / [ValoMenu](.)

# ValoMenu

`open class ValoMenu : Any`

A main screen with a responsive Valo menu and a view placeholder, where the View contents will go upon navigation.
You should set this class as the contents of your UI and set it to the [Navigator](#) as follows:

```
class UI {
  override fun init(request: VaadinRequest?) {
    val content = valoMenu {
      appTitle = "<strong>Penny's Shop</strong>"
      userMenu {
        item("John Doe", ClassResource("profilepic300px.jpg")) {
          item("Edit Profile")
          item("Preferences")
          addSeparator()
          item("Sign Out")
        }
      }
      menuButton("Shoes", VaadinIcons.WALLET)
      menuButton("Liquer", VaadinIcons.GLASS, badge = "3")
      section("Other")
    }
    navigator = Navigator(this, content as ViewDisplay)
    ...
  }
}
```

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ValoMenu()`<br>A main screen with a responsive Valo menu and a view placeholder, where the View contents will go upon navigation.
You should set this class as the contents of your UI and set it to the [Navigator](#) as follows: |

### Properties

| Name | Summary |
|---|---|
| [appTitle](app-title.md) | `var appTitle: String`<br>The application title. Warning - may contain html; html is not escaped and is sent to the browser as-is. |
| [viewPlaceholder](view-placeholder.md) | `var viewPlaceholder: <ERROR CLASS>`<br>The current view placeholder - all views will be placed here. By default a full-screen CssLayout which scrolls its contents;
you can set a different placeholder component to replace the original one. |

### Functions

| Name | Summary |
|---|---|
| [menuButton](menu-button.md) | `fun menuButton(caption: String, icon: <ERROR CLASS>? = null, badge: String? = null, view: `[`Class`](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)`<out <ERROR CLASS>>? = null, block: (`[`MenuButton`](../-menu-button/index.md)`) -> Unit = {}): `[`MenuButton`](../-menu-button/index.md)<br>Registers a button to a menu with given [icon](menu-button.md#com.github.vok.karibudsl.ValoMenu$menuButton(kotlin.String, , kotlin.String, java.lang.Class(()), kotlin.Function1((com.github.vok.karibudsl.MenuButton, kotlin.Unit)))/icon) and [caption](menu-button.md#com.github.vok.karibudsl.ValoMenu$menuButton(kotlin.String, , kotlin.String, java.lang.Class(()), kotlin.Function1((com.github.vok.karibudsl.MenuButton, kotlin.Unit)))/caption), which optionally launches given [view](menu-button.md#com.github.vok.karibudsl.ValoMenu$menuButton(kotlin.String, , kotlin.String, java.lang.Class(()), kotlin.Function1((com.github.vok.karibudsl.MenuButton, kotlin.Unit)))/view). |
| [section](section.md) | `fun section(caption: String, badge: String? = null, block: (<ERROR CLASS>) -> Unit = {}): <ERROR CLASS>`<br>Creates a separator between menu items, with given [caption](section.md#com.github.vok.karibudsl.ValoMenu$section(kotlin.String, kotlin.String, kotlin.Function1((, kotlin.Unit)))/caption) and an optional [badge](section.md#com.github.vok.karibudsl.ValoMenu$section(kotlin.String, kotlin.String, kotlin.Function1((, kotlin.Unit)))/badge). Returns the separator component. |
| [showView](show-view.md) | `open fun showView(view: <ERROR CLASS>): Unit` |
| [userMenu](user-menu.md) | `fun userMenu(block: (<ERROR CLASS>) -> Unit): <ERROR CLASS>`<br>The user menu is a popup menu, displayed when the user's photo/name is clicked. You can add typical user-related items here,
like user details, change password, logout etc. |
