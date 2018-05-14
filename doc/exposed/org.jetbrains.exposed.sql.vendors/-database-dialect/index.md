[org.jetbrains.exposed.sql.vendors](../index.md) / [DatabaseDialect](.)

# DatabaseDialect

`interface DatabaseDialect : Any`

### Properties

| Name | Summary |
|---|---|
| [dataTypeProvider](data-type-provider.md) | `abstract val dataTypeProvider: `[`DataTypeProvider`](../-data-type-provider/index.md) |
| [defaultReferenceOption](default-reference-option.md) | `open val defaultReferenceOption: `[`ReferenceOption`](../../org.jetbrains.exposed.sql/-reference-option/index.md) |
| [functionProvider](function-provider.md) | `abstract val functionProvider: `[`FunctionProvider`](../-function-provider/index.md) |
| [identifierLengthLimit](identifier-length-limit.md) | `open val identifierLengthLimit: Int` |
| [name](name.md) | `abstract val name: String` |
| [needsQuotesWhenSymbolsInNames](needs-quotes-when-symbols-in-names.md) | `open val needsQuotesWhenSymbolsInNames: Boolean` |
| [needsSequenceToAutoInc](needs-sequence-to-auto-inc.md) | `open val needsSequenceToAutoInc: Boolean` |
| [supportsIfNotExists](supports-if-not-exists.md) | `open val supportsIfNotExists: Boolean` |
| [supportsMultipleGeneratedKeys](supports-multiple-generated-keys.md) | `abstract val supportsMultipleGeneratedKeys: Boolean` |

### Functions

| Name | Summary |
|---|---|
| [allTablesNames](all-tables-names.md) | `abstract fun allTablesNames(): List<String>` |
| [catalog](catalog.md) | `open fun catalog(transaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`): String` |
| [checkTableMapping](check-table-mapping.md) | `open fun checkTableMapping(table: `[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`): Boolean` |
| [columnConstraints](column-constraints.md) | `open fun columnConstraints(vararg tables: `[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`): Map<<ERROR CLASS><String, String>, List<`[`ForeignKeyConstraint`](../../org.jetbrains.exposed.sql/-foreign-key-constraint/index.md)`>>`<br>returns map of constraint for a table name/column name pair |
| [createIndex](create-index.md) | `abstract fun createIndex(index: `[`Index`](../../org.jetbrains.exposed.sql/-index/index.md)`): String` |
| [dropIndex](drop-index.md) | `abstract fun dropIndex(tableName: String, indexName: String): String` |
| [existingIndices](existing-indices.md) | `open fun existingIndices(vararg tables: `[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`): Map<`[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`, List<`[`Index`](../../org.jetbrains.exposed.sql/-index/index.md)`>>`<br>return set of indices for each table |
| [getDatabase](get-database.md) | `abstract fun getDatabase(): String` |
| [isAllowedAsColumnDefault](is-allowed-as-column-default.md) | `open fun isAllowedAsColumnDefault(e: `[`Expression`](../../org.jetbrains.exposed.sql/-expression/index.md)`<*>): Boolean` |
| [modifyColumn](modify-column.md) | `abstract fun modifyColumn(column: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<*>): String` |
| [resetCaches](reset-caches.md) | `abstract fun resetCaches(): Unit` |
| [supportsSelectForUpdate](supports-select-for-update.md) | `abstract fun supportsSelectForUpdate(): Boolean` |
| [tableColumns](table-columns.md) | `open fun tableColumns(vararg tables: `[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`): Map<`[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`, List<`[`ColumnMetadata`](../-column-metadata/index.md)`>>`<br>returns list of pairs (column name + nullable) for every table |
| [tableExists](table-exists.md) | `abstract fun tableExists(table: `[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`): Boolean` |

### Inheritors

| Name | Summary |
|---|---|
| [VendorDialect](../-vendor-dialect/index.md) | `abstract class VendorDialect : DatabaseDialect` |
