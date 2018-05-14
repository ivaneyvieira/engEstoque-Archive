[org.jetbrains.exposed.dao](../index.md) / [EntityClass](index.md) / [optionalReferrersOn](.)

# optionalReferrersOn

`infix fun <TargetID : Comparable<TargetID>> optionalReferrersOn(column: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<`[`EntityID`](../-entity-i-d/index.md)`<TargetID>?>): `[`OptionalReferrers`](../-optional-referrers/index.md)`<TargetID, `[`Entity`](../-entity/index.md)`<TargetID>, ID, T>`
`fun <TargetID : Comparable<TargetID>> optionalReferrersOn(column: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<`[`EntityID`](../-entity-i-d/index.md)`<TargetID>?>, cache: Boolean = false): `[`OptionalReferrers`](../-optional-referrers/index.md)`<TargetID, `[`Entity`](../-entity/index.md)`<TargetID>, ID, T>`