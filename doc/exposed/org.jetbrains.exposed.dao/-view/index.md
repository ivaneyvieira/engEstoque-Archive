[org.jetbrains.exposed.dao](../index.md) / [View](.)

# View

`class View<out Target : `[`Entity`](../-entity/index.md)`<*>> : `[`SizedIterable`](../../org.jetbrains.exposed.sql/-sized-iterable/index.md)`<Target>`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `View(op: `[`Op`](../../org.jetbrains.exposed.sql/-op/index.md)`<Boolean>, factory: `[`EntityClass`](../-entity-class/index.md)`<*, Target>)` |

### Properties

| Name | Summary |
|---|---|
| [factory](factory.md) | `val factory: `[`EntityClass`](../-entity-class/index.md)`<*, Target>` |
| [op](op.md) | `val op: `[`Op`](../../org.jetbrains.exposed.sql/-op/index.md)`<Boolean>` |

### Functions

| Name | Summary |
|---|---|
| [count](count.md) | `fun count(): Int` |
| [empty](empty.md) | `fun empty(): Boolean` |
| [forUpdate](for-update.md) | `fun forUpdate(): `[`SizedIterable`](../../org.jetbrains.exposed.sql/-sized-iterable/index.md)`<Target>` |
| [getValue](get-value.md) | `operator fun getValue(o: Any?, desc: KProperty<*>): `[`SizedIterable`](../../org.jetbrains.exposed.sql/-sized-iterable/index.md)`<Target>` |
| [iterator](iterator.md) | `operator fun iterator(): Iterator<Target>` |
| [limit](limit.md) | `fun limit(n: Int, offset: Int): `[`SizedIterable`](../../org.jetbrains.exposed.sql/-sized-iterable/index.md)`<Target>` |
| [notForUpdate](not-for-update.md) | `fun notForUpdate(): `[`SizedIterable`](../../org.jetbrains.exposed.sql/-sized-iterable/index.md)`<Target>` |

### Extension Functions

| Name | Summary |
|---|---|
| [mapLazy](../../org.jetbrains.exposed.sql/map-lazy.md) | `infix fun <T, R> `[`SizedIterable`](../../org.jetbrains.exposed.sql/-sized-iterable/index.md)`<T>.mapLazy(f: (T) -> R): `[`SizedIterable`](../../org.jetbrains.exposed.sql/-sized-iterable/index.md)`<R>` |
