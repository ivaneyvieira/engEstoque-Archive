[org.jetbrains.exposed.sql.transactions](../index.md) / [TransactionInterface](.)

# TransactionInterface

`interface TransactionInterface : Any`

### Properties

| Name | Summary |
|---|---|
| [connection](connection.md) | `abstract val connection: `[`Connection`](http://docs.oracle.com/javase/6/docs/api/java/sql/Connection.html) |
| [db](db.md) | `abstract val db: `[`Database`](../../org.jetbrains.exposed.sql/-database/index.md) |
| [outerTransaction](outer-transaction.md) | `abstract val outerTransaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`?` |

### Functions

| Name | Summary |
|---|---|
| [close](close.md) | `abstract fun close(): Unit` |
| [commit](commit.md) | `abstract fun commit(): Unit` |
| [rollback](rollback.md) | `abstract fun rollback(): Unit` |

### Inheritors

| Name | Summary |
|---|---|
| [Transaction](../../org.jetbrains.exposed.sql/-transaction/index.md) | `open class Transaction : `[`UserDataHolder`](../../org.jetbrains.exposed.sql/-user-data-holder/index.md)`, TransactionInterface` |
