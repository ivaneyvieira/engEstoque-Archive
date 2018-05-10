package br.com.engecopi.saci

import br.com.engecopi.saci.beans.ProdutoSaci
import br.com.engecopi.utils.DB
import br.com.engecopi.utils.lpad

class QuerySaci : QueryDB(driver, url, username, password, sqldir) {
  
  fun findProduto(prdno : String) : List<ProdutoSaci> {
    val sql = "findProdutos.sql"
    return query(sql) { q ->
      q.addParameter("prdno", prdno.lpad(16, " "))
              .executeAndFetch(ProdutoSaci::class.java)
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
