[org.jetbrains.exposed.sql](../index.md) / [LazySizedCollection](.)

# LazySizedCollection

`class LazySizedCollection<out T> : `[`SizedIterable`](../-sized-iterable/index.md)`<T>`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `LazySizedCollection(delegate: `[`SizedIterable`](../-sized-iterable/index.md)`<T>)` |

### Properties

| Name | Summary |
|---|---|
| [delegate](delegate.md) | `val delegate: `[`SizedIterable`](../-sized-iterable/index.md)`<T>` |
| [wrapper](wrapper.md) | `val wrapper: List<T>` |

### Functions

| Name | Summary |
|---|---|
| [count](count.md) | `fun count(): Int` |
| [empty](empty.md) | `fun empty(): Boolean` |
| [forUpdate](for-update.md) | `fun forUpdate(): `[`SizedIterable`](../-sized-iterable/index.md)`<T>` |
| [iterator](iterator.md) | `operator fun iterator(): Iterator<T>` |
| [limit](limit.md) | `fun limit(n: Int, offset: Int): `[`SizedIterable`](../-sized-iterable/index.md)`<T>` |
| [notForUpdate](not-for-update.md) | `fun notForUpdate(): `[`SizedIterable`](../-sized-iterable/index.md)`<T>` |

### Extension Functions

| Name | Summary |
|---|---|
| [mapLazy](../map-lazy.md) | `infix fun <T, R> `[`SizedIterable`](../-sized-iterable/index.md)`<T>.mapLazy(f: (T) -> R): `[`SizedIterable`](../-sized-iterable/index.md)`<R>` |
