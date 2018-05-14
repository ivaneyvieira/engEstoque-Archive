[com.github.vok.karibudsl](../index.md) / [_TabSheet](index.md) / [tab](.)

# tab

`val <ERROR CLASS>.tab: <ERROR CLASS>`

Allows you to access the current tab from the DSL:

``` kotlin
tabSheet {
   // adding a component to tabsheet will create a tab for the component as well.
   label("Foo bar baz blah blah blah") {
     tab.caption = "Tab 1"
     tab.icon = VaadinIcons.TAB
   }
}
```

