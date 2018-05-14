[org.jetbrains.exposed.sql](../index.md) / [LikeOp](.)

# LikeOp

`class LikeOp : `[`ComparisonOp`](../-comparison-op/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `LikeOp(expr1: `[`Expression`](../-expression/index.md)`<*>, expr2: `[`Expression`](../-expression/index.md)`<*>)` |

### Inherited Properties

| Name | Summary |
|---|---|
| [expr1](../-comparison-op/expr1.md) | `val expr1: `[`Expression`](../-expression/index.md)`<*>` |
| [expr2](../-comparison-op/expr2.md) | `val expr2: `[`Expression`](../-expression/index.md)`<*>` |
| [opSign](../-comparison-op/op-sign.md) | `val opSign: String` |

### Inherited Functions

| Name | Summary |
|---|---|
| [toSQL](../-comparison-op/to-s-q-l.md) | `open fun toSQL(queryBuilder: `[`QueryBuilder`](../-query-builder/index.md)`): <ERROR CLASS>` |

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
