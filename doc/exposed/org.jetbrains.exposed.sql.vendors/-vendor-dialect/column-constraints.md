[org.jetbrains.exposed.sql.vendors](../index.md) / [VendorDialect](index.md) / [columnConstraints](.)

# columnConstraints

`open fun columnConstraints(vararg tables: `[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`): Map<<ERROR CLASS><String, String>, List<`[`ForeignKeyConstraint`](../../org.jetbrains.exposed.sql/-foreign-key-constraint/index.md)`>>`

Overrides [DatabaseDialect.columnConstraints](../-database-dialect/column-constraints.md)

returns map of constraint for a table name/column name pair
