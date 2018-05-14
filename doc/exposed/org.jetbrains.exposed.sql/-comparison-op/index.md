[org.jetbrains.exposed.sql](../index.md) / [ComparisonOp](.)

# ComparisonOp

`abstract class ComparisonOp : `[`Op`](../-op/index.md)`<Boolean>`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ComparisonOp(expr1: `[`Expression`](../-expression/index.md)`<*>, expr2: `[`Expression`](../-expression/index.md)`<*>, opSign: String)` |

### Properties

| Name | Summary |
|---|---|
| [expr1](expr1.md) | `val expr1: `[`Expression`](../-expression/index.md)`<*>` |
| [expr2](expr2.md) | `val expr2: `[`Expression`](../-expression/index.md)`<*>` |
| [opSign](op-sign.md) | `val opSign: String` |

### Functions

| Name | Summary |
|---|---|
| [toSQL](to-s-q-l.md) | `open fun toSQL(queryBuilder: `[`QueryBuilder`](../-query-builder/index.md)`): <ERROR CLASS>` |

### Extension Functions

| Name | Summary |
|---|---|
| [alias](../alias.md) | `fun <T> `[`Expression`](../-expression/index.md)`<T>.alias(alias: String): `[`ExpressionAlias`](../-expression-alias/index.md)`<T>` |
| [and](../and.md) | `infix fun `[`Op`](../-op/index.md)`<Boolean>.and(op: `[`Expression`](../-expression/index.md)`<Boolean>): `[`Op`](../-op/index.md)`<Boolean>` |
| [castTo](../cast-to.md) | `fun <R : Any> `[`Expression`](../-expression/index.md)`<*>.castTo(columnType: `[`IColumnType`](../-i-column-type/index.md)`): `[`Cast`](../-cast/index.md)`<R>` |
| [date](../date.md) | `fun <T> `[`Expression`](../-expression/index.md)`<T>.date(): `[`Date`](../-date/index.md)`<T>` |
| [lowerCase](../lower-case.md) | `fun <T : String?> `[`Expression`](../-expression/index.md)`<T>.lowerCase(): `[`Function`](../-function/index.md)`<T>` |
| [month](../month.md) | `fun <T> `[`Expression`](../-expression/index.md)`<T>.month(): `[`Month`](../-month/index.md)`<T>` |
| [or](../or.md) | `infix fun `[`Op`](../-op/index.md)`<Boolean>.or(op: `[`Expression`](../-expression/index.md)`<Boolean>): `[`Op`](../-op/index.md)`<Boolean>` |
| [substring](../substring.md) | `fun <T : String?> `[`Expression`](../-expression/index.md)`<T>.substring(start: Int, length: Int): `[`Function`](../-function/index.md)`<T>` |
| [trim](../trim.md) | `fun <T : String?> `[`Expression`](../-expression/index.md)`<T>.trim(): `[`Function`](../-function/index.md)`<T>` |
| [upperCase](../upper-case.md) | `fun <T : String?> `[`Expression`](../-expression/index.md)`<T>.upperCase(): `[`Function`](../-function/index.md)`<T>` |

### Inheritors

| Name | Summary |
|---|---|
| [EqOp](../-eq-op/index.md) | `class EqOp : ComparisonOp` |
| [GreaterEqOp](../-greater-eq-op/index.md) | `class GreaterEqOp : ComparisonOp` |
| [GreaterOp](../-greater-op/index.md) | `class GreaterOp : ComparisonOp` |
| [LessEqOp](../-less-eq-op/index.md) | `class LessEqOp : ComparisonOp` |
| [LessOp](../-less-op/index.md) | `class LessOp : ComparisonOp` |
| [LikeOp](../-like-op/index.md) | `class LikeOp : ComparisonOp` |
| [NeqOp](../-neq-op/index.md) | `class NeqOp : ComparisonOp` |
| [NotLikeOp](../-not-like-op/index.md) | `class NotLikeOp : ComparisonOp` |
| [NotRegexpOp](../-not-regexp-op/index.md) | `class NotRegexpOp : ComparisonOp` |
| [RegexpOp](../-regexp-op/index.md) | `class RegexpOp : ComparisonOp` |
