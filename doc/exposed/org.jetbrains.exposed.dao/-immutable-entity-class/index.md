[org.jetbrains.exposed.dao](../index.md) / [ImmutableEntityClass](.)

# ImmutableEntityClass

`abstract class ImmutableEntityClass<ID : Comparable<ID>, out T : `[`Entity`](../-entity/index.md)`<ID>> : `[`EntityClass`](../-entity-class/index.md)`<ID, T>`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ImmutableEntityClass(table: `[`IdTable`](../-id-table/index.md)`<ID>, entityType: `[`Class`](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)`<T>? = null)` |

### Inherited Properties

| Name | Summary |
|---|---|
| [dependsOnColumns](../-entity-class/depends-on-columns.md) | `open val dependsOnColumns: List<`[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<out Any?>>` |
| [dependsOnTables](../-entity-class/depends-on-tables.md) | `open val dependsOnTables: `[`ColumnSet`](../../org.jetbrains.exposed.sql/-column-set/index.md) |
| [table](../-entity-class/table.md) | `val table: `[`IdTable`](../-id-table/index.md)`<ID>` |

### Functions

| Name | Summary |
|---|---|
| [forceUpdateEntity](force-update-entity.md) | `open fun <T> forceUpdateEntity(entity: `[`Entity`](../-entity/index.md)`<ID>, column: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<T>, value: T): Unit` |

### Inherited Functions

| Name | Summary |
|---|---|
| [all](../-entity-class/all.md) | `open fun all(): `[`SizedIterable`](../../org.jetbrains.exposed.sql/-sized-iterable/index.md)`<T>` |
| [count](../-entity-class/count.md) | `fun count(op: `[`Op`](../../org.jetbrains.exposed.sql/-op/index.md)`<Boolean>? = null): Int`<br>Count the amount of entities that conform to the [op](../-entity-class/count.md#org.jetbrains.exposed.dao.EntityClass$count(org.jetbrains.exposed.sql.Op((kotlin.Boolean)))/op) statement. |
| [find](../-entity-class/find.md) | `fun find(op: `[`Op`](../../org.jetbrains.exposed.sql/-op/index.md)`<Boolean>): `[`SizedIterable`](../../org.jetbrains.exposed.sql/-sized-iterable/index.md)`<T>`<br>`fun find(op: (`[`SqlExpressionBuilder`](../../org.jetbrains.exposed.sql/-sql-expression-builder/index.md)`) -> `[`Op`](../../org.jetbrains.exposed.sql/-op/index.md)`<Boolean>): `[`SizedIterable`](../../org.jetbrains.exposed.sql/-sized-iterable/index.md)`<T>`<br>Get all the entities that conform to the [op](../-entity-class/find.md#org.jetbrains.exposed.dao.EntityClass$find(org.jetbrains.exposed.sql.Op((kotlin.Boolean)))/op) statement. |
| [isAssignableTo](../-entity-class/is-assignable-to.md) | `fun <ID : Comparable<ID>, T : `[`Entity`](../-entity/index.md)`<ID>> isAssignableTo(entityClass: `[`EntityClass`](../-entity-class/index.md)`<ID, T>): Boolean` |
| [optionalReferrersOn](../-entity-class/optional-referrers-on.md) | `infix fun <TargetID : Comparable<TargetID>> optionalReferrersOn(column: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<`[`EntityID`](../-entity-i-d/index.md)`<TargetID>?>): `[`OptionalReferrers`](../-optional-referrers/index.md)`<TargetID, `[`Entity`](../-entity/index.md)`<TargetID>, ID, T>`<br>`fun <TargetID : Comparable<TargetID>> optionalReferrersOn(column: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<`[`EntityID`](../-entity-i-d/index.md)`<TargetID>?>, cache: Boolean = false): `[`OptionalReferrers`](../-optional-referrers/index.md)`<TargetID, `[`Entity`](../-entity/index.md)`<TargetID>, ID, T>` |
| [referrersOn](../-entity-class/referrers-on.md) | `fun <TargetID : Comparable<TargetID>> referrersOn(column: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<`[`EntityID`](../-entity-i-d/index.md)`<TargetID>>, cache: Boolean): `[`Referrers`](../-referrers/index.md)`<TargetID, `[`Entity`](../-entity/index.md)`<TargetID>, ID, T>`<br>`infix fun <TargetID : Comparable<TargetID>> referrersOn(column: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<`[`EntityID`](../-entity-i-d/index.md)`<TargetID>>): `[`Referrers`](../-referrers/index.md)`<TargetID, `[`Entity`](../-entity/index.md)`<TargetID>, ID, T>` |
| [searchQuery](../-entity-class/search-query.md) | `open fun searchQuery(op: `[`Op`](../../org.jetbrains.exposed.sql/-op/index.md)`<Boolean>): `[`Query`](../../org.jetbrains.exposed.sql/-query/index.md) |
| [transform](../-entity-class/transform.md) | `fun <TColumn, TReal> `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<TColumn>.transform(toColumn: (TReal) -> TColumn, toReal: (TColumn) -> TReal): `[`ColumnWithTransform`](../-column-with-transform/index.md)`<TColumn, TReal>` |
| [view](../-entity-class/view.md) | `fun view(op: (`[`SqlExpressionBuilder`](../../org.jetbrains.exposed.sql/-sql-expression-builder/index.md)`) -> `[`Op`](../../org.jetbrains.exposed.sql/-op/index.md)`<Boolean>): `[`View`](../-view/index.md)`<T>` |
| [warmCache](../-entity-class/warm-cache.md) | `open fun warmCache(): `[`EntityCache`](../-entity-cache/index.md) |
| [warmUpLinkedReferences](../-entity-class/warm-up-linked-references.md) | `fun warmUpLinkedReferences(references: List<`[`EntityID`](../-entity-i-d/index.md)`<*>>, linkTable: `[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`): List<T>` |
| [wrapRow](../-entity-class/wrap-row.md) | `fun ~~wrapRow~~(row: `[`ResultRow`](../../org.jetbrains.exposed.sql/-result-row/index.md)`, transaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`): T`<br>`fun wrapRow(row: `[`ResultRow`](../../org.jetbrains.exposed.sql/-result-row/index.md)`): T` |
| [wrapRows](../-entity-class/wrap-rows.md) | `fun wrapRows(rows: `[`SizedIterable`](../../org.jetbrains.exposed.sql/-sized-iterable/index.md)`<`[`ResultRow`](../../org.jetbrains.exposed.sql/-result-row/index.md)`>): `[`SizedIterable`](../../org.jetbrains.exposed.sql/-sized-iterable/index.md)`<T>` |

### Inheritors

| Name | Summary |
|---|---|
| [ImmutableCachedEntityClass](../-immutable-cached-entity-class/index.md) | `abstract class ImmutableCachedEntityClass<ID : Comparable<ID>, out T : `[`Entity`](../-entity/index.md)`<ID>> : ImmutableEntityClass<ID, T>` |
