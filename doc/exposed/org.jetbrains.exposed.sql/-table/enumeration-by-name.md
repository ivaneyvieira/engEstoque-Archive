[org.jetbrains.exposed.sql](../index.md) / [Table](index.md) / [enumerationByName](.)

# enumerationByName

`fun <T : Enum<T>> enumerationByName(name: String, length: Int, klass: `[`Class`](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)`<T>): `[`Column`](../-column/index.md)`<T>`

An enumeration column where enumerations are stored by their name.

### Parameters

`name` - The column name

`length` - The maximum length of the enumeration name

`klass` - The enum class