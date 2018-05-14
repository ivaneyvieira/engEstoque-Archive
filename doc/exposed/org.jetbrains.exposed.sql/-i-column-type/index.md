[org.jetbrains.exposed.sql](../index.md) / [IColumnType](.)

# IColumnType

`interface IColumnType : Any`

### Properties

| Name | Summary |
|---|---|
| [nullable](nullable.md) | `abstract var nullable: Boolean` |

### Functions

| Name | Summary |
|---|---|
| [nonNullValueToString](non-null-value-to-string.md) | `open fun nonNullValueToString(value: Any): String` |
| [notNullValueToDB](not-null-value-to-d-b.md) | `open fun notNullValueToDB(value: Any): Any` |
| [readObject](read-object.md) | `open fun readObject(rs: `[`ResultSet`](http://docs.oracle.com/javase/6/docs/api/java/sql/ResultSet.html)`, index: Int): Any?` |
| [setParameter](set-parameter.md) | `open fun setParameter(stmt: `[`PreparedStatement`](http://docs.oracle.com/javase/6/docs/api/java/sql/PreparedStatement.html)`, index: Int, value: Any?): Unit` |
| [sqlType](sql-type.md) | `abstract fun sqlType(): String` |
| [valueFromDB](value-from-d-b.md) | `open fun valueFromDB(value: Any): Any` |
| [valueToDB](value-to-d-b.md) | `open fun valueToDB(value: Any?): Any?` |
| [valueToString](value-to-string.md) | `open fun valueToString(value: Any?): String` |

### Extension Properties

| Name | Summary |
|---|---|
| [isAutoInc](../is-auto-inc.md) | `val IColumnType.isAutoInc: Boolean` |

### Inheritors

| Name | Summary |
|---|---|
| [AutoIncColumnType](../-auto-inc-column-type/index.md) | `class AutoIncColumnType : IColumnType` |
| [ColumnType](../-column-type/index.md) | `abstract class ColumnType : IColumnType` |
