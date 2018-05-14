[org.jetbrains.exposed.sql.statements](../index.md) / [ReplaceStatement](.)

# ReplaceStatement

`class ReplaceStatement<Key : Any> : `[`InsertStatement`](../-insert-statement/index.md)`<Key>`

**Author**
max

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ReplaceStatement(table: `[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`)` |

### Inherited Properties

| Name | Summary |
|---|---|
| [arguments](../-insert-statement/arguments.md) | `open var arguments: List<List<<ERROR CLASS><`[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<*>, Any?>>>?` |
| [autoIncColumns](../-insert-statement/auto-inc-columns.md) | `val autoIncColumns: <ERROR CLASS>` |
| [flushCache](../-insert-statement/flush-cache.md) | `open val flushCache: Boolean` |
| [generatedKey](../-insert-statement/generated-key.md) | `open val generatedKey: Key?` |
| [isIgnore](../-insert-statement/is-ignore.md) | `val isIgnore: Boolean` |
| [resultedValues](../-insert-statement/resulted-values.md) | `var resultedValues: List<Map<`[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<*>, Any>>?` |
| [table](../-insert-statement/table.md) | `val table: `[`Table`](../../org.jetbrains.exposed.sql/-table/index.md) |

### Functions

| Name | Summary |
|---|---|
| [prepareSQL](prepare-s-q-l.md) | `fun prepareSQL(transaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`): String` |

### Inherited Functions

| Name | Summary |
|---|---|
| [arguments](../-insert-statement/arguments.md) | `open fun arguments(): <ERROR CLASS>` |
| [execInsertFunction](../-insert-statement/exec-insert-function.md) | `open fun `[`PreparedStatement`](http://docs.oracle.com/javase/6/docs/api/java/sql/PreparedStatement.html)`.execInsertFunction(): <ERROR CLASS><Int, `[`ResultSet`](http://docs.oracle.com/javase/6/docs/api/java/sql/ResultSet.html)`?>` |
| [executeInternal](../-insert-statement/execute-internal.md) | `open fun `[`PreparedStatement`](http://docs.oracle.com/javase/6/docs/api/java/sql/PreparedStatement.html)`.executeInternal(transaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`): Int` |
| [get](../-insert-statement/get.md) | `operator infix fun <T> get(column: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<T>): T?` |
| [prepared](../-insert-statement/prepared.md) | `open fun prepared(transaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`, sql: String): `[`PreparedStatement`](http://docs.oracle.com/javase/6/docs/api/java/sql/PreparedStatement.html) |
| [valuesAndDefaults](../-insert-statement/values-and-defaults.md) | `open fun valuesAndDefaults(values: Map<`[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<*>, Any?> = this.values): Map<`[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<*>, Any?>` |
