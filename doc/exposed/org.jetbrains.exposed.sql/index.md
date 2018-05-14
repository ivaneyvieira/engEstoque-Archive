[org.jetbrains.exposed.sql](.)

## Package org.jetbrains.exposed.sql

### Types

| Name | Summary |
|---|---|
| [Alias](-alias/index.md) | `class Alias<out T : `[`Table`](-table/index.md)`> : `[`Table`](-table/index.md) |
| [AndOp](-and-op/index.md) | `class AndOp : `[`Op`](-op/index.md)`<Boolean>` |
| [AutoIncColumnType](-auto-inc-column-type/index.md) | `class AutoIncColumnType : `[`IColumnType`](-i-column-type/index.md) |
| [Avg](-avg/index.md) | `class Avg<T : Comparable<T>, in S : T?> : `[`Function`](-function/index.md)`<`[`BigDecimal`](http://docs.oracle.com/javase/6/docs/api/java/math/BigDecimal.html)`?>` |
| [Between](-between/index.md) | `class Between : `[`Op`](-op/index.md)`<Boolean>` |
| [BinaryColumnType](-binary-column-type/index.md) | `class BinaryColumnType : `[`ColumnType`](-column-type/index.md) |
| [BlobColumnType](-blob-column-type/index.md) | `class BlobColumnType : `[`ColumnType`](-column-type/index.md) |
| [BooleanColumnType](-boolean-column-type/index.md) | `class BooleanColumnType : `[`ColumnType`](-column-type/index.md) |
| [Case](-case/index.md) | `class Case : Any` |
| [CaseWhen](-case-when/index.md) | `class CaseWhen<T> : Any` |
| [CaseWhenElse](-case-when-else/index.md) | `class CaseWhenElse<T, R : T> : `[`Expression`](-expression/index.md)`<R>` |
| [Cast](-cast/index.md) | `class Cast<T> : `[`Function`](-function/index.md)`<T?>` |
| [CharacterColumnType](-character-column-type/index.md) | `class CharacterColumnType : `[`ColumnType`](-column-type/index.md) |
| [CheckConstraint](-check-constraint/index.md) | `data class CheckConstraint : `[`DdlAware`](-ddl-aware/index.md) |
| [Coalesce](-coalesce/index.md) | `class Coalesce<out T, S : T?, R : T> : `[`Function`](-function/index.md)`<R>` |
| [Column](-column/index.md) | `class Column<T> : `[`ExpressionWithColumnType`](-expression-with-column-type/index.md)`<T>, `[`DdlAware`](-ddl-aware/index.md)`, Comparable<`[`Column`](-column/index.md)`<*>>` |
| [ColumnSet](-column-set/index.md) | `abstract class ColumnSet : `[`FieldSet`](-field-set/index.md) |
| [ColumnType](-column-type/index.md) | `abstract class ColumnType : `[`IColumnType`](-i-column-type/index.md) |
| [ComparisonOp](-comparison-op/index.md) | `abstract class ComparisonOp : `[`Op`](-op/index.md)`<Boolean>` |
| [CompositeSqlLogger](-composite-sql-logger/index.md) | `class CompositeSqlLogger : `[`SqlLogger`](-sql-logger/index.md)`, `[`StatementInterceptor`](../org.jetbrains.exposed.sql.statements/-statement-interceptor/index.md) |
| [Count](-count/index.md) | `class Count : `[`Function`](-function/index.md)`<Int>` |
| [CurrentDateTime](-current-date-time/index.md) | `class CurrentDateTime : `[`Function`](-function/index.md)`<<ERROR CLASS>>` |
| [Database](-database/index.md) | `class Database : Any` |
| [Date](-date/index.md) | `class Date<T> : `[`Function`](-function/index.md)`<<ERROR CLASS>>` |
| [DateColumnType](-date-column-type/index.md) | `class DateColumnType : `[`ColumnType`](-column-type/index.md) |
| [DdlAware](-ddl-aware/index.md) | `interface DdlAware : Any` |
| [DecimalColumnType](-decimal-column-type/index.md) | `class DecimalColumnType : `[`ColumnType`](-column-type/index.md) |
| [DivideOp](-divide-op/index.md) | `class DivideOp<T, S : T> : `[`ExpressionWithColumnType`](-expression-with-column-type/index.md)`<T>` |
| [EmptySizedIterable](-empty-sized-iterable/index.md) | `class EmptySizedIterable<out T> : `[`SizedIterable`](-sized-iterable/index.md)`<T>, Iterator<T>` |
| [EntityIDColumnType](-entity-i-d-column-type/index.md) | `class EntityIDColumnType<T : Comparable<T>> : `[`ColumnType`](-column-type/index.md) |
| [EnumerationColumnType](-enumeration-column-type/index.md) | `class EnumerationColumnType<T : Enum<T>> : `[`ColumnType`](-column-type/index.md) |
| [EnumerationNameColumnType](-enumeration-name-column-type/index.md) | `class EnumerationNameColumnType<T : Enum<T>> : `[`VarCharColumnType`](-var-char-column-type/index.md) |
| [EqOp](-eq-op/index.md) | `class EqOp : `[`ComparisonOp`](-comparison-op/index.md) |
| [Expression](-expression/index.md) | `abstract class Expression<T> : Any` |
| [ExpressionAlias](-expression-alias/index.md) | `class ExpressionAlias<T> : `[`Expression`](-expression/index.md)`<T>` |
| [ExpressionWithColumnType](-expression-with-column-type/index.md) | `abstract class ExpressionWithColumnType<T> : `[`Expression`](-expression/index.md)`<T>` |
| [FieldSet](-field-set/index.md) | `interface FieldSet : Any` |
| [FloatColumnType](-float-column-type/index.md) | `class FloatColumnType : `[`ColumnType`](-column-type/index.md) |
| [ForeignKeyConstraint](-foreign-key-constraint/index.md) | `data class ForeignKeyConstraint : `[`DdlAware`](-ddl-aware/index.md) |
| [Function](-function/index.md) | `abstract class Function<T> : `[`ExpressionWithColumnType`](-expression-with-column-type/index.md)`<T>` |
| [GreaterEqOp](-greater-eq-op/index.md) | `class GreaterEqOp : `[`ComparisonOp`](-comparison-op/index.md) |
| [GreaterOp](-greater-op/index.md) | `class GreaterOp : `[`ComparisonOp`](-comparison-op/index.md) |
| [GroupConcat](-group-concat/index.md) | `class GroupConcat : `[`Function`](-function/index.md)`<String?>` |
| [IColumnType](-i-column-type/index.md) | `interface IColumnType : Any` |
| [InListOrNotInListOp](-in-list-or-not-in-list-op/index.md) | `class InListOrNotInListOp<T> : `[`Op`](-op/index.md)`<Boolean>` |
| [Index](-index/index.md) | `data class Index : `[`DdlAware`](-ddl-aware/index.md) |
| [IntegerColumnType](-integer-column-type/index.md) | `class IntegerColumnType : `[`ColumnType`](-column-type/index.md) |
| [IsNotNullOp](-is-not-null-op/index.md) | `class IsNotNullOp : `[`Op`](-op/index.md)`<Boolean>` |
| [IsNullOp](-is-null-op/index.md) | `class IsNullOp : `[`Op`](-op/index.md)`<Boolean>` |
| [Join](-join/index.md) | `class Join : `[`ColumnSet`](-column-set/index.md) |
| [JoinType](-join-type/index.md) | `enum class JoinType : Enum<`[`JoinType`](-join-type/index.md)`>` |
| [Key](-key/index.md) | `class Key<T> : Any` |
| [LazySizedCollection](-lazy-sized-collection/index.md) | `class LazySizedCollection<out T> : `[`SizedIterable`](-sized-iterable/index.md)`<T>` |
| [LessEqOp](-less-eq-op/index.md) | `class LessEqOp : `[`ComparisonOp`](-comparison-op/index.md) |
| [LessOp](-less-op/index.md) | `class LessOp : `[`ComparisonOp`](-comparison-op/index.md) |
| [LikeOp](-like-op/index.md) | `class LikeOp : `[`ComparisonOp`](-comparison-op/index.md) |
| [LiteralOp](-literal-op/index.md) | `class LiteralOp<T> : `[`ExpressionWithColumnType`](-expression-with-column-type/index.md)`<T>` |
| [LongColumnType](-long-column-type/index.md) | `class LongColumnType : `[`ColumnType`](-column-type/index.md) |
| [LowerCase](-lower-case/index.md) | `class LowerCase<T : String?> : `[`Function`](-function/index.md)`<T>` |
| [Max](-max/index.md) | `class Max<T : Comparable<T>, in S : T?> : `[`Function`](-function/index.md)`<T?>` |
| [Min](-min/index.md) | `class Min<T : Comparable<T>, in S : T?> : `[`Function`](-function/index.md)`<T?>` |
| [MinusOp](-minus-op/index.md) | `class MinusOp<T, S : T> : `[`ExpressionWithColumnType`](-expression-with-column-type/index.md)`<T>` |
| [Month](-month/index.md) | `class Month<T> : `[`Function`](-function/index.md)`<Int>` |
| [NeqOp](-neq-op/index.md) | `class NeqOp : `[`ComparisonOp`](-comparison-op/index.md) |
| [NoOpConversion](-no-op-conversion/index.md) | `class NoOpConversion<T, S> : `[`ExpressionWithColumnType`](-expression-with-column-type/index.md)`<S>` |
| [NotLikeOp](-not-like-op/index.md) | `class NotLikeOp : `[`ComparisonOp`](-comparison-op/index.md) |
| [NotOp](-not-op/index.md) | `class NotOp<T> : `[`Op`](-op/index.md)`<Boolean>` |
| [NotRegexpOp](-not-regexp-op/index.md) | `class NotRegexpOp : `[`ComparisonOp`](-comparison-op/index.md) |
| [Op](-op/index.md) | `abstract class Op<T> : `[`Expression`](-expression/index.md)`<T>` |
| [OrOp](-or-op/index.md) | `class OrOp<T> : `[`Op`](-op/index.md)`<Boolean>` |
| [PlusOp](-plus-op/index.md) | `class PlusOp<T, S : T> : `[`ExpressionWithColumnType`](-expression-with-column-type/index.md)`<T>` |
| [Query](-query/index.md) | `open class Query : `[`SizedIterable`](-sized-iterable/index.md)`<`[`ResultRow`](-result-row/index.md)`>, `[`Statement`](../org.jetbrains.exposed.sql.statements/-statement/index.md)`<`[`ResultSet`](http://docs.oracle.com/javase/6/docs/api/java/sql/ResultSet.html)`>` |
| [QueryAlias](-query-alias/index.md) | `class QueryAlias : `[`ColumnSet`](-column-set/index.md) |
| [QueryBuilder](-query-builder/index.md) | `class QueryBuilder : Any` |
| [QueryParameter](-query-parameter/index.md) | `class QueryParameter<T> : `[`Expression`](-expression/index.md)`<T>` |
| [Random](-random/index.md) | `class Random : `[`Function`](-function/index.md)`<`[`BigDecimal`](http://docs.oracle.com/javase/6/docs/api/java/math/BigDecimal.html)`>` |
| [ReferenceOption](-reference-option/index.md) | `enum class ReferenceOption : Enum<`[`ReferenceOption`](-reference-option/index.md)`>` |
| [RegexpOp](-regexp-op/index.md) | `class RegexpOp : `[`ComparisonOp`](-comparison-op/index.md) |
| [ResultRow](-result-row/index.md) | `class ResultRow : Any` |
| [SchemaUtils](-schema-utils/index.md) | `object SchemaUtils : Any` |
| [Seq](-seq/index.md) | `data class Seq : Any` |
| [SizedCollection](-sized-collection/index.md) | `class SizedCollection<out T> : `[`SizedIterable`](-sized-iterable/index.md)`<T>` |
| [SizedIterable](-sized-iterable/index.md) | `interface SizedIterable<out T> : Iterable<T>` |
| [Slf4jSqlDebugLogger](-slf4j-sql-debug-logger/index.md) | `object Slf4jSqlDebugLogger : `[`SqlLogger`](-sql-logger/index.md) |
| [Slice](-slice/index.md) | `class Slice : `[`FieldSet`](-field-set/index.md) |
| [SortOrder](-sort-order/index.md) | `enum class SortOrder : Enum<`[`SortOrder`](-sort-order/index.md)`>` |
| [SqlExpressionBuilder](-sql-expression-builder/index.md) | `object SqlExpressionBuilder : Any` |
| [SqlLogger](-sql-logger/index.md) | `interface SqlLogger : Any` |
| [StdDevPop](-std-dev-pop/index.md) | `class StdDevPop<T> : `[`Function`](-function/index.md)`<`[`BigDecimal`](http://docs.oracle.com/javase/6/docs/api/java/math/BigDecimal.html)`?>` |
| [StdDevSamp](-std-dev-samp/index.md) | `class StdDevSamp<T> : `[`Function`](-function/index.md)`<`[`BigDecimal`](http://docs.oracle.com/javase/6/docs/api/java/math/BigDecimal.html)`?>` |
| [StdOutSqlLogger](-std-out-sql-logger/index.md) | `object StdOutSqlLogger : `[`SqlLogger`](-sql-logger/index.md) |
| [StringColumnType](-string-column-type/index.md) | `abstract class StringColumnType : `[`ColumnType`](-column-type/index.md) |
| [Substring](-substring/index.md) | `class Substring<T : String?> : `[`Function`](-function/index.md)`<T>` |
| [Sum](-sum/index.md) | `class Sum<T> : `[`Function`](-function/index.md)`<T?>` |
| [Table](-table/index.md) | `open class Table : `[`ColumnSet`](-column-set/index.md)`, `[`DdlAware`](-ddl-aware/index.md) |
| [TextColumnType](-text-column-type/index.md) | `open class TextColumnType : `[`StringColumnType`](-string-column-type/index.md) |
| [TimesOp](-times-op/index.md) | `class TimesOp<T, S : T> : `[`ExpressionWithColumnType`](-expression-with-column-type/index.md)`<T>` |
| [Transaction](-transaction/index.md) | `open class Transaction : `[`UserDataHolder`](-user-data-holder/index.md)`, `[`TransactionInterface`](../org.jetbrains.exposed.sql.transactions/-transaction-interface/index.md) |
| [Trim](-trim/index.md) | `class Trim<T : String?> : `[`Function`](-function/index.md)`<T>` |
| [UUIDColumnType](-u-u-i-d-column-type/index.md) | `class UUIDColumnType : `[`ColumnType`](-column-type/index.md) |
| [UpperCase](-upper-case/index.md) | `class UpperCase<T : String?> : `[`Function`](-function/index.md)`<T>` |
| [UserDataHolder](-user-data-holder/index.md) | `open class UserDataHolder : Any` |
| [VarCharColumnType](-var-char-column-type/index.md) | `open class VarCharColumnType : `[`StringColumnType`](-string-column-type/index.md) |
| [VarPop](-var-pop/index.md) | `class VarPop<T> : `[`Function`](-function/index.md)`<`[`BigDecimal`](http://docs.oracle.com/javase/6/docs/api/java/math/BigDecimal.html)`?>` |
| [VarSamp](-var-samp/index.md) | `class VarSamp<T> : `[`Function`](-function/index.md)`<`[`BigDecimal`](http://docs.oracle.com/javase/6/docs/api/java/math/BigDecimal.html)`?>` |
| [exists](exists/index.md) | `class exists : `[`Op`](-op/index.md)`<Boolean>` |
| [notExists](not-exists/index.md) | `class notExists : `[`Op`](-op/index.md)`<Boolean>` |

### Type Aliases

| Name | Summary |
|---|---|
| [JoinCondition](-join-condition.md) | `typealias JoinCondition = <ERROR CLASS><`[`Expression`](-expression/index.md)`<*>, `[`Expression`](-expression/index.md)`<*>>` |

### Properties

| Name | Summary |
|---|---|
| [autoIncSeqName](auto-inc-seq-name.md) | `val `[`Column`](-column/index.md)`<*>.autoIncSeqName: String?` |
| [exposedLogger](exposed-logger.md) | `val exposedLogger: <ERROR CLASS>` |
| [isAutoInc](is-auto-inc.md) | `val `[`IColumnType`](-i-column-type/index.md)`.isAutoInc: Boolean` |
| [lastQueryAlias](last-query-alias.md) | `val `[`Join`](-join/index.md)`.lastQueryAlias: `[`QueryAlias`](-query-alias/index.md)`?` |
| [name](name.md) | `val `[`Database`](-database/index.md)`.name: String` |

### Functions

| Name | Summary |
|---|---|
| [alias](alias.md) | `fun <T : `[`Table`](-table/index.md)`> T.alias(alias: String): `[`Alias`](-alias/index.md)`<T>`<br>`fun <T : `[`Query`](-query/index.md)`> T.alias(alias: String): `[`QueryAlias`](-query-alias/index.md)<br>`fun <T> `[`Expression`](-expression/index.md)`<T>.alias(alias: String): `[`ExpressionAlias`](-expression-alias/index.md)`<T>` |
| [and](and.md) | `infix fun `[`Op`](-op/index.md)`<Boolean>.and(op: `[`Expression`](-expression/index.md)`<Boolean>): `[`Op`](-op/index.md)`<Boolean>` |
| [avg](avg.md) | `fun <T : Comparable<T>, S : T?> `[`ExpressionWithColumnType`](-expression-with-column-type/index.md)`<in S>.avg(scale: Int = 2): `[`ExpressionWithColumnType`](-expression-with-column-type/index.md)`<`[`BigDecimal`](http://docs.oracle.com/javase/6/docs/api/java/math/BigDecimal.html)`?>` |
| [batchInsert](batch-insert.md) | `fun <T : `[`Table`](-table/index.md)`, E : Any> T.batchInsert(data: Iterable<E>, ignore: Boolean = false, body: (`[`BatchInsertStatement`](../org.jetbrains.exposed.sql.statements/-batch-insert-statement/index.md)`, E) -> Unit): List<Map<`[`Column`](-column/index.md)`<*>, Any>>`

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DMLTests.testBatchInsert01<br>```
<br> |
| [booleanLiteral](boolean-literal.md) | `fun booleanLiteral(value: Boolean): `[`LiteralOp`](-literal-op/index.md)`<Boolean>` |
| [booleanParam](boolean-param.md) | `fun booleanParam(value: Boolean): `[`Expression`](-expression/index.md)`<Boolean>` |
| [castTo](cast-to.md) | `fun <R : Any> `[`Expression`](-expression/index.md)`<*>.castTo(columnType: `[`IColumnType`](-i-column-type/index.md)`): `[`Cast`](-cast/index.md)`<R>` |
| [checkExcessiveIndices](check-excessive-indices.md) | `fun checkExcessiveIndices(vararg tables: `[`Table`](-table/index.md)`): Unit` |
| [checkMappingConsistence](check-mapping-consistence.md) | `fun checkMappingConsistence(vararg tables: `[`Table`](-table/index.md)`): List<String>`<br>Log Exposed table mappings &lt;-&gt; real database mapping problems and returns DDL Statements to fix them |
| [count](count.md) | `fun `[`ExpressionWithColumnType`](-expression-with-column-type/index.md)`<*>.count(): `[`Function`](-function/index.md)`<Int>` |
| [countDistinct](count-distinct.md) | `fun `[`Column`](-column/index.md)`<*>.countDistinct(): `[`Function`](-function/index.md)`<Int>` |
| [date](date.md) | `fun <T> `[`Expression`](-expression/index.md)`<T>.date(): `[`Date`](-date/index.md)`<T>` |
| [dateLiteral](date-literal.md) | `fun dateLiteral(value: <ERROR CLASS>): `[`LiteralOp`](-literal-op/index.md)`<<ERROR CLASS>>` |
| [dateParam](date-param.md) | `fun dateParam(value: <ERROR CLASS>): `[`Expression`](-expression/index.md)`<<ERROR CLASS>>` |
| [dateTimeLiteral](date-time-literal.md) | `fun dateTimeLiteral(value: <ERROR CLASS>): `[`LiteralOp`](-literal-op/index.md)`<<ERROR CLASS>>` |
| [dateTimeParam](date-time-param.md) | `fun dateTimeParam(value: <ERROR CLASS>): `[`Expression`](-expression/index.md)`<<ERROR CLASS>>` |
| [deleteAll](delete-all.md) | `fun `[`Table`](-table/index.md)`.deleteAll(): Int`

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DMLTests.testDelete01<br>```
<br> |
| [deleteIgnoreWhere](delete-ignore-where.md) | `fun `[`Table`](-table/index.md)`.deleteIgnoreWhere(limit: Int? = null, offset: Int? = null, op: (`[`SqlExpressionBuilder`](-sql-expression-builder/index.md)`) -> `[`Op`](-op/index.md)`<Boolean>): Int` |
| [deleteWhere](delete-where.md) | `fun `[`Table`](-table/index.md)`.deleteWhere(limit: Int? = null, offset: Int? = null, op: (`[`SqlExpressionBuilder`](-sql-expression-builder/index.md)`) -> `[`Op`](-op/index.md)`<Boolean>): Int`

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DMLTests.testDelete01<br>```
<br> |
| [emptySized](empty-sized.md) | `fun <T> emptySized(): `[`SizedIterable`](-sized-iterable/index.md)`<T>` |
| [exists](exists.md) | `fun `[`Table`](-table/index.md)`.exists(): Boolean`

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DDLTests.tableExists02<br>```
<br> |
| [groupConcat](group-concat.md) | `fun <T> `[`Column`](-column/index.md)`<T>.groupConcat(separator: String? = null, distinct: Boolean = false, vararg orderBy: <ERROR CLASS><`[`Expression`](-expression/index.md)`<*>, Boolean>): `[`GroupConcat`](-group-concat/index.md) |
| [idParam](id-param.md) | `fun <T : Comparable<T>> idParam(value: `[`EntityID`](../org.jetbrains.exposed.dao/-entity-i-d/index.md)`<T>, column: `[`Column`](-column/index.md)`<`[`EntityID`](../org.jetbrains.exposed.dao/-entity-i-d/index.md)`<T>>): `[`Expression`](-expression/index.md)`<`[`EntityID`](../org.jetbrains.exposed.dao/-entity-i-d/index.md)`<T>>` |
| [innerJoin](inner-join.md) | `fun <C1 : `[`ColumnSet`](-column-set/index.md)`, C2 : `[`ColumnSet`](-column-set/index.md)`> C1.innerJoin(otherTable: C2, onColumn: (C1) -> `[`Expression`](-expression/index.md)`<*>, otherColumn: (C2) -> `[`Expression`](-expression/index.md)`<*>): `[`Join`](-join/index.md) |
| [insert](insert.md) | `fun <T : `[`Table`](-table/index.md)`> T.insert(body: (T, `[`InsertStatement`](../org.jetbrains.exposed.sql.statements/-insert-statement/index.md)`<Number>) -> Unit): `[`InsertStatement`](../org.jetbrains.exposed.sql.statements/-insert-statement/index.md)`<Number>`

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DMLTests.testInsert01<br>```
<br>`fun <T : `[`Table`](-table/index.md)`> T.insert(selectQuery: `[`Query`](-query/index.md)`, columns: List<`[`Column`](-column/index.md)`<*>> = this.columns.filterNot { it.columnType.isAutoInc }): Int?`

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DMLTests.testInsertSelect01<br>```
<br> |
| [insertAndGetId](insert-and-get-id.md) | `fun <Key : Comparable<Key>, T : `[`IdTable`](../org.jetbrains.exposed.dao/-id-table/index.md)`<Key>> T.insertAndGetId(body: (T, `[`InsertStatement`](../org.jetbrains.exposed.sql.statements/-insert-statement/index.md)`<`[`EntityID`](../org.jetbrains.exposed.dao/-entity-i-d/index.md)`<Key>>) -> Unit): <ERROR CLASS>`

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DMLTests.testGeneratedKey03<br>```
<br> |
| [insertIgnore](insert-ignore.md) | `fun <T : `[`Table`](-table/index.md)`> T.insertIgnore(body: (T, `[`UpdateBuilder`](../org.jetbrains.exposed.sql.statements/-update-builder/index.md)`<*>) -> Unit): `[`InsertStatement`](../org.jetbrains.exposed.sql.statements/-insert-statement/index.md)`<Long>`<br>`fun <T : `[`Table`](-table/index.md)`> T.insertIgnore(selectQuery: `[`Query`](-query/index.md)`, columns: List<`[`Column`](-column/index.md)`<*>> = this.columns.filterNot { it.columnType.isAutoInc }): Int?` |
| [insertIgnoreAndGetId](insert-ignore-and-get-id.md) | `fun <Key : Comparable<Key>, T : `[`IdTable`](../org.jetbrains.exposed.dao/-id-table/index.md)`<Key>> T.insertIgnoreAndGetId(body: (T, `[`UpdateBuilder`](../org.jetbrains.exposed.sql.statements/-update-builder/index.md)`<*>) -> Unit): <ERROR CLASS>` |
| [intLiteral](int-literal.md) | `fun intLiteral(value: Int): `[`LiteralOp`](-literal-op/index.md)`<Int>` |
| [intParam](int-param.md) | `fun intParam(value: Int): `[`Expression`](-expression/index.md)`<Int>` |
| [joinQuery](join-query.md) | `fun `[`Join`](-join/index.md)`.joinQuery(on: (`[`SqlExpressionBuilder`](-sql-expression-builder/index.md)`, `[`QueryAlias`](-query-alias/index.md)`) -> `[`Op`](-op/index.md)`<Boolean>, joinType: `[`JoinType`](-join-type/index.md)` = JoinType.INNER, joinPart: () -> `[`Query`](-query/index.md)`): `[`Join`](-join/index.md)<br>`fun `[`Table`](-table/index.md)`.joinQuery(on: (`[`SqlExpressionBuilder`](-sql-expression-builder/index.md)`, `[`QueryAlias`](-query-alias/index.md)`) -> `[`Op`](-op/index.md)`<Boolean>, joinType: `[`JoinType`](-join-type/index.md)` = JoinType.INNER, joinPart: () -> `[`Query`](-query/index.md)`): `[`Join`](-join/index.md) |
| [leftJoin](left-join.md) | `fun <C1 : `[`ColumnSet`](-column-set/index.md)`, C2 : `[`ColumnSet`](-column-set/index.md)`> C1.leftJoin(otherTable: C2, onColumn: (C1) -> `[`Expression`](-expression/index.md)`<*>, otherColumn: (C2) -> `[`Expression`](-expression/index.md)`<*>): `[`Join`](-join/index.md) |
| [logTimeSpent](log-time-spent.md) | `fun <R> logTimeSpent(message: String, block: () -> R): R` |
| [longLiteral](long-literal.md) | `fun longLiteral(value: Long): `[`LiteralOp`](-literal-op/index.md)`<Long>` |
| [longParam](long-param.md) | `fun longParam(value: Long): `[`Expression`](-expression/index.md)`<Long>` |
| [lowerCase](lower-case.md) | `fun <T : String?> `[`Expression`](-expression/index.md)`<T>.lowerCase(): `[`Function`](-function/index.md)`<T>` |
| [mapLazy](map-lazy.md) | `infix fun <T, R> `[`SizedIterable`](-sized-iterable/index.md)`<T>.mapLazy(f: (T) -> R): `[`SizedIterable`](-sized-iterable/index.md)`<R>` |
| [max](max.md) | `fun <T : Comparable<T>, S : T?> `[`ExpressionWithColumnType`](-expression-with-column-type/index.md)`<in S>.max(): `[`ExpressionWithColumnType`](-expression-with-column-type/index.md)`<T?>` |
| [min](min.md) | `fun <T : Comparable<T>, S : T?> `[`ExpressionWithColumnType`](-expression-with-column-type/index.md)`<in S>.min(): `[`ExpressionWithColumnType`](-expression-with-column-type/index.md)`<T?>` |
| [month](month.md) | `fun <T> `[`Expression`](-expression/index.md)`<T>.month(): `[`Month`](-month/index.md)`<T>` |
| [not](not.md) | `fun not(op: `[`Expression`](-expression/index.md)`<Boolean>): `[`Op`](-op/index.md)`<Boolean>` |
| [or](or.md) | `infix fun `[`Op`](-op/index.md)`<Boolean>.or(op: `[`Expression`](-expression/index.md)`<Boolean>): `[`Op`](-op/index.md)`<Boolean>` |
| [replace](replace.md) | `fun <T : `[`Table`](-table/index.md)`> T.replace(body: (T, `[`UpdateBuilder`](../org.jetbrains.exposed.sql.statements/-update-builder/index.md)`<*>) -> Unit): `[`ReplaceStatement`](../org.jetbrains.exposed.sql.statements/-replace-statement/index.md)`<Long>`

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DMLTests.testReplace01<br>```
<br> |
| [select](select.md) | `fun `[`FieldSet`](-field-set/index.md)`.select(where: (`[`SqlExpressionBuilder`](-sql-expression-builder/index.md)`) -> `[`Op`](-op/index.md)`<Boolean>): `[`Query`](-query/index.md)

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DMLTests.testSelect01<br>```
<br>`fun `[`FieldSet`](-field-set/index.md)`.select(where: `[`Op`](-op/index.md)`<Boolean>): `[`Query`](-query/index.md) |
| [selectAll](select-all.md) | `fun `[`FieldSet`](-field-set/index.md)`.selectAll(): `[`Query`](-query/index.md)

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DMLTests.testSelectDistinct<br>```
<br> |
| [stdDevPop](std-dev-pop.md) | `fun <T> `[`ExpressionWithColumnType`](-expression-with-column-type/index.md)`<T>.stdDevPop(scale: Int = 2): `[`StdDevPop`](-std-dev-pop/index.md)`<T>` |
| [stdDevSamp](std-dev-samp.md) | `fun <T> `[`ExpressionWithColumnType`](-expression-with-column-type/index.md)`<T>.stdDevSamp(scale: Int = 2): `[`StdDevSamp`](-std-dev-samp/index.md)`<T>` |
| [stringLiteral](string-literal.md) | `fun stringLiteral(value: String): `[`LiteralOp`](-literal-op/index.md)`<String>` |
| [stringParam](string-param.md) | `fun stringParam(value: String): `[`Expression`](-expression/index.md)`<String>` |
| [substring](substring.md) | `fun <T : String?> `[`Expression`](-expression/index.md)`<T>.substring(start: Int, length: Int): `[`Function`](-function/index.md)`<T>` |
| [sum](sum.md) | `fun <T> `[`ExpressionWithColumnType`](-expression-with-column-type/index.md)`<T>.sum(): `[`Sum`](-sum/index.md)`<T>` |
| [targetTables](target-tables.md) | `fun `[`ColumnSet`](-column-set/index.md)`.targetTables(): List<`[`Table`](-table/index.md)`>` |
| [trim](trim.md) | `fun <T : String?> `[`Expression`](-expression/index.md)`<T>.trim(): `[`Function`](-function/index.md)`<T>` |
| [update](update.md) | `fun <T : `[`Table`](-table/index.md)`> T.update(where: (`[`SqlExpressionBuilder`](-sql-expression-builder/index.md)`) -> `[`Op`](-op/index.md)`<Boolean>, limit: Int? = null, body: (T, `[`UpdateStatement`](../org.jetbrains.exposed.sql.statements/-update-statement/index.md)`) -> Unit): Int`

``` kotlin
//Unresolved: org.jetbrains.exposed.sql.tests.shared.DMLTests.testUpdate01<br>```
<br>`fun `[`Join`](-join/index.md)`.update(where: (`[`SqlExpressionBuilder`](-sql-expression-builder/index.md)`) -> `[`Op`](-op/index.md)`<Boolean> = null, limit: Int? = null, body: (`[`UpdateStatement`](../org.jetbrains.exposed.sql.statements/-update-statement/index.md)`) -> Unit): Int` |
| [upperCase](upper-case.md) | `fun <T : String?> `[`Expression`](-expression/index.md)`<T>.upperCase(): `[`Function`](-function/index.md)`<T>` |
| [varPop](var-pop.md) | `fun <T> `[`ExpressionWithColumnType`](-expression-with-column-type/index.md)`<T>.varPop(scale: Int = 2): `[`VarPop`](-var-pop/index.md)`<T>` |
| [varSamp](var-samp.md) | `fun <T> `[`ExpressionWithColumnType`](-expression-with-column-type/index.md)`<T>.varSamp(scale: Int = 2): `[`VarSamp`](-var-samp/index.md)`<T>` |
| [wrapAsExpression](wrap-as-expression.md) | `fun <T : Any> wrapAsExpression(query: `[`Query`](-query/index.md)`): `[`Expression`](-expression/index.md)`<T>` |
