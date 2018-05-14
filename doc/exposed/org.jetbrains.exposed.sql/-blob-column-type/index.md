[org.jetbrains.exposed.sql](../index.md) / [BlobColumnType](.)

# BlobColumnType

`class BlobColumnType : `[`ColumnType`](../-column-type/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `BlobColumnType()` |

### Inherited Properties

| Name | Summary |
|---|---|
| [nullable](../-column-type/nullable.md) | `open var nullable: Boolean` |

### Functions

| Name | Summary |
|---|---|
| [nonNullValueToString](non-null-value-to-string.md) | `fun nonNullValueToString(value: Any): String` |
| [notNullValueToDB](not-null-value-to-d-b.md) | `fun notNullValueToDB(value: Any): Any` |
| [readObject](read-object.md) | `fun readObject(rs: `[`ResultSet`](http://docs.oracle.com/javase/6/docs/api/java/sql/ResultSet.html)`, index: Int): Any?` |
| [setParameter](set-parameter.md) | `fun setParameter(stmt: `[`PreparedStatement`](http://docs.oracle.com/javase/6/docs/api/java/sql/PreparedStatement.html)`, index: Int, value: Any?): Unit` |
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
