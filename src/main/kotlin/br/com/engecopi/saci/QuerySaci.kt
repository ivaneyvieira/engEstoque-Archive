package br.com.engecopi.saci

import br.com.engecopi.saci.beans.LojaSaci
import br.com.engecopi.saci.beans.NotaSaci
import br.com.engecopi.saci.beans.UserSaci
import br.com.engecopi.utils.DB

class QuerySaci : QueryDB(
  driver,
  url,
  username,
  password
) {

  fun findNotaEntrada(storeno: Int, nfname: String, invse: String): List<NotaSaci> {
    val sql = "/sqlSaci/findNotaEntrada.sql"
    return query(sql) { q ->
      q.addParameter("storeno", storeno)
              .addParameter("nfname", nfname)
              .addParameter("invse", invse)
              .executeAndFetch(NotaSaci::class.java)
    }
  }
  
  fun findNotaSaida(storeno: Int, nfno: String, nfse: String): List<NotaSaci> {
    val sql = "/sqlSaci/findNotaSaida.sql"
    return query(sql) { q ->
      q.addParameter("storeno", storeno)
              .addParameter("nfno", nfno)
              .addParameter("nfse", nfse)
              .executeAndFetch(NotaSaci::class.java)
    }
  }
  
  fun findLojas(storeno: Int): List<LojaSaci> {
    val sql = "/sqlSaci/findLojas.sql"
    return query(sql) { q ->
      q.executeAndFetch(LojaSaci::class.java).filter { it.storeno == storeno || storeno == 0 }
    }
  }
  
  fun findUser(login: String): UserSaci? {
    val sql = "/sqlSaci/userSenha.sql"
    return query(sql) { q ->
      q.addParameter("login", login)
              .executeAndFetch(UserSaci::class.java)
              .firstOrNull()
    }
  }
  
  companion object {
    private val db = DB("saci")
    internal val driver = db.driver
    internal val url = db.url
    internal val username = db.username
    internal val password = db.password
    
    val ipServer = QuerySaci.db.url.split("/").getOrNull(2)
  }
}

val saci = QuerySaci()