[org.jetbrains.exposed.dao](../index.md) / [Entity](.)

# Entity

`open class Entity<ID : Comparable<ID>> : Any`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Entity(id: `[`EntityID`](../-entity-i-d/index.md)`<ID>)` |

### Properties

| Name | Summary |
|---|---|
| [_readValues](_read-values.md) | `var _readValues: `[`ResultRow`](../../org.jetbrains.exposed.sql/-result-row/index.md)`?` |
| [db](db.md) | `var db: `[`Database`](../../org.jetbrains.exposed.sql/-database/index.md) |
| [id](id.md) | `val id: `[`EntityID`](../-entity-i-d/index.md)`<ID>` |
| [klass](klass.md) | `var klass: `[`EntityClass`](../-entity-class/index.md)`<ID, Entity<ID>>` |
| [readValues](read-values.md) | `val readValues: `[`ResultRow`](../../org.jetbrains.exposed.sql/-result-row/index.md) |
| [writeValues](write-values.md) | `val writeValues: `[`LinkedHashMap`](http://docs.oracle.com/javase/6/docs/api/java/util/LinkedHashMap.html)`<`[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<Any?>, Any?>` |

### Functions

| Name | Summary |
|---|---|
| [delete](delete.md) | `open fun delete(): Unit`<br>Delete this entity. |
| [flush](flush.md) | `open fun flush(batch: `[`EntityBatchUpdate`](../../org.jetbrains.exposed.sql.statements/-entity-batch-update/index.md)`? = null): Boolean` |
| [getValue](get-value.md) | `operator fun <RID : Comparable<RID>, T : Entity<RID>> `[`Reference`](../-reference/index.md)`<RID, T>.getValue(o: Entity<ID>, desc: KProperty<*>): T`<br>`operator fun <RID : Comparable<RID>, T : Entity<RID>> `[`OptionalReference`](../-optional-reference/index.md)`<RID, T>.getValue(o: Entity<ID>, desc: KProperty<*>): T?`<br>`operator fun <T> `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<T>.getValue(o: Entity<ID>, desc: KProperty<*>): T`<br>`operator fun <TColumn, TReal> `[`ColumnWithTransform`](../-column-with-transform/index.md)`<TColumn, TReal>.getValue(o: Entity<ID>, desc: KProperty<*>): TReal` |
| [lookup](lookup.md) | `fun <T> `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<T>.lookup(): T` |
| [lookupInReadValues](lookup-in-read-values.md) | `fun <T, R : Any> `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<T>.lookupInReadValues(found: (T?) -> R?, notFound: () -> R?): R?` |
| [s](s.md) | `fun <T : Entity<*>> s(c: `[`EntityClass`](../-entity-class/index.md)`<*, T>): `[`EntityClass`](../-entity-class/index.md)`<*, T>` |
| [setValue](set-value.md) | `operator fun <RID : Comparable<RID>, T : Entity<RID>> `[`Reference`](../-reference/index.md)`<RID, T>.setValue(o: Entity<ID>, desc: KProperty<*>, value: T): Unit`<br>`operator fun <RID : Comparable<RID>, T : Entity<RID>> `[`OptionalReference`](../-optional-reference/index.md)`<RID, T>.setValue(o: Entity<ID>, desc: KProperty<*>, value: T?): Unit`<br>`operator fun <T> `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<T>.setValue(o: Entity<ID>, desc: KProperty<*>, value: T): Unit`<br>`operator fun <TColumn, TReal> `[`ColumnWithTransform`](../-column-with-transform/index.md)`<TColumn, TReal>.setValue(o: Entity<ID>, desc: KProperty<*>, value: TReal): Unit` |
| [storeWrittenValues](store-written-values.md) | `fun storeWrittenValues(): Unit` |
| [via](via.md) | `infix fun <ID : Comparable<ID>, Target : Entity<ID>> `[`EntityClass`](../-entity-class/index.md)`<ID, Target>.via(table: `[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`): `[`InnerTableLink`](../-inner-table-link/index.md)`<ID, Target>` |

### Inheritors

| Name | Summary |
|---|---|
| [IntEntity](../-int-entity/index.md) | `abstract class IntEntity : Entity<Int>` |
| [LongEntity](../-long-entity/index.md) | `abstract class LongEntity : Entity<Long>` |
| [UUIDEntity](../-u-u-i-d-entity/index.md) | `abstract class UUIDEntity : Entity<`[`UUID`](http://docs.oracle.com/javase/6/docs/api/java/util/UUID.html)`>` |
