[org.jetbrains.exposed.sql](../index.md) / [FieldSet](.)

# FieldSet

`interface FieldSet : Any`

### Properties

| Name | Summary |
|---|---|
| [fields](fields.md) | `abstract val fields: List<`[`Expression`](../-expression/index.md)`<*>>` |
| [source](source.md) | `abstract val source: `[`ColumnSet`](../-column-set/index.md) |

### Extension Functions

| Name | Summary |
|---|---|
| [select](../select.md) | `fun FieldSet.select(where: (`[`SqlExpressionBuilder`](../-sql-expression-builder/index.md)`) -> `[`Op`](../-op/index.md)`<Boolean>): `[`Query`](../-query/index.md)

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DMLTests.testSelect01<br>```
<br>`fun FieldSet.select(where: `[`Op`](../-op/index.md)`<Boolean>): `[`Query`](../-query/index.md) |
| [selectAll](../select-all.md) | `fun FieldSet.selectAll(): `[`Query`](../-query/index.md)

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DMLTests.testSelectDistinct<br>```
<br> |

### Inheritors

| Name | Summary |
|---|---|
| [ColumnSet](../-column-set/index.md) | `abstract class ColumnSet : FieldSet` |
| [Slice](../-slice/index.md) | `class Slice : FieldSet` |
