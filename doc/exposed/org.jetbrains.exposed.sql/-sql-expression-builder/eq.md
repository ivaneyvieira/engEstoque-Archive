[org.jetbrains.exposed.sql](../index.md) / [SqlExpressionBuilder](index.md) / [eq](.)

# eq

`infix fun <T> `[`ExpressionWithColumnType`](../-expression-with-column-type/index.md)`<T>.eq(t: T): `[`Op`](../-op/index.md)`<Boolean>`
`infix fun <T : Comparable<T>> `[`Column`](../-column/index.md)`<`[`EntityID`](../../org.jetbrains.exposed.dao/-entity-i-d/index.md)`<T>>.eq(t: T?): `[`Op`](../-op/index.md)`<Boolean>`
`infix fun <T, S1 : T?, S2 : T?> `[`Expression`](../-expression/index.md)`<in S1>.eq(other: `[`Expression`](../-expression/index.md)`<in S2>): `[`Op`](../-op/index.md)`<Boolean>`