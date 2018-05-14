[org.jetbrains.exposed.sql](../index.md) / [SchemaUtils](index.md) / [withDataBaseLock](.)

# withDataBaseLock

`fun <T> `[`Transaction`](../-transaction/index.md)`.withDataBaseLock(body: () -> T): Unit`

Creates table with name "busy" (if not present) and single column to be used as "synchronization" point. Table wont be dropped after execution.

All code provided in *body* closure will be executed only if there is no another code which running under "withDataBaseLock" at same time.
That means that concurrent execution of long running tasks under "database lock" might lead to that only first of them will be really executed.

