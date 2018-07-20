package br.com.engecopi.estoque.viewmodel

import br.com.engecopi.estoque.model.NotaPrint
import kotlin.reflect.full.memberProperties

class EtiquetaPrinter(val tempalte: String, val print: NotaPrint) {
  fun mapAgrupado(): List<Etiqueta> {
    return listOf(Etiqueta(
            nota = print.nota,
            tiponota = print.tipoNota,
            rota = print.rota,
            data = print.data,
            prdno = print.prdno,
            grade = print.grade,
            name = print.name,
            seqnota = print.indexNot,
            seqlote = print.indexSeq,
            qtpl = print.quantidadePorLote,
            qtlote = "${print.quantLotes}",
            saldo = "",
            barras = print.barras,
            numlote = "${print.quantLotes}"                          )
                 )
  }
  
  fun mapSeparado(): List<Etiqueta> {
    return print.lotes.map { lote ->
      Etiqueta(
              nota = print.nota,
              tiponota = print.tipoNota,
              rota = print.rota,
              data = print.data,
              prdno = print.prdno,
              grade = print.grade,
              name = print.name,
              seqnota = lote.indexNot,
              seqlote = lote.indexSeq,
              qtpl = "${lote.quantidade}",
              qtlote = "1",
              saldo = "${lote.saldo}",
              barras = lote.barras,
              numlote = "${lote.numSeq}"
              )
    }
  }
  
  fun printAgrupado(): String {
    return print(mapAgrupado())
  }
  
  fun printSeparado(): String {
    return print(mapSeparado())
  }
  
  fun print(etiquetas: List<Etiqueta>): String {
    return etiquetas.fold(StringBuilder()) { text, etiqueta ->
      text.append("\n").append(etiqueta.print(tempalte))
      text
    }.toString()
  }
}

data class Etiqueta(
        val nota: String,
        val tiponota: String,
        val rota: String,
        val data: String,
        val prdno: String,
        val grade: String,
        val name: String,
        val seqnota: String,
        val seqlote: String,
        val numlote: String,
        val qtpl: String,
        val qtlote: String,
        val saldo: String,
        val barras: String
                   ) {
  fun print(template: String): String {
    return Etiqueta::class.memberProperties.fold(template) { reduce, prop ->
      reduce.replace("[${prop.name}]", "${prop.get(this)}")
    }
  }
}