[org.jetbrains.exposed.dao](../index.md) / [OptionalReference](.)

# OptionalReference

`class OptionalReference<ID : Comparable<ID>, out Target : `[`Entity`](../-entity/index.md)`<ID>> : Any`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `OptionalReference(reference: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<`[`EntityID`](../-entity-i-d/index.md)`<ID>?>, factory: `[`EntityClass`](../-entity-class/index.md)`<ID, Target>)` |

### Properties

| Name | Summary |
|---|---|
| [factory](factory.md) | `val factory: `[`EntityClass`](../-entity-class/index.md)`<ID, Target>` |
| [reference](reference.md) | `val reference: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<`[`EntityID`](../-entity-i-d/index.md)`<ID>?>` |

### Extension Functions

| Name | Summary |
|---|---|
| [getValue](../-entity/get-value.md) | `operator fun <RID : Comparable<RID>, T : `[`Entity`](../-entity/index.md)`<RID>> OptionalReference<RID, T>.getValue(o: `[`Entity`](../-entity/index.md)`<ID>, desc: KProperty<*>): T?` |
| [setValue](../-entity/set-value.md) | `operator fun <RID : Comparable<RID>, T : `[`Entity`](../-entity/index.md)`<RID>> OptionalReference<RID, T>.setValue(o: `[`Entity`](../-entity/index.md)`<ID>, desc: KProperty<*>, value: T?): Unit` |
