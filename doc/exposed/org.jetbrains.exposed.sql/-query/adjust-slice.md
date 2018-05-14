[org.jetbrains.exposed.sql](../index.md) / [Query](index.md) / [adjustSlice](.)

# adjustSlice

`fun adjustSlice(body: (`[`ColumnSet`](../-column-set/index.md)`) -> `[`FieldSet`](../-field-set/index.md)`): `[`Query`](index.md)

Changes [set.fields](#) field of a Query, [set.source](#) will be preserved

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DMLTests.testAdjustQuerySlice
```

### Parameters

`body` - builder for new column set, current [set.source](#) used as a receiver, you are expected to slice it