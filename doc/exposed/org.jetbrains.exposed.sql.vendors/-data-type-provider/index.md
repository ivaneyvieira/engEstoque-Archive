[org.jetbrains.exposed.sql.vendors](../index.md) / [DataTypeProvider](.)

# DataTypeProvider

`open class DataTypeProvider : Any`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `DataTypeProvider()` |

### Properties

| Name | Summary |
|---|---|
| [blobAsStream](blob-as-stream.md) | `open val blobAsStream: Boolean` |

### Functions

| Name | Summary |
|---|---|
| [binaryType](binary-type.md) | `open fun binaryType(length: Int): String` |
| [blobType](blob-type.md) | `open fun blobType(): String` |
| [booleanFromStringToBoolean](boolean-from-string-to-boolean.md) | `open fun booleanFromStringToBoolean(value: String): Boolean` |
| [booleanToStatementString](boolean-to-statement-string.md) | `open fun booleanToStatementString(bool: Boolean): String` |
| [booleanType](boolean-type.md) | `open fun booleanType(): String` |
| [dateTimeType](date-time-type.md) | `open fun dateTimeType(): String` |
| [floatType](float-type.md) | `open fun floatType(): String` |
| [longAutoincType](long-autoinc-type.md) | `open fun longAutoincType(): String` |
| [longType](long-type.md) | `open fun longType(): String` |
| [processForDefaultValue](process-for-default-value.md) | `open fun processForDefaultValue(e: `[`Expression`](../../org.jetbrains.exposed.sql/-expression/index.md)`<*>): String` |
| [shortAutoincType](short-autoinc-type.md) | `open fun shortAutoincType(): String` |
| [shortType](short-type.md) | `open fun shortType(): String` |
| [textType](text-type.md) | `open fun textType(): String` |
| [uuidToDB](uuid-to-d-b.md) | `open fun uuidToDB(value: `[`UUID`](http://docs.oracle.com/javase/6/docs/api/java/util/UUID.html)`): Any` |
| [uuidType](uuid-type.md) | `open fun uuidType(): String` |
