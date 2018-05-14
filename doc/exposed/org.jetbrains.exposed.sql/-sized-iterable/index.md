[org.jetbrains.exposed.sql](../index.md) / [SizedIterable](.)

# SizedIterable

`interface SizedIterable<out T> : Iterable<T>`

### Functions

| Name | Summary |
|---|---|
| [count](count.md) | `abstract fun count(): Int` |
| [empty](empty.md) | `abstract fun empty(): Boolean` |
| [forUpdate](for-update.md) | `open fun forUpdate(): SizedIterable<T>` |
| [limit](limit.md) | `abstract fun limit(n: Int, offset: Int = 0): SizedIterable<T>` |
| [notForUpdate](not-for-update.md) | `open fun notForUpdate(): SizedIterable<T>` |

### Extension Functions

| Name | Summary |
|---|---|
| [mapLazy](../map-lazy.md) | `infix fun <T, R> SizedIterable<T>.mapLazy(f: (T) -> R): SizedIterable<R>` |

### Inheritors

| Name | Summary |
|---|---|
| [EmptySizedIterable](../-empty-sized-iterable/index.md) | `class EmptySizedIterable<out T> : SizedIterable<T>, Iterator<T>` |
| [LazySizedCollection](../-lazy-sized-collection/index.md) | `class LazySizedCollection<out T> : SizedIterable<T>` |
| [Query](../-query/index.md) | `open class Query : SizedIterable<`[`ResultRow`](../-result-row/index.md)`>, `[`Statement`](../../org.jetbrains.exposed.sql.statements/-statement/index.md)`<`[`ResultSet`](http://docs.oracle.com/javase/6/docs/api/java/sql/ResultSet.html)`>` |
| [SizedCollection](../-sized-collection/index.md) | `class SizedCollection<out T> : SizedIterable<T>` |
| [View](../../org.jetbrains.exposed.dao/-view/index.md) | `class View<out Target : `[`Entity`](../../org.jetbrains.exposed.dao/-entity/index.md)`<*>> : SizedIterable<Target>` |
