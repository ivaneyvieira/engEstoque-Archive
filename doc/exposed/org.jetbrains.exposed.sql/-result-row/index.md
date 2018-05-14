[org.jetbrains.exposed.sql](../index.md) / [ResultRow](.)

# ResultRow

`class ResultRow : Any`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ResultRow(size: Int, fieldIndex: Map<`[`Expression`](../-expression/index.md)`<*>, Int>)` |

### Properties

| Name | Summary |
|---|---|
| [data](data.md) | `val data: <ERROR CLASS>` |

### Functions

| Name | Summary |
|---|---|
| [get](get.md) | `operator fun <T> get(c: `[`Expression`](../-expression/index.md)`<T>): T`<br>Function might returns null. Use @tryGet if you don't sure of nullability (e.g. in left-join cases) |
| [hasValue](has-value.md) | `fun <T> hasValue(c: `[`Expression`](../-expression/index.md)`<T>): Boolean` |
| [set](set.md) | `operator fun <T> set(c: `[`Expression`](../-expression/index.md)`<out T>, value: T): Unit` |
| [toString](to-string.md) | `fun toString(): String` |
| [tryGet](try-get.md) | `fun <T> tryGet(c: `[`Expression`](../-expression/index.md)`<T>): T?` |

### Companion Object Functions

| Name | Summary |
|---|---|
| [create](create.md) | `fun create(rs: `[`ResultSet`](http://docs.oracle.com/javase/6/docs/api/java/sql/ResultSet.html)`, fields: List<`[`Expression`](../-expression/index.md)`<*>>, fieldsIndex: Map<`[`Expression`](../-expression/index.md)`<*>, Int>): ResultRow` |
