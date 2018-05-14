[org.jetbrains.exposed.dao](../index.md) / [UUIDEntity](.)

# UUIDEntity

`abstract class UUIDEntity : `[`Entity`](../-entity/index.md)`<`[`UUID`](http://docs.oracle.com/javase/6/docs/api/java/util/UUID.html)`>`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `UUIDEntity(id: `[`EntityID`](../-entity-i-d/index.md)`<`[`UUID`](http://docs.oracle.com/javase/6/docs/api/java/util/UUID.html)`>)` |

### Inherited Properties

| Name | Summary |
|---|---|
| [_readValues](../-entity/_read-values.md) | `var _readValues: `[`ResultRow`](../../org.jetbrains.exposed.sql/-result-row/index.md)`?` |
| [db](../-entity/db.md) | `var db: `[`Database`](../../org.jetbrains.exposed.sql/-database/index.md) |
| [id](../-entity/id.md) | `val id: `[`EntityID`](../-entity-i-d/index.md)`<ID>` |
| [klass](../-entity/klass.md) | `var klass: `[`EntityClass`](../-entity-class/index.md)`<ID, `[`Entity`](../-entity/index.md)`<ID>>` |
| [readValues](../-entity/read-values.md) | `val readValues: `[`ResultRow`](../../org.jetbrains.exposed.sql/-result-row/index.md) |
| [writeValues](../-entity/write-values.md) | `val writeValues: `[`LinkedHashMap`](http://docs.oracle.com/javase/6/docs/api/java/util/LinkedHashMap.html)`<`[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<Any?>, Any?>` |

### Inherited Functions

| Name | Summary |
|---|---|
| [delete](../-entity/delete.md) | `open fun delete(): Unit`<br>Delete this entity. |
| [flush](../-entity/flush.md) | `open fun flush(batch: `[`EntityBatchUpdate`](../../org.jetbrains.exposed.sql.statements/-entity-batch-update/index.md)`? = null): Boolean` |
| [lookup](../-entity/lookup.md) | `fun <T> `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<T>.lookup(): T` |
| [lookupInReadValues](../-entity/lookup-in-read-values.md) | `fun <T, R : Any> `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<T>.lookupInReadValues(found: (T?) -> R?, notFound: () -> R?): R?` |
| [s](../-entity/s.md) | `fun <T : `[`Entity`](../-entity/index.md)`<*>> s(c: `[`EntityClass`](../-entity-class/index.md)`<*, T>): `[`EntityClass`](../-entity-class/index.md)`<*, T>` |
| [storeWrittenValues](../-entity/store-written-values.md) | `fun storeWrittenValues(): Unit` |
| [via](../-entity/via.md) | `infix fun <ID : Comparable<ID>, Target : `[`Entity`](../-entity/index.md)`<ID>> `[`EntityClass`](../-entity-class/index.md)`<ID, Target>.via(table: `[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`): `[`InnerTableLink`](../-inner-table-link/index.md)`<ID, Target>` |
