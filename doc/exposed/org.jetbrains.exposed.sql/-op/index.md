[org.jetbrains.exposed.sql](../index.md) / [Op](.)

# Op

`abstract class Op<T> : `[`Expression`](../-expression/index.md)`<T>`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Op()` |

### Inherited Functions

| Name | Summary |
|---|---|
| [equals](../-expression/equals.md) | `open fun equals(other: Any?): Boolean` |
| [hashCode](../-expression/hash-code.md) | `open fun hashCode(): Int` |
| [toSQL](../-expression/to-s-q-l.md) | `abstract fun toSQL(queryBuilder: `[`QueryBuilder`](../-query-builder/index.md)`): String` |
| [toString](../-expression/to-string.md) | `open fun toString(): String` |

### Companion Object Functions

| Name | Summary |
|---|---|
| [build](build.md) | `fun <T> build(op: (`[`SqlExpressionBuilder`](../-sql-expression-builder/index.md)`) -> Op<T>): Op<T>` |

### Extension Functions

| Name | Summary |
|---|---|
| [alias](../alias.md) | `fun <T> `[`Expression`](../-expression/index.md)`<T>.alias(alias: String): `[`ExpressionAlias`](../-expression-alias/index.md)`<T>` |
| [and](../and.md) | `infix fun Op<Boolean>.and(op: `[`Expression`](../-expression/index.md)`<Boolean>): Op<Boolean>` |
| [castTo](../cast-to.md) | `fun <R : Any> `[`Expression`](../-expression/index.md)`<*>.castTo(columnType: `[`IColumnType`](../-i-column-type/index.md)`): `[`Cast`](../-cast/index.md)`<R>` |
| [date](../date.md) | `fun <T> `[`Expression`](../-expression/index.md)`<T>.date(): `[`Date`](../-date/index.md)`<T>` |
| [lowerCase](../lower-case.md) | `fun <T : String?> `[`Expression`](../-expression/index.md)`<T>.lowerCase(): `[`Function`](../-function/index.md)`<T>` |
| [month](../month.md) | `fun <T> `[`Expression`](../-expression/index.md)`<T>.month(): `[`Month`](../-month/index.md)`<T>` |
| [or](../or.md) | `infix fun Op<Boolean>.or(op: `[`Expression`](../-expression/index.md)`<Boolean>): Op<Boolean>` |
| [substring](../substring.md) | `fun <T : String?> `[`Expression`](../-expression/index.md)`<T>.substring(start: Int, length: Int): `[`Function`](../-function/index.md)`<T>` |
| [trim](../trim.md) | `fun <T : String?> `[`Expression`](../-expression/index.md)`<T>.trim(): `[`Function`](../-function/index.md)`<T>` |
| [upperCase](../upper-case.md) | `fun <T : String?> `[`Expression`](../-expression/index.md)`<T>.upperCase(): `[`Function`](../-function/index.md)`<T>` |

### Inheritors

| Name | Summary |
|---|---|
| [AndOp](../-and-op/index.md) | `class AndOp : Op<Boolean>` |
| [Between](../-between/index.md) | `class Between : Op<Boolean>` |
| [ComparisonOp](../-comparison-op/index.md) | `abstract class ComparisonOp : Op<Boolean>` |
| [InListOrNotInListOp](../-in-list-or-not-in-list-op/index.md) | `class InListOrNotInListOp<T> : Op<Boolean>` |
| [IsNotNullOp](../-is-not-null-op/index.md) | `class IsNotNullOp : Op<Boolean>` |
| [IsNullOp](../-is-null-op/index.md) | `class IsNullOp : Op<Boolean>` |
| [NotOp](../-not-op/index.md) | `class NotOp<T> : Op<Boolean>` |
| [OrOp](../-or-op/index.md) | `class OrOp<T> : Op<Boolean>` |
| [exists](../exists/index.md) | `class exists : Op<Boolean>` |
| [notExists](../not-exists/index.md) | `class notExists : Op<Boolean>` |
