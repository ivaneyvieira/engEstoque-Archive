[org.jetbrains.exposed.dao](../index.md) / [Entity](index.md) / [setValue](.)

# setValue

`operator fun <RID : Comparable<RID>, T : `[`Entity`](index.md)`<RID>> `[`Reference`](../-reference/index.md)`<RID, T>.setValue(o: `[`Entity`](index.md)`<ID>, desc: KProperty<*>, value: T): Unit`
`operator fun <RID : Comparable<RID>, T : `[`Entity`](index.md)`<RID>> `[`OptionalReference`](../-optional-reference/index.md)`<RID, T>.setValue(o: `[`Entity`](index.md)`<ID>, desc: KProperty<*>, value: T?): Unit`
`operator fun <T> `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<T>.setValue(o: `[`Entity`](index.md)`<ID>, desc: KProperty<*>, value: T): Unit`
`operator fun <TColumn, TReal> `[`ColumnWithTransform`](../-column-with-transform/index.md)`<TColumn, TReal>.setValue(o: `[`Entity`](index.md)`<ID>, desc: KProperty<*>, value: TReal): Unit`