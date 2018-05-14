[org.jetbrains.exposed.sql](../index.md) / [UserDataHolder](.)

# UserDataHolder

`open class UserDataHolder : Any`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `UserDataHolder()` |

### Properties

| Name | Summary |
|---|---|
| [userdata](userdata.md) | `val userdata: `[`ConcurrentHashMap`](http://docs.oracle.com/javase/6/docs/api/java/util/concurrent/ConcurrentHashMap.html)`<`[`Key`](../-key/index.md)`<*>, Any?>` |

### Functions

| Name | Summary |
|---|---|
| [getOrCreate](get-or-create.md) | `fun <T : Any> getOrCreate(key: `[`Key`](../-key/index.md)`<T>, init: () -> T): T` |
| [getUserData](get-user-data.md) | `fun <T : Any> getUserData(key: `[`Key`](../-key/index.md)`<T>): T?` |
| [putUserData](put-user-data.md) | `fun <T : Any> putUserData(key: `[`Key`](../-key/index.md)`<T>, value: T): Unit` |
| [removeUserData](remove-user-data.md) | `fun <T : Any> removeUserData(key: `[`Key`](../-key/index.md)`<T>): Any?` |

### Inheritors

| Name | Summary |
|---|---|
| [Transaction](../-transaction/index.md) | `open class Transaction : UserDataHolder, `[`TransactionInterface`](../../org.jetbrains.exposed.sql.transactions/-transaction-interface/index.md) |
