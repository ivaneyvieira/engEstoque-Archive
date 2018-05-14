[org.jetbrains.exposed.dao](../index.md) / [EntityClass](index.md) / [findById](.)

# findById

`fun findById(id: ID): T?`
`open fun findById(id: `[`EntityID`](../-entity-i-d/index.md)`<ID>): T?`

Get an entity by its [id](find-by-id.md#org.jetbrains.exposed.dao.EntityClass$findById(org.jetbrains.exposed.dao.EntityClass.ID)/id).

### Parameters

`id` - The id of the entity

**Return**
The entity that has this id or null if no entity was found.

