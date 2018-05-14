[org.jetbrains.exposed.sql](../index.md) / [Table](index.md) / [index](.)

# index

`fun index(isUnique: Boolean = false, vararg columns: `[`Column`](../-column/index.md)`<*>): Unit`
`fun index(customIndexName: String? = null, isUnique: Boolean = false, vararg columns: `[`Column`](../-column/index.md)`<*>): Unit`
`fun <T> `[`Column`](../-column/index.md)`<T>.index(customIndexName: String? = null, isUnique: Boolean = false): `[`Column`](../-column/index.md)`<T>`