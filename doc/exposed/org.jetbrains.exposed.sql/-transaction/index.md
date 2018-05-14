[org.jetbrains.exposed.sql](../index.md) / [Transaction](.)

# Transaction

`open class Transaction : `[`UserDataHolder`](../-user-data-holder/index.md)`, `[`TransactionInterface`](../../org.jetbrains.exposed.sql.transactions/-transaction-interface/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Transaction(transactionImpl: `[`TransactionInterface`](../../org.jetbrains.exposed.sql.transactions/-transaction-interface/index.md)`)` |

### Properties

| Name | Summary |
|---|---|
| [currentStatement](current-statement.md) | `var currentStatement: `[`PreparedStatement`](http://docs.oracle.com/javase/6/docs/api/java/sql/PreparedStatement.html)`?` |
| [debug](debug.md) | `var debug: Boolean` |
| [duration](duration.md) | `var duration: Long` |
| [entityCache](entity-cache.md) | `val entityCache: `[`EntityCache`](../../org.jetbrains.exposed.dao/-entity-cache/index.md) |
| [logger](logger.md) | `val logger: `[`CompositeSqlLogger`](../-composite-sql-logger/index.md) |
| [monitor](monitor.md) | `val monitor: `[`StatementMonitor`](../../org.jetbrains.exposed.sql.statements/-statement-monitor/index.md) |
| [statementCount](statement-count.md) | `var statementCount: Int` |
| [statementStats](statement-stats.md) | `val statementStats: <ERROR CLASS>` |
| [statements](statements.md) | `val statements: `[`StringBuilder`](http://docs.oracle.com/javase/6/docs/api/java/lang/StringBuilder.html) |
| [warnLongQueriesDuration](warn-long-queries-duration.md) | `var warnLongQueriesDuration: Long?` |

### Inherited Properties

| Name | Summary |
|---|---|
| [userdata](../-user-data-holder/userdata.md) | `val userdata: `[`ConcurrentHashMap`](http://docs.oracle.com/javase/6/docs/api/java/util/concurrent/ConcurrentHashMap.html)`<`[`Key`](../-key/index.md)`<*>, Any?>` |

### Functions

| Name | Summary |
|---|---|
| [closeExecutedStatements](close-executed-statements.md) | `fun closeExecutedStatements(): Unit` |
| [commit](commit.md) | `open fun commit(): Unit` |
| [exec](exec.md) | `fun exec(stmt: String): Unit?`<br>`fun <T : Any> exec(stmt: String, transform: (`[`ResultSet`](http://docs.oracle.com/javase/6/docs/api/java/sql/ResultSet.html)`) -> T): T?`<br>`fun <T> exec(stmt: `[`Statement`](../../org.jetbrains.exposed.sql.statements/-statement/index.md)`<T>): T?`<br>`fun <T, R> exec(stmt: `[`Statement`](../../org.jetbrains.exposed.sql.statements/-statement/index.md)`<T>, body: (`[`Statement`](../../org.jetbrains.exposed.sql.statements/-statement/index.md)`<T>, T) -> R): R?` |
| [flushCache](flush-cache.md) | `fun flushCache(): List<`[`Entity`](../../org.jetbrains.exposed.dao/-entity/index.md)`<*>>` |
| [fullIdentity](full-identity.md) | `fun fullIdentity(column: `[`Column`](../-column/index.md)`<*>): String` |
| [identity](identity.md) | `fun identity(table: `[`Table`](../-table/index.md)`): String`<br>`fun identity(column: `[`Column`](../-column/index.md)`<*>): String` |

### Inherited Functions

| Name | Summary |
|---|---|
| [getOrCreate](../-user-data-holder/get-or-create.md) | `fun <T : Any> getOrCreate(key: `[`Key`](../-key/index.md)`<T>, init: () -> T): T` |
| [getUserData](../-user-data-holder/get-user-data.md) | `fun <T : Any> getUserData(key: `[`Key`](../-key/index.md)`<T>): T?` |
| [putUserData](../-user-data-holder/put-user-data.md) | `fun <T : Any> putUserData(key: `[`Key`](../-key/index.md)`<T>, value: T): Unit` |
| [removeUserData](../-user-data-holder/remove-user-data.md) | `fun <T : Any> removeUserData(key: `[`Key`](../-key/index.md)`<T>): Any?` |

### Extension Functions

| Name | Summary |
|---|---|
| [withDataBaseLock](../-schema-utils/with-data-base-lock.md) | `fun <T> Transaction.withDataBaseLock(body: () -> T): Unit`<br>Creates table with name "busy" (if not present) and single column to be used as "synchronization" point. Table wont be dropped after execution. |
