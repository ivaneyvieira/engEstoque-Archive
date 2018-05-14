[org.jetbrains.exposed.sql](../index.md) / [QueryParameter](.)

# QueryParameter

`class QueryParameter<T> : `[`Expression`](../-expression/index.md)`<T>`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `QueryParameter(value: T, sqlType: `[`IColumnType`](../-i-column-type/index.md)`)` |

### Properties

| Name | Summary |
|---|---|
| [sqlType](sql-type.md) | `val sqlType: `[`IColumnType`](../-i-column-type/index.md) |
| [value](value.md) | `val value: T` |

### Functions

| Name | Summary |
|---|---|
| [toSQL](to-s-q-l.md) | `fun toSQL(queryBuilder: `[`QueryBuilder`](../-query-builder/index.md)`): String` |

### Inherited Functions

| Name | Summary |
|---|---|
| [equals](../-expression/equals.md) | `open fun equals(other: Any?): Boolean` |
| [hashCode](../-expression/hash-code.md) | `open fun hashCode(): Int` |
| [toString](../-expression/to-string.md) | `open fun toString(): String` |

### Extension Functions

| Name | Summary |
|---|---|
| [alias](../alias.md) | `fun <T> `[`Expression`](../-expression/index.md)`<T>.alias(alias: String): `[`ExpressionAlias`](../-expression-alias/index.md)`<T>` |
| [castTo](../cast-to.md) | `fun <R : Any> `[`Expression`](../-expression/index.md)`<*>.castTo(columnType: `[`IColumnType`](../-i-column-type/index.md)`): `[`Cast`](../-cast/index.md)`<R>` |
| [date](../date.md) | `fun <T> `[`Expression`](../-expression/index.md)`<T>.date(): `[`Date`](../-date/index.md)`<T>` |
| [lowerCase](../lower-case.md) | `fun <T : String?> `[`Expression`](../-expression/index.md)`<T>.lowerCase(): `[`Function`](../-function/index.md)`<T>` |
| [month](../month.md) | `fun <T> `[`Expression`](../-expression/index.md)`<T>.month(): `[`Month`](../-month/index.md)`<T>` |
| [substring](../substring.md) | `fun <T : String?> `[`Expression`](../-expression/index.md)`<T>.substring(start: Int, length: Int): `[`Function`](../-function/index.md)`<T>` |
| [trim](../trim.md) | `fun <T : String?> `[`Expression`](../-expression/index.md)`<T>.trim(): `[`Function`](../-function/index.md)`<T>` |
| [upperCase](../upper-case.md) | `fun <T : String?> `[`Expression`](../-expression/index.md)`<T>.upperCase(): `[`Function`](../-function/index.md)`<T>` |
