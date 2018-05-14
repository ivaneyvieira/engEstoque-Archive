[org.jetbrains.exposed.sql.statements](../index.md) / [StatementContext](.)

# StatementContext

`class StatementContext : Any`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `StatementContext(statement: `[`Statement`](../-statement/index.md)`<*>, args: Iterable<<ERROR CLASS><`[`IColumnType`](../../org.jetbrains.exposed.sql/-i-column-type/index.md)`, Any?>>)` |

### Properties

| Name | Summary |
|---|---|
| [args](args.md) | `val args: Iterable<<ERROR CLASS><`[`IColumnType`](../../org.jetbrains.exposed.sql/-i-column-type/index.md)`, Any?>>` |
| [statement](statement.md) | `val statement: `[`Statement`](../-statement/index.md)`<*>` |

### Functions

| Name | Summary |
|---|---|
| [sql](sql.md) | `fun sql(transaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`): String` |

### Extension Functions

| Name | Summary |
|---|---|
| [expandArgs](../expand-args.md) | `fun StatementContext.expandArgs(transaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`): String` |
