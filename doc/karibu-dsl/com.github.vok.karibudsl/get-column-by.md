[com.github.vok.karibudsl](index.md) / [getColumnBy](.)

# getColumnBy

`fun <T, V> <ERROR CLASS><T>.getColumnBy(property: KProperty1<T, V>): <ERROR CLASS><T, V>`

Retrieves the column for given [property](get-column-by.md#com.github.vok.karibudsl$getColumnBy(((com.github.vok.karibudsl.getColumnBy.T)), kotlin.reflect.KProperty1((com.github.vok.karibudsl.getColumnBy.T, com.github.vok.karibudsl.getColumnBy.V)))/property); it matches [Grid.Column.getKey](#) to [KProperty1.name](#).

### Exceptions

`IllegalArgumentException` - if no such column exists.