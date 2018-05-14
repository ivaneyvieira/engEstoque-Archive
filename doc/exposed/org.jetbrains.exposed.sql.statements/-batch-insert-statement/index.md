[org.jetbrains.exposed.sql.statements](../index.md) / [BatchInsertStatement](.)

# BatchInsertStatement

`open class BatchInsertStatement : `[`InsertStatement`](../-insert-statement/index.md)`<List<Map<`[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<*>, Any>>>`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `BatchInsertStatement(table: `[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`, ignore: Boolean = false)` |

### Properties

| Name | Summary |
|---|---|
| [arguments](arguments.md) | `open var arguments: List<List<<ERROR CLASS><`[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<*>, Any?>>>?` |
| [data](data.md) | `val data: `[`ArrayList`](http://docs.oracle.com/javase/6/docs/api/java/util/ArrayList.html)`<MutableMap<`[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<*>, Any?>>` |
| [flushCache](flush-cache.md) | `open val flushCache: Boolean` |
| [generatedKey](generated-key.md) | `open val generatedKey: List<Map<`[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<*>, Any>>?` |
| [isAlwaysBatch](is-always-batch.md) | `open val isAlwaysBatch: Boolean` |

### Inherited Properties

| Name | Summary |
|---|---|
| [autoIncColumns](../-insert-statement/auto-inc-columns.md) | `val autoIncColumns: <ERROR CLASS>` |
| [isIgnore](../-insert-statement/is-ignore.md) | `val isIgnore: Boolean` |
| [resultedValues](../-insert-statement/resulted-values.md) | `var resultedValues: List<Map<`[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<*>, Any>>?` |
| [table](../-insert-statement/table.md) | `val table: `[`Table`](../../org.jetbrains.exposed.sql/-table/index.md) |

### Functions

| Name | Summary |
|---|---|
| [addBatch](add-batch.md) | `fun addBatch(): Unit` |
| [set](set.md) | `open operator fun <S> set(column: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<S>, value: S): Unit` |
| [valuesAndDefaults](values-and-defaults.md) | `open fun valuesAndDefaults(values: Map<`[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<*>, Any?>): <ERROR CLASS>` |

### Inherited Functions

| Name | Summary |
|---|---|
| [arguments](../-insert-statement/arguments.md) | `open fun arguments(): <ERROR CLASS>` |
| [execInsertFunction](../-insert-statement/exec-insert-function.md) | `open fun `[`PreparedStatement`](http://docs.oracle.com/javase/6/docs/api/java/sql/PreparedStatement.html)`.execInsertFunction(): <ERROR CLASS><Int, `[`ResultSet`](http://docs.oracle.com/javase/6/docs/api/java/sql/ResultSet.html)`?>` |
| [executeInternal](../-insert-statement/execute-internal.md) | `open fun `[`PreparedStatement`](http://docs.oracle.com/javase/6/docs/api/java/sql/PreparedStatement.html)`.executeInternal(transaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`): Int` |
| [get](../-insert-statement/get.md) | `operator infix fun <T> get(column: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<T>): T?` |
| [prepareSQL](../-insert-statement/prepare-s-q-l.md) | `open fun prepareSQL(transaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`): String` |
| [prepared](../-insert-statement/prepared.md) | `open fun prepared(transaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`, sql: String): `[`PreparedStatement`](http://docs.oracle.com/javase/6/docs/api/java/sql/PreparedStatement.html) |

### Inheritors

| Name | Summary |
|---|---|
| [SQLServerBatchInsertStatement](../-s-q-l-server-batch-insert-statement/index.md) | `class SQLServerBatchInsertStatement : BatchInsertStatement` |
