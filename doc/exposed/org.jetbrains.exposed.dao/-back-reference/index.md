[org.jetbrains.exposed.dao](../index.md) / [BackReference](.)

# BackReference

`class BackReference<ParentID : Comparable<ParentID>, out Parent : `[`Entity`](../-entity/index.md)`<ParentID>, ChildID : Comparable<ChildID>, in Child : `[`Entity`](../-entity/index.md)`<ChildID>> : Any`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `BackReference(reference: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<`[`EntityID`](../-entity-i-d/index.md)`<ChildID>>, factory: `[`EntityClass`](../-entity-class/index.md)`<ParentID, Parent>)` |

### Functions

| Name | Summary |
|---|---|
| [getValue](get-value.md) | `operator fun getValue(o: Child, desc: KProperty<*>): <ERROR CLASS>` |
