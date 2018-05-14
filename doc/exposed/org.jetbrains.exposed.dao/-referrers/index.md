[org.jetbrains.exposed.dao](../index.md) / [Referrers](.)

# Referrers

`class Referrers<ParentID : Comparable<ParentID>, in Parent : `[`Entity`](../-entity/index.md)`<ParentID>, ChildID : Comparable<ChildID>, out Child : `[`Entity`](../-entity/index.md)`<ChildID>> : Any`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Referrers(reference: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<`[`EntityID`](../-entity-i-d/index.md)`<ParentID>>, factory: `[`EntityClass`](../-entity-class/index.md)`<ChildID, Child>, cache: Boolean)` |

### Properties

| Name | Summary |
|---|---|
| [cache](cache.md) | `val cache: Boolean` |
| [factory](factory.md) | `val factory: `[`EntityClass`](../-entity-class/index.md)`<ChildID, Child>` |
| [reference](reference.md) | `val reference: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<`[`EntityID`](../-entity-i-d/index.md)`<ParentID>>` |

### Functions

| Name | Summary |
|---|---|
| [getValue](get-value.md) | `operator fun getValue(o: Parent, desc: KProperty<*>): `[`SizedIterable`](../../org.jetbrains.exposed.sql/-sized-iterable/index.md)`<Child>` |
