[com.github.vok.karibudsl](index.md) / [parameterList](.)

# parameterList

`val <ERROR CLASS>.parameterList: Map<Int, String>`

Parses the parameters back from the URI fragment. See [navigateToView](navigate-to-view.md) for details. Call in [ViewChangeListener.ViewChangeEvent](#) provided to you in the
[View.enter](#) method.

Note that the parameters are not named - instead, this is a simple list of values.

To obtain a particular parameter or null if the URL has no such parameter, just call [Map.get](#).

**Return**
list of parameters, empty if there are no parameters.

