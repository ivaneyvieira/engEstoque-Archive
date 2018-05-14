[org.jetbrains.exposed.sql.transactions](index.md) / [transaction](.)

# transaction

`fun <T> transaction(db: `[`Database`](../org.jetbrains.exposed.sql/-database/index.md)`? = null, statement: (`[`Transaction`](../org.jetbrains.exposed.sql/-transaction/index.md)`) -> T): T`
`fun <T> transaction(transactionIsolation: Int, repetitionAttempts: Int, db: `[`Database`](../org.jetbrains.exposed.sql/-database/index.md)`? = null, statement: (`[`Transaction`](../org.jetbrains.exposed.sql/-transaction/index.md)`) -> T): T`