[org.jetbrains.exposed.sql.statements](../index.md) / [StatementMonitor](.)

# StatementMonitor

`class StatementMonitor : Any`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `StatementMonitor()` |

### Functions

| Name | Summary |
|---|---|
| [notifyAfterExecution](notify-after-execution.md) | `fun notifyAfterExecution(transaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`, executedContexts: List<`[`StatementContext`](../-statement-context/index.md)`>, executedStatement: `[`PreparedStatement`](http://docs.oracle.com/javase/6/docs/api/java/sql/PreparedStatement.html)`): Unit` |
| [notifyBeforeExecution](notify-before-execution.md) | `fun notifyBeforeExecution(transaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`, context: `[`StatementContext`](../-statement-context/index.md)`): Unit` |
| [register](register.md) | `fun register(interceptor: `[`StatementInterceptor`](../-statement-interceptor/index.md)`): Boolean` |
| [unregister](unregister.md) | `fun unregister(interceptor: `[`StatementInterceptor`](../-statement-interceptor/index.md)`): Boolean` |
