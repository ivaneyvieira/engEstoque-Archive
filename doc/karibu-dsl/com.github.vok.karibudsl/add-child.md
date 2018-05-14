[com.github.vok.karibudsl](index.md) / [addChild](.)

# addChild

`fun <ERROR CLASS>.addChild(child: <ERROR CLASS>): Unit`

Adds a [child](add-child.md#com.github.vok.karibudsl$addChild(, )/child) to this component. Only concrete subclasses are supported:

* [ComponentContainer](#)
* [SingleComponentContainer](#) (fails if the container already has a child)
* [PopupView](#)
* [AbstractSplitPanel](#)
* [SpecialContainer](-special-container/index.md)

The function will fail if the component
is already full (e.g. it is a split panel and it already contains two components).

For custom containers just implement the [SpecialContainer](-special-container/index.md) interface.

