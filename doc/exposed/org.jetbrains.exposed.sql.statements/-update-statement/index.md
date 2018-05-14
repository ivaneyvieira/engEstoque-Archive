[org.jetbrains.exposed.sql.statements](../index.md) / [UpdateStatement](.)

# UpdateStatement

`open class UpdateStatement : `[`UpdateBuilder`](../-update-builder/index.md)`<Int>`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `UpdateStatement(targetsSet: `[`ColumnSet`](../../org.jetbrains.exposed.sql/-column-set/index.md)`, limit: Int?, where: `[`Op`](../../org.jetbrains.exposed.sql/-op/index.md)`<Boolean>? = null)` |

### Properties

| Name | Summary |
|---|---|
| [firstDataSet](first-data-set.md) | `open val firstDataSet: List<<ERROR CLASS><`[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<*>, Any?>>` |
| [limit](limit.md) | `val limit: Int?` |
| [targetsSet](targets-set.md) | `val targetsSet: `[`ColumnSet`](../../org.jetbrains.exposed.sql/-column-set/index.md) |
| [where](where.md) | `val where: `[`Op`](../../org.jetbrains.exposed.sql/-op/index.md)`<Boolean>?` |

### Inherited Properties

| Name | Summary |
|---|---|
| [values](../-update-builder/values.md) | `val values: MutableMap<`[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<*>, Any?>` |

### Functions

| Name | Summary |
|---|---|
| [arguments](arguments.md) | `open fun arguments(): Iterable<Iterable<<ERROR CLASS><`[`IColumnType`](../../org.jetbrains.exposed.sql/-i-column-type/index.md)`, Any?>>>` |
| [executeInternal](execute-internal.md) | `open fun `[`PreparedStatement`](http://docs.oracle.com/javase/6/docs/api/java/sql/PreparedStatement.html)`.executeInternal(transaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`): Int` |
| [prepareSQL](prepare-s-q-l.md) | `open fun prepareSQL(transaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`): String` |

### Inherited Functions

| Name | Summary |
|---|---|
| [set](../-update-builder/set.md) | `open operator fun <S> set(column: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<S>, value: S): Unit` |
| [update](../-update-builder/update.md) | `open fun <T, S : T?> update(column: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<T>, value: `[`Expression`](../../org.jetbrains.exposed.sql/-expression/index.md)`<S>): Unit` |

### Inheritors

| Name | Summary |
|---|---|
| [BatchUpdateStatement](../-batch-update-statement/index.md) | `class BatchUpdateStatement : UpdateStatement` |
