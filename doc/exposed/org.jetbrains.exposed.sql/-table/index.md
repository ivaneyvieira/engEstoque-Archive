[org.jetbrains.exposed.sql](../index.md) / [Table](.)

# Table

`open class Table : `[`ColumnSet`](../-column-set/index.md)`, `[`DdlAware`](../-ddl-aware/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Table(name: String = "")` |

### Properties

| Name | Summary |
|---|---|
| [autoIncColumn](auto-inc-column.md) | `val autoIncColumn: `[`Column`](../-column/index.md)`<*>?` |
| [checkConstraints](check-constraints.md) | `val checkConstraints: `[`ArrayList`](http://docs.oracle.com/javase/6/docs/api/java/util/ArrayList.html)`<<ERROR CLASS><String, `[`Op`](../-op/index.md)`<Boolean>>>` |
| [columns](columns.md) | `open val columns: List<`[`Column`](../-column/index.md)`<*>>` |
| [ddl](ddl.md) | `val ddl: List<String>` |
| [fields](fields.md) | `open val fields: List<`[`Expression`](../-expression/index.md)`<*>>` |
| [indices](indices.md) | `val indices: `[`ArrayList`](http://docs.oracle.com/javase/6/docs/api/java/util/ArrayList.html)`<`[`Index`](../-index/index.md)`>` |
| [tableName](table-name.md) | `open val tableName: String` |

### Inherited Properties

| Name | Summary |
|---|---|
| [source](../-column-set/source.md) | `open val source: `[`ColumnSet`](../-column-set/index.md) |

### Functions

| Name | Summary |
|---|---|
| [autoIncrement](auto-increment.md) | `fun <N : Any> `[`Column`](../-column/index.md)`<N>.autoIncrement(idSeqName: String? = null): `[`Column`](../-column/index.md)`<N>` |
| [autoinc](autoinc.md) | `fun <N : Comparable<N>> `[`Column`](../-column/index.md)`<`[`EntityID`](../../org.jetbrains.exposed.dao/-entity-i-d/index.md)`<N>>.autoinc(idSeqName: String? = null): `[`Column`](../-column/index.md)`<`[`EntityID`](../../org.jetbrains.exposed.dao/-entity-i-d/index.md)`<N>>` |
| [binary](binary.md) | `fun binary(name: String, length: Int): `[`Column`](../-column/index.md)`<ByteArray>`<br>A binary column to store an array of bytes. |
| [blob](blob.md) | `fun blob(name: String): `[`Column`](../-column/index.md)`<`[`Blob`](http://docs.oracle.com/javase/6/docs/api/java/sql/Blob.html)`>`<br>A blob column to store a large amount of binary data. |
| [bool](bool.md) | `fun bool(name: String): `[`Column`](../-column/index.md)`<Boolean>`<br>A bool column to store a boolean value. |
| [char](char.md) | `fun char(name: String): `[`Column`](../-column/index.md)`<Char>`<br>A char column to store a single character. |
| [check](check.md) | `fun <T> `[`Column`](../-column/index.md)`<T>.check(name: String = "", op: (`[`SqlExpressionBuilder`](../-sql-expression-builder/index.md)`, `[`Column`](../-column/index.md)`<T>) -> `[`Op`](../-op/index.md)`<Boolean>): <ERROR CLASS>`<br>Creates a check constraint in this column.`fun check(name: String = "", op: (`[`SqlExpressionBuilder`](../-sql-expression-builder/index.md)`) -> `[`Op`](../-op/index.md)`<Boolean>): Unit`<br>Creates a check constraint in this table. |
| [clientDefault](client-default.md) | `fun <T : Any> `[`Column`](../-column/index.md)`<T>.clientDefault(defaultValue: () -> T): `[`Column`](../-column/index.md)`<T>` |
| [createStatement](create-statement.md) | `open fun createStatement(): List<String>` |
| [crossJoin](cross-join.md) | `open infix fun crossJoin(otherTable: `[`ColumnSet`](../-column-set/index.md)`): `[`Join`](../-join/index.md) |
| [date](date.md) | `fun date(name: String): `[`Column`](../-column/index.md)`<<ERROR CLASS>>`<br>A date column to store a date. |
| [datetime](datetime.md) | `fun datetime(name: String): `[`Column`](../-column/index.md)`<<ERROR CLASS>>`<br>A datetime column to store both a date and a time. |
| [decimal](decimal.md) | `fun decimal(name: String, precision: Int, scale: Int): `[`Column`](../-column/index.md)`<`[`BigDecimal`](http://docs.oracle.com/javase/6/docs/api/java/math/BigDecimal.html)`>`<br>A decimal column to store a decimal number with a set [precision](decimal.md#org.jetbrains.exposed.sql.Table$decimal(kotlin.String, kotlin.Int, kotlin.Int)/precision) and [scale](decimal.md#org.jetbrains.exposed.sql.Table$decimal(kotlin.String, kotlin.Int, kotlin.Int)/scale). |
| [default](default.md) | `fun <T : Any> `[`Column`](../-column/index.md)`<T>.default(defaultValue: T): `[`Column`](../-column/index.md)`<T>` |
| [defaultExpression](default-expression.md) | `fun <T : Any> `[`Column`](../-column/index.md)`<T>.defaultExpression(defaultValue: `[`Expression`](../-expression/index.md)`<T>): `[`Column`](../-column/index.md)`<T>` |
| [describe](describe.md) | `open fun describe(s: `[`Transaction`](../-transaction/index.md)`): String` |
| [dropStatement](drop-statement.md) | `open fun dropStatement(): List<String>` |
| [entityId](entity-id.md) | `fun <T : Comparable<T>> `[`Column`](../-column/index.md)`<T>.entityId(): `[`Column`](../-column/index.md)`<`[`EntityID`](../../org.jetbrains.exposed.dao/-entity-i-d/index.md)`<T>>`<br>`fun <ID : Comparable<ID>> entityId(name: String, table: `[`IdTable`](../../org.jetbrains.exposed.dao/-id-table/index.md)`<ID>): `[`Column`](../-column/index.md)`<`[`EntityID`](../../org.jetbrains.exposed.dao/-entity-i-d/index.md)`<ID>>` |
| [enumeration](enumeration.md) | `fun <T : Enum<T>> enumeration(name: String, klass: `[`Class`](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)`<T>): `[`Column`](../-column/index.md)`<T>`<br>An enumeration column where enumerations are stored by their ordinal integer. |
| [enumerationByName](enumeration-by-name.md) | `fun <T : Enum<T>> enumerationByName(name: String, length: Int, klass: `[`Class`](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)`<T>): `[`Column`](../-column/index.md)`<T>`<br>An enumeration column where enumerations are stored by their name. |
| [equals](equals.md) | `open fun equals(other: Any?): Boolean` |
| [float](float.md) | `fun float(name: String): `[`Column`](../-column/index.md)`<Float>`<br>A float column to store a float number |
| [hashCode](hash-code.md) | `open fun hashCode(): Int` |
| [index](--index--.md) | `fun index(isUnique: Boolean = false, vararg columns: `[`Column`](../-column/index.md)`<*>): Unit`<br>`fun index(customIndexName: String? = null, isUnique: Boolean = false, vararg columns: `[`Column`](../-column/index.md)`<*>): Unit`<br>`fun <T> `[`Column`](../-column/index.md)`<T>.index(customIndexName: String? = null, isUnique: Boolean = false): `[`Column`](../-column/index.md)`<T>` |
| [innerJoin](inner-join.md) | `open infix fun innerJoin(otherTable: `[`ColumnSet`](../-column-set/index.md)`): `[`Join`](../-join/index.md) |
| [integer](integer.md) | `fun integer(name: String): `[`Column`](../-column/index.md)`<Int>`<br>An integer column to store an integer number. |
| [join](join.md) | `open fun join(otherTable: `[`ColumnSet`](../-column-set/index.md)`, joinType: `[`JoinType`](../-join-type/index.md)`, onColumn: `[`Expression`](../-expression/index.md)`<*>?, otherColumn: `[`Expression`](../-expression/index.md)`<*>?, additionalConstraint: (`[`SqlExpressionBuilder`](../-sql-expression-builder/index.md)`) -> `[`Op`](../-op/index.md)`<Boolean>): `[`Join`](../-join/index.md)<br>`infix fun ~~join~~(otherTable: `[`ColumnSet`](../-column-set/index.md)`): `[`Join`](../-join/index.md) |
| [leftJoin](left-join.md) | `open infix fun leftJoin(otherTable: `[`ColumnSet`](../-column-set/index.md)`): `[`Join`](../-join/index.md) |
| [long](long.md) | `fun long(name: String): `[`Column`](../-column/index.md)`<Long>`<br>A long column to store a large (long) number. |
| [modifyStatement](modify-statement.md) | `open fun modifyStatement(): Nothing` |
| [nameInDatabaseCase](name-in-database-case.md) | `fun nameInDatabaseCase(): String` |
| [nullable](nullable.md) | `fun <T : Any> `[`Column`](../-column/index.md)`<T>.nullable(): `[`Column`](../-column/index.md)`<T?>` |
| [optReference](opt-reference.md) | `fun <T : Comparable<T>> optReference(name: String, foreign: `[`IdTable`](../../org.jetbrains.exposed.dao/-id-table/index.md)`<T>, onDelete: `[`ReferenceOption`](../-reference-option/index.md)`? = null): `[`Column`](../-column/index.md)`<`[`EntityID`](../../org.jetbrains.exposed.dao/-entity-i-d/index.md)`<T>?>` |
| [primaryKey](primary-key.md) | `fun <T> `[`Column`](../-column/index.md)`<T>.primaryKey(indx: Int? = null): `[`Column`](../-column/index.md)`<T>` |
| [reference](reference.md) | `fun <T : Comparable<T>> reference(name: String, foreign: `[`IdTable`](../../org.jetbrains.exposed.dao/-id-table/index.md)`<T>, onDelete: `[`ReferenceOption`](../-reference-option/index.md)`? = null): `[`Column`](../-column/index.md)`<`[`EntityID`](../../org.jetbrains.exposed.dao/-entity-i-d/index.md)`<T>>`<br>`fun <T> Table.reference(name: String, pkColumn: `[`Column`](../-column/index.md)`<T>): `[`Column`](../-column/index.md)`<T>` |
| [references](references.md) | `fun <T, S : T, C : `[`Column`](../-column/index.md)`<S>> C.references(ref: `[`Column`](../-column/index.md)`<T>, onDelete: `[`ReferenceOption`](../-reference-option/index.md)`?): C`<br>`infix fun <T, S : T, C : `[`Column`](../-column/index.md)`<S>> C.references(ref: `[`Column`](../-column/index.md)`<T>): C` |
| [registerColumn](register-column.md) | `fun <T> registerColumn(name: String, type: `[`IColumnType`](../-i-column-type/index.md)`): `[`Column`](../-column/index.md)`<T>` |
| [replaceColumn](replace-column.md) | `fun <TColumn : `[`Column`](../-column/index.md)`<*>> replaceColumn(oldColumn: `[`Column`](../-column/index.md)`<*>, newColumn: TColumn): TColumn` |
| [text](text.md) | `fun text(name: String, collate: String? = null): `[`Column`](../-column/index.md)`<String>`<br>A text column to store a large amount of text. |
| [uniqueIndex](unique-index.md) | `fun <T> `[`Column`](../-column/index.md)`<T>.uniqueIndex(customIndexName: String? = null): `[`Column`](../-column/index.md)`<T>`<br>`fun uniqueIndex(vararg columns: `[`Column`](../-column/index.md)`<*>): Unit`<br>`fun uniqueIndex(customIndexName: String? = null, vararg columns: `[`Column`](../-column/index.md)`<*>): Unit` |
| [uuid](uuid.md) | `fun uuid(name: String): `[`Column`](../-column/index.md)`<`[`UUID`](http://docs.oracle.com/javase/6/docs/api/java/util/UUID.html)`>`<br>A uuid column to store a UUID. |
| [varchar](varchar.md) | `fun varchar(name: String, length: Int, collate: String? = null): `[`Column`](../-column/index.md)`<String>`<br>A varchar column to store a string with a set maximum amount of characters. |

### Inherited Functions

| Name | Summary |
|---|---|
| [slice](../-column-set/slice.md) | `fun slice(vararg columns: `[`Expression`](../-expression/index.md)`<*>): `[`FieldSet`](../-field-set/index.md)<br>`fun slice(columns: List<`[`Expression`](../-expression/index.md)`<*>>): `[`FieldSet`](../-field-set/index.md) |

### Extension Functions

| Name | Summary |
|---|---|
| [alias](../alias.md) | `fun <T : `[`Query`](../-query/index.md)`> T.alias(alias: String): `[`QueryAlias`](../-query-alias/index.md) |
| [batchInsert](../batch-insert.md) | `fun <T : Table, E : Any> T.batchInsert(data: Iterable<E>, ignore: Boolean = false, body: (`[`BatchInsertStatement`](../../org.jetbrains.exposed.sql.statements/-batch-insert-statement/index.md)`, E) -> Unit): List<Map<`[`Column`](../-column/index.md)`<*>, Any>>`

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DMLTests.testBatchInsert01<br>```
<br> |
| [deleteAll](../delete-all.md) | `fun Table.deleteAll(): Int`

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DMLTests.testDelete01<br>```
<br> |
| [deleteIgnoreWhere](../delete-ignore-where.md) | `fun Table.deleteIgnoreWhere(limit: Int? = null, offset: Int? = null, op: (`[`SqlExpressionBuilder`](../-sql-expression-builder/index.md)`) -> `[`Op`](../-op/index.md)`<Boolean>): Int` |
| [deleteWhere](../delete-where.md) | `fun Table.deleteWhere(limit: Int? = null, offset: Int? = null, op: (`[`SqlExpressionBuilder`](../-sql-expression-builder/index.md)`) -> `[`Op`](../-op/index.md)`<Boolean>): Int`

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DMLTests.testDelete01<br>```
<br> |
| [exists](../exists.md) | `fun Table.exists(): Boolean`

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DDLTests.tableExists02<br>```
<br> |
| [insert](../insert.md) | `fun <T : Table> T.insert(body: (T, `[`InsertStatement`](../../org.jetbrains.exposed.sql.statements/-insert-statement/index.md)`<Number>) -> Unit): `[`InsertStatement`](../../org.jetbrains.exposed.sql.statements/-insert-statement/index.md)`<Number>`

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DMLTests.testInsert01<br>```
<br>`fun <T : Table> T.insert(selectQuery: `[`Query`](../-query/index.md)`, columns: List<`[`Column`](../-column/index.md)`<*>> = this.columns.filterNot { it.columnType.isAutoInc }): Int?`

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DMLTests.testInsertSelect01<br>```
<br> |
| [insertIgnore](../insert-ignore.md) | `fun <T : Table> T.insertIgnore(body: (T, `[`UpdateBuilder`](../../org.jetbrains.exposed.sql.statements/-update-builder/index.md)`<*>) -> Unit): `[`InsertStatement`](../../org.jetbrains.exposed.sql.statements/-insert-statement/index.md)`<Long>`<br>`fun <T : Table> T.insertIgnore(selectQuery: `[`Query`](../-query/index.md)`, columns: List<`[`Column`](../-column/index.md)`<*>> = this.columns.filterNot { it.columnType.isAutoInc }): Int?` |
| [joinQuery](../join-query.md) | `fun Table.joinQuery(on: (`[`SqlExpressionBuilder`](../-sql-expression-builder/index.md)`, `[`QueryAlias`](../-query-alias/index.md)`) -> `[`Op`](../-op/index.md)`<Boolean>, joinType: `[`JoinType`](../-join-type/index.md)` = JoinType.INNER, joinPart: () -> `[`Query`](../-query/index.md)`): `[`Join`](../-join/index.md) |
| [reference](reference.md) | `fun <T> Table.reference(name: String, pkColumn: `[`Column`](../-column/index.md)`<T>): `[`Column`](../-column/index.md)`<T>` |
| [replace](../replace.md) | `fun <T : Table> T.replace(body: (T, `[`UpdateBuilder`](../../org.jetbrains.exposed.sql.statements/-update-builder/index.md)`<*>) -> Unit): `[`ReplaceStatement`](../../org.jetbrains.exposed.sql.statements/-replace-statement/index.md)`<Long>`

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DMLTests.testReplace01<br>```
<br> |
| [select](../select.md) | `fun `[`FieldSet`](../-field-set/index.md)`.select(where: (`[`SqlExpressionBuilder`](../-sql-expression-builder/index.md)`) -> `[`Op`](../-op/index.md)`<Boolean>): `[`Query`](../-query/index.md)

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DMLTests.testSelect01<br>```
<br>`fun `[`FieldSet`](../-field-set/index.md)`.select(where: `[`Op`](../-op/index.md)`<Boolean>): `[`Query`](../-query/index.md) |
| [selectAll](../select-all.md) | `fun `[`FieldSet`](../-field-set/index.md)`.selectAll(): `[`Query`](../-query/index.md)

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DMLTests.testSelectDistinct<br>```
<br> |
| [targetTables](../target-tables.md) | `fun `[`ColumnSet`](../-column-set/index.md)`.targetTables(): List<Table>` |
| [update](../update.md) | `fun <T : Table> T.update(where: (`[`SqlExpressionBuilder`](../-sql-expression-builder/index.md)`) -> `[`Op`](../-op/index.md)`<Boolean>, limit: Int? = null, body: (T, `[`UpdateStatement`](../../org.jetbrains.exposed.sql.statements/-update-statement/index.md)`) -> Unit): Int`

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DMLTests.testUpdate01<br>```
<br> |

### Inheritors

| Name | Summary |
|---|---|
| [Alias](../-alias/index.md) | `class Alias<out T : Table> : Table` |
| [IdTable](../../org.jetbrains.exposed.dao/-id-table/index.md) | `abstract class IdTable<T : Comparable<T>> : Table` |
