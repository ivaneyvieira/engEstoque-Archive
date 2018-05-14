[com.github.vok.karibudsl](../index.md) / [SpecialContainer](.)

# SpecialContainer

`interface SpecialContainer : Any`

A specialized version of [ComponentContainer](#), for certain special containers. The DSL's
[init](../init.md) method will invoke [addComponent](add-component.md) method with the components being registered.

For example, there may be a special container (say, a ticker) which does not attach the components as its Vaadin
children immediately - rather it only remembers the components added via [addComponent](add-component.md) in a special list and
Vaadin-attaches them once every 10 seconds, one at a time. This way, you can use the DSL to define all children (or
pages) of this special component, without having them attached immediately as Vaadin children.

### Functions

| Name | Summary |
|---|---|
| [addComponent](add-component.md) | `abstract fun addComponent(component: <ERROR CLASS>): Unit`<br>Adds the component into this container. Called by [init](../init.md) when DSL-adding children to this container. |
