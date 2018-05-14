[org.jetbrains.exposed.sql](../index.md) / [Alias](.)

# Alias

`class Alias<out T : `[`Table`](../-table/index.md)`> : `[`Table`](../-table/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Alias(delegate: T, alias: String)` |

### Properties

| Name | Summary |
|---|---|
| [alias](alias.md) | `val alias: String` |
| [columns](columns.md) | `val columns: List<`[`Column`](../-column/index.md)`<*>>` |
| [delegate](delegate.md) | `val delegate: T` |
| [fields](fields.md) | `val fields: List<`[`Expression`](../-expression/index.md)`<*>>` |
| [tableName](table-name.md) | `val tableName: String` |
| [tableNameWithAlias](table-name-with-alias.md) | `val tableNameWithAlias: String` |

### Inherited Properties

| Name | Summary |
|---|---|
| [autoIncColumn](../-table/auto-inc-column.md) | `val autoIncColumn: `[`Column`](../-column/index.md)`<*>?` |
| [checkConstraints](../-table/check-constraints.md) | `val checkConstraints: `[`ArrayList`](http://docs.oracle.com/javase/6/docs/api/java/util/ArrayList.html)`<<ERROR CLASS><String, `[`Op`](../-op/index.md)`<Boolean>>>` |
| [ddl](../-table/ddl.md) | `val ddl: List<String>` |
| [indices](../-table/indices.md) | `val indices: `[`ArrayList`](http://docs.oracle.com/javase/6/docs/api/java/util/ArrayList.html)`<`[`Index`](../-index/index.md)`>` |

### Functions

| Name | Summary |
|---|---|
| [createStatement](create-statement.md) | `fun createStatement(): Nothing` |
| [dropStatement](drop-statement.md) | `fun dropStatement(): Nothing` |
| [equals](equals.md) | `fun equals(other: Any?): Boolean` |
| [get](get.md) | `operator fun <T> get(original: `[`Column`](../-column/index.md)`<T>): `[`Column`](../-column/index.md)`<T>` |
| [hashCode](hash-code.md) | `fun hashCode(): Int` |
| [modifyStatement](modify-statement.md) | `fun modifyStatement(): Nothing` |

### Inherited Functions

| Name | Summary |
|---|---|
| [autoIncrement](../-table/auto-increment.md) | `fun <N : Any> `[`Column`](../-column/index.md)`<N>.autoIncrement(idSeqName: String? = null): `[`Column`](../-column/index.md)`<N>` |
| [autoinc](../-table/autoinc.md) | `fun <N : Comparable<N>> `[`Column`](../-column/index.md)`<`[`EntityID`](../../org.jetbrains.exposed.dao/-entity-i-d/index.md)`<N>>.autoinc(idSeqName: String? = null): `[`Column`](../-column/index.md)`<`[`EntityID`](../../org.jetbrains.exposed.dao/-entity-i-d/index.md)`<N>>` |
| [binary](../-table/binary.md) | `fun binary(name: String, length: Int): `[`Column`](../-column/index.md)`<ByteArray>`<br>A binary column to store an array of bytes. |
| [blob](../-table/blob.md) | `fun blob(name: String): `[`Column`](../-column/index.md)`<`[`Blob`](http://docs.oracle.com/javase/6/docs/api/java/sql/Blob.html)`>`<br>A blob column to store a large amount of binary data. |
| [bool](../-table/bool.md) | `fun bool(name: String): `[`Column`](../-column/index.md)`<Boolean>`<br>A bool column to store a boolean value. |
| [char](../-table/char.md) | `fun char(name: String): `[`Column`](../-column/index.md)`<Char>`<br>A char column to store a single character. |
| [check](../-table/check.md) | `fun <T> `[`Column`](../-column/index.md)`<T>.check(name: String = "", op: (`[`SqlExpressionBuilder`](../-sql-expression-builder/index.md)`, `[`Column`](../-column/index.md)`<T>) -> `[`Op`](../-op/index.md)`<Boolean>): <ERROR CLASS>`<br>Creates a check constraint in this column.`fun check(name: String = "", op: (`[`SqlExpressionBuilder`](../-sql-expression-builder/index.md)`) -> `[`Op`](../-op/index.md)`<Boolean>): Unit`<br>Creates a check constraint in this table. |
| [clientDefault](../-table/client-default.md) | `fun <T : Any> `[`Column`](../-column/index.md)`<T>.clientDefault(defaultValue: () -> T): `[`Column`](../-column/index.md)`<T>` |
| [crossJoin](../-table/cross-join.md) | `open infix fun crossJoin(otherTable: `[`ColumnSet`](../-column-set/index.md)`): `[`Join`](../-join/index.md) |
| [date](../-table/date.md) | `fun date(name: String): `[`Column`](../-column/index.md)`<<ERROR CLASS>>`<br>A date column to store a date. |
| [datetime](../-table/datetime.md) | `fun datetime(name: String): `[`Column`](../-column/index.md)`<<ERROR CLASS>>`<br>A datetime column to store both a date and a time. |
| [decimal](../-table/decimal.md) | `fun decimal(name: String, precision: Int, scale: Int): `[`Column`](../-column/index.md)`<`[`BigDecimal`](http://docs.oracle.com/javase/6/docs/api/java/math/BigDecimal.html)`>`<br>A decimal column to store a decimal number with a set [precision](../-table/decimal.md#org.jetbrains.exposed.sql.Table$decimal(kotlin.String, kotlin.Int, kotlin.Int)/precision) and [scale](../-table/decimal.md#org.jetbrains.exposed.sql.Table$decimal(kotlin.String, kotlin.Int, kotlin.Int)/scale). |
| [default](../-table/default.md) | `fun <T : Any> `[`Column`](../-column/index.md)`<T>.default(defaultValue: T): `[`Column`](../-column/index.md)`<T>` |
| [defaultExpression](../-table/default-expression.md) | `fun <T : Any> `[`Column`](../-column/index.md)`<T>.defaultExpression(defaultValue: `[`Expression`](../-expression/index.md)`<T>): `[`Column`](../-column/index.md)`<T>` |
| [describe](../-table/describe.md) | `open fun describe(s: `[`Transaction`](../-transaction/index.md)`): String` |
| [entityId](../-table/entity-id.md) | `fun <T : Comparable<T>> `[`Column`](../-column/index.md)`<T>.entityId(): `[`Column`](../-column/index.md)`<`[`EntityID`](../../org.jetbrains.exposed.dao/-entity-i-d/index.md)`<T>>`<br>`fun <ID : Comparable<ID>> entityId(name: String, table: `[`IdTable`](../../org.jetbrains.exposed.dao/-id-table/index.md)`<ID>): `[`Column`](../-column/index.md)`<`[`EntityID`](../../org.jetbrains.exposed.dao/-entity-i-d/index.md)`<ID>>` |
| [enumeration](../-table/enumeration.md) | `fun <T : Enum<T>> enumeration(name: String, klass: `[`Class`](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)`<T>): `[`Column`](../-column/index.md)`<T>`<br>An enumeration column where enumerations are stored by their ordinal integer. |
| [enumerationByName](../-table/enumeration-by-name.md) | `fun <T : Enum<T>> enumerationByName(name: String, length: Int, klass: `[`Class`](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)`<T>): `[`Column`](../-column/index.md)`<T>`<br>An enumeration column where enumerations are stored by their name. |
| [float](../-table/float.md) | `fun float(name: String): `[`Column`](../-column/index.md)`<Float>`<br>A float column to store a float number |
| [index](../-table/--index--.md) | `fun index(isUnique: Boolean = false, vararg columns: `[`Column`](../-column/index.md)`<*>): Unit`<br>`fun index(customIndexName: String? = null, isUnique: Boolean = false, vararg columns: `[`Column`](../-column/index.md)`<*>): Unit`<br>`fun <T> `[`Column`](../-column/index.md)`<T>.index(customIndexName: String? = null, isUnique: Boolean = false): `[`Column`](../-column/index.md)`<T>` |
| [innerJoin](../-table/inner-join.md) | `open infix fun innerJoin(otherTable: `[`ColumnSet`](../-column-set/index.md)`): `[`Join`](../-join/index.md) |
| [integer](../-table/integer.md) | `fun integer(name: String): `[`Column`](../-column/index.md)`<Int>`<br>An integer column to store an integer number. |
| [join](../-table/join.md) | `open fun join(otherTable: `[`ColumnSet`](../-column-set/index.md)`, joinType: `[`JoinType`](../-join-type/index.md)`, onColumn: `[`Expression`](../-expression/index.md)`<*>?, otherColumn: `[`Expression`](../-expression/index.md)`<*>?, additionalConstraint: (`[`SqlExpressionBuilder`](../-sql-expression-builder/index.md)`) -> `[`Op`](../-op/index.md)`<Boolean>): `[`Join`](../-join/index.md)<br>`infix fun ~~join~~(otherTable: `[`ColumnSet`](../-column-set/index.md)`): `[`Join`](../-join/index.md) |
| [leftJoin](../-table/left-join.md) | `open infix fun leftJoin(otherTable: `[`ColumnSet`](../-column-set/index.md)`): `[`Join`](../-join/index.md) |
| [long](../-table/long.md) | `fun long(name: String): `[`Column`](../-column/index.md)`<Long>`<br>A long column to store a large (long) number. |
| [nameInDatabaseCase](../-table/name-in-database-case.md) | `fun nameInDatabaseCase(): String` |
| [nullable](../-table/nullable.md) | `fun <T : Any> `[`Column`](../-column/index.md)`<T>.nullable(): `[`Column`](../-column/index.md)`<T?>` |
| [optReference](../-table/opt-reference.md) | `fun <T : Comparable<T>> optReference(name: String, foreign: `[`IdTable`](../../org.jetbrains.exposed.dao/-id-table/index.md)`<T>, onDelete: `[`ReferenceOption`](../-reference-option/index.md)`? = null): `[`Column`](../-column/index.md)`<`[`EntityID`](../../org.jetbrains.exposed.dao/-entity-i-d/index.md)`<T>?>` |
| [primaryKey](../-table/primary-key.md) | `fun <T> `[`Column`](../-column/index.md)`<T>.primaryKey(indx: Int? = null): `[`Column`](../-column/index.md)`<T>` |
| [reference](../-table/reference.md) | `fun <T : Comparable<T>> reference(name: String, foreign: `[`IdTable`](../../org.jetbrains.exposed.dao/-id-table/index.md)`<T>, onDelete: `[`ReferenceOption`](../-reference-option/index.md)`? = null): `[`Column`](../-column/index.md)`<`[`EntityID`](../../org.jetbrains.exposed.dao/-entity-i-d/index.md)`<T>>`<br>`fun <T> `[`Table`](../-table/index.md)`.reference(name: String, pkColumn: `[`Column`](../-column/index.md)`<T>): `[`Column`](../-column/index.md)`<T>` |
| [references](../-table/references.md) | `fun <T, S : T, C : `[`Column`](../-column/index.md)`<S>> C.references(ref: `[`Column`](../-column/index.md)`<T>, onDelete: `[`ReferenceOption`](../-reference-option/index.md)`?): C`<br>`infix fun <T, S : T, C : `[`Column`](../-column/index.md)`<S>> C.references(ref: `[`Column`](../-column/index.md)`<T>): C` |
| [registerColumn](../-table/register-column.md) | `fun <T> registerColumn(name: String, type: `[`IColumnType`](../-i-column-type/index.md)`): `[`Column`](../-column/index.md)`<T>` |
| [replaceColumn](../-table/replace-column.md) | `fun <TColumn : `[`Column`](../-column/index.md)`<*>> replaceColumn(oldColumn: `[`Column`](../-column/index.md)`<*>, newColumn: TColumn): TColumn` |
| [text](../-table/text.md) | `fun text(name: String, collate: String? = null): `[`Column`](../-column/index.md)`<String>`<br>A text column to store a large amount of text. |
| [uniqueIndex](../-table/unique-index.md) | `fun <T> `[`Column`](../-column/index.md)`<T>.uniqueIndex(customIndexName: String? = null): `[`Column`](../-column/index.md)`<T>`<br>`fun uniqueIndex(vararg columns: `[`Column`](../-column/index.md)`<*>): Unit`<br>`fun uniqueIndex(customIndexName: String? = null, vararg columns: `[`Column`](../-column/index.md)`<*>): Unit` |
| [uuid](../-table/uuid.md) | `fun uuid(name: String): `[`Column`](../-column/index.md)`<`[`UUID`](http://docs.oracle.com/javase/6/docs/api/java/util/UUID.html)`>`<br>A uuid column to store a UUID. |
| [varchar](../-table/varchar.md) | `fun varchar(name: String, length: Int, collate: String? = null): `[`Column`](../-column/index.md)`<String>`<br>A varchar column to store a string with a set maximum amount of characters. |

### Extension Functions

| Name | Summary |
|---|---|
| [deleteAll](../delete-all.md) | `fun `[`Table`](../-table/index.md)`.deleteAll(): Int`

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DMLTests.testDelete01<br>```
<br> |
| [deleteIgnoreWhere](../delete-ignore-where.md) | `fun `[`Table`](../-table/index.md)`.deleteIgnoreWhere(limit: Int? = null, offset: Int? = null, op: (`[`SqlExpressionBuilder`](../-sql-expression-builder/index.md)`) -> `[`Op`](../-op/index.md)`<Boolean>): Int` |
| [deleteWhere](../delete-where.md) | `fun `[`Table`](../-table/index.md)`.deleteWhere(limit: Int? = null, offset: Int? = null, op: (`[`SqlExpressionBuilder`](../-sql-expression-builder/index.md)`) -> `[`Op`](../-op/index.md)`<Boolean>): Int`

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DMLTests.testDelete01<br>```
<br> |
| [exists](../exists.md) | `fun `[`Table`](../-table/index.md)`.exists(): Boolean`

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DDLTests.tableExists02<br>```
<br> |
| [joinQuery](../join-query.md) | `fun `[`Table`](../-table/index.md)`.joinQuery(on: (`[`SqlExpressionBuilder`](../-sql-expression-builder/index.md)`, `[`QueryAlias`](../-query-alias/index.md)`) -> `[`Op`](../-op/index.md)`<Boolean>, joinType: `[`JoinType`](../-join-type/index.md)` = JoinType.INNER, joinPart: () -> `[`Query`](../-query/index.md)`): `[`Join`](../-join/index.md) |
| [select](../select.md) | `fun `[`FieldSet`](../-field-set/index.md)`.select(where: (`[`SqlExpressionBuilder`](../-sql-expression-builder/index.md)`) -> `[`Op`](../-op/index.md)`<Boolean>): `[`Query`](../-query/index.md)

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DMLTests.testSelect01<br>```
<br>`fun `[`FieldSet`](../-field-set/index.md)`.select(where: `[`Op`](../-op/index.md)`<Boolean>): `[`Query`](../-query/index.md) |
| [selectAll](../select-all.md) | `fun `[`FieldSet`](../-field-set/index.md)`.selectAll(): `[`Query`](../-query/index.md)

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DMLTests.testSelectDistinct<br>```
<br> |
| [targetTables](../target-tables.md) | `fun `[`ColumnSet`](../-column-set/index.md)`.targetTables(): List<`[`Table`](../-table/index.md)`>` |
