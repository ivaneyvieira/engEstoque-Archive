[org.jetbrains.exposed.dao](../index.md) / [ImmutableCachedEntityClass](.)

# ImmutableCachedEntityClass

`abstract class ImmutableCachedEntityClass<ID : Comparable<ID>, out T : `[`Entity`](../-entity/index.md)`<ID>> : `[`ImmutableEntityClass`](../-immutable-entity-class/index.md)`<ID, T>`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ImmutableCachedEntityClass(table: `[`IdTable`](../-id-table/index.md)`<ID>, entityType: `[`Class`](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)`<T>? = null)` |

### Functions

| Name | Summary |
|---|---|
| [all](all.md) | `open fun all(): `[`SizedIterable`](../../org.jetbrains.exposed.sql/-sized-iterable/index.md)`<T>` |
| [expireCache](expire-cache.md) | `fun expireCache(): Unit` |
| [forceUpdateEntity](force-update-entity.md) | `open fun <T> forceUpdateEntity(entity: `[`Entity`](../-entity/index.md)`<ID>, column: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<T>, value: T): Unit` |
| [warmCache](warm-cache.md) | `fun warmCache(): `[`EntityCache`](../-entity-cache/index.md) |
