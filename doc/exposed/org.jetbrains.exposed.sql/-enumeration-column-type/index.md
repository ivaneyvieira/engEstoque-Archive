[org.jetbrains.exposed.sql](../index.md) / [EnumerationColumnType](.)

# EnumerationColumnType

`class EnumerationColumnType<T : Enum<T>> : `[`ColumnType`](../-column-type/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `EnumerationColumnType(klass: `[`Class`](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)`<T>)` |

### Properties

| Name | Summary |
|---|---|
| [klass](klass.md) | `val klass: `[`Class`](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)`<T>` |

### Inherited Properties

| Name | Summary |
|---|---|
| [nullable](../-column-type/nullable.md) | `open var nullable: Boolean` |

### Functions

| Name | Summary |
|---|---|
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
