[org.jetbrains.exposed.sql](index.md) / [leftJoin](.)

# leftJoin

`fun <C1 : `[`ColumnSet`](-column-set/index.md)`, C2 : `[`ColumnSet`](-column-set/index.md)`> C1.leftJoin(otherTable: C2, onColumn: (C1) -> `[`Expression`](-expression/index.md)`<*>, otherColumn: (C2) -> `[`Expression`](-expression/index.md)`<*>): `[`Join`](-join/index.md)