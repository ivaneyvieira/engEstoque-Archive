[org.jetbrains.exposed.sql](../index.md) / [SqlLogger](.)

# SqlLogger

`interface SqlLogger : Any`

### Functions

| Name | Summary |
|---|---|
| [log](log.md) | `abstract fun log(context: `[`StatementContext`](../../org.jetbrains.exposed.sql.statements/-statement-context/index.md)`, transaction: `[`Transaction`](../-transaction/index.md)`): Unit` |

### Inheritors

| Name | Summary |
|---|---|
| [CompositeSqlLogger](../-composite-sql-logger/index.md) | `class CompositeSqlLogger : SqlLogger, `[`StatementInterceptor`](../../org.jetbrains.exposed.sql.statements/-statement-interceptor/index.md) |
| [Slf4jSqlDebugLogger](../-slf4j-sql-debug-logger/index.md) | `object Slf4jSqlDebugLogger : SqlLogger` |
| [StdOutSqlLogger](../-std-out-sql-logger/index.md) | `object StdOutSqlLogger : SqlLogger` |
