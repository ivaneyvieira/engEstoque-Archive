[com.github.vok.karibudsl](index.md) / [autoDiscoverViews](.)

# autoDiscoverViews

`fun autoDiscoverViews(packageName: String? = null): Unit`

Auto-discovers views and register them to [autoViewProvider](auto-view-provider.md). Can be called multiple times.

DON'T CALL THIS in Servlet environment - Servlet container is responsible to discover views.
This function is intended to be used in tests only.

### Parameters

`packageName` - set the package name for the detector to be faster; or provide null to scan the whole classpath, but this is quite slow.