[org.jetbrains.exposed.sql](../index.md) / [ExpressionWithColumnType](.)

# ExpressionWithColumnType

`abstract class ExpressionWithColumnType<T> : `[`Expression`](../-expression/index.md)`<T>`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ExpressionWithColumnType()` |

### Properties

| Name | Summary |
|---|---|
| [columnType](column-type.md) | `abstract val columnType: `[`IColumnType`](../-i-column-type/index.md) |

### Inherited Functions

| Name | Summary |
|---|---|
| [equals](../-expression/equals.md) | `open fun equals(other: Any?): Boolean` |
| [hashCode](../-expression/hash-code.md) | `open fun hashCode(): Int` |
| [toSQL](../-expression/to-s-q-l.md) | `abstract fun toSQL(queryBuilder: `[`QueryBuilder`](../-query-builder/index.md)`): String` |
| [toString](../-expression/to-string.md) | `open fun toString(): String` |

### Extension Functions

| Name | Summary |
|---|---|
| [alias](../alias.md) | `fun <T> `[`Expression`](../-expression/index.md)`<T>.alias(alias: String): `[`ExpressionAlias`](../-expression-alias/index.md)`<T>` |
| [asLiteral](../-sql-expression-builder/as-literal.md) | `fun <T, S : T?> ExpressionWithColumnType<S>.asLiteral(value: T): `[`LiteralOp`](../-literal-op/index.md)`<T>` |
| [avg](../avg.md) | `fun <T : Comparable<T>, S : T?> ExpressionWithColumnType<in S>.avg(scale: Int = 2): ExpressionWithColumnType<`[`BigDecimal`](http://docs.oracle.com/javase/6/docs/api/java/math/BigDecimal.html)`?>` |
| [between](../-sql-expression-builder/between.md) | `fun <T, S : T?> ExpressionWithColumnType<S>.between(from: T, to: T): `[`Op`](../-op/index.md)`<Boolean>` |
| [castTo](../cast-to.md) | `fun <R : Any> `[`Expression`](../-expression/index.md)`<*>.castTo(columnType: `[`IColumnType`](../-i-column-type/index.md)`): `[`Cast`](../-cast/index.md)`<R>` |
| [count](../count.md) | `fun ExpressionWithColumnType<*>.count(): `[`Function`](../-function/index.md)`<Int>` |
| [date](../date.md) | `fun <T> `[`Expression`](../-expression/index.md)`<T>.date(): `[`Date`](../-date/index.md)`<T>` |
| [div](../-sql-expression-builder/div.md) | `operator fun <T, S : T> ExpressionWithColumnType<T>.div(other: `[`Expression`](../-expression/index.md)`<S>): ExpressionWithColumnType<T>`<br>`operator fun <T> ExpressionWithColumnType<T>.div(t: T): ExpressionWithColumnType<T>` |
| [eq](../-sql-expression-builder/eq.md) | `infix fun <T> ExpressionWithColumnType<T>.eq(t: T): `[`Op`](../-op/index.md)`<Boolean>` |
| [greater](../-sql-expression-builder/greater.md) | `infix fun <T : Comparable<T>, S : T?> ExpressionWithColumnType<in S>.greater(t: T): `[`Op`](../-op/index.md)`<Boolean>`<br>`fun <T : Comparable<T>, S : T?> ExpressionWithColumnType<in S>.greater(other: `[`Expression`](../-expression/index.md)`<S>): `[`Op`](../-op/index.md)`<Boolean>` |
| [greaterEq](../-sql-expression-builder/greater-eq.md) | `infix fun <T : Comparable<T>, S : T?> ExpressionWithColumnType<in S>.greaterEq(t: T): `[`Op`](../-op/index.md)`<Boolean>`<br>`fun <T : Comparable<T>, S : T?> ExpressionWithColumnType<in S>.greaterEq(other: `[`Expression`](../-expression/index.md)`<T>): `[`Op`](../-op/index.md)`<Boolean>` |
| [inList](../-sql-expression-builder/in-list.md) | `infix fun <T> ExpressionWithColumnType<T>.inList(list: Iterable<T>): `[`Op`](../-op/index.md)`<Boolean>` |
| [intToDecimal](../-sql-expression-builder/int-to-decimal.md) | `fun ExpressionWithColumnType<Int>.intToDecimal(): ExpressionWithColumnType<`[`BigDecimal`](http://docs.oracle.com/javase/6/docs/api/java/math/BigDecimal.html)`>` |
| [isNotNull](../-sql-expression-builder/is-not-null.md) | `fun <T> ExpressionWithColumnType<T>.isNotNull(): `[`Op`](../-op/index.md)`<Boolean>` |
| [isNull](../-sql-expression-builder/is-null.md) | `fun <T> ExpressionWithColumnType<T>.isNull(): `[`Op`](../-op/index.md)`<Boolean>` |
| [less](../-sql-expression-builder/less.md) | `infix fun <T : Comparable<T>, S : T?> ExpressionWithColumnType<in S>.less(t: T): `[`Op`](../-op/index.md)`<Boolean>`<br>`fun <T : Comparable<T>, S : T?> ExpressionWithColumnType<in S>.less(other: `[`Expression`](../-expression/index.md)`<S>): `[`LessOp`](../-less-op/index.md) |
| [lessEq](../-sql-expression-builder/less-eq.md) | `infix fun <T : Comparable<T>, S : T?> ExpressionWithColumnType<in S>.lessEq(t: T): `[`Op`](../-op/index.md)`<Boolean>`<br>`fun <T : Comparable<T>, S : T?> ExpressionWithColumnType<in S>.lessEq(other: `[`Expression`](../-expression/index.md)`<S>): `[`Op`](../-op/index.md)`<Boolean>` |
| [like](../-sql-expression-builder/like.md) | `infix fun <T : String?> ExpressionWithColumnType<T>.like(pattern: String): `[`Op`](../-op/index.md)`<Boolean>` |
| [lowerCase](../lower-case.md) | `fun <T : String?> `[`Expression`](../-expression/index.md)`<T>.lowerCase(): `[`Function`](../-function/index.md)`<T>` |
| [match](../-sql-expression-builder/match.md) | `fun <T : String?> ExpressionWithColumnType<T>.match(pattern: String, mode: `[`MatchMode`](../../org.jetbrains.exposed.sql.vendors/-function-provider/-match-mode/index.md)`?): `[`Op`](../-op/index.md)`<Boolean>`<br>`infix fun <T : String?> ExpressionWithColumnType<T>.match(pattern: String): `[`Op`](../-op/index.md)`<Boolean>` |
| [match](../../org.jetbrains.exposed.sql.vendors/-function-provider/match.md) | `open fun <T : String?> ExpressionWithColumnType<T>.match(pattern: String, mode: `[`MatchMode`](../../org.jetbrains.exposed.sql.vendors/-function-provider/-match-mode/index.md)`? = null): `[`Op`](../-op/index.md)`<Boolean>` |
| [max](../max.md) | `fun <T : Comparable<T>, S : T?> ExpressionWithColumnType<in S>.max(): ExpressionWithColumnType<T?>` |
| [min](../min.md) | `fun <T : Comparable<T>, S : T?> ExpressionWithColumnType<in S>.min(): ExpressionWithColumnType<T?>` |
| [minus](../-sql-expression-builder/minus.md) | `operator fun <T, S : T> ExpressionWithColumnType<T>.minus(other: `[`Expression`](../-expression/index.md)`<S>): ExpressionWithColumnType<T>`<br>`operator fun <T> ExpressionWithColumnType<T>.minus(t: T): ExpressionWithColumnType<T>` |
| [month](../month.md) | `fun <T> `[`Expression`](../-expression/index.md)`<T>.month(): `[`Month`](../-month/index.md)`<T>` |
| [neq](../-sql-expression-builder/neq.md) | `infix fun <T> ExpressionWithColumnType<T>.neq(other: T): `[`Op`](../-op/index.md)`<Boolean>`<br>`infix fun <T, S1 : T?, S2 : T?> ExpressionWithColumnType<in S1>.neq(other: `[`Expression`](../-expression/index.md)`<in S2>): `[`Op`](../-op/index.md)`<Boolean>` |
| [notInList](../-sql-expression-builder/not-in-list.md) | `infix fun <T> ExpressionWithColumnType<T>.notInList(list: Iterable<T>): `[`Op`](../-op/index.md)`<Boolean>` |
| [notLike](../-sql-expression-builder/not-like.md) | `infix fun <T : String?> ExpressionWithColumnType<T>.notLike(pattern: String): `[`Op`](../-op/index.md)`<Boolean>` |
| [notRegexp](../-sql-expression-builder/not-regexp.md) | `infix fun <T : String?> ExpressionWithColumnType<T>.notRegexp(pattern: String): `[`Op`](../-op/index.md)`<Boolean>` |
| [plus](../-sql-expression-builder/plus.md) | `operator fun <T, S : T> ExpressionWithColumnType<T>.plus(other: `[`Expression`](../-expression/index.md)`<S>): ExpressionWithColumnType<T>`<br>`operator fun <T> ExpressionWithColumnType<T>.plus(t: T): ExpressionWithColumnType<T>` |
| [regexp](../-sql-expression-builder/regexp.md) | `infix fun <T : String?> ExpressionWithColumnType<T>.regexp(pattern: String): `[`Op`](../-op/index.md)`<Boolean>` |
| [stdDevPop](../std-dev-pop.md) | `fun <T> ExpressionWithColumnType<T>.stdDevPop(scale: Int = 2): `[`StdDevPop`](../-std-dev-pop/index.md)`<T>` |
| [stdDevSamp](../std-dev-samp.md) | `fun <T> ExpressionWithColumnType<T>.stdDevSamp(scale: Int = 2): `[`StdDevSamp`](../-std-dev-samp/index.md)`<T>` |
| [substring](../substring.md) | `fun <T : String?> `[`Expression`](../-expression/index.md)`<T>.substring(start: Int, length: Int): `[`Function`](../-function/index.md)`<T>` |
| [sum](../sum.md) | `fun <T> ExpressionWithColumnType<T>.sum(): `[`Sum`](../-sum/index.md)`<T>` |
| [times](../-sql-expression-builder/times.md) | `operator fun <T, S : T> ExpressionWithColumnType<T>.times(other: `[`Expression`](../-expression/index.md)`<S>): ExpressionWithColumnType<T>`<br>`operator fun <T> ExpressionWithColumnType<T>.times(t: T): ExpressionWithColumnType<T>` |
| [trim](../trim.md) | `fun <T : String?> `[`Expression`](../-expression/index.md)`<T>.trim(): `[`Function`](../-function/index.md)`<T>` |
| [upperCase](../upper-case.md) | `fun <T : String?> `[`Expression`](../-expression/index.md)`<T>.upperCase(): `[`Function`](../-function/index.md)`<T>` |
| [varPop](../var-pop.md) | `fun <T> ExpressionWithColumnType<T>.varPop(scale: Int = 2): `[`VarPop`](../-var-pop/index.md)`<T>` |
| [varSamp](../var-samp.md) | `fun <T> ExpressionWithColumnType<T>.varSamp(scale: Int = 2): `[`VarSamp`](../-var-samp/index.md)`<T>` |
| [wrap](../-sql-expression-builder/wrap.md) | `fun <T, S : T?> ExpressionWithColumnType<in S>.wrap(value: T): `[`Expression`](../-expression/index.md)`<T>` |

### Inheritors

| Name | Summary |
|---|---|
| [Column](../-column/index.md) | `class Column<T> : ExpressionWithColumnType<T>, `[`DdlAware`](../-ddl-aware/index.md)`, Comparable<`[`Column`](../-column/index.md)`<*>>` |
| [DivideOp](../-divide-op/index.md) | `class DivideOp<T, S : T> : ExpressionWithColumnType<T>` |
| [Function](../-function/index.md) | `abstract class Function<T> : ExpressionWithColumnType<T>` |
| [LiteralOp](../-literal-op/index.md) | `class LiteralOp<T> : ExpressionWithColumnType<T>` |
| [MinusOp](../-minus-op/index.md) | `class MinusOp<T, S : T> : ExpressionWithColumnType<T>` |
| [NoOpConversion](../-no-op-conversion/index.md) | `class NoOpConversion<T, S> : ExpressionWithColumnType<S>` |
| [PlusOp](../-plus-op/index.md) | `class PlusOp<T, S : T> : ExpressionWithColumnType<T>` |
| [TimesOp](../-times-op/index.md) | `class TimesOp<T, S : T> : ExpressionWithColumnType<T>` |
