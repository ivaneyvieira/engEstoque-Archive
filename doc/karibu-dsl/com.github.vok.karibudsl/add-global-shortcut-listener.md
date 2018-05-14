[com.github.vok.karibudsl](index.md) / [addGlobalShortcutListener](.)

# addGlobalShortcutListener

`fun <ERROR CLASS>.addGlobalShortcutListener(shortcut: `[`KeyShortcut`](-key-shortcut/index.md)`, action: () -> Unit): <ERROR CLASS>`

Adds global shortcut listener. The listener is not added directly for this component - instead it is global, up to the nearest parent
Panel, UI or Window.

### Parameters

`shortcut` - the shortcut, e.g. `Ctrl + Alt + C`

`fun <ERROR CLASS>.addGlobalShortcutListener(keyCode: Int, action: () -> Unit): <ERROR CLASS>`

Adds global shortcut listener. The listener is not added directly for this component - instead it is global, up to the nearest parent
Panel, UI or Window.

### Parameters

`keyCode` - the key code, e.g. [ShortcutAction.KeyCode.C](#)