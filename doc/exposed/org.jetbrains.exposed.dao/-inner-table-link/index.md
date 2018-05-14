[org.jetbrains.exposed.dao](../index.md) / [InnerTableLink](.)

# InnerTableLink

`class InnerTableLink<ID : Comparable<ID>, Target : `[`Entity`](../-entity/index.md)`<ID>> : Any`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `InnerTableLink(table: `[`Table`](../../org.jetbrains.exposed.sql/-table/index.md)`, target: `[`EntityClass`](../-entity-class/index.md)`<ID, Target>)` |

### Properties

| Name | Summary |
|---|---|
| [table](table.md) | `val table: `[`Table`](../../org.jetbrains.exposed.sql/-table/index.md) |
| [target](target.md) | `val target: `[`EntityClass`](../-entity-class/index.md)`<ID, Target>` |

### Functions

| Name | Summary |
|---|---|
| [getValue](get-value.md) | `operator fun getValue(o: `[`Entity`](../-entity/index.md)`<*>, desc: KProperty<*>): `[`SizedIterable`](../../org.jetbrains.exposed.sql/-sized-iterable/index.md)`<Target>` |
| [setValue](set-value.md) | `operator fun <SrcID : Comparable<SrcID>> setValue(o: `[`Entity`](../-entity/index.md)`<SrcID>, desc: KProperty<*>, value: `[`SizedIterable`](../../org.jetbrains.exposed.sql/-sized-iterable/index.md)`<Target>): Unit` |
