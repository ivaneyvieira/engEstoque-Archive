[org.jetbrains.exposed.dao](../index.md) / [EntityClass](index.md) / [backReferencedOn](.)

# backReferencedOn

`infix fun <TargetID : Comparable<TargetID>, Target : `[`Entity`](../-entity/index.md)`<TargetID>> `[`EntityClass`](index.md)`<TargetID, Target>.backReferencedOn(column: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<`[`EntityID`](../-entity-i-d/index.md)`<ID>>): `[`BackReference`](../-back-reference/index.md)`<TargetID, Target, ID, `[`Entity`](../-entity/index.md)`<ID>>`
`infix fun <TargetID : Comparable<TargetID>, Target : `[`Entity`](../-entity/index.md)`<TargetID>> `[`EntityClass`](index.md)`<TargetID, Target>.backReferencedOn(column: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<`[`EntityID`](../-entity-i-d/index.md)`<ID>?>): `[`BackReference`](../-back-reference/index.md)`<TargetID, Target, ID, `[`Entity`](../-entity/index.md)`<ID>>`