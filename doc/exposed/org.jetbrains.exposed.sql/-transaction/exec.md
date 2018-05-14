[org.jetbrains.exposed.sql](../index.md) / [Transaction](index.md) / [exec](.)

# exec

`fun exec(stmt: String): Unit?`
`fun <T : Any> exec(stmt: String, transform: (`[`ResultSet`](http://docs.oracle.com/javase/6/docs/api/java/sql/ResultSet.html)`) -> T): T?`
`fun <T> exec(stmt: `[`Statement`](../../org.jetbrains.exposed.sql.statements/-statement/index.md)`<T>): T?`
`fun <T, R> exec(stmt: `[`Statement`](../../org.jetbrains.exposed.sql.statements/-statement/index.md)`<T>, body: (`[`Statement`](../../org.jetbrains.exposed.sql.statements/-statement/index.md)`<T>, T) -> R): R?`