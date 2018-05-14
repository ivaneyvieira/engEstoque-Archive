[org.jetbrains.exposed.sql](../index.md) / [SizedCollection](.)

# SizedCollection

`class SizedCollection<out T> : `[`SizedIterable`](../-sized-iterable/index.md)`<T>`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `SizedCollection(delegate: Collection<T>)` |

### Properties

| Name | Summary |
|---|---|
| [delegate](delegate.md) | `val delegate: Collection<T>` |

### Functions

| Name | Summary |
|---|---|
| [count](count.md) | `fun count(): Int` |
| [empty](empty.md) | `fun empty(): Boolean` |
| [iterator](iterator.md) | `operator fun iterator(): Iterator<T>` |
| [limit](limit.md) | `fun limit(n: Int, offset: Int): `[`SizedIterable`](../-sized-iterable/index.md)`<T>` |

### Inherited Functions

| Name | Summary |
|---|---|
| [forUpdate](../-sized-iterable/for-update.md) | `open fun forUpdate(): `[`SizedIterable`](../-sized-iterable/index.md)`<T>` |
| [notForUpdate](../-sized-iterable/not-for-update.md) | `open fun notForUpdate(): `[`SizedIterable`](../-sized-iterable/index.md)`<T>` |

### Extension Functions

| Name | Summary |
|---|---|
| [mapLazy](../map-lazy.md) | `infix fun <T, R> `[`SizedIterable`](../-sized-iterable/index.md)`<T>.mapLazy(f: (T) -> R): `[`SizedIterable`](../-sized-iterable/index.md)`<R>` |
