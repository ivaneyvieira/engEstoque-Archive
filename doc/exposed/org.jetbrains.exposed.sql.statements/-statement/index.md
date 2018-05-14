[org.jetbrains.exposed.sql.statements](../index.md) / [Statement](.)

# Statement

`abstract class Statement<out T> : Any`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Statement(type: `[`StatementType`](../-statement-type/index.md)`, targets: List<`[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`>)` |

### Properties

| Name | Summary |
|---|---|
| [isAlwaysBatch](is-always-batch.md) | `open val isAlwaysBatch: Boolean` |
| [targets](targets.md) | `val targets: List<`[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`>` |
| [type](type.md) | `val type: `[`StatementType`](../-statement-type/index.md) |

### Functions

| Name | Summary |
|---|---|
| [arguments](arguments.md) | `abstract fun arguments(): Iterable<Iterable<<ERROR CLASS><`[`IColumnType`](../../org.jetbrains.exposed.sql/-i-column-type/index.md)`, Any?>>>` |
| [execute](execute.md) | `fun execute(transaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`): T?` |
| [executeInternal](execute-internal.md) | `abstract fun `[`PreparedStatement`](http://docs.oracle.com/javase/6/docs/api/java/sql/PreparedStatement.html)`.executeInternal(transaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`): T?` |
| [prepareSQL](prepare-s-q-l.md) | `abstract fun prepareSQL(transaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`): String` |
| [prepared](prepared.md) | `open fun prepared(transaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`, sql: String): `[`PreparedStatement`](http://docs.oracle.com/javase/6/docs/api/java/sql/PreparedStatement.html) |

### Inheritors

| Name | Summary |
|---|---|
| [DeleteStatement](../-delete-statement/index.md) | `class DeleteStatement : Statement<Int>` |
| [InsertSelectStatement](../-insert-select-statement/index.md) | `class InsertSelectStatement : Statement<Int>` |
| [Query](../../org.jetbrains.exposed.sql/-query/index.md) | `open class Query : `[`SizedIterable`](../../org.jetbrains.exposed.sql/-sized-iterable/index.md)`<`[`ResultRow`](../../org.jetbrains.exposed.sql/-result-row/index.md)`>, Statement<`[`ResultSet`](http://docs.oracle.com/javase/6/docs/api/java/sql/ResultSet.html)`>` |
| [UpdateBuilder](../-update-builder/index.md) | `abstract class UpdateBuilder<out T> : Statement<T>` |
