[org.jetbrains.exposed.sql.transactions](../index.md) / [TransactionManager](.)

# TransactionManager

`interface TransactionManager : Any`

### Properties

| Name | Summary |
|---|---|
| [defaultIsolationLevel](default-isolation-level.md) | `abstract var defaultIsolationLevel: Int` |

### Functions

| Name | Summary |
|---|---|
| [currentOrNull](current-or-null.md) | `abstract fun currentOrNull(): `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`?` |
| [newTransaction](new-transaction.md) | `abstract fun newTransaction(isolation: Int = defaultIsolationLevel): `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md) |

### Companion Object Properties

| Name | Summary |
|---|---|
| [manager](manager.md) | `val manager: TransactionManager` |

### Companion Object Functions

| Name | Summary |
|---|---|
| [current](current.md) | `fun current(): <ERROR CLASS>` |
| [currentOrNew](current-or-new.md) | `fun currentOrNew(isolation: Int): `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md) |
| [currentOrNull](current-or-null.md) | `fun currentOrNull(): `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`?` |
| [isInitialized](is-initialized.md) | `fun isInitialized(): Boolean` |
| [registerManager](register-manager.md) | `fun registerManager(database: `[`Database`](../../org.jetbrains.exposed.sql/-database/index.md)`, manager: TransactionManager): Unit` |
| [resetCurrent](reset-current.md) | `fun resetCurrent(manager: TransactionManager?): Unit` |

### Inheritors

| Name | Summary |
|---|---|
| [ThreadLocalTransactionManager](../-thread-local-transaction-manager/index.md) | `class ThreadLocalTransactionManager : TransactionManager` |
