[com.github.vok.karibudsl](../index.md) / [LocalDateToInstantConverter](.)

# LocalDateToInstantConverter

`class LocalDateToInstantConverter : Any`

A converter that converts between [LocalDate](http://docs.oracle.com/javase/6/docs/api/java/time/LocalDate.html) and [Instant](http://docs.oracle.com/javase/6/docs/api/java/time/Instant.html).

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `LocalDateToInstantConverter(zoneId: `[`ZoneId`](http://docs.oracle.com/javase/6/docs/api/java/time/ZoneId.html)` = browserTimeZone)`<br>A converter that converts between [LocalDate](http://docs.oracle.com/javase/6/docs/api/java/time/LocalDate.html) and [Instant](http://docs.oracle.com/javase/6/docs/api/java/time/Instant.html). |

### Properties

| Name | Summary |
|---|---|
| [zoneId](zone-id.md) | `val zoneId: `[`ZoneId`](http://docs.oracle.com/javase/6/docs/api/java/time/ZoneId.html)<br>the time zone id to use. |

### Functions

| Name | Summary |
|---|---|
| [convertToModel](convert-to-model.md) | `fun convertToModel(localDate: `[`LocalDate`](http://docs.oracle.com/javase/6/docs/api/java/time/LocalDate.html)`?, context: <ERROR CLASS>): <ERROR CLASS><`[`Instant`](http://docs.oracle.com/javase/6/docs/api/java/time/Instant.html)`?>` |
| [convertToPresentation](convert-to-presentation.md) | `fun convertToPresentation(date: `[`Instant`](http://docs.oracle.com/javase/6/docs/api/java/time/Instant.html)`?, context: <ERROR CLASS>): `[`LocalDate`](http://docs.oracle.com/javase/6/docs/api/java/time/LocalDate.html)`?` |
