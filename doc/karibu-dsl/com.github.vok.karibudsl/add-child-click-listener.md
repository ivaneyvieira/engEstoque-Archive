[com.github.vok.karibudsl](index.md) / [addChildClickListener](.)

# addChildClickListener

`fun <ERROR CLASS>.addChildClickListener(listener: (<ERROR CLASS>) -> Unit): Unit`

Adds a click listener to a layout. The click listener will be called when the layout and any descendant component is clicked,
except for descendants which have their own click listeners attached.

Only left mouse button clicks are reported; double-clicks are ignored.

### Parameters

`listener` - the click listener.