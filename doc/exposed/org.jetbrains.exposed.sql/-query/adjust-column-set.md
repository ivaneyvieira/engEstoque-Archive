[org.jetbrains.exposed.sql](../index.md) / [Query](index.md) / [adjustColumnSet](.)

# adjustColumnSet

`fun adjustColumnSet(body: (`[`ColumnSet`](../-column-set/index.md)`) -> `[`ColumnSet`](../-column-set/index.md)`): `[`Query`](index.md)

Changes [set.source](#) field of a Query, [set.fields](#) will be preserved

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DMLTests.testAdjustQueryColumnSet
```

### Parameters

`body` - builder for new column set, previous value used as a receiver