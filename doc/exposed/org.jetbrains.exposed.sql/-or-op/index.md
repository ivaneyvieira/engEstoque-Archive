[org.jetbrains.exposed.sql](../index.md) / [OrOp](.)

# OrOp

`class OrOp<T> : `[`Op`](../-op/index.md)`<Boolean>`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `OrOp(expr1: `[`Expression`](../-expression/index.md)`<T>, expr2: `[`Expression`](../-expression/index.md)`<T>)` |

### Properties

| Name | Summary |
|---|---|
| [expr1](expr1.md) | `val expr1: `[`Expression`](../-expression/index.md)`<T>` |
| [expr2](expr2.md) | `val expr2: `[`Expression`](../-expression/index.md)`<T>` |

### Functions

| Name | Summary |
|---|---|
| [toSQL](to-s-q-l.md) | `fun toSQL(queryBuilder: `[`QueryBuilder`](../-query-builder/index.md)`): String` |

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
