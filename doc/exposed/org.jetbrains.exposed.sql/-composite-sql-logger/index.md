[org.jetbrains.exposed.sql](../index.md) / [CompositeSqlLogger](.)

# CompositeSqlLogger

`class CompositeSqlLogger : `[`SqlLogger`](../-sql-logger/index.md)`, `[`StatementInterceptor`](../../org.jetbrains.exposed.sql.statements/-statement-interceptor/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `CompositeSqlLogger()` |

### Functions

| Name | Summary |
|---|---|
| [addLogger](add-logger.md) | `fun addLogger(logger: `[`SqlLogger`](../-sql-logger/index.md)`): Unit` |
| [afterExecution](after-execution.md) | `fun afterExecution(transaction: `[`Transaction`](../-transaction/index.md)`, contexts: List<`[`StatementContext`](../../org.jetbrains.exposed.sql.statements/-statement-context/index.md)`>, executedStatement: `[`PreparedStatement`](http://docs.oracle.com/javase/6/docs/api/java/sql/PreparedStatement.html)`): Unit` |
| [beforeExecution](before-execution.md) | `fun beforeExecution(transaction: `[`Transaction`](../-transaction/index.md)`, context: `[`StatementContext`](../../org.jetbrains.exposed.sql.statements/-statement-context/index.md)`): Unit` |
| [log](log.md) | `fun log(context: `[`StatementContext`](../../org.jetbrains.exposed.sql.statements/-statement-context/index.md)`, transaction: `[`Transaction`](../-transaction/index.md)`): Unit` |
| [removeLogger](remove-logger.md) | `fun removeLogger(logger: `[`SqlLogger`](../-sql-logger/index.md)`): Unit` |
