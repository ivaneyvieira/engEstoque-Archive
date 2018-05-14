[com.github.vok.karibudsl](index.md) / [init](.)

# init

`fun <T> <ERROR CLASS>.init(component: T, block: (T) -> Unit = {}): T`

When introducing extensions for your custom components, just call this in your method. For example:

`fun HasComponents.shinyComponent(caption: String? = null, block: ShinyComponent.()->Unit = {}) = init(ShinyComponent(caption), block)`

Adds [component](init.md#com.github.vok.karibudsl$init(, com.github.vok.karibudsl.init.T, kotlin.Function1((com.github.vok.karibudsl.init.T, kotlin.Unit)))/component) to receiver, see [addChild](add-child.md) for details.

### Parameters

`component` - the component to attach

`block` - optional block to run over the component, allowing you to add children to the [component](init.md#com.github.vok.karibudsl$init(, com.github.vok.karibudsl.init.T, kotlin.Function1((com.github.vok.karibudsl.init.T, kotlin.Unit)))/component)