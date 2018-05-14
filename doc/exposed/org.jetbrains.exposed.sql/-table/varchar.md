[org.jetbrains.exposed.sql](../index.md) / [Table](index.md) / [varchar](.)

# varchar

`fun varchar(name: String, length: Int, collate: String? = null): `[`Column`](../-column/index.md)`<String>`

A varchar column to store a string with a set maximum amount of characters.

### Parameters

`name` - The column name

`length` - The maximum amount of characters

`collate` - The text collate type. Set to null to use the default type.