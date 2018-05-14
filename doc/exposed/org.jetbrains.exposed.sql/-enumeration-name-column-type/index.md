[org.jetbrains.exposed.sql](../index.md) / [EnumerationNameColumnType](.)

# EnumerationNameColumnType

`class EnumerationNameColumnType<T : Enum<T>> : `[`VarCharColumnType`](../-var-char-column-type/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `EnumerationNameColumnType(klass: `[`Class`](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)`<T>, length: Int)` |

### Properties

| Name | Summary |
|---|---|
| [klass](klass.md) | `val klass: `[`Class`](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)`<T>` |

### Inherited Properties

| Name | Summary |
|---|---|
| [colLength](../-var-char-column-type/col-length.md) | `val colLength: Int` |

### Functions

| Name | Summary |
|---|---|
| [notNullValueToDB](not-null-value-to-d-b.md) | `fun notNullValueToDB(value: Any): Any` |
| [valueFromDB](value-from-d-b.md) | `fun valueFromDB(value: Any): Any` |

### Inherited Functions

| Name | Summary |
|---|---|
| [sqlType](../-var-char-column-type/sql-type.md) | `open fun sqlType(): String` |

### Extension Properties

| Name | Summary |
|---|---|
| [isAutoInc](../is-auto-inc.md) | `val `[`IColumnType`](../-i-column-type/index.md)`.isAutoInc: Boolean` |
