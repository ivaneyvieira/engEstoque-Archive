[org.jetbrains.exposed.sql](../index.md) / [ResultRow](index.md) / [get](.)

# get

`operator fun <T> get(c: `[`Expression`](../-expression/index.md)`<T>): T`

Function might returns null. Use @tryGet if you don't sure of nullability (e.g. in left-join cases)

