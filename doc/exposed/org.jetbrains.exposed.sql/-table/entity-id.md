[org.jetbrains.exposed.sql](../index.md) / [Table](index.md) / [entityId](.)

# entityId

`fun <T : Comparable<T>> `[`Column`](../-column/index.md)`<T>.entityId(): `[`Column`](../-column/index.md)`<`[`EntityID`](../../org.jetbrains.exposed.dao/-entity-i-d/index.md)`<T>>`
`fun <ID : Comparable<ID>> entityId(name: String, table: `[`IdTable`](../../org.jetbrains.exposed.dao/-id-table/index.md)`<ID>): `[`Column`](../-column/index.md)`<`[`EntityID`](../../org.jetbrains.exposed.dao/-entity-i-d/index.md)`<ID>>`