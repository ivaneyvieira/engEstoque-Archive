[org.jetbrains.exposed.sql](../index.md) / [Query](index.md) / [adjustWhere](.)

# adjustWhere

`fun adjustWhere(body: (`[`Op`](../-op/index.md)`<Boolean>?) -> `[`Op`](../-op/index.md)`<Boolean>): `[`Query`](index.md)

Changes [where](where.md) field of a Query.

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DMLTests.testAdjustQueryWhere
```

### Parameters

`body` - new WHERE condition builder, previous value used as a receiver