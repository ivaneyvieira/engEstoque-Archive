[org.jetbrains.exposed.dao](../index.md) / [EntityClass](index.md) / [referrersOn](.)

# referrersOn

`infix fun <TargetID : Comparable<TargetID>> referrersOn(column: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<`[`EntityID`](../-entity-i-d/index.md)`<TargetID>>): `[`Referrers`](../-referrers/index.md)`<TargetID, `[`Entity`](../-entity/index.md)`<TargetID>, ID, T>`
`fun <TargetID : Comparable<TargetID>> referrersOn(column: `[`Column`](../../org.jetbrains.exposed.sql/-column/index.md)`<`[`EntityID`](../-entity-i-d/index.md)`<TargetID>>, cache: Boolean): `[`Referrers`](../-referrers/index.md)`<TargetID, `[`Entity`](../-entity/index.md)`<TargetID>, ID, T>`