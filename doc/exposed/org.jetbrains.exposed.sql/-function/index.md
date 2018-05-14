[org.jetbrains.exposed.sql](../index.md) / [Function](.)

# Function

`abstract class Function<T> : `[`ExpressionWithColumnType`](../-expression-with-column-type/index.md)`<T>`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Function(columnType: `[`IColumnType`](../-i-column-type/index.md)`)` |

### Properties

| Name | Summary |
|---|---|
| [columnType](column-type.md) | `open val columnType: `[`IColumnType`](../-i-column-type/index.md) |

### Extension Functions

| Name | Summary |
|---|---|
| [alias](../alias.md) | `fun <T> `[`Expression`](../-expression/index.md)`<T>.alias(alias: String): `[`ExpressionAlias`](../-expression-alias/index.md)`<T>` |
| [avg](../avg.md) | `fun <T : Comparable<T>, S : T?> `[`ExpressionWithColumnType`](../-expression-with-column-type/index.md)`<in S>.avg(scale: Int = 2): `[`ExpressionWithColumnType`](../-expression-with-column-type/index.md)`<`[`BigDecimal`](http://docs.oracle.com/javase/6/docs/api/java/math/BigDecimal.html)`?>` |
| [castTo](../cast-to.md) | `fun <R : Any> `[`Expression`](../-expression/index.md)`<*>.castTo(columnType: `[`IColumnType`](../-i-column-type/index.md)`): `[`Cast`](../-cast/index.md)`<R>` |
| [count](../count.md) | `fun `[`ExpressionWithColumnType`](../-expression-with-column-type/index.md)`<*>.count(): Function<Int>` |
| [date](../date.md) | `fun <T> `[`Expression`](../-expression/index.md)`<T>.date(): `[`Date`](../-date/index.md)`<T>` |
| [lowerCase](../lower-case.md) | `fun <T : String?> `[`Expression`](../-expression/index.md)`<T>.lowerCase(): Function<T>` |
| [max](../max.md) | `fun <T : Comparable<T>, S : T?> `[`ExpressionWithColumnType`](../-expression-with-column-type/index.md)`<in S>.max(): `[`ExpressionWithColumnType`](../-expression-with-column-type/index.md)`<T?>` |
| [min](../min.md) | `fun <T : Comparable<T>, S : T?> `[`ExpressionWithColumnType`](../-expression-with-column-type/index.md)`<in S>.min(): `[`ExpressionWithColumnType`](../-expression-with-column-type/index.md)`<T?>` |
| [month](../month.md) | `fun <T> `[`Expression`](../-expression/index.md)`<T>.month(): `[`Month`](../-month/index.md)`<T>` |
| [stdDevPop](../std-dev-pop.md) | `fun <T> `[`ExpressionWithColumnType`](../-expression-with-column-type/index.md)`<T>.stdDevPop(scale: Int = 2): `[`StdDevPop`](../-std-dev-pop/index.md)`<T>` |
| [stdDevSamp](../std-dev-samp.md) | `fun <T> `[`ExpressionWithColumnType`](../-expression-with-column-type/index.md)`<T>.stdDevSamp(scale: Int = 2): `[`StdDevSamp`](../-std-dev-samp/index.md)`<T>` |
| [substring](../substring.md) | `fun <T : String?> `[`Expression`](../-expression/index.md)`<T>.substring(start: Int, length: Int): Function<T>` |
| [sum](../sum.md) | `fun <T> `[`ExpressionWithColumnType`](../-expression-with-column-type/index.md)`<T>.sum(): `[`Sum`](../-sum/index.md)`<T>` |
| [trim](../trim.md) | `fun <T : String?> `[`Expression`](../-expression/index.md)`<T>.trim(): Function<T>` |
| [upperCase](../upper-case.md) | `fun <T : String?> `[`Expression`](../-expression/index.md)`<T>.upperCase(): Function<T>` |
| [varPop](../var-pop.md) | `fun <T> `[`ExpressionWithColumnType`](../-expression-with-column-type/index.md)`<T>.varPop(scale: Int = 2): `[`VarPop`](../-var-pop/index.md)`<T>` |
| [varSamp](../var-samp.md) | `fun <T> `[`ExpressionWithColumnType`](../-expression-with-column-type/index.md)`<T>.varSamp(scale: Int = 2): `[`VarSamp`](../-var-samp/index.md)`<T>` |

### Inheritors

| Name | Summary |
|---|---|
| [Avg](../-avg/index.md) | `class Avg<T : Comparable<T>, in S : T?> : Function<`[`BigDecimal`](http://docs.oracle.com/javase/6/docs/api/java/math/BigDecimal.html)`?>` |
| [Cast](../-cast/index.md) | `class Cast<T> : Function<T?>` |
| [Coalesce](../-coalesce/index.md) | `class Coalesce<out T, S : T?, R : T> : Function<R>` |
| [Count](../-count/index.md) | `class Count : Function<Int>` |
| [CurrentDateTime](../-current-date-time/index.md) | `class CurrentDateTime : Function<<ERROR CLASS>>` |
| [Date](../-date/index.md) | `class Date<T> : Function<<ERROR CLASS>>` |
| [GroupConcat](../-group-concat/index.md) | `class GroupConcat : Function<String?>` |
| [LowerCase](../-lower-case/index.md) | `class LowerCase<T : String?> : Function<T>` |
| [Max](../-max/index.md) | `class Max<T : Comparable<T>, in S : T?> : Function<T?>` |
| [Min](../-min/index.md) | `class Min<T : Comparable<T>, in S : T?> : Function<T?>` |
| [Month](../-month/index.md) | `class Month<T> : Function<Int>` |
| [Random](../-random/index.md) | `class Random : Function<`[`BigDecimal`](http://docs.oracle.com/javase/6/docs/api/java/math/BigDecimal.html)`>` |
| [StdDevPop](../-std-dev-pop/index.md) | `class StdDevPop<T> : Function<`[`BigDecimal`](http://docs.oracle.com/javase/6/docs/api/java/math/BigDecimal.html)`?>` |
| [StdDevSamp](../-std-dev-samp/index.md) | `class StdDevSamp<T> : Function<`[`BigDecimal`](http://docs.oracle.com/javase/6/docs/api/java/math/BigDecimal.html)`?>` |
| [Substring](../-substring/index.md) | `class Substring<T : String?> : Function<T>` |
| [Sum](../-sum/index.md) | `class Sum<T> : Function<T?>` |
| [Trim](../-trim/index.md) | `class Trim<T : String?> : Function<T>` |
| [UpperCase](../-upper-case/index.md) | `class UpperCase<T : String?> : Function<T>` |
| [VarPop](../-var-pop/index.md) | `class VarPop<T> : Function<`[`BigDecimal`](http://docs.oracle.com/javase/6/docs/api/java/math/BigDecimal.html)`?>` |
| [VarSamp](../-var-samp/index.md) | `class VarSamp<T> : Function<`[`BigDecimal`](http://docs.oracle.com/javase/6/docs/api/java/math/BigDecimal.html)`?>` |
