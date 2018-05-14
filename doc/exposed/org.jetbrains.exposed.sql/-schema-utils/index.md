[org.jetbrains.exposed.sql](../index.md) / [SchemaUtils](.)

# SchemaUtils

`object SchemaUtils : Any`

### Functions

| Name | Summary |
|---|---|
| [create](create.md) | `fun <T : `[`Table`](../-table/index.md)`> create(vararg tables: T): Unit` |
| [createFKey](create-f-key.md) | `fun createFKey(reference: `[`Column`](../-column/index.md)`<*>): <ERROR CLASS>` |
| [createIndex](create-index.md) | `fun ~~createIndex~~(columns: Array<out `[`Column`](../-column/index.md)`<*>>, isUnique: Boolean): <ERROR CLASS>`<br>`fun createIndex(index: `[`Index`](../-index/index.md)`): <ERROR CLASS>` |
| [createMissingTablesAndColumns](create-missing-tables-and-columns.md) | `fun createMissingTablesAndColumns(vararg tables: `[`Table`](../-table/index.md)`): Unit`<br>This function should be used in cases when you want an easy-to-use auto-actualization of database scheme.
It will create all absent tables, add missing columns for existing tables if it's possible (columns are nullable or have default values). |
| [createSequence](create-sequence.md) | `fun createSequence(name: String): <ERROR CLASS>` |
| [createStatements](create-statements.md) | `fun createStatements(vararg tables: `[`Table`](../-table/index.md)`): List<String>` |
| [drop](drop.md) | `fun drop(vararg tables: `[`Table`](../-table/index.md)`): Unit` |
| [withDataBaseLock](with-data-base-lock.md) | `fun <T> `[`Transaction`](../-transaction/index.md)`.withDataBaseLock(body: () -> T): Unit`<br>Creates table with name "busy" (if not present) and single column to be used as "synchronization" point. Table wont be dropped after execution. |
