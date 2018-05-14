[com.github.vok.karibudsl](../index.md) / [ValoMenu](index.md) / [&lt;init&gt;](.)

# &lt;init&gt;

`ValoMenu()`

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

