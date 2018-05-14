[org.jetbrains.exposed.sql](../index.md) / [AutoIncColumnType](.)

# AutoIncColumnType

`class AutoIncColumnType : `[`IColumnType`](../-i-column-type/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `AutoIncColumnType(delegate: `[`ColumnType`](../-column-type/index.md)`, _autoincSeq: String)` |

### Properties

| Name | Summary |
|---|---|
| [autoincSeq](autoinc-seq.md) | `val autoincSeq: String?` |
| [delegate](delegate.md) | `val delegate: `[`ColumnType`](../-column-type/index.md) |

### Functions

| Name | Summary |
|---|---|
| [sqlType](sql-type.md) | `fun sqlType(): String` |

### Extension Properties

| Name | Summary |
|---|---|
| [isAutoInc](../is-auto-inc.md) | `val `[`IColumnType`](../-i-column-type/index.md)`.isAutoInc: Boolean` |
