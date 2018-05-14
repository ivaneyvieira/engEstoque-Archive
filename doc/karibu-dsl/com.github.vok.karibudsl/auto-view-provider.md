[com.github.vok.karibudsl](index.md) / [autoViewProvider](.)

# autoViewProvider

`val autoViewProvider: AutoViewProvider.Companion`

Set this view provider to the [com.vaadin.navigator.Navigator](#):

`navigator.addProvider(autoViewProvider)`

The view provider will auto-discover all of your views and will create names for them, see [AutoView](-auto-view/index.md) for more details.
Only views annotated with [AutoView](-auto-view/index.md) are discovered - this is to avoid automagically exposing all of views packaged in all jars,
even unwanted ones.

To navigate to a view, just call the [navigateToView](navigate-to-view.md) helper method which will generate the correct URI fragment and will navigate.
You can parse the parameters back later on in your [View.enter](#), by calling `event.parameterList`.

