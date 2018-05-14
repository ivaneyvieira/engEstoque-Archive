[org.jetbrains.exposed.sql](index.md) / [insert](.)

# insert

`fun <T : `[`Table`](-table/index.md)`> T.insert(body: (T, `[`InsertStatement`](../org.jetbrains.exposed.sql.statements/-insert-statement/index.md)`<Number>) -> Unit): `[`InsertStatement`](../org.jetbrains.exposed.sql.statements/-insert-statement/index.md)`<Number>`

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DMLTests.testInsert01
```

`fun <T : `[`Table`](-table/index.md)`> T.insert(selectQuery: `[`Query`](-query/index.md)`, columns: List<`[`Column`](-column/index.md)`<*>> = this.columns.filterNot { it.columnType.isAutoInc }): Int?`

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DMLTests.testInsertSelect01
```

