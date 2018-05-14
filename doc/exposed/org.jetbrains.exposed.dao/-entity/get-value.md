[org.jetbrains.exposed.dao](../index.md) / [Entity](index.md) / [getValue](.)

# getValue

`operator fun <RID : Comparable<RID>, T : `[`Entity`](index.md)`<RID>> `[`Reference`](../-reference/index.md)`<RID, T>.getValue(o: `[`Entity`](index.md)`<ID>, desc: KProperty<*>): T`
`operator fun <RID : Comparable<RID>, T : `[`Entity`](index.md)`<RID>> `[`OptionalReference`](../-optional-reference/index.md)`<RID, T>.getValue(o: `[`Entity`](index.md)`<ID>, desc: KProperty<*>): T?`
`operator fun <T> `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<T>.getValue(o: `[`Entity`](index.md)`<ID>, desc: KProperty<*>): T`
`operator fun <TColumn, TReal> `[`ColumnWithTransform`](../-column-with-transform/index.md)`<TColumn, TReal>.getValue(o: `[`Entity`](index.md)`<ID>, desc: KProperty<*>): TReal`