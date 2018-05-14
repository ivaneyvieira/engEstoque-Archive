[org.jetbrains.exposed.sql](../index.md) / [ForeignKeyConstraint](.)

# ForeignKeyConstraint

`data class ForeignKeyConstraint : `[`DdlAware`](../-ddl-aware/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ForeignKeyConstraint(fkName: String, refereeTable: String, refereeColumn: String, referencedTable: String, referencedColumn: String, deleteRule: `[`ReferenceOption`](../-reference-option/index.md)`)` |

### Properties

| Name | Summary |
|---|---|
| [deleteRule](delete-rule.md) | `var deleteRule: `[`ReferenceOption`](../-reference-option/index.md) |
| [fkName](fk-name.md) | `val fkName: String` |
| [refereeColumn](referee-column.md) | `val refereeColumn: String` |
| [refereeTable](referee-table.md) | `val refereeTable: String` |
| [referencedColumn](referenced-column.md) | `val referencedColumn: String` |
| [referencedTable](referenced-table.md) | `val referencedTable: String` |

### Functions

| Name | Summary |
|---|---|
| [createStatement](create-statement.md) | `fun createStatement(): <ERROR CLASS>` |
| [dropStatement](drop-statement.md) | `fun dropStatement(): <ERROR CLASS>` |
| [modifyStatement](modify-statement.md) | `fun modifyStatement(): <ERROR CLASS>` |

### Companion Object Functions

| Name | Summary |
|---|---|
| [from](from.md) | `fun from(column: `[`Column`](../-column/index.md)`<*>): ForeignKeyConstraint` |
