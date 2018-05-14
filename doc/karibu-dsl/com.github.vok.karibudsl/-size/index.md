[com.github.vok.karibudsl](../index.md) / [Size](.)

# Size

`data class Size : `[`Serializable`](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)

Represents a Vaadin component width or height.

### Parameters

`size` - the size, may be negative for undefined/wrapContent size.

`units` - states the size units.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Size(size: Float, units: <ERROR CLASS>)`<br>Represents a Vaadin component width or height. |

### Properties

| Name | Summary |
|---|---|
| [isFillParent](is-fill-parent.md) | `val isFillParent: Boolean`<br>true if this size is set to 100% and the component fills its parent in this dimension. |
| [isFixed](is-fixed.md) | `val isFixed: Boolean`<br>True if the component is of fixed size, e.g. 48px, 20em etc. When the size is fixed,
it cannot be [isWrapContent](is-wrap-content.md) nor [isFillParent](is-fill-parent.md) |
| [isFull](is-full.md) | `val isFull: Boolean`<br>Same as [isFillParent](is-fill-parent.md), it's here just to keep in sync with Vaadin terminology ([Component.setSizeFull](#)). |
| [isPercent](is-percent.md) | `val isPercent: Boolean` |
| [isPixels](is-pixels.md) | `val isPixels: Boolean` |
| [isUndefined](is-undefined.md) | `val isUndefined: Boolean`<br>Same as [isWrapContent](is-wrap-content.md), it's here just to keep in sync with Vaadin terminology ([Component.setSizeUndefined](#)). |
| [isWrapContent](is-wrap-content.md) | `val isWrapContent: Boolean`<br>true if this component wraps its content in this dimension (size is -1px). |
| [size](size.md) | `val size: Float` |
| [units](units.md) | `val units: <ERROR CLASS>` |

### Functions

| Name | Summary |
|---|---|
| [toString](to-string.md) | `fun toString(): String` |
