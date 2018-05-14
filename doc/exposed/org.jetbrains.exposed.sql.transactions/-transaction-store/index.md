[org.jetbrains.exposed.sql.transactions](../index.md) / [TransactionStore](.)

# TransactionStore

`class TransactionStore<T : Any> : Any`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `TransactionStore(init: () -> T = null)` |

### Properties

| Name | Summary |
|---|---|
| [init](init.md) | `val init: () -> T` |

### Functions

| Name | Summary |
|---|---|
| [getValue](get-value.md) | `fun getValue(thisRef: Any?, property: KProperty<*>): T?` |
| [setValue](set-value.md) | `fun setValue(thisRef: Any?, property: KProperty<*>, value: T?): Unit` |
