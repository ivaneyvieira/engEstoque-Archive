[org.jetbrains.exposed.sql.vendors](../index.md) / [FunctionProvider](.)

# FunctionProvider

`abstract class FunctionProvider : Any`

### Types

| Name | Summary |
|---|---|
| [MatchMode](-match-mode/index.md) | `interface MatchMode : Any` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `FunctionProvider()` |

### Properties

| Name | Summary |
|---|---|
| [DEFAULT_VALUE_EXPRESSION](-d-e-f-a-u-l-t_-v-a-l-u-e_-e-x-p-r-e-s-s-i-o-n.md) | `open val DEFAULT_VALUE_EXPRESSION: String` |

### Functions

| Name | Summary |
|---|---|
| [cast](cast.md) | `open fun cast(expr: `[`Expression`](../../org.jetbrains.exposed.sql/-expression/index.md)`<*>, type: `[`IColumnType`](../../org.jetbrains.exposed.sql/-i-column-type/index.md)`, builder: `[`QueryBuilder`](../../org.jetbrains.exposed.sql/-query-builder/index.md)`): String` |
| [delete](delete.md) | `open fun delete(ignore: Boolean, table: `[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`, where: String?, limit: Int?, offset: Int?, transaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`): String` |
| [insert](insert.md) | `open fun insert(ignore: Boolean, table: `[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`, columns: List<`[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<*>>, expr: String, transaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`): String` |
| [match](match.md) | `open fun <T : String?> `[`ExpressionWithColumnType`](../../org.jetbrains.exposed.sql/-expression-with-column-type/index.md)`<T>.match(pattern: String, mode: `[`MatchMode`](-match-mode/index.md)`? = null): `[`Op`](../../org.jetbrains.exposed.sql/-op/index.md)`<Boolean>` |
| [queryLimit](query-limit.md) | `open fun queryLimit(size: Int, offset: Int, alreadyOrdered: Boolean): String` |
| [random](random.md) | `open fun random(seed: Int?): String` |
| [replace](replace.md) | `open fun replace(table: `[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`, data: List<<ERROR CLASS><`[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<*>, Any?>>, transaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`): String` |
| [substring](substring.md) | `open fun <T : String?> substring(expr: `[`Expression`](../../org.jetbrains.exposed.sql/-expression/index.md)`<T>, start: `[`Expression`](../../org.jetbrains.exposed.sql/-expression/index.md)`<Int>, length: `[`Expression`](../../org.jetbrains.exposed.sql/-expression/index.md)`<Int>, builder: `[`QueryBuilder`](../../org.jetbrains.exposed.sql/-query-builder/index.md)`): String` |
| [update](update.md) | `open fun update(targets: `[`ColumnSet`](../../org.jetbrains.exposed.sql/-column-set/index.md)`, columnsAndValues: List<<ERROR CLASS><`[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<*>, Any?>>, limit: Int?, where: `[`Op`](../../org.jetbrains.exposed.sql/-op/index.md)`<Boolean>?, transaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`): String` |
