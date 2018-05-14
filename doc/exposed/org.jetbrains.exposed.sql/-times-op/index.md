[org.jetbrains.exposed.sql](../index.md) / [TimesOp](.)

# TimesOp

`class TimesOp<T, S : T> : `[`ExpressionWithColumnType`](../-expression-with-column-type/index.md)`<T>`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `TimesOp(expr1: `[`Expression`](../-expression/index.md)`<T>, expr2: `[`Expression`](../-expression/index.md)`<S>, columnType: `[`IColumnType`](../-i-column-type/index.md)`)` |

### Properties

| Name | Summary |
|---|---|
| [columnType](column-type.md) | `val columnType: `[`IColumnType`](../-i-column-type/index.md) |
| [expr1](expr1.md) | `val expr1: `[`Expression`](../-expression/index.md)`<T>` |
| [expr2](expr2.md) | `val expr2: `[`Expression`](../-expression/index.md)`<S>` |

### Functions

| Name | Summary |
|---|---|
| [toSQL](to-s-q-l.md) | `fun toSQL(queryBuilder: `[`QueryBuilder`](../-query-builder/index.md)`): String` |

### Extension Functions

| Name | Summary |
|---|---|
| [alias](../alias.md) | `fun <T> `[`Expression`](../-expression/index.md)`<T>.alias(alias: String): `[`ExpressionAlias`](../-expression-alias/index.md)`<T>` |
| [avg](../avg.md) | `fun <T : Comparable<T>, S : T?> `[`ExpressionWithColumnType`](../-expression-with-column-type/index.md)`<in S>.avg(scale: Int = 2): `[`ExpressionWithColumnType`](../-expression-with-column-type/index.md)`<`[`BigDecimal`](http://docs.oracle.com/javase/6/docs/api/java/math/BigDecimal.html)`?>` |
| [castTo](../cast-to.md) | `fun <R : Any> `[`Expression`](../-expression/index.md)`<*>.castTo(columnType: `[`IColumnType`](../-i-column-type/index.md)`): `[`Cast`](../-cast/index.md)`<R>` |
| [count](../count.md) | `fun `[`ExpressionWithColumnType`](../-expression-with-column-type/index.md)`<*>.count(): `[`Function`](../-function/index.md)`<Int>` |
| [date](../date.md) | `fun <T> `[`Expression`](../-expression/index.md)`<T>.date(): `[`Date`](../-date/index.md)`<T>` |
| [lowerCase](../lower-case.md) | `fun <T : String?> `[`Expression`](../-expression/index.md)`<T>.lowerCase(): `[`Function`](../-function/index.md)`<T>` |
| [max](../max.md) | `fun <T : Comparable<T>, S : T?> `[`ExpressionWithColumnType`](../-expression-with-column-type/index.md)`<in S>.max(): `[`ExpressionWithColumnType`](../-expression-with-column-type/index.md)`<T?>` |
| [min](../min.md) | `fun <T : Comparable<T>, S : T?> `[`ExpressionWithColumnType`](../-expression-with-column-type/index.md)`<in S>.min(): `[`ExpressionWithColumnType`](../-expression-with-column-type/index.md)`<T?>` |
| [month](../month.md) | `fun <T> `[`Expression`](../-expression/index.md)`<T>.month(): `[`Month`](../-month/index.md)`<T>` |
| [stdDevPop](../std-dev-pop.md) | `fun <T> `[`ExpressionWithColumnType`](../-expression-with-column-type/index.md)`<T>.stdDevPop(scale: Int = 2): `[`StdDevPop`](../-std-dev-pop/index.md)`<T>` |
| [stdDevSamp](../std-dev-samp.md) | `fun <T> `[`ExpressionWithColumnType`](../-expression-with-column-type/index.md)`<T>.stdDevSamp(scale: Int = 2): `[`StdDevSamp`](../-std-dev-samp/index.md)`<T>` |
| [substring](../substring.md) | `fun <T : String?> `[`Expression`](../-expression/index.md)`<T>.substring(start: Int, length: Int): `[`Function`](../-function/index.md)`<T>` |
| [sum](../sum.md) | `fun <T> `[`ExpressionWithColumnType`](../-expression-with-column-type/index.md)`<T>.sum(): `[`Sum`](../-sum/index.md)`<T>` |
| [trim](../trim.md) | `fun <T : String?> `[`Expression`](../-expression/index.md)`<T>.trim(): `[`Function`](../-function/index.md)`<T>` |
| [upperCase](../upper-case.md) | `fun <T : String?> `[`Expression`](../-expression/index.md)`<T>.upperCase(): `[`Function`](../-function/index.md)`<T>` |
| [varPop](../var-pop.md) | `fun <T> `[`ExpressionWithColumnType`](../-expression-with-column-type/index.md)`<T>.varPop(scale: Int = 2): `[`VarPop`](../-var-pop/index.md)`<T>` |
| [varSamp](../var-samp.md) | `fun <T> `[`ExpressionWithColumnType`](../-expression-with-column-type/index.md)`<T>.varSamp(scale: Int = 2): `[`VarSamp`](../-var-samp/index.md)`<T>` |
