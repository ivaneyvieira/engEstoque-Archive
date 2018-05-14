[com.github.vok.karibudsl](index.md) / [addColumnFor](.)

# addColumnFor

`fun <T, V> <ERROR CLASS><T>.addColumnFor(property: KProperty1<T, V>, block: (<ERROR CLASS><T, V>) -> Unit = {}): <ERROR CLASS><T, V>`

Adds a new column for given [property](add-column-for.md#com.github.vok.karibudsl$addColumnFor(((com.github.vok.karibudsl.addColumnFor.T)), kotlin.reflect.KProperty1((com.github.vok.karibudsl.addColumnFor.T, com.github.vok.karibudsl.addColumnFor.V)), kotlin.Function1((((com.github.vok.karibudsl.addColumnFor.T, com.github.vok.karibudsl.addColumnFor.V)), kotlin.Unit)))/property) which is by default sortable. The [Grid.Column.setId](#)
is set to property name, the column caption is set by converting camelCase to Human Friendly.

