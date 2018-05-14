[com.github.vok.karibudsl](index.md) / [removeComponentAt](.)

# removeComponentAt

`fun <ERROR CLASS>.removeComponentAt(index: Int): Unit`

Removes a component at given [index](remove-component-at.md#com.github.vok.karibudsl$removeComponentAt(, kotlin.Int)/index) from this container. Optimized for [CssLayout](#) and [AbstractOrderedLayout](#)s, but works with any
[ComponentContainer](#).

### Exceptions

`IndexOutOfBoundsException` - If the index is out of range.