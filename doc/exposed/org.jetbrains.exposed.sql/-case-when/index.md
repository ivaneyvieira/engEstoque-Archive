[org.jetbrains.exposed.sql](../index.md) / [CaseWhen](.)

# CaseWhen

`class CaseWhen<T> : Any`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `CaseWhen(value: `[`Expression`](../-expression/index.md)`<*>?)` |

### Properties

| Name | Summary |
|---|---|
| [cases](cases.md) | `val cases: `[`ArrayList`](http://docs.oracle.com/javase/6/docs/api/java/util/ArrayList.html)`<<ERROR CLASS><`[`Expression`](../-expression/index.md)`<Boolean>, `[`Expression`](../-expression/index.md)`<out T>>>` |
| [value](value.md) | `val value: `[`Expression`](../-expression/index.md)`<*>?` |

### Functions

| Name | Summary |
|---|---|
| [Else](-else.md) | `fun <R : T> Else(e: `[`Expression`](../-expression/index.md)`<R>): `[`Expression`](../-expression/index.md)`<R>` |
| [When](-when.md) | `fun <R : T> When(cond: `[`Expression`](../-expression/index.md)`<Boolean>, result: `[`Expression`](../-expression/index.md)`<R>): CaseWhen<R>` |
