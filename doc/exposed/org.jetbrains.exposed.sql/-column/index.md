[org.jetbrains.exposed.sql](../index.md) / [Column](.)

# Column

`class Column<T> : `[`ExpressionWithColumnType`](../-expression-with-column-type/index.md)`<T>, `[`DdlAware`](../-ddl-aware/index.md)`, Comparable<Column<*>>`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Column(table: `[`Table`](../-table/index.md)`, name: String, columnType: `[`IColumnType`](../-i-column-type/index.md)`)` |

### Properties

| Name | Summary |
|---|---|
| [columnType](column-type.md) | `val columnType: `[`IColumnType`](../-i-column-type/index.md) |
| [ddl](ddl.md) | `val ddl: List<String>` |
| [name](name.md) | `val name: String` |
| [referee](referee.md) | `var referee: Column<*>?` |
| [table](table.md) | `val table: `[`Table`](../-table/index.md) |

### Functions

| Name | Summary |
|---|---|
| [compareTo](compare-to.md) | `fun compareTo(other: Column<*>): Int` |
| [createStatement](create-statement.md) | `fun createStatement(): List<String>` |
| [descriptionDdl](description-ddl.md) | `fun descriptionDdl(): String` |
| [dropStatement](drop-statement.md) | `fun dropStatement(): <ERROR CLASS>` |
| [equals](equals.md) | `fun equals(other: Any?): Boolean` |
| [hashCode](hash-code.md) | `fun hashCode(): Int` |
| [modifyStatement](modify-statement.md) | `fun modifyStatement(): <ERROR CLASS>` |
| [toSQL](to-s-q-l.md) | `fun toSQL(queryBuilder: `[`QueryBuilder`](../-query-builder/index.md)`): String` |
| [toString](to-string.md) | `fun toString(): String` |

### Extension Properties

| Name | Summary |
|---|---|
| [autoIncSeqName](../auto-inc-seq-name.md) | `val Column<*>.autoIncSeqName: String?` |

### Extension Functions

| Name | Summary |
|---|---|
| [alias](../alias.md) | `fun <T> `[`Expression`](../-expression/index.md)`<T>.alias(alias: String): `[`ExpressionAlias`](../-expression-alias/index.md)`<T>` |
| [autoIncrement](../-table/auto-increment.md) | `fun <N : Any> Column<N>.autoIncrement(idSeqName: String? = null): Column<N>` |
| [autoinc](../-table/autoinc.md) | `fun <N : Comparable<N>> Column<`[`EntityID`](../../org.jetbrains.exposed.dao/-entity-i-d/index.md)`<N>>.autoinc(idSeqName: String? = null): Column<`[`EntityID`](../../org.jetbrains.exposed.dao/-entity-i-d/index.md)`<N>>` |
| [avg](../avg.md) | `fun <T : Comparable<T>, S : T?> `[`ExpressionWithColumnType`](../-expression-with-column-type/index.md)`<in S>.avg(scale: Int = 2): `[`ExpressionWithColumnType`](../-expression-with-column-type/index.md)`<`[`BigDecimal`](http://docs.oracle.com/javase/6/docs/api/java/math/BigDecimal.html)`?>` |
| [castTo](../cast-to.md) | `fun <R : Any> `[`Expression`](../-expression/index.md)`<*>.castTo(columnType: `[`IColumnType`](../-i-column-type/index.md)`): `[`Cast`](../-cast/index.md)`<R>` |
| [check](../-table/check.md) | `fun <T> Column<T>.check(name: String = "", op: (`[`SqlExpressionBuilder`](../-sql-expression-builder/index.md)`, Column<T>) -> `[`Op`](../-op/index.md)`<Boolean>): <ERROR CLASS>`<br>Creates a check constraint in this column. |
| [clientDefault](../-table/client-default.md) | `fun <T : Any> Column<T>.clientDefault(defaultValue: () -> T): Column<T>` |
| [count](../count.md) | `fun `[`ExpressionWithColumnType`](../-expression-with-column-type/index.md)`<*>.count(): `[`Function`](../-function/index.md)`<Int>` |
| [countDistinct](../count-distinct.md) | `fun Column<*>.countDistinct(): `[`Function`](../-function/index.md)`<Int>` |
| [date](../date.md) | `fun <T> `[`Expression`](../-expression/index.md)`<T>.date(): `[`Date`](../-date/index.md)`<T>` |
| [default](../-table/default.md) | `fun <T : Any> Column<T>.default(defaultValue: T): Column<T>` |
| [defaultExpression](../-table/default-expression.md) | `fun <T : Any> Column<T>.defaultExpression(defaultValue: `[`Expression`](../-expression/index.md)`<T>): Column<T>` |
| [entityId](../-table/entity-id.md) | `fun <T : Comparable<T>> Column<T>.entityId(): Column<`[`EntityID`](../../org.jetbrains.exposed.dao/-entity-i-d/index.md)`<T>>` |
| [eq](../-sql-expression-builder/eq.md) | `infix fun <T : Comparable<T>> Column<`[`EntityID`](../../org.jetbrains.exposed.dao/-entity-i-d/index.md)`<T>>.eq(t: T?): `[`Op`](../-op/index.md)`<Boolean>` |
| [getValue](../../org.jetbrains.exposed.dao/-entity/get-value.md) | `operator fun <T> Column<T>.getValue(o: `[`Entity`](../../org.jetbrains.exposed.dao/-entity/index.md)`<ID>, desc: KProperty<*>): T` |
| [groupConcat](../group-concat.md) | `fun <T> Column<T>.groupConcat(separator: String? = null, distinct: Boolean = false, vararg orderBy: <ERROR CLASS><`[`Expression`](../-expression/index.md)`<*>, Boolean>): `[`GroupConcat`](../-group-concat/index.md) |
| [inList](../-sql-expression-builder/in-list.md) | `infix fun <T : Comparable<T>> Column<`[`EntityID`](../../org.jetbrains.exposed.dao/-entity-i-d/index.md)`<T>>.inList(list: Iterable<T>): `[`Op`](../-op/index.md)`<Boolean>` |
| [index](../-table/--index--.md) | `fun <T> Column<T>.index(customIndexName: String? = null, isUnique: Boolean = false): Column<T>` |
| [lookup](../../org.jetbrains.exposed.dao/-entity/lookup.md) | `fun <T> Column<T>.lookup(): T` |
| [lookupInReadValues](../../org.jetbrains.exposed.dao/-entity/lookup-in-read-values.md) | `fun <T, R : Any> Column<T>.lookupInReadValues(found: (T?) -> R?, notFound: () -> R?): R?` |
| [lowerCase](../lower-case.md) | `fun <T : String?> `[`Expression`](../-expression/index.md)`<T>.lowerCase(): `[`Function`](../-function/index.md)`<T>` |
| [max](../max.md) | `fun <T : Comparable<T>, S : T?> `[`ExpressionWithColumnType`](../-expression-with-column-type/index.md)`<in S>.max(): `[`ExpressionWithColumnType`](../-expression-with-column-type/index.md)`<T?>` |
| [min](../min.md) | `fun <T : Comparable<T>, S : T?> `[`ExpressionWithColumnType`](../-expression-with-column-type/index.md)`<in S>.min(): `[`ExpressionWithColumnType`](../-expression-with-column-type/index.md)`<T?>` |
| [month](../month.md) | `fun <T> `[`Expression`](../-expression/index.md)`<T>.month(): `[`Month`](../-month/index.md)`<T>` |
| [nullable](../-table/nullable.md) | `fun <T : Any> Column<T>.nullable(): Column<T?>` |
| [primaryKey](../-table/primary-key.md) | `fun <T> Column<T>.primaryKey(indx: Int? = null): Column<T>` |
| [references](../-table/references.md) | `fun <T, S : T, C : Column<S>> C.references(ref: Column<T>, onDelete: `[`ReferenceOption`](../-reference-option/index.md)`?): C`<br>`infix fun <T, S : T, C : Column<S>> C.references(ref: Column<T>): C` |
| [setValue](../../org.jetbrains.exposed.dao/-entity/set-value.md) | `operator fun <T> Column<T>.setValue(o: `[`Entity`](../../org.jetbrains.exposed.dao/-entity/index.md)`<ID>, desc: KProperty<*>, value: T): Unit` |
| [stdDevPop](../std-dev-pop.md) | `fun <T> `[`ExpressionWithColumnType`](../-expression-with-column-type/index.md)`<T>.stdDevPop(scale: Int = 2): `[`StdDevPop`](../-std-dev-pop/index.md)`<T>` |
| [stdDevSamp](../std-dev-samp.md) | `fun <T> `[`ExpressionWithColumnType`](../-expression-with-column-type/index.md)`<T>.stdDevSamp(scale: Int = 2): `[`StdDevSamp`](../-std-dev-samp/index.md)`<T>` |
| [substring](../substring.md) | `fun <T : String?> `[`Expression`](../-expression/index.md)`<T>.substring(start: Int, length: Int): `[`Function`](../-function/index.md)`<T>` |
| [sum](../sum.md) | `fun <T> `[`ExpressionWithColumnType`](../-expression-with-column-type/index.md)`<T>.sum(): `[`Sum`](../-sum/index.md)`<T>` |
| [transform](../../org.jetbrains.exposed.dao/-entity-class/transform.md) | `fun <TColumn, TReal> Column<TColumn>.transform(toColumn: (TReal) -> TColumn, toReal: (TColumn) -> TReal): `[`ColumnWithTransform`](../../org.jetbrains.exposed.dao/-column-with-transform/index.md)`<TColumn, TReal>` |
| [trim](../trim.md) | `fun <T : String?> `[`Expression`](../-expression/index.md)`<T>.trim(): `[`Function`](../-function/index.md)`<T>` |
| [uniqueIndex](../-table/unique-index.md) | `fun <T> Column<T>.uniqueIndex(customIndexName: String? = null): Column<T>` |
| [upperCase](../upper-case.md) | `fun <T : String?> `[`Expression`](../-expression/index.md)`<T>.upperCase(): `[`Function`](../-function/index.md)`<T>` |
| [varPop](../var-pop.md) | `fun <T> `[`ExpressionWithColumnType`](../-expression-with-column-type/index.md)`<T>.varPop(scale: Int = 2): `[`VarPop`](../-var-pop/index.md)`<T>` |
| [varSamp](../var-samp.md) | `fun <T> `[`ExpressionWithColumnType`](../-expression-with-column-type/index.md)`<T>.varSamp(scale: Int = 2): `[`VarSamp`](../-var-samp/index.md)`<T>` |
