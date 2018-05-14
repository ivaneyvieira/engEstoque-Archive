[org.jetbrains.exposed.dao](../index.md) / [EntityHook](.)

# EntityHook

`object EntityHook : Any`

### Properties

| Name | Summary |
|---|---|
| [registeredEvents](registered-events.md) | `val registeredEvents: List<`[`EntityChange`](../-entity-change/index.md)`>` |

### Functions

| Name | Summary |
|---|---|
| [alertSubscribers](alert-subscribers.md) | `fun alertSubscribers(): Unit` |
| [registerChange](register-change.md) | `fun registerChange(change: `[`EntityChange`](../-entity-change/index.md)`): Unit` |
| [subscribe](subscribe.md) | `fun subscribe(action: (`[`EntityChange`](../-entity-change/index.md)`) -> Unit): (`[`EntityChange`](../-entity-change/index.md)`) -> Unit` |
| [unsubscribe](unsubscribe.md) | `fun unsubscribe(action: (`[`EntityChange`](../-entity-change/index.md)`) -> Unit): Unit` |
