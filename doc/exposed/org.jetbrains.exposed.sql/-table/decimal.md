[org.jetbrains.exposed.sql](../index.md) / [Table](index.md) / [decimal](.)

# decimal

`fun decimal(name: String, precision: Int, scale: Int): `[`Column`](../-column/index.md)`<`[`BigDecimal`](http://docs.oracle.com/javase/6/docs/api/java/math/BigDecimal.html)`>`

A decimal column to store a decimal number with a set [precision](decimal.md#org.jetbrains.exposed.sql.Table$decimal(kotlin.String, kotlin.Int, kotlin.Int)/precision) and [scale](decimal.md#org.jetbrains.exposed.sql.Table$decimal(kotlin.String, kotlin.Int, kotlin.Int)/scale).

[precision](decimal.md#org.jetbrains.exposed.sql.Table$decimal(kotlin.String, kotlin.Int, kotlin.Int)/precision) sets the total amount of digits to store (including the digits behind the decimal point).
[scale](decimal.md#org.jetbrains.exposed.sql.Table$decimal(kotlin.String, kotlin.Int, kotlin.Int)/scale) sets the amount of digits to store behind the decimal point.

So to store the decimal 123.45, [precision](decimal.md#org.jetbrains.exposed.sql.Table$decimal(kotlin.String, kotlin.Int, kotlin.Int)/precision) would have to be set to 5 (as there are five digits in total) and [scale](decimal.md#org.jetbrains.exposed.sql.Table$decimal(kotlin.String, kotlin.Int, kotlin.Int)/scale) to 2 (as there are two digits behind the decimal point).

### Parameters

`name` - The column name

`precision` - The amount of digits to store in total (including the digits behind the decimal point)

`scale` - The amount of digits to store behind the decimal point