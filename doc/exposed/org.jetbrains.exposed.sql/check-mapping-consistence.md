[org.jetbrains.exposed.sql](index.md) / [checkMappingConsistence](.)

# checkMappingConsistence

`fun checkMappingConsistence(vararg tables: `[`Table`](-table/index.md)`): List<String>`

Log Exposed table mappings &lt;-&gt; real database mapping problems and returns DDL Statements to fix them

