[org.jetbrains.exposed.sql](../index.md) / [Table](index.md) / [text](.)

# text

`fun text(name: String, collate: String? = null): `[`Column`](../-column/index.md)`<String>`

A text column to store a large amount of text.

### Parameters

`name` - The column name

`collate` - The text collate type. Set to null to use the default type.