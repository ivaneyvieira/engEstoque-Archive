package br.com.engecopi.saci.beans

class NotaSaci(
  val rota: String?,
  val storeno: Int?,
  val numero: String?,
  val serie: String?,
  val date: Int?,
  val dt_emissao: Int?,
  val prdno: String?,
  val grade: String?,
  val quant: Int?,
  val vendName: String? = "",
  val clienteName: String? = "",
  val tipo: String?,
  val localizacao: String?,
  val invno: Int?
)