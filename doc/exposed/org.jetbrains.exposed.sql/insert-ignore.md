[org.jetbrains.exposed.sql](index.md) / [insertIgnore](.)

# insertIgnore

`fun <T : `[`Table`](-table/index.md)`> T.insertIgnore(body: (T, `[`UpdateBuilder`](../org.jetbrains.exposed.sql.statements/-update-builder/index.md)`<*>) -> Unit): `[`InsertStatement`](../org.jetbrains.exposed.sql.statements/-insert-statement/index.md)`<Long>`
`fun <T : `[`Table`](-table/index.md)`> T.insertIgnore(selectQuery: `[`Query`](-query/index.md)`, columns: List<`[`Column`](-column/index.md)`<*>> = this.columns.filterNot { it.columnType.isAutoInc }): Int?`