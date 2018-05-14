[org.jetbrains.exposed.sql](../index.md) / [Table](index.md) / [join](.)

# join

`open fun join(otherTable: `[`ColumnSet`](../-column-set/index.md)`, joinType: `[`JoinType`](../-join-type/index.md)`, onColumn: `[`Expression`](../-expression/index.md)`<*>?, otherColumn: `[`Expression`](../-expression/index.md)`<*>?, additionalConstraint: (`[`SqlExpressionBuilder`](../-sql-expression-builder/index.md)`) -> `[`Op`](../-op/index.md)`<Boolean>): `[`Join`](../-join/index.md)

Overrides [ColumnSet.join](../-column-set/join.md)


`infix fun ~~join~~(otherTable: `[`ColumnSet`](../-column-set/index.md)`): `[`Join`](../-join/index.md)
**Deprecated:** Just an alias to innerJoin

