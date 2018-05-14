[org.jetbrains.exposed.sql.statements](../index.md) / [UpdateBuilder](.)

# UpdateBuilder

`abstract class UpdateBuilder<out T> : `[`Statement`](../-statement/index.md)`<T>`

**Author**
max

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `UpdateBuilder(type: `[`StatementType`](../-statement-type/index.md)`, targets: List<`[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`>)` |

### Properties

| Name | Summary |
|---|---|
| [values](values.md) | `val values: MutableMap<`[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<*>, Any?>` |

### Inherited Properties

| Name | Summary |
|---|---|
| [isAlwaysBatch](../-statement/is-always-batch.md) | `open val isAlwaysBatch: Boolean` |
| [targets](../-statement/targets.md) | `val targets: List<`[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`>` |
| [type](../-statement/type.md) | `val type: `[`StatementType`](../-statement-type/index.md) |

### Functions

| Name | Summary |
|---|---|
| [set](set.md) | `open operator fun <S> set(column: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<S>, value: S): Unit` |
| [update](update.md) | `open fun <T, S : T?> update(column: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<T>, value: `[`Expression`](../../org.jetbrains.exposed.sql/-expression/index.md)`<S>): Unit` |

### Inherited Functions

| Name | Summary |
|---|---|
| [arguments](../-statement/arguments.md) | `abstract fun arguments(): Iterable<Iterable<<ERROR CLASS><`[`IColumnType`](../../org.jetbrains.exposed.sql/-i-column-type/index.md)`, Any?>>>` |
| [execute](../-statement/execute.md) | `fun execute(transaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`): T?` |
| [executeInternal](../-statement/execute-internal.md) | `abstract fun `[`PreparedStatement`](http://docs.oracle.com/javase/6/docs/api/java/sql/PreparedStatement.html)`.executeInternal(transaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`): T?` |
| [prepareSQL](../-statement/prepare-s-q-l.md) | `abstract fun prepareSQL(transaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`): String` |
| [prepared](../-statement/prepared.md) | `open fun prepared(transaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`, sql: String): `[`PreparedStatement`](http://docs.oracle.com/javase/6/docs/api/java/sql/PreparedStatement.html) |

### Inheritors

| Name | Summary |
|---|---|
| [InsertStatement](../-insert-statement/index.md) | `open class InsertStatement<Key : Any> : UpdateBuilder<Int>`<br>isIgnore is supported for mysql only |
| [UpdateStatement](../-update-statement/index.md) | `open class UpdateStatement : UpdateBuilder<Int>` |
