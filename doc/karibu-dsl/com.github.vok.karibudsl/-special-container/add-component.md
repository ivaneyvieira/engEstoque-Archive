[com.github.vok.karibudsl](../index.md) / [SpecialContainer](index.md) / [addComponent](.)

# addComponent

`abstract fun addComponent(component: <ERROR CLASS>): Unit`

Adds the component into this container. Called by [init](../init.md) when DSL-adding children to this container.

Note that there is no `removeComponent()` method nor any sort of support for listing of the components added via this
method. This is because the DSL only needs to add the components; the component list itself highly depends on the implementation
of the component and might cause confusion with the [HasComponents](#) list of attached components.
Therefore, I'm keeping this interface as dumb as possible.

### Parameters

`component` - the component to be added.