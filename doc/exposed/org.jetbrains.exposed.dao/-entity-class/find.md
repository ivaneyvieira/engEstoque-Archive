[org.jetbrains.exposed.dao](../index.md) / [EntityClass](index.md) / [find](.)

# find

`fun find(op: `[`Op`](../../org.jetbrains.exposed.sql/-op/index.md)`<Boolean>): `[`SizedIterable`](../../org.jetbrains.exposed.sql/-sized-iterable/index.md)`<T>`
`fun find(op: (`[`SqlExpressionBuilder`](../../org.jetbrains.exposed.sql/-sql-expression-builder/index.md)`) -> `[`Op`](../../org.jetbrains.exposed.sql/-op/index.md)`<Boolean>): `[`SizedIterable`](../../org.jetbrains.exposed.sql/-sized-iterable/index.md)`<T>`

Get all the entities that conform to the [op](find.md#org.jetbrains.exposed.dao.EntityClass$find(org.jetbrains.exposed.sql.Op((kotlin.Boolean)))/op) statement.

### Parameters

`op` - The statement to select the entities for. The statement must be of boolean type.

**Return**
All the entities that conform to the [op](find.md#org.jetbrains.exposed.dao.EntityClass$find(org.jetbrains.exposed.sql.Op((kotlin.Boolean)))/op) statement.

