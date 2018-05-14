[org.jetbrains.exposed.sql](../index.md) / [SchemaUtils](index.md) / [createMissingTablesAndColumns](.)

# createMissingTablesAndColumns

`fun createMissingTablesAndColumns(vararg tables: `[`Table`](../-table/index.md)`): Unit`

This function should be used in cases when you want an easy-to-use auto-actualization of database scheme.
It will create all absent tables, add missing columns for existing tables if it's possible (columns are nullable or have default values).

Also if there is inconsistency in DB vs code mappings (excessive or absent indexes)
then DDLs to fix it will be logged to exposedLogger.

This functionality is based on jdbc metadata what might be a bit slow, so it is recommended to call this function once
at application startup and provide all tables you want to actualize.

Please note, that execution of this function concurrently might lead to unpredictable state in database due to
non-transactional behavior of some DBMS on processing DDL statements (e.g. MySQL) and metadata caches.

To prevent such cases is advised to use any "global" synchronization you prefer (via redis, memcached, etc) or
with Exposed's provided lock based on synchronization on a dummy "Buzy" table (@see SchemaUtils#withDataBaseLock).

