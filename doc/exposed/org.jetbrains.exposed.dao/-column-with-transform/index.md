[org.jetbrains.exposed.dao](../index.md) / [ColumnWithTransform](.)

# ColumnWithTransform

`open class ColumnWithTransform<TColumn, TReal> : Any`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ColumnWithTransform(column: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<TColumn>, toColumn: (TReal) -> TColumn, toReal: (TColumn) -> TReal)` |

### Properties

| Name | Summary |
|---|---|
| [column](column.md) | `val column: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<TColumn>` |
| [toColumn](to-column.md) | `val toColumn: (TReal) -> TColumn` |
| [toReal](to-real.md) | `val toReal: (TColumn) -> TReal` |

### Extension Functions

| Name | Summary |
|---|---|
| [getValue](../-entity/get-value.md) | `operator fun <TColumn, TReal> ColumnWithTransform<TColumn, TReal>.getValue(o: `[`Entity`](../-entity/index.md)`<ID>, desc: KProperty<*>): TReal` |
| [setValue](../-entity/set-value.md) | `operator fun <TColumn, TReal> ColumnWithTransform<TColumn, TReal>.setValue(o: `[`Entity`](../-entity/index.md)`<ID>, desc: KProperty<*>, value: TReal): Unit` |
