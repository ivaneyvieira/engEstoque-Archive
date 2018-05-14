[com.github.vok.karibudsl](../index.md) / [AutoView](.)

# AutoView

`@Target([AnnotationTarget.CLASS]) annotation class AutoView : Annotation`

Annotate your views with this annotation, and the [autoViewProvider](../auto-view-provider.md) will auto-discover them and register them.

By default the view will be assigned a colon-separated name, derived from your view class name. The trailing View is dropped.
For example, UserListView will be mapped to user-list. You can attach this annotation to a view, to modify this behavior.
It is often a good practice to mark one particular view as the root view, by annotating the class with `AutoView("")`.
This view will be shown initially when the user enters your application.

All views must directly be annotated with this annotation. This annotation does not apply to subclasses; this annotation does not apply to
implementors of an annotated interface. This is an unfortunate limitation of ServletContainerInitializer.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `AutoView(value: String = VIEW_NAME_USE_DEFAULT)`<br>Annotate your views with this annotation, and the [autoViewProvider](../auto-view-provider.md) will auto-discover them and register them. |

### Properties

| Name | Summary |
|---|---|
| [value](value.md) | `val value: String` |
