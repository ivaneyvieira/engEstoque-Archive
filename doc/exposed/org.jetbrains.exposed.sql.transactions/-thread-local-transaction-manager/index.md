[org.jetbrains.exposed.sql.transactions](../index.md) / [ThreadLocalTransactionManager](.)

# ThreadLocalTransactionManager

`class ThreadLocalTransactionManager : `[`TransactionManager`](../-transaction-manager/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ThreadLocalTransactionManager(db: `[`Database`](../../org.jetbrains.exposed.sql/-database/index.md)`, defaultIsolationLevel: Int)` |

### Properties

| Name | Summary |
|---|---|
| [defaultIsolationLevel](default-isolation-level.md) | `var defaultIsolationLevel: Int` |
| [threadLocal](thread-local.md) | `val threadLocal: `[`ThreadLocal`](http://docs.oracle.com/javase/6/docs/api/java/lang/ThreadLocal.html)`<`[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`>` |

### Functions

| Name | Summary |
|---|---|
| [currentOrNull](current-or-null.md) | `fun currentOrNull(): `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`?` |
| [newTransaction](new-transaction.md) | `fun newTransaction(isolation: Int): `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md) |
