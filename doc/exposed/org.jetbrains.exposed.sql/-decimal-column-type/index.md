[org.jetbrains.exposed.sql](../index.md) / [DecimalColumnType](.)

# DecimalColumnType

`class DecimalColumnType : `[`ColumnType`](../-column-type/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `DecimalColumnType(precision: Int, scale: Int)` |

### Properties

| Name | Summary |
|---|---|
| [precision](precision.md) | `val precision: Int` |
| [scale](scale.md) | `val scale: Int` |

### Inherited Properties

| Name | Summary |
|---|---|
| [nullable](../-column-type/nullable.md) | `open var nullable: Boolean` |

### Functions

| Name | Summary |
|---|---|
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
