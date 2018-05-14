[org.jetbrains.exposed.sql.statements](../index.md) / [BatchUpdateStatement](.)

# BatchUpdateStatement

`class BatchUpdateStatement : `[`UpdateStatement`](../-update-statement/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `BatchUpdateStatement(table: `[`IdTable`](../../org.jetbrains.exposed.dao/-id-table/index.md)`<*>)` |

### Properties

| Name | Summary |
|---|---|
| [data](data.md) | `val data: `[`ArrayList`](http://docs.oracle.com/javase/6/docs/api/java/util/ArrayList.html)`<<ERROR CLASS><`[`EntityID`](../../org.jetbrains.exposed.dao/-entity-i-d/index.md)`<*>, `[`SortedMap`](http://docs.oracle.com/javase/6/docs/api/java/util/SortedMap.html)`<`[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<*>, Any?>>>` |
| [firstDataSet](first-data-set.md) | `val firstDataSet: List<<ERROR CLASS><`[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<*>, Any?>>` |
| [table](table.md) | `val table: `[`IdTable`](../../org.jetbrains.exposed.dao/-id-table/index.md)`<*>` |

### Inherited Properties

| Name | Summary |
|---|---|
| [limit](../-update-statement/limit.md) | `val limit: Int?` |
| [targetsSet](../-update-statement/targets-set.md) | `val targetsSet: `[`ColumnSet`](../../org.jetbrains.exposed.sql/-column-set/index.md) |
| [where](../-update-statement/where.md) | `val where: `[`Op`](../../org.jetbrains.exposed.sql/-op/index.md)`<Boolean>?` |

### Functions

| Name | Summary |
|---|---|
| [addBatch](add-batch.md) | `fun addBatch(id: `[`EntityID`](../../org.jetbrains.exposed.dao/-entity-i-d/index.md)`<*>): Unit` |
| [arguments](arguments.md) | `fun arguments(): Iterable<Iterable<<ERROR CLASS><`[`IColumnType`](../../org.jetbrains.exposed.sql/-i-column-type/index.md)`, Any?>>>` |
| [executeInternal](execute-internal.md) | `fun `[`PreparedStatement`](http://docs.oracle.com/javase/6/docs/api/java/sql/PreparedStatement.html)`.executeInternal(transaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`): Int` |
| [prepareSQL](prepare-s-q-l.md) | `fun prepareSQL(transaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`): String` |
| [update](update.md) | `fun <T, S : T?> update(column: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<T>, value: `[`Expression`](../../org.jetbrains.exposed.sql/-expression/index.md)`<S>): <ERROR CLASS>` |
