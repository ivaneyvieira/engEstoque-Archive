[org.jetbrains.exposed.sql](../index.md) / [ColumnType](.)

# ColumnType

`abstract class ColumnType : `[`IColumnType`](../-i-column-type/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ColumnType(nullable: Boolean = false)` |

### Properties

| Name | Summary |
|---|---|
| [nullable](nullable.md) | `open var nullable: Boolean` |

### Functions

| Name | Summary |
|---|---|
| [toString](to-string.md) | `open fun toString(): String` |

### Inherited Functions

| Name | Summary |
|---|---|
| [nonNullValueToString](../-i-column-type/non-null-value-to-string.md) | `open fun nonNullValueToString(value: Any): String` |
| [notNullValueToDB](../-i-column-type/not-null-value-to-d-b.md) | `open fun notNullValueToDB(value: Any): Any` |
| [readObject](../-i-column-type/read-object.md) | `open fun readObject(rs: `[`ResultSet`](http://docs.oracle.com/javase/6/docs/api/java/sql/ResultSet.html)`, index: Int): Any?` |
| [setParameter](../-i-column-type/set-parameter.md) | `open fun setParameter(stmt: `[`PreparedStatement`](http://docs.oracle.com/javase/6/docs/api/java/sql/PreparedStatement.html)`, index: Int, value: Any?): Unit` |
| [sqlType](../-i-column-type/sql-type.md) | `abstract fun sqlType(): String` |
| [valueFromDB](../-i-column-type/value-from-d-b.md) | `open fun valueFromDB(value: Any): Any` |
| [valueToDB](../-i-column-type/value-to-d-b.md) | `open fun valueToDB(value: Any?): Any?` |
| [valueToString](../-i-column-type/value-to-string.md) | `open fun valueToString(value: Any?): String` |

### Extension Properties

| Name | Summary |
|---|---|
| [isAutoInc](../is-auto-inc.md) | `val `[`IColumnType`](../-i-column-type/index.md)`.isAutoInc: Boolean` |

### Inheritors

| Name | Summary |
|---|---|
| [BinaryColumnType](../-binary-column-type/index.md) | `class BinaryColumnType : ColumnType` |
| [BlobColumnType](../-blob-column-type/index.md) | `class BlobColumnType : ColumnType` |
| [BooleanColumnType](../-boolean-column-type/index.md) | `class BooleanColumnType : ColumnType` |
| [CharacterColumnType](../-character-column-type/index.md) | `class CharacterColumnType : ColumnType` |
| [DateColumnType](../-date-column-type/index.md) | `class DateColumnType : ColumnType` |
| [DecimalColumnType](../-decimal-column-type/index.md) | `class DecimalColumnType : ColumnType` |
| [EntityIDColumnType](../-entity-i-d-column-type/index.md) | `class EntityIDColumnType<T : Comparable<T>> : ColumnType` |
| [EnumerationColumnType](../-enumeration-column-type/index.md) | `class EnumerationColumnType<T : Enum<T>> : ColumnType` |
| [FloatColumnType](../-float-column-type/index.md) | `class FloatColumnType : ColumnType` |
| [IntegerColumnType](../-integer-column-type/index.md) | `class IntegerColumnType : ColumnType` |
| [LongColumnType](../-long-column-type/index.md) | `class LongColumnType : ColumnType` |
| [StringColumnType](../-string-column-type/index.md) | `abstract class StringColumnType : ColumnType` |
| [UUIDColumnType](../-u-u-i-d-column-type/index.md) | `class UUIDColumnType : ColumnType` |
