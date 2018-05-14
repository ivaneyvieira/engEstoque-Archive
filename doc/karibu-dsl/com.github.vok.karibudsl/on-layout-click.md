[com.github.vok.karibudsl](index.md) / [onLayoutClick](.)

# onLayoutClick

`fun <ERROR CLASS>.onLayoutClick(listener: (<ERROR CLASS>) -> Unit): Unit`

Sets a click listener to a layout. The click listener will be called when the layout and any descendant component is clicked,
except for descendants which have their own click listeners attached. Note that Vaadin does not fire this event e.g. when clicking
on children's captions, so this is not 100% perfect.

Removes any previously attached layout click listeners

### Parameters

`listener` - the click listener.