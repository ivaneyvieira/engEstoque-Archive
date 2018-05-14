[com.github.vok.karibudsl](../index.md) / [ValoMenu](index.md) / [viewPlaceholder](.)

# viewPlaceholder

`var viewPlaceholder: <ERROR CLASS>`

The current view placeholder - all views will be placed here. By default a full-screen CssLayout which scrolls its contents;
you can set a different placeholder component to replace the original one.

Note: the old placeholder is removed from [ValoMenu](index.md), but the new one is not added. This supports more complex placeholder
layouts where the placeholder is not directly nested in the [ValoMenu](index.md) layout.
When you add a component to [ValoMenu](index.md) to act as a placeholder (or to host a placeholder), don't forget to set the following on the newly added component:

```
primaryStyleName = "valo-content"; setSizeFull(); expandRatio = 1f
```

