package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.NotaPrint
import kotlin.reflect.full.memberProperties

class EtiquetaPrinter(val tempalte: String, val print: NotaPrint) {
  fun mapAgrupado(): List<EtiquetaTemplate> {
    return listOf(EtiquetaTemplate(
            nota = print.nota,
            tiponota = print.tipoNota,
            rota = print.rota,
            data = print.data,
            prdno = print.prdno,
            grade = print.grade,
            name = print.name,
            saldo = "",
            barras = print.barras                        )
                 )
  }
  
  
  fun print(): String {
    return print(mapAgrupado())
  }

  
  fun print(etiquetas: List<EtiquetaTemplate>): String {
    return etiquetas.fold(StringBuilder()) { text, etiqueta ->
      text.append("\n").append(etiqueta.print(tempalte))
      text
    }.toString()
  }
}

data class EtiquetaTemplate(
        val nota: String,
        val tiponota: String,
        val rota: String,
        val data: String,
        val prdno: String,
        val grade: String,
        val name: String,
        val saldo: String,
        val barras: String
                   ) {
  fun print(template: String): String {
    return EtiquetaTemplate::class.memberProperties.fold(template) { reduce, prop ->
      reduce.replace("[${prop.name}]", "${prop.get(this)}")
    }
  }
}