[org.jetbrains.exposed.sql](../index.md) / [ColumnSet](.)

# ColumnSet

`abstract class ColumnSet : `[`FieldSet`](../-field-set/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ColumnSet()` |

### Properties

| Name | Summary |
|---|---|
| [columns](columns.md) | `abstract val columns: List<`[`Column`](../-column/index.md)`<*>>` |
| [fields](fields.md) | `open val fields: List<`[`Expression`](../-expression/index.md)`<*>>` |
| [source](source.md) | `open val source: ColumnSet` |

### Functions

| Name | Summary |
|---|---|
| [crossJoin](cross-join.md) | `abstract fun crossJoin(otherTable: ColumnSet): `[`Join`](../-join/index.md) |
| [describe](describe.md) | `abstract fun describe(s: `[`Transaction`](../-transaction/index.md)`): String` |
| [innerJoin](inner-join.md) | `abstract fun innerJoin(otherTable: ColumnSet): `[`Join`](../-join/index.md) |
| [join](join.md) | `abstract fun join(otherTable: ColumnSet, joinType: `[`JoinType`](../-join-type/index.md)`, onColumn: `[`Expression`](../-expression/index.md)`<*>? = null, otherColumn: `[`Expression`](../-expression/index.md)`<*>? = null, additionalConstraint: (`[`SqlExpressionBuilder`](../-sql-expression-builder/index.md)`) -> `[`Op`](../-op/index.md)`<Boolean> = null): `[`Join`](../-join/index.md) |
| [leftJoin](left-join.md) | `abstract fun leftJoin(otherTable: ColumnSet): `[`Join`](../-join/index.md) |
| [slice](slice.md) | `fun slice(vararg columns: `[`Expression`](../-expression/index.md)`<*>): `[`FieldSet`](../-field-set/index.md)<br>`fun slice(columns: List<`[`Expression`](../-expression/index.md)`<*>>): `[`FieldSet`](../-field-set/index.md) |

### Extension Functions

| Name | Summary |
|---|---|
| [innerJoin](../inner-join.md) | `fun <C1 : ColumnSet, C2 : ColumnSet> C1.innerJoin(otherTable: C2, onColumn: (C1) -> `[`Expression`](../-expression/index.md)`<*>, otherColumn: (C2) -> `[`Expression`](../-expression/index.md)`<*>): `[`Join`](../-join/index.md) |
| [leftJoin](../left-join.md) | `fun <C1 : ColumnSet, C2 : ColumnSet> C1.leftJoin(otherTable: C2, onColumn: (C1) -> `[`Expression`](../-expression/index.md)`<*>, otherColumn: (C2) -> `[`Expression`](../-expression/index.md)`<*>): `[`Join`](../-join/index.md) |
| [select](../select.md) | `fun `[`FieldSet`](../-field-set/index.md)`.select(where: (`[`SqlExpressionBuilder`](../-sql-expression-builder/index.md)`) -> `[`Op`](../-op/index.md)`<Boolean>): `[`Query`](../-query/index.md)

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DMLTests.testSelect01<br>```
<br>`fun `[`FieldSet`](../-field-set/index.md)`.select(where: `[`Op`](../-op/index.md)`<Boolean>): `[`Query`](../-query/index.md) |
| [selectAll](../select-all.md) | `fun `[`FieldSet`](../-field-set/index.md)`.selectAll(): `[`Query`](../-query/index.md)

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DMLTests.testSelectDistinct<br>```
<br> |
| [targetTables](../target-tables.md) | `fun ColumnSet.targetTables(): List<`[`Table`](../-table/index.md)`>` |

### Inheritors

| Name | Summary |
|---|---|
| [Join](../-join/index.md) | `class Join : ColumnSet` |
| [QueryAlias](../-query-alias/index.md) | `class QueryAlias : ColumnSet` |
| [Table](../-table/index.md) | `open class Table : ColumnSet, `[`DdlAware`](../-ddl-aware/index.md) |
