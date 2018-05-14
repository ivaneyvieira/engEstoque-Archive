[org.jetbrains.exposed.exceptions](../index.md) / [ExposedSQLException](.)

# ExposedSQLException

`class ExposedSQLException : `[`SQLException`](http://docs.oracle.com/javase/6/docs/api/java/sql/SQLException.html)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ExposedSQLException(cause: Throwable?, contexts: List<`[`StatementContext`](../../org.jetbrains.exposed.sql.statements/-statement-context/index.md)`>)` |

### Properties

| Name | Summary |
|---|---|
| [contexts](contexts.md) | `val contexts: List<`[`StatementContext`](../../org.jetbrains.exposed.sql.statements/-statement-context/index.md)`>` |

### Functions

| Name | Summary |
|---|---|
| [causedByQueries](caused-by-queries.md) | `fun causedByQueries(): List<String>` |
