[org.jetbrains.exposed.dao](../index.md) / [EntityID](.)

# EntityID

`class EntityID<T : Comparable<T>> : Comparable<EntityID<T>>`

**Author**
max

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `EntityID(id: T?, table: `[`IdTable`](../-id-table/index.md)`<T>)` |

### Properties

| Name | Summary |
|---|---|
| [_value](_value.md) | `var _value: Any?` |
| [table](table.md) | `val table: `[`IdTable`](../-id-table/index.md)`<T>` |
| [value](value.md) | `val value: T` |

### Functions

| Name | Summary |
|---|---|
| [compareTo](compare-to.md) | `fun compareTo(other: EntityID<T>): Int` |
| [equals](equals.md) | `fun equals(other: Any?): Boolean` |
| [hashCode](hash-code.md) | `fun hashCode(): Int` |
| [toString](to-string.md) | `fun toString(): String` |
