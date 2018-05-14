[com.github.vok.karibudsl](../index.md) / [ValoMenu](index.md) / [menuButton](.)

# menuButton

`fun menuButton(caption: String, icon: <ERROR CLASS>? = null, badge: String? = null, view: `[`Class`](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)`<out <ERROR CLASS>>? = null, block: (`[`MenuButton`](../-menu-button/index.md)`) -> Unit = {}): `[`MenuButton`](../-menu-button/index.md)

Registers a button to a menu with given [icon](menu-button.md#com.github.vok.karibudsl.ValoMenu$menuButton(kotlin.String, , kotlin.String, java.lang.Class(()), kotlin.Function1((com.github.vok.karibudsl.MenuButton, kotlin.Unit)))/icon) and [caption](menu-button.md#com.github.vok.karibudsl.ValoMenu$menuButton(kotlin.String, , kotlin.String, java.lang.Class(()), kotlin.Function1((com.github.vok.karibudsl.MenuButton, kotlin.Unit)))/caption), which optionally launches given [view](menu-button.md#com.github.vok.karibudsl.ValoMenu$menuButton(kotlin.String, , kotlin.String, java.lang.Class(()), kotlin.Function1((com.github.vok.karibudsl.MenuButton, kotlin.Unit)))/view).

### Parameters

`badge` - optional badge which is displayed in the button's top-right corner. Usually this is a number, showing number of notifications or such.

`view` - optional view; if not null, clicking this menu button will launch this view with no parameters; also the button will be marked selected
when the view is shown.