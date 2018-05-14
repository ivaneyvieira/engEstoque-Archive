[com.github.vok.karibudsl](../index.md) / [java.net.URI](index.md) / [queryMap](.)

# queryMap

`val `[`URI`](http://docs.oracle.com/javase/6/docs/api/java/net/URI.html)`.queryMap: Map<String, List<String>>`

Parses the [URI.query](#) string and returns a map of all query parameters. Since a key may be present multiple times in the query,
the map maps key to a list of values. The order of the values follows the order in which the values were present in the query.

To retrieve the query map from Vaadin, just use `Page.getCurrent().location.queryMap`.

