[org.jetbrains.exposed.sql](../index.md) / [Slice](.)

# Slice

`class Slice : `[`FieldSet`](../-field-set/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Slice(source: `[`ColumnSet`](../-column-set/index.md)`, fields: List<`[`Expression`](../-expression/index.md)`<*>>)` |

### Properties

| Name | Summary |
|---|---|
| [fields](fields.md) | `val fields: List<`[`Expression`](../-expression/index.md)`<*>>` |
| [source](source.md) | `val source: `[`ColumnSet`](../-column-set/index.md) |

### Extension Functions

| Name | Summary |
|---|---|
| [select](../select.md) | `fun `[`FieldSet`](../-field-set/index.md)`.select(where: (`[`SqlExpressionBuilder`](../-sql-expression-builder/index.md)`) -> `[`Op`](../-op/index.md)`<Boolean>): `[`Query`](../-query/index.md)

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DMLTests.testSelect01<br>```
<br>`fun `[`FieldSet`](../-field-set/index.md)`.select(where: `[`Op`](../-op/index.md)`<Boolean>): `[`Query`](../-query/index.md) |
| [selectAll](../select-all.md) | `fun `[`FieldSet`](../-field-set/index.md)`.selectAll(): `[`Query`](../-query/index.md)

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DMLTests.testSelectDistinct<br>```
<br> |
