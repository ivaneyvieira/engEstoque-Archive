[com.github.vok.karibudsl](index.md) / [getComponentAt](.)

# getComponentAt

`fun <ERROR CLASS>.getComponentAt(index: Int): <ERROR CLASS>`

Returns component at given [index](get-component-at.md#com.github.vok.karibudsl$getComponentAt(, kotlin.Int)/index). Optimized for [CssLayout](#) and [AbstractOrderedLayout](#)s, but works with any
[ComponentContainer](#).

### Exceptions

`IndexOutOfBoundsException` - If the index is out of range.