[org.jetbrains.exposed.sql](../index.md) / [StringColumnType](.)

# StringColumnType

`abstract class StringColumnType : `[`ColumnType`](../-column-type/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `StringColumnType(collate: String? = null)` |

### Properties

| Name | Summary |
|---|---|
| [collate](collate.md) | `val collate: String?` |

### Inherited Properties

| Name | Summary |
|---|---|
| [nullable](../-column-type/nullable.md) | `open var nullable: Boolean` |

### Functions

| Name | Summary |
|---|---|
| [nonNullValueToString](non-null-value-to-string.md) | `open fun nonNullValueToString(value: Any): String` |
| [valueFromDB](value-from-d-b.md) | `open fun valueFromDB(value: Any): Any` |

### Inherited Functions

| Name | Summary |
|---|---|
| [toString](../-column-type/to-string.md) | `open fun toString(): String` |

### Extension Properties

| Name | Summary |
|---|---|
| [isAutoInc](../is-auto-inc.md) | `val `[`IColumnType`](../-i-column-type/index.md)`.isAutoInc: Boolean` |

### Inheritors

| Name | Summary |
|---|---|
| [TextColumnType](../-text-column-type/index.md) | `open class TextColumnType : StringColumnType` |
| [VarCharColumnType](../-var-char-column-type/index.md) | `open class VarCharColumnType : StringColumnType` |
