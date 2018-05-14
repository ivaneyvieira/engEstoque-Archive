[org.jetbrains.exposed.sql.vendors](../index.md) / [VendorDialect](.)

# VendorDialect

`abstract class VendorDialect : `[`DatabaseDialect`](../-database-dialect/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `VendorDialect(name: String, dataTypeProvider: `[`DataTypeProvider`](../-data-type-provider/index.md)`, functionProvider: `[`FunctionProvider`](../-function-provider/index.md)`)` |

### Properties

| Name | Summary |
|---|---|
| [allTablesNames](all-tables-names.md) | `val allTablesNames: List<String>` |
| [dataTypeProvider](data-type-provider.md) | `open val dataTypeProvider: `[`DataTypeProvider`](../-data-type-provider/index.md) |
| [functionProvider](function-provider.md) | `open val functionProvider: `[`FunctionProvider`](../-function-provider/index.md) |
| [inProperCase](in-proper-case.md) | `val String.inProperCase: String` |
| [name](name.md) | `open val name: String` |
| [supportsMultipleGeneratedKeys](supports-multiple-generated-keys.md) | `open val supportsMultipleGeneratedKeys: Boolean` |

### Inherited Properties

| Name | Summary |
|---|---|
| [defaultReferenceOption](../-database-dialect/default-reference-option.md) | `open val defaultReferenceOption: `[`ReferenceOption`](../../org.jetbrains.exposed.sql/-reference-option/index.md) |
| [identifierLengthLimit](../-database-dialect/identifier-length-limit.md) | `open val identifierLengthLimit: Int` |
| [needsQuotesWhenSymbolsInNames](../-database-dialect/needs-quotes-when-symbols-in-names.md) | `open val needsQuotesWhenSymbolsInNames: Boolean` |
| [needsSequenceToAutoInc](../-database-dialect/needs-sequence-to-auto-inc.md) | `open val needsSequenceToAutoInc: Boolean` |
| [supportsIfNotExists](../-database-dialect/supports-if-not-exists.md) | `open val supportsIfNotExists: Boolean` |

### Functions

| Name | Summary |
|---|---|
| [allTablesNames](all-tables-names.md) | `open fun allTablesNames(): List<String>` |
| [columnConstraints](column-constraints.md) | `open fun columnConstraints(vararg tables: `[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`): Map<<ERROR CLASS><String, String>, List<`[`ForeignKeyConstraint`](../../org.jetbrains.exposed.sql/-foreign-key-constraint/index.md)`>>`<br>returns map of constraint for a table name/column name pair |
| [createIndex](create-index.md) | `open fun createIndex(index: `[`Index`](../../org.jetbrains.exposed.sql/-index/index.md)`): String` |
| [dropIndex](drop-index.md) | `open fun dropIndex(tableName: String, indexName: String): String` |
| [existingIndices](existing-indices.md) | `open fun existingIndices(vararg tables: `[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`): Map<`[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`, List<`[`Index`](../../org.jetbrains.exposed.sql/-index/index.md)`>>`<br>return set of indices for each table |
| [extractColumns](extract-columns.md) | `fun `[`ResultSet`](http://docs.oracle.com/javase/6/docs/api/java/sql/ResultSet.html)`.extractColumns(tables: Array<out `[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`>, extract: (`[`ResultSet`](http://docs.oracle.com/javase/6/docs/api/java/sql/ResultSet.html)`) -> <ERROR CLASS><String, `[`ColumnMetadata`](../-column-metadata/index.md)`>): Map<`[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`, List<`[`ColumnMetadata`](../-column-metadata/index.md)`>>` |
| [getDatabase](get-database.md) | `open fun getDatabase(): String` |
| [modifyColumn](modify-column.md) | `open fun modifyColumn(column: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<*>): String` |
| [resetCaches](reset-caches.md) | `open fun resetCaches(): Unit` |
| [supportsSelectForUpdate](supports-select-for-update.md) | `open fun supportsSelectForUpdate(): <ERROR CLASS>` |
| [tableColumns](table-columns.md) | `open fun tableColumns(vararg tables: `[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`): Map<`[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`, List<`[`ColumnMetadata`](../-column-metadata/index.md)`>>`<br>returns list of pairs (column name + nullable) for every table |
| [tableExists](table-exists.md) | `open fun tableExists(table: `[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`): <ERROR CLASS>` |

### Inherited Functions

| Name | Summary |
|---|---|
| [catalog](../-database-dialect/catalog.md) | `open fun catalog(transaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`): String` |
| [checkTableMapping](../-database-dialect/check-table-mapping.md) | `open fun checkTableMapping(table: `[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`): Boolean` |
| [isAllowedAsColumnDefault](../-database-dialect/is-allowed-as-column-default.md) | `open fun isAllowedAsColumnDefault(e: `[`Expression`](../../org.jetbrains.exposed.sql/-expression/index.md)`<*>): Boolean` |
