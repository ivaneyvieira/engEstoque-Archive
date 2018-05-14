[org.jetbrains.exposed.sql.transactions](.)

## Package org.jetbrains.exposed.sql.transactions

### Types

| Name | Summary |
|---|---|
| [ThreadLocalTransactionManager](-thread-local-transaction-manager/index.md) | `class ThreadLocalTransactionManager : `[`TransactionManager`](-transaction-manager/index.md) |
| [TransactionInterface](-transaction-interface/index.md) | `interface TransactionInterface : Any` |
| [TransactionManager](-transaction-manager/index.md) | `interface TransactionManager : Any` |
| [TransactionStore](-transaction-store/index.md) | `class TransactionStore<T : Any> : Any` |

### Properties

| Name | Summary |
|---|---|
| [DEFAULT_ISOLATION_LEVEL](-d-e-f-a-u-l-t_-i-s-o-l-a-t-i-o-n_-l-e-v-e-l.md) | `const val DEFAULT_ISOLATION_LEVEL: Int` |

### Functions

| Name | Summary |
|---|---|
| [inTopLevelTransaction](in-top-level-transaction.md) | `fun <T> inTopLevelTransaction(transactionIsolation: Int, repetitionAttempts: Int, manager: `[`TransactionManager`](-transaction-manager/index.md)`? = null, statement: (`[`Transaction`](../org.jetbrains.exposed.sql/-transaction/index.md)`) -> T): T` |
| [nullableTransactionScope](nullable-transaction-scope.md) | `fun <T : Any> nullableTransactionScope(): `[`TransactionStore`](-transaction-store/index.md)`<T>` |
| [transaction](transaction.md) | `fun <T> transaction(db: `[`Database`](../org.jetbrains.exposed.sql/-database/index.md)`? = null, statement: (`[`Transaction`](../org.jetbrains.exposed.sql/-transaction/index.md)`) -> T): T`<br>`fun <T> transaction(transactionIsolation: Int, repetitionAttempts: Int, db: `[`Database`](../org.jetbrains.exposed.sql/-database/index.md)`? = null, statement: (`[`Transaction`](../org.jetbrains.exposed.sql/-transaction/index.md)`) -> T): T` |
| [transactionScope](transaction-scope.md) | `fun <T : Any> transactionScope(init: () -> T): <ERROR CLASS><Any?, T>` |
