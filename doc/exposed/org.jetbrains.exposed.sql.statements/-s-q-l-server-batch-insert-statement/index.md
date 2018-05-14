[org.jetbrains.exposed.sql.statements](../index.md) / [SQLServerBatchInsertStatement](.)

# SQLServerBatchInsertStatement

`class SQLServerBatchInsertStatement : `[`BatchInsertStatement`](../-batch-insert-statement/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `SQLServerBatchInsertStatement(table: `[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`, ignore: Boolean = false)` |

### Properties

| Name | Summary |
|---|---|
| [isAlwaysBatch](is-always-batch.md) | `val isAlwaysBatch: Boolean` |

### Inherited Properties

| Name | Summary |
|---|---|
| [arguments](../-batch-insert-statement/arguments.md) | `open var arguments: List<List<<ERROR CLASS><`[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<*>, Any?>>>?` |
| [data](../-batch-insert-statement/data.md) | `val data: `[`ArrayList`](http://docs.oracle.com/javase/6/docs/api/java/util/ArrayList.html)`<MutableMap<`[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<*>, Any?>>` |
| [flushCache](../-batch-insert-statement/flush-cache.md) | `open val flushCache: Boolean` |
| [generatedKey](../-batch-insert-statement/generated-key.md) | `open val generatedKey: List<Map<`[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<*>, Any>>?` |

### Functions

| Name | Summary |
|---|---|
| [arguments](arguments.md) | `fun arguments(): <ERROR CLASS>` |
| [execInsertFunction](exec-insert-function.md) | `fun `[`PreparedStatement`](http://docs.oracle.com/javase/6/docs/api/java/sql/PreparedStatement.html)`.execInsertFunction(): <ERROR CLASS><Int, `[`ResultSet`](http://docs.oracle.com/javase/6/docs/api/java/sql/ResultSet.html)`?>` |
| [prepareSQL](prepare-s-q-l.md) | `fun prepareSQL(transaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`): String` |

### Inherited Functions

| Name | Summary |
|---|---|
| [addBatch](../-batch-insert-statement/add-batch.md) | `fun addBatch(): Unit` |
| [set](../-batch-insert-statement/set.md) | `open operator fun <S> set(column: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<S>, value: S): Unit` |
| [valuesAndDefaults](../-batch-insert-statement/values-and-defaults.md) | `open fun valuesAndDefaults(values: Map<`[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<*>, Any?>): <ERROR CLASS>` |
