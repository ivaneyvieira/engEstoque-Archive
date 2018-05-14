[com.github.vok.karibudsl](index.md) / [column](.)

# column

`fun <T, V> <ERROR CLASS><T>.column(prop: KProperty1<T, V>, block: (<ERROR CLASS><T, V>) -> Unit = {}): <ERROR CLASS><T, V>`

Allows you to re-configure a particular column in a Grid. E.g.:

```
grid(...) {
  showColumns(Person::name, Person::age)
  column(Person::age) { isSortable = false }
}
```

### Parameters

`prop` - the bean property for which to retrieve the column

`block` - run this block with the column as a receiver