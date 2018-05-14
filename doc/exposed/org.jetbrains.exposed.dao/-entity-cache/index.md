[org.jetbrains.exposed.dao](../index.md) / [EntityCache](.)

# EntityCache

`class EntityCache : Any`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `EntityCache()` |

### Properties

| Name | Summary |
|---|---|
| [data](data.md) | `val data: `[`HashMap`](http://docs.oracle.com/javase/6/docs/api/java/util/HashMap.html)`<`[`IdTable`](../-id-table/index.md)`<*>, MutableMap<Any, `[`Entity`](../-entity/index.md)`<*>>>` |
| [inserts](inserts.md) | `val inserts: `[`HashMap`](http://docs.oracle.com/javase/6/docs/api/java/util/HashMap.html)`<`[`IdTable`](../-id-table/index.md)`<*>, MutableList<`[`Entity`](../-entity/index.md)`<*>>>` |
| [referrers](referrers.md) | `val referrers: `[`HashMap`](http://docs.oracle.com/javase/6/docs/api/java/util/HashMap.html)`<`[`EntityID`](../-entity-i-d/index.md)`<*>, MutableMap<`[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<*>, `[`SizedIterable`](../../org.jetbrains.exposed.sql/-sized-iterable/index.md)`<*>>>` |

### Functions

| Name | Summary |
|---|---|
| [clearReferrersCache](clear-referrers-cache.md) | `fun clearReferrersCache(): Unit` |
| [find](find.md) | `fun <ID : Comparable<ID>, T : `[`Entity`](../-entity/index.md)`<ID>> find(f: `[`EntityClass`](../-entity-class/index.md)`<ID, T>, id: `[`EntityID`](../-entity-i-d/index.md)`<ID>): T?` |
| [findAll](find-all.md) | `fun <ID : Comparable<ID>, T : `[`Entity`](../-entity/index.md)`<ID>> findAll(f: `[`EntityClass`](../-entity-class/index.md)`<ID, T>): Collection<T>` |
| [flush](flush.md) | `fun flush(): Unit`<br>`fun flush(tables: Iterable<`[`IdTable`](../-id-table/index.md)`<*>>): Unit` |
| [getOrPutReferrers](get-or-put-referrers.md) | `fun <ID : Any, R : `[`Entity`](../-entity/index.md)`<ID>> getOrPutReferrers(sourceId: `[`EntityID`](../-entity-i-d/index.md)`<*>, key: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<*>, refs: () -> `[`SizedIterable`](../../org.jetbrains.exposed.sql/-sized-iterable/index.md)`<R>): `[`SizedIterable`](../../org.jetbrains.exposed.sql/-sized-iterable/index.md)`<R>` |
| [remove](remove.md) | `fun <ID : Comparable<ID>, T : `[`Entity`](../-entity/index.md)`<ID>> remove(table: `[`IdTable`](../-id-table/index.md)`<ID>, o: T): Unit` |
| [scheduleInsert](schedule-insert.md) | `fun <ID : Comparable<ID>, T : `[`Entity`](../-entity/index.md)`<ID>> scheduleInsert(f: `[`EntityClass`](../-entity-class/index.md)`<ID, T>, o: T): Unit` |
| [store](store.md) | `fun <ID : Comparable<ID>, T : `[`Entity`](../-entity/index.md)`<ID>> store(f: `[`EntityClass`](../-entity-class/index.md)`<ID, T>, o: T): Unit`<br>`fun store(o: `[`Entity`](../-entity/index.md)`<*>): Unit` |

### Companion Object Functions

| Name | Summary |
|---|---|
| [addDependencies](add-dependencies.md) | `fun addDependencies(tables: Iterable<`[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`>): Iterable<`[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`>` |
| [invalidateGlobalCaches](invalidate-global-caches.md) | `fun invalidateGlobalCaches(created: List<`[`Entity`](../-entity/index.md)`<*>>): Unit` |
| [sortTablesByReferences](sort-tables-by-references.md) | `fun sortTablesByReferences(tables: Iterable<`[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`>): <ERROR CLASS>` |
