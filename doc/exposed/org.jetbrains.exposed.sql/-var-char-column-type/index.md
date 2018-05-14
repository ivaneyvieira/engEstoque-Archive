[org.jetbrains.exposed.sql](../index.md) / [VarCharColumnType](.)

# VarCharColumnType

`open class VarCharColumnType : `[`StringColumnType`](../-string-column-type/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `VarCharColumnType(colLength: Int = 255, collate: String? = null)` |

### Properties

| Name | Summary |
|---|---|
| [colLength](col-length.md) | `val colLength: Int` |

### Inherited Properties

| Name | Summary |
|---|---|
| [collate](../-string-column-type/collate.md) | `val collate: String?` |

### Functions

| Name | Summary |
|---|---|
| [sqlType](sql-type.md) | `open fun sqlType(): String` |

### Inherited Functions

| Name | Summary |
|---|---|
| [nonNullValueToString](../-string-column-type/non-null-value-to-string.md) | `open fun nonNullValueToString(value: Any): String` |
| [valueFromDB](../-string-column-type/value-from-d-b.md) | `open fun valueFromDB(value: Any): Any` |

### Extension Properties

| Name | Summary |
|---|---|
| [isAutoInc](../is-auto-inc.md) | `val `[`IColumnType`](../-i-column-type/index.md)`.isAutoInc: Boolean` |

### Inheritors

| Name | Summary |
|---|---|
| [EnumerationNameColumnType](../-enumeration-name-column-type/index.md) | `class EnumerationNameColumnType<T : Enum<T>> : VarCharColumnType` |
