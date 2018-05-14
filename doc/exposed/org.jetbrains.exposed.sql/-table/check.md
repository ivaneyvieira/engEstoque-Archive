[org.jetbrains.exposed.sql](../index.md) / [Table](index.md) / [check](.)

# check

`fun <T> `[`Column`](../-column/index.md)`<T>.check(name: String = "", op: (`[`SqlExpressionBuilder`](../-sql-expression-builder/index.md)`, `[`Column`](../-column/index.md)`<T>) -> `[`Op`](../-op/index.md)`<Boolean>): <ERROR CLASS>`

Creates a check constraint in this column.

### Parameters

`name` - The name to identify the constraint, optional. Must be **unique** (case-insensitive) to this table, otherwise, the constraint will
not be created. All names are [trimmed](#), blank names are ignored and the database engine decides the default name.

`op` - The expression against which the newly inserted values will be compared.

`fun check(name: String = "", op: (`[`SqlExpressionBuilder`](../-sql-expression-builder/index.md)`) -> `[`Op`](../-op/index.md)`<Boolean>): Unit`

Creates a check constraint in this table.

### Parameters

`name` - The name to identify the constraint, optional. Must be **unique** (case-insensitive) to this table, otherwise, the constraint will
not be created. All names are [trimmed](#), blank names are ignored and the database engine decides the default name.

`op` - The expression against which the newly inserted values will be compared.