[org.jetbrains.exposed.sql](../index.md) / [Index](.)

# Index

`data class Index : `[`DdlAware`](../-ddl-aware/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Index(columns: List<`[`Column`](../-column/index.md)`<*>>, unique: Boolean, customName: String? = null)` |

### Properties

| Name | Summary |
|---|---|
| [columns](columns.md) | `val columns: List<`[`Column`](../-column/index.md)`<*>>` |
| [customName](custom-name.md) | `val customName: String?` |
| [indexName](index-name.md) | `val indexName: String` |
| [table](table.md) | `val table: `[`Table`](../-table/index.md) |
| [unique](unique.md) | `val unique: Boolean` |

### Functions

| Name | Summary |
|---|---|
| [createStatement](create-statement.md) | `fun createStatement(): <ERROR CLASS>` |
| [dropStatement](drop-statement.md) | `fun dropStatement(): <ERROR CLASS>` |
| [modifyStatement](modify-statement.md) | `fun modifyStatement(): <ERROR CLASS>` |
| [onlyNameDiffer](only-name-differ.md) | `fun onlyNameDiffer(other: Index): Boolean` |
| [toString](to-string.md) | `fun toString(): String` |
