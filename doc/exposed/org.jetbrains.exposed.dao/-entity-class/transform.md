[org.jetbrains.exposed.dao](../index.md) / [EntityClass](index.md) / [transform](.)

# transform

`fun <TColumn, TReal> `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<TColumn>.transform(toColumn: (TReal) -> TColumn, toReal: (TColumn) -> TReal): `[`ColumnWithTransform`](../-column-with-transform/index.md)`<TColumn, TReal>`