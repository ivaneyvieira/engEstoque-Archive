[org.jetbrains.exposed.sql](../index.md) / [Database](.)

# Database

`class Database : Any`

### Properties

| Name | Summary |
|---|---|
| [checkedIdentities](checked-identities.md) | `val checkedIdentities: `[`LinkedHashMap`](http://docs.oracle.com/javase/6/docs/api/java/util/LinkedHashMap.html)`<String, Boolean>` |
| [connector](connector.md) | `val connector: () -> `[`Connection`](http://docs.oracle.com/javase/6/docs/api/java/sql/Connection.html) |
| [extraNameCharacters](extra-name-characters.md) | `val extraNameCharacters: <ERROR CLASS>` |
| [identityQuoteString](identity-quote-string.md) | `val identityQuoteString: <ERROR CLASS>` |
| [keywords](keywords.md) | `val keywords: <ERROR CLASS>` |
| [shouldQuoteIdentifiers](should-quote-identifiers.md) | `val shouldQuoteIdentifiers: <ERROR CLASS>` |
| [supportsAlterTableWithAddColumn](supports-alter-table-with-add-column.md) | `val supportsAlterTableWithAddColumn: <ERROR CLASS>` |
| [supportsMultipleResultSets](supports-multiple-result-sets.md) | `val supportsMultipleResultSets: <ERROR CLASS>` |
| [url](url.md) | `val url: String` |
| [vendor](vendor.md) | `val vendor: String` |
| [version](version.md) | `val version: <ERROR CLASS>` |

### Functions

| Name | Summary |
|---|---|
| [isVersionCovers](is-version-covers.md) | `fun isVersionCovers(version: `[`BigDecimal`](http://docs.oracle.com/javase/6/docs/api/java/math/BigDecimal.html)`): <ERROR CLASS>` |
| [needQuotes](need-quotes.md) | `fun needQuotes(identity: String): Boolean` |

### Companion Object Functions

| Name | Summary |
|---|---|
| [connect](connect.md) | `fun connect(datasource: `[`DataSource`](http://docs.oracle.com/javase/6/docs/api/javax/sql/DataSource.html)`, setupConnection: (`[`Connection`](http://docs.oracle.com/javase/6/docs/api/java/sql/Connection.html)`) -> Unit = {}, manager: (Database) -> `[`TransactionManager`](../../org.jetbrains.exposed.sql.transactions/-transaction-manager/index.md)` = { ThreadLocalTransactionManager(it, DEFAULT_ISOLATION_LEVEL) }): Database`<br>`fun connect(getNewConnection: () -> `[`Connection`](http://docs.oracle.com/javase/6/docs/api/java/sql/Connection.html)`, manager: (Database) -> `[`TransactionManager`](../../org.jetbrains.exposed.sql.transactions/-transaction-manager/index.md)` = { ThreadLocalTransactionManager(it, DEFAULT_ISOLATION_LEVEL) }): Database`<br>`fun connect(url: String, driver: String, user: String = "", password: String = "", setupConnection: (`[`Connection`](http://docs.oracle.com/javase/6/docs/api/java/sql/Connection.html)`) -> Unit = {}, manager: (Database) -> `[`TransactionManager`](../../org.jetbrains.exposed.sql.transactions/-transaction-manager/index.md)` = { ThreadLocalTransactionManager(it, DEFAULT_ISOLATION_LEVEL) }): Database` |
| [registerDialect](register-dialect.md) | `fun registerDialect(prefix: String, dialect: () -> `[`DatabaseDialect`](../../org.jetbrains.exposed.sql.vendors/-database-dialect/index.md)`): Unit` |

### Extension Properties

| Name | Summary |
|---|---|
| [name](../name.md) | `val Database.name: String` |
