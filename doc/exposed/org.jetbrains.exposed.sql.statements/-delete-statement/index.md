[org.jetbrains.exposed.sql.statements](../index.md) / [DeleteStatement](.)

# DeleteStatement

`class DeleteStatement : `[`Statement`](../-statement/index.md)`<Int>`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `DeleteStatement(table: `[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`, where: `[`Op`](../../org.jetbrains.exposed.sql/-op/index.md)`<Boolean>? = null, isIgnore: Boolean = false, limit: Int? = null, offset: Int? = null)` |

### Properties

| Name | Summary |
|---|---|
| [isIgnore](is-ignore.md) | `val isIgnore: Boolean` |
| [limit](limit.md) | `val limit: Int?` |
| [offset](offset.md) | `val offset: Int?` |
| [table](table.md) | `val table: `[`Table`](../../org.jetbrains.exposed.sql/-table/index.md) |
| [where](where.md) | `val where: `[`Op`](../../org.jetbrains.exposed.sql/-op/index.md)`<Boolean>?` |

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
| [executeInternal](execute-internal.md) | `fun `[`PreparedStatement`](http://docs.oracle.com/javase/6/docs/api/java/sql/PreparedStatement.html)`.executeInternal(transaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`): Int` |
| [prepareSQL](prepare-s-q-l.md) | `fun prepareSQL(transaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`): String` |

### Inherited Functions

| Name | Summary |
|---|---|
| [execute](../-statement/execute.md) | `fun execute(transaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`): T?` |
| [prepared](../-statement/prepared.md) | `open fun prepared(transaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`, sql: String): `[`PreparedStatement`](http://docs.oracle.com/javase/6/docs/api/java/sql/PreparedStatement.html) |

### Companion Object Functions

| Name | Summary |
|---|---|
| [all](all.md) | `fun all(transaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`, table: `[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`): Int` |
| [where](where.md) | `fun where(transaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`, table: `[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`, op: `[`Op`](../../org.jetbrains.exposed.sql/-op/index.md)`<Boolean>, isIgnore: Boolean = false, limit: Int? = null, offset: Int? = null): Int` |
