[org.jetbrains.exposed.sql](index.md) / [update](.)

# update

`fun <T : `[`Table`](-table/index.md)`> T.update(where: (`[`SqlExpressionBuilder`](-sql-expression-builder/index.md)`) -> `[`Op`](-op/index.md)`<Boolean>, limit: Int? = null, body: (T, `[`UpdateStatement`](../org.jetbrains.exposed.sql.statements/-update-statement/index.md)`) -> Unit): Int`

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DMLTests.testUpdate01
```

`fun `[`Join`](-join/index.md)`.update(where: (`[`SqlExpressionBuilder`](-sql-expression-builder/index.md)`) -> `[`Op`](-op/index.md)`<Boolean> = null, limit: Int? = null, body: (`[`UpdateStatement`](../org.jetbrains.exposed.sql.statements/-update-statement/index.md)`) -> Unit): Int`