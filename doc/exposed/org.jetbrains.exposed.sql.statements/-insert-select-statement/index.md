[org.jetbrains.exposed.sql.statements](../index.md) / [InsertSelectStatement](.)

# InsertSelectStatement

`class InsertSelectStatement : `[`Statement`](../-statement/index.md)`<Int>`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `InsertSelectStatement(columns: List<`[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<*>>, selectQuery: `[`Query`](../../org.jetbrains.exposed.sql/-query/index.md)`, isIgnore: Boolean = false)` |

### Properties

| Name | Summary |
|---|---|
| [columns](columns.md) | `val columns: List<`[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<*>>` |
| [isIgnore](is-ignore.md) | `val isIgnore: Boolean` |
| [selectQuery](select-query.md) | `val selectQuery: `[`Query`](../../org.jetbrains.exposed.sql/-query/index.md) |

### Inherited Properties

| Name | Summary |
|---|---|
| [isAlwaysBatch](../-statement/is-always-batch.md) | `open val isAlwaysBatch: Boolean` |
| [targets](../-statement/targets.md) | `val targets: List<`[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`>` |
| [type](../-statement/type.md) | `val type: `[`StatementType`](../-statement-type/index.md) |

### Functions

| Name | Summary |
|---|---|
| [arguments](arguments.md) | `fun arguments(): Iterable<Iterable<<ERROR CLASS><`[`IColumnType`](../../org.jetbrains.exposed.sql/-i-column-type/index.md)`, Any?>>>` |
| [executeInternal](execute-internal.md) | `fun `[`PreparedStatement`](http://docs.oracle.com/javase/6/docs/api/java/sql/PreparedStatement.html)`.executeInternal(transaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`): Int?` |
| [prepareSQL](prepare-s-q-l.md) | `fun prepareSQL(transaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`): String` |

### Inherited Functions

| Name | Summary |
|---|---|
| [execute](../-statement/execute.md) | `fun execute(transaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`): T?` |
| [prepared](../-statement/prepared.md) | `open fun prepared(transaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`, sql: String): `[`PreparedStatement`](http://docs.oracle.com/javase/6/docs/api/java/sql/PreparedStatement.html) |
