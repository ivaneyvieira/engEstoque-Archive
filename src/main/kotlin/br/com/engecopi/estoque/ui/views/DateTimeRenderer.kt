package br.com.engecopi.estoque.ui.views

import com.vaadin.ui.renderers.AbstractRenderer
import elemental.json.JsonValue
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat.forPattern
import org.joda.time.format.DateTimeFormatter

class DateTimeRenderer(format: String) : AbstractRenderer<Object, DateTime>(DateTime::class.java, "") {
  val formater: DateTimeFormatter = forPattern(format)
  
  override fun encode(value: DateTime?): JsonValue {
    val dateString = if (value == null) nullRepresentation else formater.print(value)
    return encode(dateString, String::class.java)
  }
  
}

