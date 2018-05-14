[org.jetbrains.exposed.dao](../index.md) / [IdTable](.)

# IdTable

`abstract class IdTable<T : Comparable<T>> : `[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `IdTable(name: String = "")` |

### Properties

| Name | Summary |
|---|---|
| [id](id.md) | `abstract val id: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<`[`EntityID`](../-entity-i-d/index.md)`<T>>` |

### Inherited Properties

| Name | Summary |
|---|---|
| [autoIncColumn](../../org.jetbrains.exposed.sql/-table/auto-inc-column.md) | `val autoIncColumn: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<*>?` |
| [checkConstraints](../../org.jetbrains.exposed.sql/-table/check-constraints.md) | `val checkConstraints: `[`ArrayList`](http://docs.oracle.com/javase/6/docs/api/java/util/ArrayList.html)`<<ERROR CLASS><String, `[`Op`](../../org.jetbrains.exposed.sql/-op/index.md)`<Boolean>>>` |
| [columns](../../org.jetbrains.exposed.sql/-table/columns.md) | `open val columns: List<`[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<*>>` |
| [ddl](../../org.jetbrains.exposed.sql/-table/ddl.md) | `val ddl: List<String>` |
| [fields](../../org.jetbrains.exposed.sql/-table/fields.md) | `open val fields: List<`[`Expression`](../../org.jetbrains.exposed.sql/-expression/index.md)`<*>>` |
| [indices](../../org.jetbrains.exposed.sql/-table/indices.md) | `val indices: `[`ArrayList`](http://docs.oracle.com/javase/6/docs/api/java/util/ArrayList.html)`<`[`Index`](../../org.jetbrains.exposed.sql/-index/index.md)`>` |
| [tableName](../../org.jetbrains.exposed.sql/-table/table-name.md) | `open val tableName: String` |

### Inherited Functions

| Name | Summary |
|---|---|
| [autoIncrement](../../org.jetbrains.exposed.sql/-table/auto-increment.md) | `fun <N : Any> `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<N>.autoIncrement(idSeqName: String? = null): `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<N>` |
| [autoinc](../../org.jetbrains.exposed.sql/-table/autoinc.md) | `fun <N : Comparable<N>> `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<`[`EntityID`](../-entity-i-d/index.md)`<N>>.autoinc(idSeqName: String? = null): `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<`[`EntityID`](../-entity-i-d/index.md)`<N>>` |
| [binary](../../org.jetbrains.exposed.sql/-table/binary.md) | `fun binary(name: String, length: Int): `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<ByteArray>`<br>A binary column to store an array of bytes. |
| [blob](../../org.jetbrains.exposed.sql/-table/blob.md) | `fun blob(name: String): `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<`[`Blob`](http://docs.oracle.com/javase/6/docs/api/java/sql/Blob.html)`>`<br>A blob column to store a large amount of binary data. |
| [bool](../../org.jetbrains.exposed.sql/-table/bool.md) | `fun bool(name: String): `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<Boolean>`<br>A bool column to store a boolean value. |
| [char](../../org.jetbrains.exposed.sql/-table/char.md) | `fun char(name: String): `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<Char>`<br>A char column to store a single character. |
| [check](../../org.jetbrains.exposed.sql/-table/check.md) | `fun <T> `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<T>.check(name: String = "", op: (`[`SqlExpressionBuilder`](../../org.jetbrains.exposed.sql/-sql-expression-builder/index.md)`, `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<T>) -> `[`Op`](../../org.jetbrains.exposed.sql/-op/index.md)`<Boolean>): <ERROR CLASS>`<br>Creates a check constraint in this column.`fun check(name: String = "", op: (`[`SqlExpressionBuilder`](../../org.jetbrains.exposed.sql/-sql-expression-builder/index.md)`) -> `[`Op`](../../org.jetbrains.exposed.sql/-op/index.md)`<Boolean>): Unit`<br>Creates a check constraint in this table. |
| [clientDefault](../../org.jetbrains.exposed.sql/-table/client-default.md) | `fun <T : Any> `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<T>.clientDefault(defaultValue: () -> T): `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<T>` |
| [createStatement](../../org.jetbrains.exposed.sql/-table/create-statement.md) | `open fun createStatement(): List<String>` |
| [crossJoin](../../org.jetbrains.exposed.sql/-table/cross-join.md) | `open infix fun crossJoin(otherTable: `[`ColumnSet`](../../org.jetbrains.exposed.sql/-column-set/index.md)`): `[`Join`](../../org.jetbrains.exposed.sql/-join/index.md) |
| [date](../../org.jetbrains.exposed.sql/-table/date.md) | `fun date(name: String): `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<<ERROR CLASS>>`<br>A date column to store a date. |
| [datetime](../../org.jetbrains.exposed.sql/-table/datetime.md) | `fun datetime(name: String): `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<<ERROR CLASS>>`<br>A datetime column to store both a date and a time. |
| [decimal](../../org.jetbrains.exposed.sql/-table/decimal.md) | `fun decimal(name: String, precision: Int, scale: Int): `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<`[`BigDecimal`](http://docs.oracle.com/javase/6/docs/api/java/math/BigDecimal.html)`>`<br>A decimal column to store a decimal number with a set [precision](../../org.jetbrains.exposed.sql/-table/decimal.md#org.jetbrains.exposed.sql.Table$decimal(kotlin.String, kotlin.Int, kotlin.Int)/precision) and [scale](../../org.jetbrains.exposed.sql/-table/decimal.md#org.jetbrains.exposed.sql.Table$decimal(kotlin.String, kotlin.Int, kotlin.Int)/scale). |
| [default](../../org.jetbrains.exposed.sql/-table/default.md) | `fun <T : Any> `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<T>.default(defaultValue: T): `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<T>` |
| [defaultExpression](../../org.jetbrains.exposed.sql/-table/default-expression.md) | `fun <T : Any> `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<T>.defaultExpression(defaultValue: `[`Expression`](../../org.jetbrains.exposed.sql/-expression/index.md)`<T>): `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<T>` |
| [describe](../../org.jetbrains.exposed.sql/-table/describe.md) | `open fun describe(s: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`): String` |
| [dropStatement](../../org.jetbrains.exposed.sql/-table/drop-statement.md) | `open fun dropStatement(): List<String>` |
| [entityId](../../org.jetbrains.exposed.sql/-table/entity-id.md) | `fun <T : Comparable<T>> `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<T>.entityId(): `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<`[`EntityID`](../-entity-i-d/index.md)`<T>>`<br>`fun <ID : Comparable<ID>> entityId(name: String, table: IdTable<ID>): `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<`[`EntityID`](../-entity-i-d/index.md)`<ID>>` |
| [enumeration](../../org.jetbrains.exposed.sql/-table/enumeration.md) | `fun <T : Enum<T>> enumeration(name: String, klass: `[`Class`](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)`<T>): `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<T>`<br>An enumeration column where enumerations are stored by their ordinal integer. |
| [enumerationByName](../../org.jetbrains.exposed.sql/-table/enumeration-by-name.md) | `fun <T : Enum<T>> enumerationByName(name: String, length: Int, klass: `[`Class`](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)`<T>): `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<T>`<br>An enumeration column where enumerations are stored by their name. |
| [equals](../../org.jetbrains.exposed.sql/-table/equals.md) | `open fun equals(other: Any?): Boolean` |
| [float](../../org.jetbrains.exposed.sql/-table/float.md) | `fun float(name: String): `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<Float>`<br>A float column to store a float number |
| [hashCode](../../org.jetbrains.exposed.sql/-table/hash-code.md) | `open fun hashCode(): Int` |
| [index](../../org.jetbrains.exposed.sql/-table/--index--.md) | `fun index(isUnique: Boolean = false, vararg columns: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<*>): Unit`<br>`fun index(customIndexName: String? = null, isUnique: Boolean = false, vararg columns: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<*>): Unit`<br>`fun <T> `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<T>.index(customIndexName: String? = null, isUnique: Boolean = false): `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<T>` |
| [innerJoin](../../org.jetbrains.exposed.sql/-table/inner-join.md) | `open infix fun innerJoin(otherTable: `[`ColumnSet`](../../org.jetbrains.exposed.sql/-column-set/index.md)`): `[`Join`](../../org.jetbrains.exposed.sql/-join/index.md) |
| [integer](../../org.jetbrains.exposed.sql/-table/integer.md) | `fun integer(name: String): `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<Int>`<br>An integer column to store an integer number. |
| [join](../../org.jetbrains.exposed.sql/-table/join.md) | `open fun join(otherTable: `[`ColumnSet`](../../org.jetbrains.exposed.sql/-column-set/index.md)`, joinType: `[`JoinType`](../../org.jetbrains.exposed.sql/-join-type/index.md)`, onColumn: `[`Expression`](../../org.jetbrains.exposed.sql/-expression/index.md)`<*>?, otherColumn: `[`Expression`](../../org.jetbrains.exposed.sql/-expression/index.md)`<*>?, additionalConstraint: (`[`SqlExpressionBuilder`](../../org.jetbrains.exposed.sql/-sql-expression-builder/index.md)`) -> `[`Op`](../../org.jetbrains.exposed.sql/-op/index.md)`<Boolean>): `[`Join`](../../org.jetbrains.exposed.sql/-join/index.md)<br>`infix fun ~~join~~(otherTable: `[`ColumnSet`](../../org.jetbrains.exposed.sql/-column-set/index.md)`): `[`Join`](../../org.jetbrains.exposed.sql/-join/index.md) |
| [leftJoin](../../org.jetbrains.exposed.sql/-table/left-join.md) | `open infix fun leftJoin(otherTable: `[`ColumnSet`](../../org.jetbrains.exposed.sql/-column-set/index.md)`): `[`Join`](../../org.jetbrains.exposed.sql/-join/index.md) |
| [long](../../org.jetbrains.exposed.sql/-table/long.md) | `fun long(name: String): `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<Long>`<br>A long column to store a large (long) number. |
| [modifyStatement](../../org.jetbrains.exposed.sql/-table/modify-statement.md) | `open fun modifyStatement(): Nothing` |
| [nameInDatabaseCase](../../org.jetbrains.exposed.sql/-table/name-in-database-case.md) | `fun nameInDatabaseCase(): String` |
| [nullable](../../org.jetbrains.exposed.sql/-table/nullable.md) | `fun <T : Any> `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<T>.nullable(): `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<T?>` |
| [optReference](../../org.jetbrains.exposed.sql/-table/opt-reference.md) | `fun <T : Comparable<T>> optReference(name: String, foreign: IdTable<T>, onDelete: `[`ReferenceOption`](../../org.jetbrains.exposed.sql/-reference-option/index.md)`? = null): `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<`[`EntityID`](../-entity-i-d/index.md)`<T>?>` |
| [primaryKey](../../org.jetbrains.exposed.sql/-table/primary-key.md) | `fun <T> `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<T>.primaryKey(indx: Int? = null): `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<T>` |
| [reference](../../org.jetbrains.exposed.sql/-table/reference.md) | `fun <T : Comparable<T>> reference(name: String, foreign: IdTable<T>, onDelete: `[`ReferenceOption`](../../org.jetbrains.exposed.sql/-reference-option/index.md)`? = null): `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<`[`EntityID`](../-entity-i-d/index.md)`<T>>`<br>`fun <T> `[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`.reference(name: String, pkColumn: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<T>): `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<T>` |
| [references](../../org.jetbrains.exposed.sql/-table/references.md) | `fun <T, S : T, C : `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<S>> C.references(ref: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<T>, onDelete: `[`ReferenceOption`](../../org.jetbrains.exposed.sql/-reference-option/index.md)`?): C`<br>`infix fun <T, S : T, C : `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<S>> C.references(ref: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<T>): C` |
| [registerColumn](../../org.jetbrains.exposed.sql/-table/register-column.md) | `fun <T> registerColumn(name: String, type: `[`IColumnType`](../../org.jetbrains.exposed.sql/-i-column-type/index.md)`): `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<T>` |
| [replaceColumn](../../org.jetbrains.exposed.sql/-table/replace-column.md) | `fun <TColumn : `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<*>> replaceColumn(oldColumn: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<*>, newColumn: TColumn): TColumn` |
| [text](../../org.jetbrains.exposed.sql/-table/text.md) | `fun text(name: String, collate: String? = null): `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<String>`<br>A text column to store a large amount of text. |
| [uniqueIndex](../../org.jetbrains.exposed.sql/-table/unique-index.md) | `fun <T> `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<T>.uniqueIndex(customIndexName: String? = null): `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<T>`<br>`fun uniqueIndex(vararg columns: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<*>): Unit`<br>`fun uniqueIndex(customIndexName: String? = null, vararg columns: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<*>): Unit` |
| [uuid](../../org.jetbrains.exposed.sql/-table/uuid.md) | `fun uuid(name: String): `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<`[`UUID`](http://docs.oracle.com/javase/6/docs/api/java/util/UUID.html)`>`<br>A uuid column to store a UUID. |
| [varchar](../../org.jetbrains.exposed.sql/-table/varchar.md) | `fun varchar(name: String, length: Int, collate: String? = null): `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<String>`<br>A varchar column to store a string with a set maximum amount of characters. |

### Extension Functions

| Name | Summary |
|---|---|
| [deleteAll](../../org.jetbrains.exposed.sql/delete-all.md) | `fun `[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`.deleteAll(): Int`

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DMLTests.testDelete01<br>```
<br> |
| [deleteIgnoreWhere](../../org.jetbrains.exposed.sql/delete-ignore-where.md) | `fun `[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`.deleteIgnoreWhere(limit: Int? = null, offset: Int? = null, op: (`[`SqlExpressionBuilder`](../../org.jetbrains.exposed.sql/-sql-expression-builder/index.md)`) -> `[`Op`](../../org.jetbrains.exposed.sql/-op/index.md)`<Boolean>): Int` |
| [deleteWhere](../../org.jetbrains.exposed.sql/delete-where.md) | `fun `[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`.deleteWhere(limit: Int? = null, offset: Int? = null, op: (`[`SqlExpressionBuilder`](../../org.jetbrains.exposed.sql/-sql-expression-builder/index.md)`) -> `[`Op`](../../org.jetbrains.exposed.sql/-op/index.md)`<Boolean>): Int`

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DMLTests.testDelete01<br>```
<br> |
| [exists](../../org.jetbrains.exposed.sql/exists.md) | `fun `[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`.exists(): Boolean`

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DDLTests.tableExists02<br>```
<br> |
| [insertAndGetId](../../org.jetbrains.exposed.sql/insert-and-get-id.md) | `fun <Key : Comparable<Key>, T : IdTable<Key>> T.insertAndGetId(body: (T, `[`InsertStatement`](../../org.jetbrains.exposed.sql.statements/-insert-statement/index.md)`<`[`EntityID`](../-entity-i-d/index.md)`<Key>>) -> Unit): <ERROR CLASS>`

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DMLTests.testGeneratedKey03<br>```
<br> |
| [insertIgnoreAndGetId](../../org.jetbrains.exposed.sql/insert-ignore-and-get-id.md) | `fun <Key : Comparable<Key>, T : IdTable<Key>> T.insertIgnoreAndGetId(body: (T, `[`UpdateBuilder`](../../org.jetbrains.exposed.sql.statements/-update-builder/index.md)`<*>) -> Unit): <ERROR CLASS>` |
| [joinQuery](../../org.jetbrains.exposed.sql/join-query.md) | `fun `[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`.joinQuery(on: (`[`SqlExpressionBuilder`](../../org.jetbrains.exposed.sql/-sql-expression-builder/index.md)`, `[`QueryAlias`](../../org.jetbrains.exposed.sql/-query-alias/index.md)`) -> `[`Op`](../../org.jetbrains.exposed.sql/-op/index.md)`<Boolean>, joinType: `[`JoinType`](../../org.jetbrains.exposed.sql/-join-type/index.md)` = JoinType.INNER, joinPart: () -> `[`Query`](../../org.jetbrains.exposed.sql/-query/index.md)`): `[`Join`](../../org.jetbrains.exposed.sql/-join/index.md) |
| [select](../../org.jetbrains.exposed.sql/select.md) | `fun `[`FieldSet`](../../org.jetbrains.exposed.sql/-field-set/index.md)`.select(where: (`[`SqlExpressionBuilder`](../../org.jetbrains.exposed.sql/-sql-expression-builder/index.md)`) -> `[`Op`](../../org.jetbrains.exposed.sql/-op/index.md)`<Boolean>): `[`Query`](../../org.jetbrains.exposed.sql/-query/index.md)

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DMLTests.testSelect01<br>```
<br>`fun `[`FieldSet`](../../org.jetbrains.exposed.sql/-field-set/index.md)`.select(where: `[`Op`](../../org.jetbrains.exposed.sql/-op/index.md)`<Boolean>): `[`Query`](../../org.jetbrains.exposed.sql/-query/index.md) |
| [selectAll](../../org.jetbrains.exposed.sql/select-all.md) | `fun `[`FieldSet`](../../org.jetbrains.exposed.sql/-field-set/index.md)`.selectAll(): `[`Query`](../../org.jetbrains.exposed.sql/-query/index.md)

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DMLTests.testSelectDistinct<br>```
<br> |
| [targetTables](../../org.jetbrains.exposed.sql/target-tables.md) | `fun `[`ColumnSet`](../../org.jetbrains.exposed.sql/-column-set/index.md)`.targetTables(): List<`[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`>` |

### Inheritors

| Name | Summary |
|---|---|
| [IntIdTable](../-int-id-table/index.md) | `open class IntIdTable : IdTable<Int>` |
| [LongIdTable](../-long-id-table/index.md) | `open class LongIdTable : IdTable<Long>` |
| [UUIDTable](../-u-u-i-d-table/index.md) | `open class UUIDTable : IdTable<`[`UUID`](http://docs.oracle.com/javase/6/docs/api/java/util/UUID.html)`>` |
