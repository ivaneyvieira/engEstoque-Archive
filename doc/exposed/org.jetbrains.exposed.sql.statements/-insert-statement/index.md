[org.jetbrains.exposed.sql.statements](../index.md) / [InsertStatement](.)

# InsertStatement

`open class InsertStatement<Key : Any> : `[`UpdateBuilder`](../-update-builder/index.md)`<Int>`

isIgnore is supported for mysql only

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `InsertStatement(table: `[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`, isIgnore: Boolean = false)`<br>isIgnore is supported for mysql only |

### Properties

| Name | Summary |
|---|---|
| [arguments](arguments.md) | `open var arguments: List<List<<ERROR CLASS><`[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<*>, Any?>>>?` |
| [autoIncColumns](auto-inc-columns.md) | `val autoIncColumns: <ERROR CLASS>` |
| [flushCache](flush-cache.md) | `open val flushCache: Boolean` |
| [generatedKey](generated-key.md) | `open val generatedKey: Key?` |
| [isIgnore](is-ignore.md) | `val isIgnore: Boolean` |
| [resultedValues](resulted-values.md) | `var resultedValues: List<Map<`[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<*>, Any>>?` |
| [table](table.md) | `val table: `[`Table`](../../org.jetbrains.exposed.sql/-table/index.md) |

### Inherited Properties

| Name | Summary |
|---|---|
| [values](../-update-builder/values.md) | `val values: MutableMap<`[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<*>, Any?>` |

### Functions

| Name | Summary |
|---|---|
| [arguments](arguments.md) | `open fun arguments(): <ERROR CLASS>` |
| [execInsertFunction](exec-insert-function.md) | `open fun `[`PreparedStatement`](http://docs.oracle.com/javase/6/docs/api/java/sql/PreparedStatement.html)`.execInsertFunction(): <ERROR CLASS><Int, `[`ResultSet`](http://docs.oracle.com/javase/6/docs/api/java/sql/ResultSet.html)`?>` |
| [executeInternal](execute-internal.md) | `open fun `[`PreparedStatement`](http://docs.oracle.com/javase/6/docs/api/java/sql/PreparedStatement.html)`.executeInternal(transaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`): Int` |
| [get](get.md) | `operator infix fun <T> get(column: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<T>): T?` |
| [prepareSQL](prepare-s-q-l.md) | `open fun prepareSQL(transaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`): String` |
| [prepared](prepared.md) | `open fun prepared(transaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`, sql: String): `[`PreparedStatement`](http://docs.oracle.com/javase/6/docs/api/java/sql/PreparedStatement.html) |
| [valuesAndDefaults](values-and-defaults.md) | `open fun valuesAndDefaults(values: Map<`[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<*>, Any?> = this.values): Map<`[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<*>, Any?>` |

### Inherited Functions

| Name | Summary |
|---|---|
| [set](../-update-builder/set.md) | `open operator fun <S> set(column: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<S>, value: S): Unit` |
| [update](../-update-builder/update.md) | `open fun <T, S : T?> update(column: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<T>, value: `[`Expression`](../../org.jetbrains.exposed.sql/-expression/index.md)`<S>): Unit` |

### Inheritors

| Name | Summary |
|---|---|
| [BatchInsertStatement](../-batch-insert-statement/index.md) | `open class BatchInsertStatement : InsertStatement<List<Map<`[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<*>, Any>>>` |
| [ReplaceStatement](../-replace-statement/index.md) | `class ReplaceStatement<Key : Any> : InsertStatement<Key>` |
