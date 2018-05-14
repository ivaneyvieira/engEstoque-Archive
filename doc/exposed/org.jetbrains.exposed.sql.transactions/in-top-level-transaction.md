[org.jetbrains.exposed.sql.transactions](index.md) / [inTopLevelTransaction](.)

# inTopLevelTransaction

`fun <T> inTopLevelTransaction(transactionIsolation: Int, repetitionAttempts: Int, manager: `[`TransactionManager`](-transaction-manager/index.md)`? = null, statement: (`[`Transaction`](../org.jetbrains.exposed.sql/-transaction/index.md)`) -> T): T`