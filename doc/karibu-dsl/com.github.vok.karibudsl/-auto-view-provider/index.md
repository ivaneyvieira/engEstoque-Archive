[com.github.vok.karibudsl](../index.md) / [AutoViewProvider](.)

# AutoViewProvider

`class AutoViewProvider : Any`

Internal class which enumerates views. Do not use directly - instead, just add [autoViewProvider](../auto-view-provider.md) to your [com.vaadin.navigator.Navigator](#),
see [autoViewProvider](../auto-view-provider.md) for more details.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `AutoViewProvider()`<br>Internal class which enumerates views. Do not use directly - instead, just add [autoViewProvider](../auto-view-provider.md) to your [com.vaadin.navigator.Navigator](#),
see [autoViewProvider](../auto-view-provider.md) for more details. |

### Functions

| Name | Summary |
|---|---|
| [onStartup](on-startup.md) | `fun onStartup(c: MutableSet<`[`Class`](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)`<*>>?, ctx: <ERROR CLASS>?): Unit` |

### Companion Object Functions

| Name | Summary |
|---|---|
| [getMapping](get-mapping.md) | `fun <T> getMapping(clazz: `[`Class`](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)`<T>): String` |
| [getView](get-view.md) | `fun getView(viewName: String): <ERROR CLASS>?` |
| [getViewName](get-view-name.md) | `fun getViewName(viewAndParameters: String): String?` |
