package br.com.engecopi.saci

import br.com.engecopi.utils.DB

class QuerySaci : QueryDB(driver, url, username, password, sqldir) {
  
  companion object {
    val db = DB("saci")
    internal val driver = db.driver
    internal val url = db.url
    internal val username = db.username
    internal val password = db.password
    internal val sqldir = db.sqldir
    val querySaci = QuerySaci()
    
    val ipServer = QuerySaci.db.url.split("/").getOrNull(2)
  }
  
}
