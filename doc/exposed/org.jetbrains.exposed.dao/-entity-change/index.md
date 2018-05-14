[org.jetbrains.exposed.dao](../index.md) / [EntityChange](.)

# EntityChange

`data class EntityChange : Any`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `EntityChange(entityClass: `[`EntityClass`](../-entity-class/index.md)`<*, `[`Entity`](../-entity/index.md)`<*>>, id: `[`EntityID`](../-entity-i-d/index.md)`<*>, changeType: `[`EntityChangeType`](../-entity-change-type/index.md)`)` |

### Properties

| Name | Summary |
|---|---|
| [changeType](change-type.md) | `var changeType: `[`EntityChangeType`](../-entity-change-type/index.md) |
| [entityClass](entity-class.md) | `val entityClass: `[`EntityClass`](../-entity-class/index.md)`<*, `[`Entity`](../-entity/index.md)`<*>>` |
| [id](id.md) | `val id: `[`EntityID`](../-entity-i-d/index.md)`<*>` |

### Extension Functions

| Name | Summary |
|---|---|
| [toEntity](../to-entity.md) | `fun <ID : Comparable<ID>, T : `[`Entity`](../-entity/index.md)`<ID>> EntityChange.toEntity(): T?`<br>`fun <ID : Comparable<ID>, T : `[`Entity`](../-entity/index.md)`<ID>> EntityChange.toEntity(klass: `[`EntityClass`](../-entity-class/index.md)`<ID, T>): T?` |
