[org.jetbrains.exposed.dao](../index.md) / [UUIDTable](.)

# UUIDTable

`open class UUIDTable : `[`IdTable`](../-id-table/index.md)`<`[`UUID`](http://docs.oracle.com/javase/6/docs/api/java/util/UUID.html)`>`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `UUIDTable(name: String = "", columnName: String = "id")` |

### Properties

| Name | Summary |
|---|---|
| [id](id.md) | `open val id: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<`[`EntityID`](../-entity-i-d/index.md)`<`[`UUID`](http://docs.oracle.com/javase/6/docs/api/java/util/UUID.html)`>>` |

### Extension Functions

| Name | Summary |
|---|---|
| [deleteAll](../../org.jetbrains.exposed.sql/delete-all.md) | `fun `[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`.deleteAll(): Int`

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DMLTests.testDelete01<br>```
<br> |
| [deleteIgnoreWhere](../../org.jetbrains.exposed.sql/delete-ignore-where.md) | `fun `[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`.deleteIgnoreWhere(limit: Int? = null, offset: Int? = null, op: (`[`SqlExpressionBuilder`](../../org.jetbrains.exposed.sql/-sql-expression-builder/index.md)`) -> `[`Op`](../../org.jetbrains.exposed.sql/-op/index.md)`<Boolean>): Int` |
| [deleteWhere](../../org.jetbrains.exposed.sql/delete-where.md) | `fun `[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`.deleteWhere(limit: Int? = null, offset: Int? = null, op: (`[`SqlExpressionBuilder`](../../org.jetbrains.exposed.sql/-sql-expression-builder/index.md)`) -> `[`Op`](../../org.jetbrains.exposed.sql/-op/index.md)`<Boolean>): Int`

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DMLTests.testDelete01<br>```
<br> |
| [exists](../../org.jetbrains.exposed.sql/exists.md) | `fun `[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`.exists(): Boolean`

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DDLTests.tableExists02<br>```
<br> |
| [joinQuery](../../org.jetbrains.exposed.sql/join-query.md) | `fun `[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`.joinQuery(on: (`[`SqlExpressionBuilder`](../../org.jetbrains.exposed.sql/-sql-expression-builder/index.md)`, `[`QueryAlias`](../../org.jetbrains.exposed.sql/-query-alias/index.md)`) -> `[`Op`](../../org.jetbrains.exposed.sql/-op/index.md)`<Boolean>, joinType: `[`JoinType`](../../org.jetbrains.exposed.sql/-join-type/index.md)` = JoinType.INNER, joinPart: () -> `[`Query`](../../org.jetbrains.exposed.sql/-query/index.md)`): `[`Join`](../../org.jetbrains.exposed.sql/-join/index.md) |
| [select](../../org.jetbrains.exposed.sql/select.md) | `fun `[`FieldSet`](../../org.jetbrains.exposed.sql/-field-set/index.md)`.select(where: (`[`SqlExpressionBuilder`](../../org.jetbrains.exposed.sql/-sql-expression-builder/index.md)`) -> `[`Op`](../../org.jetbrains.exposed.sql/-op/index.md)`<Boolean>): `[`Query`](../../org.jetbrains.exposed.sql/-query/index.md)

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DMLTests.testSelect01<br>```
<br>`fun `[`FieldSet`](../../org.jetbrains.exposed.sql/-field-set/index.md)`.select(where: `[`Op`](../../org.jetbrains.exposed.sql/-op/index.md)`<Boolean>): `[`Query`](../../org.jetbrains.exposed.sql/-query/index.md) |
| [selectAll](../../org.jetbrains.exposed.sql/select-all.md) | `fun `[`FieldSet`](../../org.jetbrains.exposed.sql/-field-set/index.md)`.selectAll(): `[`Query`](../../org.jetbrains.exposed.sql/-query/index.md)

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DMLTests.testSelectDistinct<br>```
<br> |
| [targetTables](../../org.jetbrains.exposed.sql/target-tables.md) | `fun `[`ColumnSet`](../../org.jetbrains.exposed.sql/-column-set/index.md)`.targetTables(): List<`[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`>` |
