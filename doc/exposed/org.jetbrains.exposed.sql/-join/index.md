[org.jetbrains.exposed.sql](../index.md) / [Join](.)

# Join

`class Join : `[`ColumnSet`](../-column-set/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Join(table: `[`ColumnSet`](../-column-set/index.md)`, otherTable: `[`ColumnSet`](../-column-set/index.md)`, joinType: `[`JoinType`](../-join-type/index.md)` = JoinType.INNER, onColumn: `[`Expression`](../-expression/index.md)`<*>? = null, otherColumn: `[`Expression`](../-expression/index.md)`<*>? = null, additionalConstraint: (`[`SqlExpressionBuilder`](../-sql-expression-builder/index.md)`) -> `[`Op`](../-op/index.md)`<Boolean> = null)`<br>`Join(table: `[`ColumnSet`](../-column-set/index.md)`)` |

### Properties

| Name | Summary |
|---|---|
| [columns](columns.md) | `val columns: List<`[`Column`](../-column/index.md)`<*>>` |
| [table](table.md) | `val table: `[`ColumnSet`](../-column-set/index.md) |

### Inherited Properties

| Name | Summary |
|---|---|
| [fields](../-column-set/fields.md) | `open val fields: List<`[`Expression`](../-expression/index.md)`<*>>` |
| [source](../-column-set/source.md) | `open val source: `[`ColumnSet`](../-column-set/index.md) |

### Functions

| Name | Summary |
|---|---|
| [alreadyInJoin](already-in-join.md) | `fun alreadyInJoin(table: `[`Table`](../-table/index.md)`): <ERROR CLASS>` |
| [crossJoin](cross-join.md) | `infix fun crossJoin(otherTable: `[`ColumnSet`](../-column-set/index.md)`): Join` |
| [describe](describe.md) | `fun describe(s: `[`Transaction`](../-transaction/index.md)`): String` |
| [innerJoin](inner-join.md) | `infix fun innerJoin(otherTable: `[`ColumnSet`](../-column-set/index.md)`): Join` |
| [join](join.md) | `fun join(otherTable: `[`ColumnSet`](../-column-set/index.md)`, joinType: `[`JoinType`](../-join-type/index.md)`, onColumn: `[`Expression`](../-expression/index.md)`<*>?, otherColumn: `[`Expression`](../-expression/index.md)`<*>?, additionalConstraint: (`[`SqlExpressionBuilder`](../-sql-expression-builder/index.md)`) -> `[`Op`](../-op/index.md)`<Boolean>): Join` |
| [leftJoin](left-join.md) | `infix fun leftJoin(otherTable: `[`ColumnSet`](../-column-set/index.md)`): Join` |

### Inherited Functions

| Name | Summary |
|---|---|
| [slice](../-column-set/slice.md) | `fun slice(vararg columns: `[`Expression`](../-expression/index.md)`<*>): `[`FieldSet`](../-field-set/index.md)<br>`fun slice(columns: List<`[`Expression`](../-expression/index.md)`<*>>): `[`FieldSet`](../-field-set/index.md) |

### Extension Properties

| Name | Summary |
|---|---|
| [lastQueryAlias](../last-query-alias.md) | `val Join.lastQueryAlias: `[`QueryAlias`](../-query-alias/index.md)`?` |

### Extension Functions

| Name | Summary |
|---|---|
| [joinQuery](../join-query.md) | `fun Join.joinQuery(on: (`[`SqlExpressionBuilder`](../-sql-expression-builder/index.md)`, `[`QueryAlias`](../-query-alias/index.md)`) -> `[`Op`](../-op/index.md)`<Boolean>, joinType: `[`JoinType`](../-join-type/index.md)` = JoinType.INNER, joinPart: () -> `[`Query`](../-query/index.md)`): Join` |
| [select](../select.md) | `fun `[`FieldSet`](../-field-set/index.md)`.select(where: (`[`SqlExpressionBuilder`](../-sql-expression-builder/index.md)`) -> `[`Op`](../-op/index.md)`<Boolean>): `[`Query`](../-query/index.md)

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DMLTests.testSelect01<br>```
<br>`fun `[`FieldSet`](../-field-set/index.md)`.select(where: `[`Op`](../-op/index.md)`<Boolean>): `[`Query`](../-query/index.md) |
| [selectAll](../select-all.md) | `fun `[`FieldSet`](../-field-set/index.md)`.selectAll(): `[`Query`](../-query/index.md)

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DMLTests.testSelectDistinct<br>```
<br> |
| [targetTables](../target-tables.md) | `fun `[`ColumnSet`](../-column-set/index.md)`.targetTables(): List<`[`Table`](../-table/index.md)`>` |
| [update](../update.md) | `fun Join.update(where: (`[`SqlExpressionBuilder`](../-sql-expression-builder/index.md)`) -> `[`Op`](../-op/index.md)`<Boolean> = null, limit: Int? = null, body: (`[`UpdateStatement`](../../org.jetbrains.exposed.sql.statements/-update-statement/index.md)`) -> Unit): Int` |
