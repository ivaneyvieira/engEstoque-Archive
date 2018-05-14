[org.jetbrains.exposed.sql](../index.md) / [QueryAlias](.)

# QueryAlias

`class QueryAlias : `[`ColumnSet`](../-column-set/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `QueryAlias(query: `[`Query`](../-query/index.md)`, alias: String)` |

### Properties

| Name | Summary |
|---|---|
| [alias](alias.md) | `val alias: String` |
| [columns](columns.md) | `val columns: List<`[`Column`](../-column/index.md)`<*>>` |
| [query](query.md) | `val query: `[`Query`](../-query/index.md) |

### Inherited Properties

| Name | Summary |
|---|---|
| [fields](../-column-set/fields.md) | `open val fields: List<`[`Expression`](../-expression/index.md)`<*>>` |
| [source](../-column-set/source.md) | `open val source: `[`ColumnSet`](../-column-set/index.md) |

### Functions

| Name | Summary |
|---|---|
| [crossJoin](cross-join.md) | `infix fun crossJoin(otherTable: `[`ColumnSet`](../-column-set/index.md)`): `[`Join`](../-join/index.md) |
| [describe](describe.md) | `fun describe(s: `[`Transaction`](../-transaction/index.md)`): String` |
| [get](get.md) | `operator fun <T> get(original: `[`Column`](../-column/index.md)`<T>): `[`Column`](../-column/index.md)`<T>`<br>`operator fun <T> get(original: `[`Expression`](../-expression/index.md)`<T>): `[`Expression`](../-expression/index.md)`<T>` |
| [innerJoin](inner-join.md) | `infix fun innerJoin(otherTable: `[`ColumnSet`](../-column-set/index.md)`): `[`Join`](../-join/index.md) |
| [join](join.md) | `fun join(otherTable: `[`ColumnSet`](../-column-set/index.md)`, joinType: `[`JoinType`](../-join-type/index.md)`, onColumn: `[`Expression`](../-expression/index.md)`<*>?, otherColumn: `[`Expression`](../-expression/index.md)`<*>?, additionalConstraint: (`[`SqlExpressionBuilder`](../-sql-expression-builder/index.md)`) -> `[`Op`](../-op/index.md)`<Boolean>): `[`Join`](../-join/index.md) |
| [leftJoin](left-join.md) | `infix fun leftJoin(otherTable: `[`ColumnSet`](../-column-set/index.md)`): `[`Join`](../-join/index.md) |

### Inherited Functions

| Name | Summary |
|---|---|
| [slice](../-column-set/slice.md) | `fun slice(vararg columns: `[`Expression`](../-expression/index.md)`<*>): `[`FieldSet`](../-field-set/index.md)<br>`fun slice(columns: List<`[`Expression`](../-expression/index.md)`<*>>): `[`FieldSet`](../-field-set/index.md) |

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
| [targetTables](../target-tables.md) | `fun `[`ColumnSet`](../-column-set/index.md)`.targetTables(): List<`[`Table`](../-table/index.md)`>` |
