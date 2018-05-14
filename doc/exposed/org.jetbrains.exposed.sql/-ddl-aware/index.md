[org.jetbrains.exposed.sql](../index.md) / [DdlAware](.)

# DdlAware

`interface DdlAware : Any`

### Functions

| Name | Summary |
|---|---|
| [createStatement](create-statement.md) | `abstract fun createStatement(): List<String>` |
| [dropStatement](drop-statement.md) | `abstract fun dropStatement(): List<String>` |
| [modifyStatement](modify-statement.md) | `abstract fun modifyStatement(): List<String>` |

### Inheritors

| Name | Summary |
|---|---|
| [CheckConstraint](../-check-constraint/index.md) | `data class CheckConstraint : DdlAware` |
| [Column](../-column/index.md) | `class Column<T> : `[`ExpressionWithColumnType`](../-expression-with-column-type/index.md)`<T>, DdlAware, Comparable<`[`Column`](../-column/index.md)`<*>>` |
| [ForeignKeyConstraint](../-foreign-key-constraint/index.md) | `data class ForeignKeyConstraint : DdlAware` |
| [Index](../-index/index.md) | `data class Index : DdlAware` |
| [Table](../-table/index.md) | `open class Table : `[`ColumnSet`](../-column-set/index.md)`, DdlAware` |
