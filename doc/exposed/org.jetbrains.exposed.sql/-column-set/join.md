[org.jetbrains.exposed.sql](../index.md) / [ColumnSet](index.md) / [join](.)

# join

`abstract fun join(otherTable: `[`ColumnSet`](index.md)`, joinType: `[`JoinType`](../-join-type/index.md)`, onColumn: `[`Expression`](../-expression/index.md)`<*>? = null, otherColumn: `[`Expression`](../-expression/index.md)`<*>? = null, additionalConstraint: (`[`SqlExpressionBuilder`](../-sql-expression-builder/index.md)`) -> `[`Op`](../-op/index.md)`<Boolean> = null): `[`Join`](../-join/index.md)