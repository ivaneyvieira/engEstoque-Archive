[org.jetbrains.exposed.sql.statements](.)

## Package org.jetbrains.exposed.sql.statements

### Types

| Name | Summary |
|---|---|
| [BatchInsertStatement](-batch-insert-statement/index.md) | `open class BatchInsertStatement : `[`InsertStatement`](-insert-statement/index.md)`<List<Map<`[`Column`](../org.jetbrains.exposed.sql/-column/index.md)`<*>, Any>>>` |
| [BatchUpdateStatement](-batch-update-statement/index.md) | `class BatchUpdateStatement : `[`UpdateStatement`](-update-statement/index.md) |
| [DeleteStatement](-delete-statement/index.md) | `class DeleteStatement : `[`Statement`](-statement/index.md)`<Int>` |
| [EntityBatchUpdate](-entity-batch-update/index.md) | `class EntityBatchUpdate : Any` |
| [InsertSelectStatement](-insert-select-statement/index.md) | `class InsertSelectStatement : `[`Statement`](-statement/index.md)`<Int>` |
| [InsertStatement](-insert-statement/index.md) | `open class InsertStatement<Key : Any> : `[`UpdateBuilder`](-update-builder/index.md)`<Int>`<br>isIgnore is supported for mysql only |
| [ReplaceStatement](-replace-statement/index.md) | `class ReplaceStatement<Key : Any> : `[`InsertStatement`](-insert-statement/index.md)`<Key>` |
| [SQLServerBatchInsertStatement](-s-q-l-server-batch-insert-statement/index.md) | `class SQLServerBatchInsertStatement : `[`BatchInsertStatement`](-batch-insert-statement/index.md) |
| [Statement](-statement/index.md) | `abstract class Statement<out T> : Any` |
| [StatementContext](-statement-context/index.md) | `class StatementContext : Any` |
| [StatementGroup](-statement-group/index.md) | `enum class StatementGroup : Enum<`[`StatementGroup`](-statement-group/index.md)`>` |
| [StatementInterceptor](-statement-interceptor/index.md) | `interface StatementInterceptor : Any` |
| [StatementMonitor](-statement-monitor/index.md) | `class StatementMonitor : Any` |
| [StatementType](-statement-type/index.md) | `enum class StatementType : Enum<`[`StatementType`](-statement-type/index.md)`>` |
| [UpdateBuilder](-update-builder/index.md) | `abstract class UpdateBuilder<out T> : `[`Statement`](-statement/index.md)`<T>` |
| [UpdateStatement](-update-statement/index.md) | `open class UpdateStatement : `[`UpdateBuilder`](-update-builder/index.md)`<Int>` |

### Extensions for External Classes

| Name | Summary |
|---|---|
| [java.sql.PreparedStatement](java.sql.-prepared-statement/index.md) |  |

### Functions

| Name | Summary |
|---|---|
| [expandArgs](expand-args.md) | `fun `[`StatementContext`](-statement-context/index.md)`.expandArgs(transaction: `[`Transaction`](../org.jetbrains.exposed.sql/-transaction/index.md)`): String` |
