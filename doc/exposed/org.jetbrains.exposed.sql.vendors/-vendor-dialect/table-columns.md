[org.jetbrains.exposed.sql.vendors](../index.md) / [VendorDialect](index.md) / [tableColumns](.)

# tableColumns

`open fun tableColumns(vararg tables: `[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`): Map<`[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`, List<`[`ColumnMetadata`](../-column-metadata/index.md)`>>`

Overrides [DatabaseDialect.tableColumns](../-database-dialect/table-columns.md)

returns list of pairs (column name + nullable) for every table

