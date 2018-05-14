[org.jetbrains.exposed.dao](../index.md) / [EntityClass](.)

# EntityClass

`abstract class EntityClass<ID : Comparable<ID>, out T : `[`Entity`](../-entity/index.md)`<ID>> : Any`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `EntityClass(table: `[`IdTable`](../-id-table/index.md)`<ID>, entityType: `[`Class`](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)`<T>? = null)` |

### Properties

| Name | Summary |
|---|---|
| [dependsOnColumns](depends-on-columns.md) | `open val dependsOnColumns: List<`[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<out Any?>>` |
| [dependsOnTables](depends-on-tables.md) | `open val dependsOnTables: `[`ColumnSet`](../../org.jetbrains.exposed.sql/-column-set/index.md) |
| [table](table.md) | `val table: `[`IdTable`](../-id-table/index.md)`<ID>` |

### Functions

| Name | Summary |
|---|---|
| [all](all.md) | `open fun all(): `[`SizedIterable`](../../org.jetbrains.exposed.sql/-sized-iterable/index.md)`<T>` |
| [backReferencedOn](back-referenced-on.md) | `infix fun <TargetID : Comparable<TargetID>, Target : `[`Entity`](../-entity/index.md)`<TargetID>> EntityClass<TargetID, Target>.backReferencedOn(column: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<`[`EntityID`](../-entity-i-d/index.md)`<ID>>): `[`BackReference`](../-back-reference/index.md)`<TargetID, Target, ID, `[`Entity`](../-entity/index.md)`<ID>>`<br>`infix fun <TargetID : Comparable<TargetID>, Target : `[`Entity`](../-entity/index.md)`<TargetID>> EntityClass<TargetID, Target>.backReferencedOn(column: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<`[`EntityID`](../-entity-i-d/index.md)`<ID>?>): `[`BackReference`](../-back-reference/index.md)`<TargetID, Target, ID, `[`Entity`](../-entity/index.md)`<ID>>` |
| [count](count.md) | `fun count(op: `[`Op`](../../org.jetbrains.exposed.sql/-op/index.md)`<Boolean>? = null): Int`<br>Count the amount of entities that conform to the [op](count.md#org.jetbrains.exposed.dao.EntityClass$count(org.jetbrains.exposed.sql.Op((kotlin.Boolean)))/op) statement. |
| [createInstance](create-instance.md) | `open fun createInstance(entityId: `[`EntityID`](../-entity-i-d/index.md)`<ID>, row: `[`ResultRow`](../../org.jetbrains.exposed.sql/-result-row/index.md)`?): T` |
| [find](find.md) | `fun find(op: `[`Op`](../../org.jetbrains.exposed.sql/-op/index.md)`<Boolean>): `[`SizedIterable`](../../org.jetbrains.exposed.sql/-sized-iterable/index.md)`<T>`<br>`fun find(op: (`[`SqlExpressionBuilder`](../../org.jetbrains.exposed.sql/-sql-expression-builder/index.md)`) -> `[`Op`](../../org.jetbrains.exposed.sql/-op/index.md)`<Boolean>): `[`SizedIterable`](../../org.jetbrains.exposed.sql/-sized-iterable/index.md)`<T>`<br>Get all the entities that conform to the [op](find.md#org.jetbrains.exposed.dao.EntityClass$find(org.jetbrains.exposed.sql.Op((kotlin.Boolean)))/op) statement. |
| [findById](find-by-id.md) | `fun findById(id: ID): T?`<br>`open fun findById(id: `[`EntityID`](../-entity-i-d/index.md)`<ID>): T?`<br>Get an entity by its [id](find-by-id.md#org.jetbrains.exposed.dao.EntityClass$findById(org.jetbrains.exposed.dao.EntityClass.ID)/id). |
| [findWithCacheCondition](find-with-cache-condition.md) | `fun findWithCacheCondition(cacheCheckCondition: (T) -> Boolean, op: (`[`SqlExpressionBuilder`](../../org.jetbrains.exposed.sql/-sql-expression-builder/index.md)`) -> `[`Op`](../../org.jetbrains.exposed.sql/-op/index.md)`<Boolean>): <ERROR CLASS><T>` |
| [forEntityIds](for-entity-ids.md) | `open fun forEntityIds(ids: List<`[`EntityID`](../-entity-i-d/index.md)`<ID>>): `[`SizedIterable`](../../org.jetbrains.exposed.sql/-sized-iterable/index.md)`<T>` |
| [forIds](for-ids.md) | `fun forIds(ids: List<ID>): `[`SizedIterable`](../../org.jetbrains.exposed.sql/-sized-iterable/index.md)`<T>` |
| [get](get.md) | `operator fun get(id: `[`EntityID`](../-entity-i-d/index.md)`<ID>): T`<br>`operator fun get(id: ID): T` |
| [isAssignableTo](is-assignable-to.md) | `fun <ID : Comparable<ID>, T : `[`Entity`](../-entity/index.md)`<ID>> isAssignableTo(entityClass: EntityClass<ID, T>): Boolean` |
| [new](new.md) | `open fun new(init: (T) -> Unit): T`<br>Create a new entity with the fields that are set in the [init](new.md#org.jetbrains.exposed.dao.EntityClass$new(kotlin.Function1((org.jetbrains.exposed.dao.EntityClass.T, kotlin.Unit)))/init) block. The id will be automatically set.`open fun new(id: ID?, init: (T) -> Unit): T`<br>Create a new entity with the fields that are set in the [init](new.md#org.jetbrains.exposed.dao.EntityClass$new(org.jetbrains.exposed.dao.EntityClass.ID, kotlin.Function1((org.jetbrains.exposed.dao.EntityClass.T, kotlin.Unit)))/init) block and with a set [id](new.md#org.jetbrains.exposed.dao.EntityClass$new(org.jetbrains.exposed.dao.EntityClass.ID, kotlin.Function1((org.jetbrains.exposed.dao.EntityClass.T, kotlin.Unit)))/id). |
| [optionalBackReferencedOn](optional-back-referenced-on.md) | `infix fun <TargetID : Comparable<TargetID>, Target : `[`Entity`](../-entity/index.md)`<TargetID>> EntityClass<TargetID, Target>.optionalBackReferencedOn(column: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<`[`EntityID`](../-entity-i-d/index.md)`<ID>>): `[`OptionalBackReference`](../-optional-back-reference/index.md)`<TargetID, Target, ID, `[`Entity`](../-entity/index.md)`<ID>>`<br>`infix fun <TargetID : Comparable<TargetID>, Target : `[`Entity`](../-entity/index.md)`<TargetID>> EntityClass<TargetID, Target>.optionalBackReferencedOn(column: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<`[`EntityID`](../-entity-i-d/index.md)`<ID>?>): `[`OptionalBackReference`](../-optional-back-reference/index.md)`<TargetID, Target, ID, `[`Entity`](../-entity/index.md)`<ID>>` |
| [optionalReferencedOn](optional-referenced-on.md) | `infix fun optionalReferencedOn(column: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<`[`EntityID`](../-entity-i-d/index.md)`<ID>?>): `[`OptionalReference`](../-optional-reference/index.md)`<ID, T>` |
| [optionalReferrersOn](optional-referrers-on.md) | `infix fun <TargetID : Comparable<TargetID>> optionalReferrersOn(column: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<`[`EntityID`](../-entity-i-d/index.md)`<TargetID>?>): `[`OptionalReferrers`](../-optional-referrers/index.md)`<TargetID, `[`Entity`](../-entity/index.md)`<TargetID>, ID, T>`<br>`fun <TargetID : Comparable<TargetID>> optionalReferrersOn(column: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<`[`EntityID`](../-entity-i-d/index.md)`<TargetID>?>, cache: Boolean = false): `[`OptionalReferrers`](../-optional-referrers/index.md)`<TargetID, `[`Entity`](../-entity/index.md)`<TargetID>, ID, T>` |
| [referencedOn](referenced-on.md) | `infix fun referencedOn(column: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<`[`EntityID`](../-entity-i-d/index.md)`<ID>>): `[`Reference`](../-reference/index.md)`<ID, T>` |
| [referrersOn](referrers-on.md) | `infix fun <TargetID : Comparable<TargetID>> referrersOn(column: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<`[`EntityID`](../-entity-i-d/index.md)`<TargetID>>): `[`Referrers`](../-referrers/index.md)`<TargetID, `[`Entity`](../-entity/index.md)`<TargetID>, ID, T>`<br>`fun <TargetID : Comparable<TargetID>> referrersOn(column: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<`[`EntityID`](../-entity-i-d/index.md)`<TargetID>>, cache: Boolean): `[`Referrers`](../-referrers/index.md)`<TargetID, `[`Entity`](../-entity/index.md)`<TargetID>, ID, T>` |
| [reload](reload.md) | `fun reload(entity: `[`Entity`](../-entity/index.md)`<ID>): T?` |
| [removeFromCache](remove-from-cache.md) | `fun removeFromCache(entity: `[`Entity`](../-entity/index.md)`<ID>): Unit` |
| [searchQuery](search-query.md) | `open fun searchQuery(op: `[`Op`](../../org.jetbrains.exposed.sql/-op/index.md)`<Boolean>): `[`Query`](../../org.jetbrains.exposed.sql/-query/index.md) |
| [testCache](test-cache.md) | `fun testCache(id: `[`EntityID`](../-entity-i-d/index.md)`<ID>): T?`<br>`fun testCache(cacheCheckCondition: (T) -> Boolean): <ERROR CLASS><T>` |
| [transform](transform.md) | `fun <TColumn, TReal> `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<TColumn>.transform(toColumn: (TReal) -> TColumn, toReal: (TColumn) -> TReal): `[`ColumnWithTransform`](../-column-with-transform/index.md)`<TColumn, TReal>` |
| [view](view.md) | `fun view(op: (`[`SqlExpressionBuilder`](../../org.jetbrains.exposed.sql/-sql-expression-builder/index.md)`) -> `[`Op`](../../org.jetbrains.exposed.sql/-op/index.md)`<Boolean>): `[`View`](../-view/index.md)`<T>` |
| [warmCache](warm-cache.md) | `open fun warmCache(): `[`EntityCache`](../-entity-cache/index.md) |
| [warmUpLinkedReferences](warm-up-linked-references.md) | `fun warmUpLinkedReferences(references: List<`[`EntityID`](../-entity-i-d/index.md)`<*>>, linkTable: `[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`): List<T>` |
| [warmUpOptReferences](warm-up-opt-references.md) | `fun warmUpOptReferences(references: List<`[`EntityID`](../-entity-i-d/index.md)`<ID>>, refColumn: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<`[`EntityID`](../-entity-i-d/index.md)`<ID>?>): List<T>` |
| [warmUpReferences](warm-up-references.md) | `fun warmUpReferences(references: List<`[`EntityID`](../-entity-i-d/index.md)`<ID>>, refColumn: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<`[`EntityID`](../-entity-i-d/index.md)`<ID>>): List<T>` |
| [wrap](wrap.md) | `fun wrap(id: `[`EntityID`](../-entity-i-d/index.md)`<ID>, row: `[`ResultRow`](../../org.jetbrains.exposed.sql/-result-row/index.md)`?): T` |
| [wrapRow](wrap-row.md) | `fun ~~wrapRow~~(row: `[`ResultRow`](../../org.jetbrains.exposed.sql/-result-row/index.md)`, transaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`): T`<br>`fun wrapRow(row: `[`ResultRow`](../../org.jetbrains.exposed.sql/-result-row/index.md)`): T` |
| [wrapRows](wrap-rows.md) | `fun wrapRows(rows: `[`SizedIterable`](../../org.jetbrains.exposed.sql/-sized-iterable/index.md)`<`[`ResultRow`](../../org.jetbrains.exposed.sql/-result-row/index.md)`>): `[`SizedIterable`](../../org.jetbrains.exposed.sql/-sized-iterable/index.md)`<T>` |

### Extension Functions

| Name | Summary |
|---|---|
| [backReferencedOn](back-referenced-on.md) | `infix fun <TargetID : Comparable<TargetID>, Target : `[`Entity`](../-entity/index.md)`<TargetID>> EntityClass<TargetID, Target>.backReferencedOn(column: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<`[`EntityID`](../-entity-i-d/index.md)`<ID>?>): `[`BackReference`](../-back-reference/index.md)`<TargetID, Target, ID, `[`Entity`](../-entity/index.md)`<ID>>` |
| [optionalBackReferencedOn](optional-back-referenced-on.md) | `infix fun <TargetID : Comparable<TargetID>, Target : `[`Entity`](../-entity/index.md)`<TargetID>> EntityClass<TargetID, Target>.optionalBackReferencedOn(column: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<`[`EntityID`](../-entity-i-d/index.md)`<ID>?>): `[`OptionalBackReference`](../-optional-back-reference/index.md)`<TargetID, Target, ID, `[`Entity`](../-entity/index.md)`<ID>>` |
| [via](../-entity/via.md) | `infix fun <ID : Comparable<ID>, Target : `[`Entity`](../-entity/index.md)`<ID>> EntityClass<ID, Target>.via(table: `[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`): `[`InnerTableLink`](../-inner-table-link/index.md)`<ID, Target>` |

### Inheritors

| Name | Summary |
|---|---|
| [ImmutableEntityClass](../-immutable-entity-class/index.md) | `abstract class ImmutableEntityClass<ID : Comparable<ID>, out T : `[`Entity`](../-entity/index.md)`<ID>> : EntityClass<ID, T>` |
| [IntEntityClass](../-int-entity-class/index.md) | `abstract class IntEntityClass<out E : `[`IntEntity`](../-int-entity/index.md)`> : EntityClass<Int, E>` |
| [LongEntityClass](../-long-entity-class/index.md) | `abstract class LongEntityClass<out E : `[`LongEntity`](../-long-entity/index.md)`> : EntityClass<Long, E>` |
| [UUIDEntityClass](../-u-u-i-d-entity-class/index.md) | `abstract class UUIDEntityClass<out E : `[`UUIDEntity`](../-u-u-i-d-entity/index.md)`> : EntityClass<`[`UUID`](http://docs.oracle.com/javase/6/docs/api/java/util/UUID.html)`, E>` |
