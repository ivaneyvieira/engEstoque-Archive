[org.jetbrains.exposed.sql](../index.md) / [Table](index.md) / [enumeration](.)

# enumeration

`fun <T : Enum<T>> enumeration(name: String, klass: `[`Class`](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)`<T>): `[`Column`](../-column/index.md)`<T>`

An enumeration column where enumerations are stored by their ordinal integer.

### Parameters

`name` - The column name

`klass` - The enum class