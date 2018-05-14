[org.jetbrains.exposed.sql](../index.md) / [CheckConstraint](.)

# CheckConstraint

`data class CheckConstraint : `[`DdlAware`](../-ddl-aware/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `CheckConstraint(tableName: String, checkName: String, checkOp: String)` |

### Properties

| Name | Summary |
|---|---|
| [checkName](check-name.md) | `val checkName: String` |
| [checkOp](check-op.md) | `val checkOp: String` |
| [tableName](table-name.md) | `val tableName: String` |

### Functions

| Name | Summary |
|---|---|
| [createStatement](create-statement.md) | `fun createStatement(): List<String>` |
| [dropStatement](drop-statement.md) | `fun dropStatement(): List<String>` |
| [modifyStatement](modify-statement.md) | `fun modifyStatement(): <ERROR CLASS>` |
