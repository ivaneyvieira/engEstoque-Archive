[org.jetbrains.exposed.dao](../index.md) / [EntityCache](index.md) / [getOrPutReferrers](.)

# getOrPutReferrers

`fun <ID : Any, R : `[`Entity`](../-entity/index.md)`<ID>> getOrPutReferrers(sourceId: `[`EntityID`](../-entity-i-d/index.md)`<*>, key: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<*>, refs: () -> `[`SizedIterable`](../../org.jetbrains.exposed.sql/-sized-iterable/index.md)`<R>): `[`SizedIterable`](../../org.jetbrains.exposed.sql/-sized-iterable/index.md)`<R>`