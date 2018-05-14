[org.jetbrains.exposed.sql](../index.md) / [QueryBuilder](.)

# QueryBuilder

`class QueryBuilder : Any`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `QueryBuilder(prepared: Boolean)` |

### Properties

| Name | Summary |
|---|---|
| [args](args.md) | `val args: `[`ArrayList`](http://docs.oracle.com/javase/6/docs/api/java/util/ArrayList.html)`<<ERROR CLASS><`[`IColumnType`](../-i-column-type/index.md)`, Any?>>` |
| [prepared](prepared.md) | `val prepared: Boolean` |

### Functions

| Name | Summary |
|---|---|
| [registerArgument](register-argument.md) | `fun <T> registerArgument(column: `[`Column`](../-column/index.md)`<*>, argument: T): String`<br>`fun <T> registerArgument(sqlType: `[`IColumnType`](../-i-column-type/index.md)`, argument: T): <ERROR CLASS>` |
| [registerArguments](register-arguments.md) | `fun <T> registerArguments(sqlType: `[`IColumnType`](../-i-column-type/index.md)`, arguments: Iterable<T>): List<String>` |
