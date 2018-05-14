[com.github.vok.karibudsl](index.md) / [navigateToView](.)

# navigateToView

`fun navigateToView(view: `[`Class`](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)`<out <ERROR CLASS>>, vararg params: String): Unit`

`inline fun <reified V> navigateToView(vararg params: String): Unit`

Asks the current UI navigator to navigate to given view.

As a convention, you should introduce a static method `navigateTo(params)` to all of your views,
which will then simply call this function.

### Parameters

`V` - the class of the view, not null.

`params` - an optional list of string params. The View will receive the params via
[View.enter](#)'s [ViewChangeListener.ViewChangeEvent](#), use [parameterList](parameter-list.md) to parse them back in.