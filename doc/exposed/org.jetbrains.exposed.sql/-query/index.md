[org.jetbrains.exposed.sql](../index.md) / [Query](.)

# Query

`open class Query : `[`SizedIterable`](../-sized-iterable/index.md)`<`[`ResultRow`](../-result-row/index.md)`>, `[`Statement`](../../org.jetbrains.exposed.sql.statements/-statement/index.md)`<`[`ResultSet`](http://docs.oracle.com/javase/6/docs/api/java/sql/ResultSet.html)`>`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Query(set: `[`FieldSet`](../-field-set/index.md)`, where: `[`Op`](../-op/index.md)`<Boolean>?)` |

### Properties

| Name | Summary |
|---|---|
| [distinct](distinct.md) | `var distinct: Boolean` |
| [groupedByColumns](grouped-by-columns.md) | `var groupedByColumns: List<`[`Expression`](../-expression/index.md)`<*>>` |
| [having](having.md) | `var having: `[`Op`](../-op/index.md)`<Boolean>?` |
| [limit](limit.md) | `var limit: Int?` |
| [offset](offset.md) | `var offset: Int` |
| [orderByExpressions](order-by-expressions.md) | `var orderByExpressions: List<<ERROR CLASS><`[`Expression`](../-expression/index.md)`<*>, `[`SortOrder`](../-sort-order/index.md)`>>` |
| [set](set.md) | `var set: `[`FieldSet`](../-field-set/index.md) |
| [where](where.md) | `var where: `[`Op`](../-op/index.md)`<Boolean>?` |

### Inherited Properties

| Name | Summary |
|---|---|
| [isAlwaysBatch](../../org.jetbrains.exposed.sql.statements/-statement/is-always-batch.md) | `open val isAlwaysBatch: Boolean` |
| [targets](../../org.jetbrains.exposed.sql.statements/-statement/targets.md) | `val targets: List<`[`Table`](../-table/index.md)`>` |
| [type](../../org.jetbrains.exposed.sql.statements/-statement/type.md) | `val type: `[`StatementType`](../../org.jetbrains.exposed.sql.statements/-statement-type/index.md) |

### Functions

| Name | Summary |
|---|---|
| [adjustColumnSet](adjust-column-set.md) | `fun adjustColumnSet(body: (`[`ColumnSet`](../-column-set/index.md)`) -> `[`ColumnSet`](../-column-set/index.md)`): Query`<br>Changes [set.source](#) field of a Query, [set.fields](#) will be preserved |
| [adjustSlice](adjust-slice.md) | `fun adjustSlice(body: (`[`ColumnSet`](../-column-set/index.md)`) -> `[`FieldSet`](../-field-set/index.md)`): Query`<br>Changes [set.fields](#) field of a Query, [set.source](#) will be preserved |
| [adjustWhere](adjust-where.md) | `fun adjustWhere(body: (`[`Op`](../-op/index.md)`<Boolean>?) -> `[`Op`](../-op/index.md)`<Boolean>): Query`<br>Changes [where](where.md) field of a Query. |
| [arguments](arguments.md) | `open fun arguments(): <ERROR CLASS>` |
| [count](count.md) | `open fun count(): Int` |
| [empty](empty.md) | `open fun empty(): Boolean` |
| [executeInternal](execute-internal.md) | `open fun `[`PreparedStatement`](http://docs.oracle.com/javase/6/docs/api/java/sql/PreparedStatement.html)`.executeInternal(transaction: `[`Transaction`](../-transaction/index.md)`): `[`ResultSet`](http://docs.oracle.com/javase/6/docs/api/java/sql/ResultSet.html)`?` |
| [forUpdate](for-update.md) | `open fun forUpdate(): Query` |
| [groupBy](group-by.md) | `fun groupBy(vararg columns: `[`Expression`](../-expression/index.md)`<*>): Query` |
| [hasCustomForUpdateState](has-custom-for-update-state.md) | `fun hasCustomForUpdateState(): Boolean` |
| [having](having.md) | `fun having(op: (`[`SqlExpressionBuilder`](../-sql-expression-builder/index.md)`) -> `[`Op`](../-op/index.md)`<Boolean>): Query` |
| [isForUpdate](is-for-update.md) | `fun isForUpdate(): Boolean` |
| [iterator](iterator.md) | `open operator fun iterator(): Iterator<`[`ResultRow`](../-result-row/index.md)`>` |
| [limit](limit.md) | `open fun limit(n: Int, offset: Int): Query` |
| [notForUpdate](not-for-update.md) | `open fun notForUpdate(): Query` |
| [orderBy](order-by.md) | `fun orderBy(column: `[`Expression`](../-expression/index.md)`<*>, isAsc: Boolean = true): Query`<br>`fun orderBy(vararg columns: <ERROR CLASS><`[`Expression`](../-expression/index.md)`<*>, Boolean>): Query`<br>`fun orderBy(vararg columns: <ERROR CLASS><`[`Expression`](../-expression/index.md)`<*>, `[`SortOrder`](../-sort-order/index.md)`>): Query` |
| [prepareSQL](prepare-s-q-l.md) | `open fun prepareSQL(transaction: `[`Transaction`](../-transaction/index.md)`): String`<br>`fun prepareSQL(builder: `[`QueryBuilder`](../-query-builder/index.md)`): String` |
| [withDistinct](with-distinct.md) | `fun withDistinct(value: Boolean = true): Query` |

### Inherited Functions

| Name | Summary |
|---|---|
| [execute](../../org.jetbrains.exposed.sql.statements/-statement/execute.md) | `fun execute(transaction: `[`Transaction`](../-transaction/index.md)`): T?` |
| [prepared](../../org.jetbrains.exposed.sql.statements/-statement/prepared.md) | `open fun prepared(transaction: `[`Transaction`](../-transaction/index.md)`, sql: String): `[`PreparedStatement`](http://docs.oracle.com/javase/6/docs/api/java/sql/PreparedStatement.html) |

### Extension Functions

| Name | Summary |
|---|---|
| [alias](../alias.md) | `fun <T : Query> T.alias(alias: String): `[`QueryAlias`](../-query-alias/index.md) |
| [mapLazy](../map-lazy.md) | `infix fun <T, R> `[`SizedIterable`](../-sized-iterable/index.md)`<T>.mapLazy(f: (T) -> R): `[`SizedIterable`](../-sized-iterable/index.md)`<R>` |
