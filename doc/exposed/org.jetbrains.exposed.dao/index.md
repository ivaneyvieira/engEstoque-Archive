[org.jetbrains.exposed.dao](.)

## Package org.jetbrains.exposed.dao

### Types

| Name | Summary |
|---|---|
| [BackReference](-back-reference/index.md) | `class BackReference<ParentID : Comparable<ParentID>, out Parent : `[`Entity`](-entity/index.md)`<ParentID>, ChildID : Comparable<ChildID>, in Child : `[`Entity`](-entity/index.md)`<ChildID>> : Any` |
| [ColumnWithTransform](-column-with-transform/index.md) | `open class ColumnWithTransform<TColumn, TReal> : Any` |
| [Entity](-entity/index.md) | `open class Entity<ID : Comparable<ID>> : Any` |
| [EntityCache](-entity-cache/index.md) | `class EntityCache : Any` |
| [EntityChange](-entity-change/index.md) | `data class EntityChange : Any` |
| [EntityChangeType](-entity-change-type/index.md) | `enum class EntityChangeType : Enum<`[`EntityChangeType`](-entity-change-type/index.md)`>` |
| [EntityClass](-entity-class/index.md) | `abstract class EntityClass<ID : Comparable<ID>, out T : `[`Entity`](-entity/index.md)`<ID>> : Any` |
| [EntityHook](-entity-hook/index.md) | `object EntityHook : Any` |
| [EntityID](-entity-i-d/index.md) | `class EntityID<T : Comparable<T>> : Comparable<`[`EntityID`](-entity-i-d/index.md)`<T>>` |
| [IdTable](-id-table/index.md) | `abstract class IdTable<T : Comparable<T>> : `[`Table`](../org.jetbrains.exposed.sql/-table/index.md) |
| [ImmutableCachedEntityClass](-immutable-cached-entity-class/index.md) | `abstract class ImmutableCachedEntityClass<ID : Comparable<ID>, out T : `[`Entity`](-entity/index.md)`<ID>> : `[`ImmutableEntityClass`](-immutable-entity-class/index.md)`<ID, T>` |
| [ImmutableEntityClass](-immutable-entity-class/index.md) | `abstract class ImmutableEntityClass<ID : Comparable<ID>, out T : `[`Entity`](-entity/index.md)`<ID>> : `[`EntityClass`](-entity-class/index.md)`<ID, T>` |
| [InnerTableLink](-inner-table-link/index.md) | `class InnerTableLink<ID : Comparable<ID>, Target : `[`Entity`](-entity/index.md)`<ID>> : Any` |
| [IntEntity](-int-entity/index.md) | `abstract class IntEntity : `[`Entity`](-entity/index.md)`<Int>` |
| [IntEntityClass](-int-entity-class/index.md) | `abstract class IntEntityClass<out E : `[`IntEntity`](-int-entity/index.md)`> : `[`EntityClass`](-entity-class/index.md)`<Int, E>` |
| [IntIdTable](-int-id-table/index.md) | `open class IntIdTable : `[`IdTable`](-id-table/index.md)`<Int>` |
| [LongEntity](-long-entity/index.md) | `abstract class LongEntity : `[`Entity`](-entity/index.md)`<Long>` |
| [LongEntityClass](-long-entity-class/index.md) | `abstract class LongEntityClass<out E : `[`LongEntity`](-long-entity/index.md)`> : `[`EntityClass`](-entity-class/index.md)`<Long, E>` |
| [LongIdTable](-long-id-table/index.md) | `open class LongIdTable : `[`IdTable`](-id-table/index.md)`<Long>` |
| [OptionalBackReference](-optional-back-reference/index.md) | `class OptionalBackReference<ParentID : Comparable<ParentID>, out Parent : `[`Entity`](-entity/index.md)`<ParentID>, ChildID : Comparable<ChildID>, in Child : `[`Entity`](-entity/index.md)`<ChildID>> : Any` |
| [OptionalReference](-optional-reference/index.md) | `class OptionalReference<ID : Comparable<ID>, out Target : `[`Entity`](-entity/index.md)`<ID>> : Any` |
| [OptionalReferrers](-optional-referrers/index.md) | `class OptionalReferrers<ParentID : Comparable<ParentID>, in Parent : `[`Entity`](-entity/index.md)`<ParentID>, ChildID : Comparable<ChildID>, out Child : `[`Entity`](-entity/index.md)`<ChildID>> : Any` |
| [Reference](-reference/index.md) | `class Reference<ID : Comparable<ID>, out Target : `[`Entity`](-entity/index.md)`<ID>> : Any` |
| [Referrers](-referrers/index.md) | `class Referrers<ParentID : Comparable<ParentID>, in Parent : `[`Entity`](-entity/index.md)`<ParentID>, ChildID : Comparable<ChildID>, out Child : `[`Entity`](-entity/index.md)`<ChildID>> : Any` |
| [UUIDEntity](-u-u-i-d-entity/index.md) | `abstract class UUIDEntity : `[`Entity`](-entity/index.md)`<`[`UUID`](http://docs.oracle.com/javase/6/docs/api/java/util/UUID.html)`>` |
| [UUIDEntityClass](-u-u-i-d-entity-class/index.md) | `abstract class UUIDEntityClass<out E : `[`UUIDEntity`](-u-u-i-d-entity/index.md)`> : `[`EntityClass`](-entity-class/index.md)`<`[`UUID`](http://docs.oracle.com/javase/6/docs/api/java/util/UUID.html)`, E>` |
| [UUIDTable](-u-u-i-d-table/index.md) | `open class UUIDTable : `[`IdTable`](-id-table/index.md)`<`[`UUID`](http://docs.oracle.com/javase/6/docs/api/java/util/UUID.html)`>` |
| [View](-view/index.md) | `class View<out Target : `[`Entity`](-entity/index.md)`<*>> : `[`SizedIterable`](../org.jetbrains.exposed.sql/-sized-iterable/index.md)`<Target>` |

### Functions

| Name | Summary |
|---|---|
| [toEntity](to-entity.md) | `fun <ID : Comparable<ID>, T : `[`Entity`](-entity/index.md)`<ID>> `[`EntityChange`](-entity-change/index.md)`.toEntity(): T?`<br>`fun <ID : Comparable<ID>, T : `[`Entity`](-entity/index.md)`<ID>> `[`EntityChange`](-entity-change/index.md)`.toEntity(klass: `[`EntityClass`](-entity-class/index.md)`<ID, T>): T?` |
| [withHook](with-hook.md) | `fun <T> withHook(action: (`[`EntityChange`](-entity-change/index.md)`) -> Unit, body: () -> T): T` |
