[org.jetbrains.exposed.sql.statements](../index.md) / [StatementInterceptor](.)

# StatementInterceptor

`interface StatementInterceptor : Any`

### Functions

| Name | Summary |
|---|---|
| [afterExecution](after-execution.md) | `abstract fun afterExecution(transaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`, contexts: List<`[`StatementContext`](../-statement-context/index.md)`>, executedStatement: `[`PreparedStatement`](http://docs.oracle.com/javase/6/docs/api/java/sql/PreparedStatement.html)`): Unit` |
| [beforeExecution](before-execution.md) | `abstract fun beforeExecution(transaction: `[`Transaction`](../../org.jetbrains.exposed.sql/-transaction/index.md)`, context: `[`StatementContext`](../-statement-context/index.md)`): Unit` |

### Inheritors

| Name | Summary |
|---|---|
| [CompositeSqlLogger](../../org.jetbrains.exposed.sql/-composite-sql-logger/index.md) | `class CompositeSqlLogger : `[`SqlLogger`](../../org.jetbrains.exposed.sql/-sql-logger/index.md)`, StatementInterceptor` |
