[org.jetbrains.exposed.sql](../index.md) / [Expression](.)

# Expression

`abstract class Expression<T> : Any`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Expression()` |

### Functions

| Name | Summary |
|---|---|
| [equals](equals.md) | `open fun equals(other: Any?): Boolean` |
| [hashCode](hash-code.md) | `open fun hashCode(): Int` |
| [toSQL](to-s-q-l.md) | `abstract fun toSQL(queryBuilder: `[`QueryBuilder`](../-query-builder/index.md)`): String` |
| [toString](to-string.md) | `open fun toString(): String` |

### Companion Object Functions

| Name | Summary |
|---|---|
| [build](build.md) | `fun <T> build(builder: (`[`SqlExpressionBuilder`](../-sql-expression-builder/index.md)`) -> Expression<T>): Expression<T>` |

### Extension Functions

| Name | Summary |
|---|---|
| [alias](../alias.md) | `fun <T> Expression<T>.alias(alias: String): `[`ExpressionAlias`](../-expression-alias/index.md)`<T>` |
| [castTo](../cast-to.md) | `fun <R : Any> Expression<*>.castTo(columnType: `[`IColumnType`](../-i-column-type/index.md)`): `[`Cast`](../-cast/index.md)`<R>` |
| [date](../date.md) | `fun <T> Expression<T>.date(): `[`Date`](../-date/index.md)`<T>` |
| [eq](../-sql-expression-builder/eq.md) | `infix fun <T, S1 : T?, S2 : T?> Expression<in S1>.eq(other: Expression<in S2>): `[`Op`](../-op/index.md)`<Boolean>` |
| [lowerCase](../lower-case.md) | `fun <T : String?> Expression<T>.lowerCase(): `[`Function`](../-function/index.md)`<T>` |
| [month](../month.md) | `fun <T> Expression<T>.month(): `[`Month`](../-month/index.md)`<T>` |
| [substring](../substring.md) | `fun <T : String?> Expression<T>.substring(start: Int, length: Int): `[`Function`](../-function/index.md)`<T>` |
| [trim](../trim.md) | `fun <T : String?> Expression<T>.trim(): `[`Function`](../-function/index.md)`<T>` |
| [upperCase](../upper-case.md) | `fun <T : String?> Expression<T>.upperCase(): `[`Function`](../-function/index.md)`<T>` |

### Inheritors

| Name | Summary |
|---|---|
| [CaseWhenElse](../-case-when-else/index.md) | `class CaseWhenElse<T, R : T> : Expression<R>` |
| [ExpressionAlias](../-expression-alias/index.md) | `class ExpressionAlias<T> : Expression<T>` |
| [ExpressionWithColumnType](../-expression-with-column-type/index.md) | `abstract class ExpressionWithColumnType<T> : Expression<T>` |
| [Op](../-op/index.md) | `abstract class Op<T> : Expression<T>` |
| [QueryParameter](../-query-parameter/index.md) | `class QueryParameter<T> : Expression<T>` |
