package br.com.engecopi.framework.model

import javax.persistence.RollbackException
import io.ebean.config.ServerConfig
import io.ebean.EbeanServerFactory
import io.ebean.Query
import io.ebean.SqlQuery
import io.ebean.SqlUpdate

object Transaction {
  val config = ServerConfig().apply {
    loadFromProperties()
  }
  
  val server = EbeanServerFactory.create(config)
  
  private fun inTransaction(): Boolean {
    return server.currentTransaction() != null
  }
  
  @Throws(Exception::class)
  fun execTransacao(lambda: () -> Unit) {
    try {
      server.beginTransaction()
      lambda()
      server.commitTransaction()
    } catch (e: RollbackException) {
      server.rollbackTransaction()
      throw e
    } catch (exception: Exception) {
      server.rollbackTransaction()
      throw exception
    } catch (error: Error) {
      server.rollbackTransaction()
      throw Exception(error)
    } finally {
      server.endTransaction()
    }
  }
  
  fun commit() {
    if (inTransaction())
      server.commitTransaction()
    server.beginTransaction()
  }
  
  fun rollback() {
    if (inTransaction())
      server.rollbackTransaction()
    server.beginTransaction()
  }
  
  fun createSqlUpdate(sql: String): SqlUpdate? {
    return server.createSqlUpdate(sql)
  }
  
  fun <T> find(javaClass: Class<T>): Query<T>? {
    return server.find(javaClass)
  }
  
  fun createSqlQuery(sql: String): SqlQuery? {
    return server.createSqlQuery(sql)
  }
}
