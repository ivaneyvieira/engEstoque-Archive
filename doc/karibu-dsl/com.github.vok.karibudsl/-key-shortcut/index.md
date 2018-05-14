[com.github.vok.karibudsl](../index.md) / [KeyShortcut](.)

# KeyShortcut

`data class KeyShortcut : `[`Serializable`](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)

Denotes a keyboard shortcut, such as [ModifierKey.Ctrl](../-modifier-key/-ctrl.md)+[ModifierKey.Alt](../-modifier-key/-alt.md)+[ShortcutAction.KeyCode.C](#)`. When properly imported, this
becomes `Ctrl+Alt+C` ;)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `KeyShortcut(keyCode: Int, modifierKeys: Set<`[`ModifierKey`](../-modifier-key/index.md)`> = setOf())`<br>Denotes a keyboard shortcut, such as [ModifierKey.Ctrl](../-modifier-key/-ctrl.md)+[ModifierKey.Alt](../-modifier-key/-alt.md)+[ShortcutAction.KeyCode.C](#)`. When properly imported, this
becomes `Ctrl+Alt+C` ;) |

### Properties

| Name | Summary |
|---|---|
| [keyCode](key-code.md) | `val keyCode: Int`<br>one of the ShortcutAction.KeyCode.* constants. |
| [modifierKeys](modifier-keys.md) | `val modifierKeys: Set<`[`ModifierKey`](../-modifier-key/index.md)`>` |
| [vaadinModifiers](vaadin-modifiers.md) | `val vaadinModifiers: IntArray` |
