package br.com.engecopi.saci

import br.com.engecopi.saci.beans.NotaEntradaSaci
import br.com.engecopi.saci.beans.ProdutoSaci
import br.com.engecopi.utils.DB
import br.com.engecopi.utils.lpad

class QuerySaci : QueryDB(driver, url, username, password, sqldir) {
  
  fun findProduto(prdno: String): List<ProdutoSaci> {
    val sql = "findProdutos.sql"
    return query(sql) { q ->
      val codigo = prdno.lpad(16, " ")
      q.addParameter("prdno", codigo)
      q.executeAndFetch(ProdutoSaci::class.java)
    }
  }
  
  fun findNotaEntrada(storeno: Int, nfname: String, invse: String): List<NotaEntradaSaci> {
    val sql = "findNotaEntrada.sql"
    return query(sql) { q ->
      q.addParameter("storeno", "$storeno")
              .addParameter("nfname", nfname)
              .addParameter("invse", invse)
              .executeAndFetch(NotaEntradaSaci::class.java)
    }
  }
  
  companion object {
    private val db = DB("saci")
    internal val driver = db.driver
    internal val url = db.url
    internal val username = db.username
    internal val password = db.password
    internal val sqldir = db.sqldir
    val querySaci = QuerySaci()
    
    val ipServer = QuerySaci.db.url.split("/").getOrNull(2)
  }
}
