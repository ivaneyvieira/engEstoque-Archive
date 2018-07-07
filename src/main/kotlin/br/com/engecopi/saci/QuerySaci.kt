package br.com.engecopi.saci

import br.com.engecopi.saci.beans.LojaSaci
import br.com.engecopi.saci.beans.NotaEntradaSaci
import br.com.engecopi.saci.beans.ProdutoSaci
import br.com.engecopi.saci.beans.UserSaci
import br.com.engecopi.utils.DB
import br.com.engecopi.utils.lpad

class QuerySaci : QueryDB(driver, url, username, password, sqldir) {
  
  val findProduto = Cache<String, List<ProdutoSaci>> { prdno ->
    val sql = "/sqlSaci/findProdutos.sql"
    query(sql) { q ->
      val codigo = prdno.lpad(16, " ")
      q.addParameter("prdno", codigo)
      q.executeAndFetch(ProdutoSaci::class.java)
    }
  }
  
  fun findProduto(prdno: String): List<ProdutoSaci> {
    return findProduto.value(prdno.lpad(16, " "))
  }
  
  fun findNotaEntrada(storeno: Int, nfname: String, invse: String): List<NotaEntradaSaci> {
    val sql = "/sqlSaci/findNotaEntrada.sql"
    return query(sql) { q ->
      q.addParameter("storeno", "$storeno")
              .addParameter("nfname", nfname)
              .addParameter("invse", invse)
              .executeAndFetch(NotaEntradaSaci::class.java)
    }
  }
  
  fun findNotaSaida(storeno: Int, nfname: String, invse: String): List<NotaEntradaSaci> {
    val sql = "/sqlSaci/findNotaSaida.sql"
    return query(sql) { q ->
      q.addParameter("storeno", "$storeno")
              .addParameter("nfname", nfname)
              .addParameter("invse", invse)
              .executeAndFetch(NotaEntradaSaci::class.java)
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
    internal val sqldir = db.sqldir
    val querySaci = QuerySaci()
    
    val ipServer = QuerySaci.db.url.split("/").getOrNull(2)
  }
}
