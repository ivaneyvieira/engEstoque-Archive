package br.com.engecopi.saci.beans

class NotaSaidaSaci(
        val storenoT : Int?,
        val rota : String?,
        val storeno : Int?,
        val nfno : String?,
        val nfse : String?,
        val date : Int?,
        val prdno : String?,
        val grade : String?,
        val quant : Int?,
        val clienteName : String?,
        val tipo: String?
                   ){
  val invalida = (tipo == null) || (tipo == "INVALIDA")
}