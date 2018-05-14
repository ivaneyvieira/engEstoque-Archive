[org.jetbrains.exposed.sql](../index.md) / [EntityIDColumnType](.)

# EntityIDColumnType

`class EntityIDColumnType<T : Comparable<T>> : `[`ColumnType`](../-column-type/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `EntityIDColumnType(idColumn: `[`Column`](../-column/index.md)`<T>)` |

### Properties

| Name | Summary |
|---|---|
| [idColumn](id-column.md) | `val idColumn: `[`Column`](../-column/index.md)`<T>` |

### Inherited Properties

| Name | Summary |
|---|---|
| [nullable](../-column-type/nullable.md) | `open var nullable: Boolean` |

### Functions

| Name | Summary |
|---|---|
| [nonNullValueToString](non-null-value-to-string.md) | `fun nonNullValueToString(value: Any): String` |
| [notNullValueToDB](not-null-value-to-d-b.md) | `fun notNullValueToDB(value: Any): Any` |
| [sqlType](sql-type.md) | `fun sqlType(): String` |
| [valueFromDB](value-from-d-b.md) | `fun valueFromDB(value: Any): Any` |

### Inherited Functions

| Name | Summary |
|---|---|
| [toString](../-column-type/to-string.md) | `open fun toString(): String` |

### Extension Properties

| Name | Summary |
|---|---|
| [isAutoInc](../is-auto-inc.md) | `val `[`IColumnType`](../-i-column-type/index.md)`.isAutoInc: Boolean` |
