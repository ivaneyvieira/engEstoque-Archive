[com.github.vok.karibudsl](index.md) / [grid](.)

# grid

`fun <T : Any> <ERROR CLASS>.grid(itemClass: KClass<T>? = null, caption: String? = null, dataProvider: <ERROR CLASS><T, out <ERROR CLASS>>? = null, block: (<ERROR CLASS><T>) -> Unit = {}): <ERROR CLASS>`

Creates a grid.

### Parameters

`itemClass` - host items of this class. If not null, columns are created automatically for this class. This is not recommended:
https://github.com/mvysny/karibu-dsl/issues/4