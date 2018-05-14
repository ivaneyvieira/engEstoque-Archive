[org.jetbrains.exposed.dao](../index.md) / [EntityClass](index.md) / [optionalBackReferencedOn](.)

# optionalBackReferencedOn

`infix fun <TargetID : Comparable<TargetID>, Target : `[`Entity`](../-entity/index.md)`<TargetID>> `[`EntityClass`](index.md)`<TargetID, Target>.optionalBackReferencedOn(column: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<`[`EntityID`](../-entity-i-d/index.md)`<ID>>): `[`OptionalBackReference`](../-optional-back-reference/index.md)`<TargetID, Target, ID, `[`Entity`](../-entity/index.md)`<ID>>`
`infix fun <TargetID : Comparable<TargetID>, Target : `[`Entity`](../-entity/index.md)`<TargetID>> `[`EntityClass`](index.md)`<TargetID, Target>.optionalBackReferencedOn(column: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<`[`EntityID`](../-entity-i-d/index.md)`<ID>?>): `[`OptionalBackReference`](../-optional-back-reference/index.md)`<TargetID, Target, ID, `[`Entity`](../-entity/index.md)`<ID>>`