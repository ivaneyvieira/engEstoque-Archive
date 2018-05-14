[com.github.vok.karibudsl](.)

## Package com.github.vok.karibudsl

### Types

| Name | Summary |
|---|---|
| [AutoViewProvider](-auto-view-provider/index.md) | `class AutoViewProvider : Any`<br>Internal class which enumerates views. Do not use directly - instead, just add [autoViewProvider](auto-view-provider.md) to your [com.vaadin.navigator.Navigator](#),
see [autoViewProvider](auto-view-provider.md) for more details. |
| [KeyShortcut](-key-shortcut/index.md) | `data class KeyShortcut : `[`Serializable`](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)<br>Denotes a keyboard shortcut, such as [ModifierKey.Ctrl](-modifier-key/-ctrl.md)+[ModifierKey.Alt](-modifier-key/-alt.md)+[ShortcutAction.KeyCode.C](#)`. When properly imported, this
becomes `Ctrl+Alt+C` ;) |
| [LocalDateTimeToInstantConverter](-local-date-time-to-instant-converter/index.md) | `class LocalDateTimeToInstantConverter : Any`<br>A converter that converts between [LocalDateTime](http://docs.oracle.com/javase/6/docs/api/java/time/LocalDateTime.html) and [Instant](http://docs.oracle.com/javase/6/docs/api/java/time/Instant.html). |
| [LocalDateToInstantConverter](-local-date-to-instant-converter/index.md) | `class LocalDateToInstantConverter : Any`<br>A converter that converts between [LocalDate](http://docs.oracle.com/javase/6/docs/api/java/time/LocalDate.html) and [Instant](http://docs.oracle.com/javase/6/docs/api/java/time/Instant.html). |
| [MenuButton](-menu-button/index.md) | `class MenuButton : Any` |
| [ModifierKey](-modifier-key/index.md) | `enum class ModifierKey : Enum<`[`ModifierKey`](-modifier-key/index.md)`>` |
| [SimpleContent](-simple-content/index.md) | `data class SimpleContent : Any` |
| [Size](-size/index.md) | `data class Size : `[`Serializable`](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)<br>Represents a Vaadin component width or height. |
| [SpecialContainer](-special-container/index.md) | `interface SpecialContainer : Any`<br>A specialized version of [ComponentContainer](#), for certain special containers. The DSL's
[init](init.md) method will invoke [addComponent](-special-container/add-component.md) method with the components being registered. |
| [TreeIterator](-tree-iterator/index.md) | `class TreeIterator<out T> : Iterator<T>`<br>Given a tree root and a closure which computes children, iterates recursively over a tree of objects.
Iterator works breadth-first: initially the root itself is offered, then its children, then the child's children etc. |
| [VAlign](-v-align/index.md) | `enum class VAlign : Enum<`[`VAlign`](-v-align/index.md)`>` |
| [ValoMenu](-valo-menu/index.md) | `open class ValoMenu : Any`<br>A main screen with a responsive Valo menu and a view placeholder, where the View contents will go upon navigation.
You should set this class as the contents of your UI and set it to the [Navigator](#) as follows: |
| [_Accordion](_-accordion/index.md) | `class _Accordion : Any` |
| [_TabSheet](_-tab-sheet/index.md) | `class _TabSheet : Any` |

### Annotations

| Name | Summary |
|---|---|
| [AutoView](-auto-view/index.md) | `annotation class AutoView : Annotation`<br>Annotate your views with this annotation, and the [autoViewProvider](auto-view-provider.md) will auto-discover them and register them. |
| [VaadinDsl](-vaadin-dsl/index.md) | `annotation class VaadinDsl : Annotation` |

### Extensions for External Classes

| Name | Summary |
|---|---|
| [java.net.URI](java.net.-u-r-i/index.md) |  |
| [kotlin.Float](kotlin.-float/index.md) |  |
| [kotlin.Int](kotlin.-int/index.md) |  |
| [kotlin.collections.Iterable](kotlin.collections.-iterable/index.md) |  |
| [kotlin.collections.Set](kotlin.collections.-set/index.md) |  |
| [kotlin.reflect.KProperty1](kotlin.reflect.-k-property1/index.md) |  |

### Properties

| Name | Summary |
|---|---|
| [absolutePosition](absolute-position.md) | `val <ERROR CLASS>.absolutePosition: <ERROR CLASS>`<br>Returns the [AbsoluteLayout.ComponentPosition](#) of this component. Fails if this component is not nested inside [AbsoluteLayout](#) |
| [align](align.md) | `var <ERROR CLASS><out <ERROR CLASS>, out <ERROR CLASS>>.align: `[`VAlign`](-v-align/index.md)<br>Aligns the text in the columns, by using [setStyleGenerator](#) to set either `v-align-center` or `v-align-right` style. |
| [alignment](alignment.md) | `var <ERROR CLASS>.alignment: <ERROR CLASS>`<br>Sets or gets alignment for this component with respect to its parent layout. Use
predefined alignments from Alignment class. Fails if the component is not nested inside [AbstractOrderedLayout](#) |
| [autoViewProvider](auto-view-provider.md) | `val autoViewProvider: AutoViewProvider.Companion`<br>Set this view provider to the [com.vaadin.navigator.Navigator](#): |
| [bottom](bottom.md) | `var <ERROR CLASS>.bottom: `[`Size`](-size/index.md) |
| [browserTimeZone](browser-time-zone.md) | `val browserTimeZone: `[`ZoneId`](http://docs.oracle.com/javase/6/docs/api/java/time/ZoneId.html) |
| [clickShortcut](click-shortcut.md) | `var <ERROR CLASS>.clickShortcut: `[`KeyShortcut`](-key-shortcut/index.md)<br>Makes it possible to invoke a click on this button by pressing the given
[ShortcutAction.KeyCode](#) and (optional) [ModifierKey](-modifier-key/index.md)s.
The shortcut is global (bound to the containing Window). |
| [expandRatio](expand-ratio.md) | `var <ERROR CLASS>.expandRatio: Float`<br>Sets the expand ratio of this component with respect to its parent layout. See [AbstractOrderedLayout.setExpandRatio](#) for more details. |
| [fillParent](fill-parent.md) | `val fillParent: `[`Size`](-size/index.md)<br>Tells the component to fill-parent in particular direction. Typing `w = fillParent` is equal to calling setWidth("100%"). |
| [h](h.md) | `var <ERROR CLASS>.h: `[`Size`](-size/index.md) |
| [indices](indices.md) | `val <ERROR CLASS>.indices: IntRange`<br>Returns an [IntRange](#) of the valid component indices for this container. |
| [isExpanded](is-expanded.md) | `var <ERROR CLASS>.isExpanded: Boolean`<br>Sets the expand ratio of this component with respect to its parent layout. See [AbstractOrderedLayout.setExpandRatio](#) for more details. |
| [isMargin](is-margin.md) | `var <ERROR CLASS>.isMargin: Boolean`<br>Sets all four margins to given value. |
| [isMultiSelect](is-multi-select.md) | `val <ERROR CLASS><out <ERROR CLASS>>.isMultiSelect: Boolean` |
| [isSelectionEmpty](is-selection-empty.md) | `val <ERROR CLASS><out <ERROR CLASS>>.isSelectionEmpty: Boolean` |
| [isSingleSelect](is-single-select.md) | `val <ERROR CLASS><out <ERROR CLASS>>.isSingleSelect: Boolean` |
| [isSizeFull](is-size-full.md) | `val <ERROR CLASS>.isSizeFull: Boolean`<br>true if both the component width and height is set to 100% |
| [lastTab](last-tab.md) | `val <ERROR CLASS>.lastTab: <ERROR CLASS>` |
| [left](left.md) | `var <ERROR CLASS>.left: `[`Size`](-size/index.md) |
| [minimizedValueAsHTML](minimized-value-as-h-t-m-l.md) | `var <ERROR CLASS>.minimizedValueAsHTML: String`<br>Allows you to set the minimized text directly, without changing [popupComponent](popup-component.md) |
| [parameterList](parameter-list.md) | `val <ERROR CLASS>.parameterList: Map<Int, String>`<br>Parses the parameters back from the URI fragment. See [navigateToView](navigate-to-view.md) for details. Call in [ViewChangeListener.ViewChangeEvent](#) provided to you in the
[View.enter](#) method. |
| [popupComponent](popup-component.md) | `var <ERROR CLASS>.popupComponent: <ERROR CLASS>`<br>Allows you to set the popup component directly, without changing [minimizedValueAsHTML](minimized-value-as-h-t-m-l.md) |
| [right](right.md) | `var <ERROR CLASS>.right: `[`Size`](-size/index.md) |
| [styleNames](style-names.md) | `val <ERROR CLASS>.styleNames: Set<String>`<br>Returns a set of styles currently present on the component. |
| [top](top.md) | `var <ERROR CLASS>.top: `[`Size`](-size/index.md) |
| [w](w.md) | `var <ERROR CLASS>.w: `[`Size`](-size/index.md) |
| [wrapContent](wrap-content.md) | `val wrapContent: `[`Size`](-size/index.md)<br>Tells the component to wrap-content in particular direction. Typing `w = wrapContent` is equal to calling [Sizeable.setWidthUndefined](#)
or setWidth(null) or setWidth(-1, Sizeable.Unit.PIXELS). |
| [zIndex](z-index.md) | `var <ERROR CLASS>.zIndex: Int`<br>Sets [AbsoluteLayout.ComponentPosition.zIndex](#). Fails if this component is not nested inside [AbsoluteLayout](#) |

### Functions

| Name | Summary |
|---|---|
| [absoluteLayout](absolute-layout.md) | `fun <ERROR CLASS>.absoluteLayout(block: (<ERROR CLASS>) -> Unit = {}): <ERROR CLASS>` |
| [accordion](accordion.md) | `fun <ERROR CLASS>.accordion(block: (`[`_Accordion`](_-accordion/index.md)`) -> Unit = {}): <ERROR CLASS>` |
| [addChild](add-child.md) | `fun <ERROR CLASS>.addChild(child: <ERROR CLASS>): Unit`<br>Adds a [child](add-child.md#com.github.vok.karibudsl$addChild(, )/child) to this component. Only concrete subclasses are supported: |
| [addChildClickListener](add-child-click-listener.md) | `fun <ERROR CLASS>.addChildClickListener(listener: (<ERROR CLASS>) -> Unit): Unit`<br>Adds a click listener to a layout. The click listener will be called when the layout and any descendant component is clicked,
except for descendants which have their own click listeners attached. |
| [addColumnFor](add-column-for.md) | `fun <T, V> <ERROR CLASS><T>.addColumnFor(property: KProperty1<T, V>, block: (<ERROR CLASS><T, V>) -> Unit = {}): <ERROR CLASS><T, V>`<br>Adds a new column for given [property](add-column-for.md#com.github.vok.karibudsl$addColumnFor(((com.github.vok.karibudsl.addColumnFor.T)), kotlin.reflect.KProperty1((com.github.vok.karibudsl.addColumnFor.T, com.github.vok.karibudsl.addColumnFor.V)), kotlin.Function1((((com.github.vok.karibudsl.addColumnFor.T, com.github.vok.karibudsl.addColumnFor.V)), kotlin.Unit)))/property) which is by default sortable. The [Grid.Column.setId](#)
is set to property name, the column caption is set by converting camelCase to Human Friendly. |
| [addGlobalShortcutListener](add-global-shortcut-listener.md) | `fun <ERROR CLASS>.addGlobalShortcutListener(shortcut: `[`KeyShortcut`](-key-shortcut/index.md)`, action: () -> Unit): <ERROR CLASS>`<br>`fun <ERROR CLASS>.addGlobalShortcutListener(keyCode: Int, action: () -> Unit): <ERROR CLASS>`<br>Adds global shortcut listener. The listener is not added directly for this component - instead it is global, up to the nearest parent
Panel, UI or Window. |
| [audio](audio.md) | `fun <ERROR CLASS>.audio(caption: String? = null, resource: <ERROR CLASS>? = null, block: (<ERROR CLASS>) -> Unit = {}): <ERROR CLASS>` |
| [autoDiscoverViews](auto-discover-views.md) | `fun autoDiscoverViews(packageName: String? = null): Unit`<br>Auto-discovers views and register them to [autoViewProvider](auto-view-provider.md). Can be called multiple times. |
| [beanValidationBinder](bean-validation-binder.md) | `fun <T : Any> beanValidationBinder(): <ERROR CLASS><T>`<br>Allows you to create [BeanValidationBinder](#) like this: `beanValidationBinder<Person>()` instead of `BeanValidationBinder(Person::class.java)` |
| [bind](bind.md) | `fun <BEAN, FIELDVALUE> <ERROR CLASS><FIELDVALUE>.bind(binder: <ERROR CLASS><BEAN>): <ERROR CLASS><BEAN, FIELDVALUE>`<br>Allows you to bind the component directly in the component's definition. E.g.`fun <BEAN, FIELDVALUE> <ERROR CLASS><BEAN, FIELDVALUE>.bind(prop: KMutableProperty1<BEAN, out FIELDVALUE?>): <ERROR CLASS><BEAN, FIELDVALUE?>`<br>A type-safe binding which binds only to a property of given type, found on given bean. |
| [browserFrame](browser-frame.md) | `fun <ERROR CLASS>.browserFrame(url: String? = null, block: (<ERROR CLASS>) -> Unit = {}): <ERROR CLASS>` |
| [button](button.md) | `fun <ERROR CLASS>.button(caption: String? = null, block: (<ERROR CLASS>) -> Unit = {}): <ERROR CLASS>` |
| [checkBox](check-box.md) | `fun <ERROR CLASS>.checkBox(caption: String? = null, checked: Boolean? = null, block: (<ERROR CLASS>) -> Unit = {}): <ERROR CLASS>` |
| [checkBoxGroup](check-box-group.md) | `fun <T> <ERROR CLASS>.checkBoxGroup(caption: String? = null, block: (<ERROR CLASS><T>) -> Unit = {}): <ERROR CLASS>` |
| [colorPicker](color-picker.md) | `fun <ERROR CLASS>.colorPicker(popupCaption: String? = null, block: (<ERROR CLASS>) -> Unit = {}): <ERROR CLASS>` |
| [column](column.md) | `fun <T, V> <ERROR CLASS><T>.column(prop: KProperty1<T, V>, block: (<ERROR CLASS><T, V>) -> Unit = {}): <ERROR CLASS><T, V>`<br>Allows you to re-configure a particular column in a Grid. E.g.: |
| [comboBox](combo-box.md) | `fun <T> <ERROR CLASS>.comboBox(caption: String? = null, block: (<ERROR CLASS><T>) -> Unit = {}): <ERROR CLASS>` |
| [cssLayout](css-layout.md) | `fun <ERROR CLASS>.cssLayout(caption: String? = null, block: (<ERROR CLASS>) -> Unit = {}): <ERROR CLASS>` |
| [dateField](date-field.md) | `fun <ERROR CLASS>.dateField(caption: String? = null, block: (<ERROR CLASS>) -> Unit = {}): <ERROR CLASS>` |
| [dateTimeField](date-time-field.md) | `fun <ERROR CLASS>.dateTimeField(caption: String? = null, block: (<ERROR CLASS>) -> Unit = {}): <ERROR CLASS>` |
| [dropDownButton](drop-down-button.md) | `fun <ERROR CLASS>.dropDownButton(caption: String, splitButton: Boolean = false, block: (<ERROR CLASS>) -> Unit = {}): <ERROR CLASS>`<br>A drop-down button emulated via a [MenuBar](#). See [https//demo.vaadin.com/valo-theme/#menubars](https//demo.vaadin.com/valo-theme/#menubars) for demo and details. |
| [embedded](embedded.md) | `fun <ERROR CLASS>.embedded(caption: String? = null, block: (<ERROR CLASS>) -> Unit = {}): <ERROR CLASS>` |
| [formLayout](form-layout.md) | `fun <ERROR CLASS>.formLayout(block: (<ERROR CLASS>) -> Unit = {}): <ERROR CLASS>` |
| [getAll](get-all.md) | `fun <T : Any, F> <ERROR CLASS><T, F>.getAll(): List<T>`<br>Returns all items provided by this data provider as an eager list. Careful with larger data! |
| [getColumnBy](get-column-by.md) | `fun <T, V> <ERROR CLASS><T>.getColumnBy(property: KProperty1<T, V>): <ERROR CLASS><T, V>`<br>Retrieves the column for given [property](get-column-by.md#com.github.vok.karibudsl$getColumnBy(((com.github.vok.karibudsl.getColumnBy.T)), kotlin.reflect.KProperty1((com.github.vok.karibudsl.getColumnBy.T, com.github.vok.karibudsl.getColumnBy.V)))/property); it matches [Grid.Column.getKey](#) to [KProperty1.name](#). |
| [getComponentAt](get-component-at.md) | `fun <ERROR CLASS>.getComponentAt(index: Int): <ERROR CLASS>`<br>Returns component at given [index](get-component-at.md#com.github.vok.karibudsl$getComponentAt(, kotlin.Int)/index). Optimized for [CssLayout](#) and [AbstractOrderedLayout](#)s, but works with any
[ComponentContainer](#). |
| [goBack](go-back.md) | `fun goBack(): <ERROR CLASS>`<br>Directs the browser to go back. |
| [grid](grid.md) | `fun <T : Any> <ERROR CLASS>.grid(itemClass: KClass<T>? = null, caption: String? = null, dataProvider: <ERROR CLASS><T, out <ERROR CLASS>>? = null, block: (<ERROR CLASS><T>) -> Unit = {}): <ERROR CLASS>`<br>Creates a grid. |
| [gridLayout](grid-layout.md) | `fun <ERROR CLASS>.gridLayout(columns: Int = 1, rows: Int = 1, block: (<ERROR CLASS>) -> Unit = {}): <ERROR CLASS>` |
| [hasStyleName](has-style-name.md) | `fun <ERROR CLASS>.hasStyleName(style: String): Boolean`<br>Checks whether the component has given [style](has-style-name.md#com.github.vok.karibudsl$hasStyleName(, kotlin.String)/style). |
| [horizontalLayout](horizontal-layout.md) | `fun <ERROR CLASS>.horizontalLayout(block: (<ERROR CLASS>) -> Unit = {}): <ERROR CLASS>` |
| [horizontalSplitPanel](horizontal-split-panel.md) | `fun <ERROR CLASS>.horizontalSplitPanel(block: (<ERROR CLASS>) -> Unit = {}): <ERROR CLASS>` |
| [html](html.md) | `fun <ERROR CLASS>.html(html: String?): Unit`<br>Shows given html in this label. |
| [image](image.md) | `fun <ERROR CLASS>.image(caption: String? = null, resource: <ERROR CLASS>? = null, block: (<ERROR CLASS>) -> Unit = {}): <ERROR CLASS>` |
| [init](init.md) | `fun <T> <ERROR CLASS>.init(component: T, block: (T) -> Unit = {}): T`<br>When introducing extensions for your custom components, just call this in your method. For example: |
| [inlineDateField](inline-date-field.md) | `fun <ERROR CLASS>.inlineDateField(caption: String? = null, block: (<ERROR CLASS>) -> Unit = {}): <ERROR CLASS>` |
| [inlineDateTimeField](inline-date-time-field.md) | `fun <ERROR CLASS>.inlineDateTimeField(caption: String? = null, block: (<ERROR CLASS>) -> Unit = {}): <ERROR CLASS>` |
| [item](item.md) | `fun <ERROR CLASS>.item(caption: String, menuSelected: (<ERROR CLASS>) -> Unit = null, block: (<ERROR CLASS>) -> Unit = {}): <ERROR CLASS>`<br>`fun <ERROR CLASS>.item(caption: String, icon: <ERROR CLASS>? = null, menuSelected: (<ERROR CLASS>) -> Unit = null, block: (<ERROR CLASS>) -> Unit = {}): <ERROR CLASS>`<br>`fun <ERROR CLASS>.item(caption: String, menuSelected: (<ERROR CLASS>) -> Unit = null, block: (<ERROR CLASS>) -> Unit = {}): <ERROR CLASS>`<br>`fun <ERROR CLASS>.item(caption: String, icon: <ERROR CLASS>? = null, menuSelected: (<ERROR CLASS>) -> Unit = null, block: (<ERROR CLASS>) -> Unit = {}): <ERROR CLASS>` |
| [label](label.md) | `fun <ERROR CLASS>.label(content: String? = null, block: (<ERROR CLASS>) -> Unit = {}): <ERROR CLASS>`<br>Creates a [Label](#) |
| [link](link.md) | `fun <ERROR CLASS>.link(caption: String? = null, url: String? = null, block: (<ERROR CLASS>) -> Unit = {}): <ERROR CLASS>` |
| [listSelect](list-select.md) | `fun <T> <ERROR CLASS>.listSelect(caption: String? = null, block: (<ERROR CLASS><T>) -> Unit = {}): <ERROR CLASS>` |
| [menuBar](menu-bar.md) | `fun <ERROR CLASS>.menuBar(block: (<ERROR CLASS>) -> Unit = {}): <ERROR CLASS>` |
| [nativeButton](native-button.md) | `fun <ERROR CLASS>.nativeButton(caption: String? = null, leftClickListener: (<ERROR CLASS>) -> Unit = null, block: (<ERROR CLASS>) -> Unit = {}): <ERROR CLASS>` |
| [nativeSelect](native-select.md) | `fun <T> <ERROR CLASS>.nativeSelect(caption: String? = null, block: (<ERROR CLASS><T>) -> Unit = {}): <ERROR CLASS>` |
| [navigateBack](navigate-back.md) | `fun navigateBack(): <ERROR CLASS>`<br>Alias for [goBack](go-back.md). |
| [navigateToView](navigate-to-view.md) | `fun navigateToView(view: `[`Class`](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)`<out <ERROR CLASS>>, vararg params: String): Unit``fun <V> navigateToView(vararg params: String): Unit`<br>Asks the current UI navigator to navigate to given view. |
| [onEnterPressed](on-enter-pressed.md) | `fun <ERROR CLASS>.onEnterPressed(enterListener: (<ERROR CLASS>) -> Unit): Unit`<br>Triggers given listener when the text field is focused and user presses the Enter key. |
| [onLayoutClick](on-layout-click.md) | `fun <ERROR CLASS>.onLayoutClick(listener: (<ERROR CLASS>) -> Unit): Unit`<br>Sets a click listener to a layout. The click listener will be called when the layout and any descendant component is clicked,
except for descendants which have their own click listeners attached. Note that Vaadin does not fire this event e.g. when clicking
on children's captions, so this is not 100% perfect. |
| [onLeftClick](on-left-click.md) | `fun <ERROR CLASS>.onLeftClick(listener: (<ERROR CLASS>) -> Unit): Unit`<br>`fun <ERROR CLASS>.onLeftClick(listener: (<ERROR CLASS>) -> Unit): Unit`<br>Replaces any click listeners with this one. |
| [panel](panel.md) | `fun <ERROR CLASS>.panel(caption: String? = null, block: (<ERROR CLASS>) -> Unit = {}): <ERROR CLASS>` |
| [passwordField](password-field.md) | `fun <ERROR CLASS>.passwordField(caption: String? = null, block: (<ERROR CLASS>) -> Unit = {}): <ERROR CLASS>`<br>Creates a [PasswordField](#). |
| [popupView](popup-view.md) | `fun <ERROR CLASS>.popupView(small: String? = null, block: (<ERROR CLASS>) -> Unit = {}): <ERROR CLASS>` |
| [progressBar](progress-bar.md) | `fun <ERROR CLASS>.progressBar(caption: String? = null, block: (<ERROR CLASS>) -> Unit = {}): <ERROR CLASS>` |
| [radioButtonGroup](radio-button-group.md) | `fun <T> <ERROR CLASS>.radioButtonGroup(caption: String? = null, block: (<ERROR CLASS><T>) -> Unit = {}): <ERROR CLASS>` |
| [refresh](refresh.md) | `fun <ERROR CLASS><out <ERROR CLASS>>.refresh(): <ERROR CLASS>`<br>Refreshes the Grid and re-polls for data. |
| [removeAll](remove-all.md) | `fun <ERROR CLASS>.removeAll(components: Iterable<<ERROR CLASS>>): Unit` |
| [removeAllComponents](remove-all-components.md) | `fun <ERROR CLASS>.removeAllComponents(): Unit`<br>Removes all components from given container. |
| [removeColumn](remove-column.md) | `fun <T, V> <ERROR CLASS><T>.removeColumn(property: KProperty1<T, V>): <ERROR CLASS>`<br>Removes column showing given [property](remove-column.md#com.github.vok.karibudsl$removeColumn(((com.github.vok.karibudsl.removeColumn.T)), kotlin.reflect.KProperty1((com.github.vok.karibudsl.removeColumn.T, com.github.vok.karibudsl.removeColumn.V)))/property). |
| [removeComponentAt](remove-component-at.md) | `fun <ERROR CLASS>.removeComponentAt(index: Int): Unit`<br>Removes a component at given [index](remove-component-at.md#com.github.vok.karibudsl$removeComponentAt(, kotlin.Int)/index) from this container. Optimized for [CssLayout](#) and [AbstractOrderedLayout](#)s, but works with any
[ComponentContainer](#). |
| [removeComponentsAt](remove-components-at.md) | `fun <ERROR CLASS>.removeComponentsAt(indexRange: IntRange): Unit`<br>Removes components with given indices. |
| [richTextArea](rich-text-area.md) | `fun <ERROR CLASS>.richTextArea(caption: String? = null, block: (<ERROR CLASS>) -> Unit = {}): <ERROR CLASS>` |
| [setPrimary](set-primary.md) | `fun <ERROR CLASS>.setPrimary(): Unit`<br>Configures this button as primary. Beware - all buttons marked primary using this function, attached to the current UI
or Window will be pressed on Enter key press. |
| [showColumns](show-columns.md) | `fun <T> <ERROR CLASS><T>.showColumns(vararg ids: KProperty1<T, *>): <ERROR CLASS>`<br>Causes the Grid to only show given set of columns, and in given order. |
| [slider](slider.md) | `fun <ERROR CLASS>.slider(caption: String? = null, block: (<ERROR CLASS>) -> Unit = {}): <ERROR CLASS>` |
| [spinner](spinner.md) | `fun <ERROR CLASS>.spinner(block: (<ERROR CLASS>) -> Unit = {}): <ERROR CLASS>`<br>A small rectangular spinner (indeterminate progress bar). |
| [tabSheet](tab-sheet.md) | `fun <ERROR CLASS>.tabSheet(block: (`[`_TabSheet`](_-tab-sheet/index.md)`) -> Unit = {}): <ERROR CLASS>` |
| [textArea](text-area.md) | `fun <ERROR CLASS>.textArea(caption: String? = null, block: (<ERROR CLASS>) -> Unit = {}): <ERROR CLASS>`<br>Creates a [TextArea](#) and attaches it to this component. |
| [textField](text-field.md) | `fun <ERROR CLASS>.textField(caption: String? = null, value: String? = null, block: (<ERROR CLASS>) -> Unit = {}): <ERROR CLASS>`<br>Creates a [TextField](#) and attaches it to this component. |
| [toBigDecimal](to-big-decimal.md) | `fun <BEAN> <ERROR CLASS><BEAN, String?>.toBigDecimal(): <ERROR CLASS><BEAN, `[`BigDecimal`](http://docs.oracle.com/javase/6/docs/api/java/math/BigDecimal.html)`?>` |
| [toBigInteger](to-big-integer.md) | `fun <BEAN> <ERROR CLASS><BEAN, String?>.toBigInteger(): <ERROR CLASS><BEAN, `[`BigInteger`](http://docs.oracle.com/javase/6/docs/api/java/math/BigInteger.html)`?>` |
| [toDate](to-date.md) | `fun <BEAN> <ERROR CLASS><BEAN, `[`LocalDate`](http://docs.oracle.com/javase/6/docs/api/java/time/LocalDate.html)`?>.toDate(): <ERROR CLASS><BEAN, `[`Date`](http://docs.oracle.com/javase/6/docs/api/java/util/Date.html)`?>`<br>`fun <BEAN> <ERROR CLASS><BEAN, `[`LocalDateTime`](http://docs.oracle.com/javase/6/docs/api/java/time/LocalDateTime.html)`?>.toDate(): <ERROR CLASS><BEAN, `[`Date`](http://docs.oracle.com/javase/6/docs/api/java/util/Date.html)`?>` |
| [toDouble](to-double.md) | `fun <BEAN> <ERROR CLASS><BEAN, String?>.toDouble(): <ERROR CLASS><BEAN, Double?>` |
| [toInstant](to-instant.md) | `fun <BEAN> <ERROR CLASS><BEAN, `[`LocalDate`](http://docs.oracle.com/javase/6/docs/api/java/time/LocalDate.html)`?>.toInstant(): <ERROR CLASS><BEAN, `[`Instant`](http://docs.oracle.com/javase/6/docs/api/java/time/Instant.html)`?>`<br>`fun <BEAN> <ERROR CLASS><BEAN, `[`LocalDateTime`](http://docs.oracle.com/javase/6/docs/api/java/time/LocalDateTime.html)`?>.toInstant(): <ERROR CLASS><BEAN, `[`Instant`](http://docs.oracle.com/javase/6/docs/api/java/time/Instant.html)`?>` |
| [toInt](to-int.md) | `fun <BEAN> <ERROR CLASS><BEAN, String?>.toInt(): <ERROR CLASS><BEAN, Int?>` |
| [toLong](to-long.md) | `fun <BEAN> <ERROR CLASS><BEAN, String?>.toLong(): <ERROR CLASS><BEAN, Long?>` |
| [toggleStyleName](toggle-style-name.md) | `fun <ERROR CLASS>.toggleStyleName(style: String, isPresent: Boolean): Unit`<br>Adds or removes given [style](toggle-style-name.md#com.github.vok.karibudsl$toggleStyleName(, kotlin.String, kotlin.Boolean)/style) from the component, depending on the value of the [isPresent](toggle-style-name.md#com.github.vok.karibudsl$toggleStyleName(, kotlin.String, kotlin.Boolean)/isPresent) parameter. |
| [tree](tree.md) | `fun <T : Any> <ERROR CLASS>.tree(caption: String? = null, block: (<ERROR CLASS><T>) -> Unit = {}): <ERROR CLASS>` |
| [treeGrid](tree-grid.md) | `fun <T> <ERROR CLASS>.treeGrid(dataProvider: <ERROR CLASS><T, out <ERROR CLASS>>? = null, block: (<ERROR CLASS><T>) -> Unit = {}): <ERROR CLASS>` |
| [trimmingConverter](trimming-converter.md) | `fun <BEAN> <ERROR CLASS><BEAN, String?>.trimmingConverter(): <ERROR CLASS><BEAN, String?>`<br>Trims the user input string before storing it into the underlying property data source. Vital for mobile-oriented apps:
Android keyboard often adds whitespace to the end of the text when auto-completion occurs. Imagine storing a username ending with a space upon registration:
such person can no longer log in from his PC unless he explicitely types in the space. |
| [twinColSelect](twin-col-select.md) | `fun <T> <ERROR CLASS>.twinColSelect(caption: String? = null, block: (<ERROR CLASS><T>) -> Unit = {}): <ERROR CLASS>` |
| [upload](upload.md) | `fun <ERROR CLASS>.upload(caption: String? = null, block: (<ERROR CLASS>) -> Unit = {}): <ERROR CLASS>` |
| [valoMenu](valo-menu.md) | `fun <ERROR CLASS>.valoMenu(block: (`[`ValoMenu`](-valo-menu/index.md)`) -> Unit = {}): `[`ValoMenu`](-valo-menu/index.md) |
| [verticalLayout](vertical-layout.md) | `fun <ERROR CLASS>.verticalLayout(block: (<ERROR CLASS>) -> Unit = {}): <ERROR CLASS>` |
| [verticalSplitPanel](vertical-split-panel.md) | `fun <ERROR CLASS>.verticalSplitPanel(block: (<ERROR CLASS>) -> Unit = {}): <ERROR CLASS>` |
| [video](video.md) | `fun <ERROR CLASS>.video(caption: String? = null, resource: <ERROR CLASS>? = null, block: (<ERROR CLASS>) -> Unit = {}): <ERROR CLASS>` |
| [walk](walk.md) | `fun <ERROR CLASS>.walk(): Iterable<<ERROR CLASS>>`<br>Walks over this component and all descendants of this component, breadth-first. |
