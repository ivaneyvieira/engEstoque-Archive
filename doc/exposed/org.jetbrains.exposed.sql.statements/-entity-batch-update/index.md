[org.jetbrains.exposed.sql.statements](../index.md) / [EntityBatchUpdate](.)

# EntityBatchUpdate

`class EntityBatchUpdate : Any`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `EntityBatchUpdate(klass: `[`EntityClass`](../../org.jetbrains.exposed.dao/-entity-class/index.md)`<*, `[`Entity`](../../org.jetbrains.exposed.dao/-entity/index.md)`<*>>)` |

### Properties

| Name | Summary |
|---|---|
| [klass](klass.md) | `val klass: `[`EntityClass`](../../org.jetbrains.exposed.dao/-entity-class/index.md)`<*, `[`Entity`](../../org.jetbrains.exposed.dao/-entity/index.md)`<*>>` |

### Functions

| Name | Summary |
|---|---|
| [addBatch](add-batch.md) | `fun addBatch(id: `[`EntityID`](../../org.jetbrains.exposed.dao/-entity-i-d/index.md)`<*>): Unit` |
| [execute](execute.md) | `fun execute(transaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`): Int` |
| [set](set.md) | `operator fun set(column: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<*>, value: Any?): Unit` |
