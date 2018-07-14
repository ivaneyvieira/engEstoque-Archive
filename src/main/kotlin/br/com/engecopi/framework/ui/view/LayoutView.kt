package br.com.engecopi.framework.ui.view

import br.com.engecopi.estoque.ui.title
import br.com.engecopi.framework.viewmodel.IView
import br.com.engecopi.framework.viewmodel.ViewModel
import com.github.vok.karibudsl.VAlign
import com.github.vok.karibudsl.VaadinDsl
import com.github.vok.karibudsl.align
import com.github.vok.karibudsl.bind
import com.github.vok.karibudsl.init
import com.github.vok.karibudsl.isMargin
import com.sun.jmx.snmp.SnmpStatusException.readOnly
import com.vaadin.data.Binder
import com.vaadin.data.Binder.Binding
import com.vaadin.data.HasItems
import com.vaadin.data.HasValue
import com.vaadin.data.ReadOnlyHasValue
import com.vaadin.data.provider.ListDataProvider
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent
import com.vaadin.server.Resource
import com.vaadin.ui.ComboBox
import com.vaadin.ui.Component
import com.vaadin.ui.Grid
import com.vaadin.ui.Grid.Column
import com.vaadin.ui.HasComponents
import com.vaadin.ui.Label
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.renderers.LocalDateRenderer
import com.vaadin.ui.renderers.NumberRenderer
import com.vaadin.ui.themes.ValoTheme
import org.vaadin.viritin.fields.ClearableTextField
import org.vaadin.viritin.fields.DoubleField
import org.vaadin.viritin.fields.EmailField
import org.vaadin.viritin.fields.EnumSelect
import org.vaadin.viritin.fields.HeaderField
import org.vaadin.viritin.fields.IntegerField
import org.vaadin.viritin.fields.IntegerSliderField
import org.vaadin.viritin.fields.LabelField
import org.vaadin.viritin.fields.MCheckBox
import org.vaadin.viritin.fields.MTextField
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredMemberProperties

abstract class LayoutView<V : ViewModel> : VerticalLayout(), View, IView {
  abstract val viewModel: V
  
  fun form(titleForm: String, block: (@VaadinDsl VerticalLayout).() -> Unit = {}) {
    isMargin = true
    setSizeFull()
    title(titleForm)
    this.block()
  }
  
  override fun enter(event: ViewChangeEvent) {
    updateView(viewModel)
  }
  
  fun <T> Grid<T>.actionSelected(msgErro: String = "Selecione um item", action: (T) -> Unit) {
    this.selectedItems.firstOrNull()?.let { item -> action(item) } ?: showWarning(msgErro)
  }
  
  override fun showWarning(msg: String) {
    if (msg.isNotBlank())
      MessageDialog.warning(message = msg)
  }
  
  override fun showError(msg: String) {
    if (msg.isNotBlank())
      MessageDialog.error(message = msg)
  }
  
  override fun showInfo(msg: String) {
    if (msg.isNotBlank())
      MessageDialog.info(message = msg)
  }
}

fun <T> ComboBox<T>.default(
        valueEmpty: T? = null,
        captionGenerator: (T) -> String = { it.toString() }
                           ) {
  isEmptySelectionAllowed = false
  isTextInputAllowed = false
  valueEmpty?.let {
    this.emptySelectionCaption = "Todas"
    isEmptySelectionAllowed = true
  }
  setItemCaptionGenerator(captionGenerator)
}

fun <V, T> HasItems<T>.bindItens(
        binder: Binder<V>,
        propertyList: KProperty1<V, List<T>>,
        binding: Binding<V, T>? = null
                                ) {
  val hasValue = (this as? HasValue<*>)
  
  val itensOld: List<T>? = (this.dataProvider as? ListDataProvider)?.items?.toList()
  
  bind(binder, propertyList) { itens ->
    //  val oldValue = hasValue?.value
    if (itensOld == null)
      setItems(itens)
    else
      if (itensOld != itens)
        setItems(itens)
    //  val value = if (oldValue == null)
    //    itens.firstOrNull()
    //  else
    //    itens.find { it == oldValue }
    //  binding?.setter?.accept(binder.bean, value)
    //  binding?.read(binder.bean)
  }
}

fun <BEAN> HasValue<*>.bindReadOnly(
        binder: Binder<BEAN>,
        property: KProperty1<BEAN, Boolean>,
        block: (Boolean) -> Unit = {}
                                   ) {
  bind(binder, property) { readOnly ->
    isReadOnly = readOnly
    block(readOnly)
  }
}

fun <BEAN> Component.bindCaption(
        binder: Binder<BEAN>,
        property: KProperty1<BEAN, String>,
        block: (String) -> Unit = {}
                                ) {
  bind(binder, property) {
    caption = it
    block(it)
  }
}

private fun <BEAN, FIELDVALUE> bind(
        binder: Binder<BEAN>,
        property: KProperty1<BEAN, FIELDVALUE>,
        blockBinder: (FIELDVALUE) -> Unit
                                   ): Binding<BEAN, FIELDVALUE> {
  val field = ReadOnlyHasValue<FIELDVALUE> { itens -> blockBinder(itens) }
  return field.bind(binder).bind(property.name)
}

fun Binder<*>.reload() {
  bean = bean
}

inline fun <reified BEAN : Any, FIELDVALUE> HasValue<FIELDVALUE>.reloadBinderOnChange(
        binder: Binder<BEAN>, vararg propertys: KProperty1<BEAN, *>
                                                                                     ) {
  addValueChangeListener { event ->
    if (event.isUserOriginated && (event.oldValue != event.value)) {
      val bean = binder.bean
      if (propertys.isEmpty()) {
        BEAN::class.declaredMemberProperties.forEach { prop ->
          binder.getBinding(prop.name).ifPresent { binding ->
            if (binding.field != this)
              binding.read(bean)
          }
        }
      } else {
        propertys.forEach { prop ->
          binder.getBinding(prop.name).ifPresent { binding ->
            binding.read(bean)
          }
        }
      }
    }
  }
}

fun <C> Column<C, LocalDate?>.dateFormat() {
  this.setRenderer(LocalDateRenderer(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
}

fun <C> Column<C, Int?>.intFormat() {
  setRenderer(NumberRenderer(DecimalFormat("0")))
  align = VAlign.Right
}

fun HasComponents.integerField(caption: String = "", block: IntegerField.() -> Unit = {}) =
        init(IntegerField(caption), block).apply {
          addStyleName(ValoTheme.TEXTFIELD_ALIGN_RIGHT)
        }

fun HasComponents.doubleField(caption: String = "", block: DoubleField.() -> Unit = {}) =
        init(DoubleField(caption), block)

fun HasComponents.emailField(caption: String = "", block: EmailField.() -> Unit = {}) = init(EmailField(caption), block)

fun HasComponents.clearableTextField(caption: String = "", block: ClearableTextField.() -> Unit = {}) =
        init(ClearableTextField(caption), block)

fun <T> HasComponents.headerField(caption: String = "", block: HeaderField<T>.() -> Unit = {}) =
        init(HeaderField(caption), block)

fun HasComponents.integerSliderField(captionPar: String = "", block: IntegerSliderField.() -> Unit = {}) =
        init(IntegerSliderField(), block).apply {
          this.caption = captionPar
        }

fun HasComponents.mCheckBox(captionPar: String = "", block: MCheckBox.() -> Unit = {}) =
        init(MCheckBox(), block).apply {
          this.caption = captionPar
        }

fun HasComponents.mTextField(captionPar: String = "", block: MTextField.() -> Unit = {}) =
        init(MTextField(), block).apply {
          this.caption = captionPar
        }

fun <T> HasComponents.labelField(caption: String = "", block: LabelField<T>.() -> Unit = {}) =
        init(LabelField(caption), block)

inline fun <reified T : Enum<*>> HasComponents.enumSelect(caption: String = "", block: EnumSelect<T>.() -> Unit = {}) =
        init(EnumSelect<T>(caption, T::class.java))
