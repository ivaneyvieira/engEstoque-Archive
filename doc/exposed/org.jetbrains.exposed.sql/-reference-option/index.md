[org.jetbrains.exposed.sql](../index.md) / [ReferenceOption](.)

# ReferenceOption

`enum class ReferenceOption : Enum<ReferenceOption>`

### Enum Values

| Name | Summary |
|---|---|
| [CASCADE](-c-a-s-c-a-d-e.md) |  |
| [SET_NULL](-s-e-t_-n-u-l-l.md) |  |
| [RESTRICT](-r-e-s-t-r-i-c-t.md) |  |
| [NO_ACTION](-n-o_-a-c-t-i-o-n.md) |  |

### Functions

| Name | Summary |
|---|---|
| [toString](to-string.md) | `fun toString(): String` |

### Companion Object Functions

| Name | Summary |
|---|---|
| [resolveRefOptionFromJdbc](resolve-ref-option-from-jdbc.md) | `fun resolveRefOptionFromJdbc(refOption: Int): ReferenceOption` |
