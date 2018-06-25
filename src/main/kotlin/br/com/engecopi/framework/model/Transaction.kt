package br.com.engecopi.framework.model

import io.ebean.Ebean
import javax.persistence.RollbackException

object Transaction {
  private fun inTransaction(): Boolean {
    return Ebean.getDefaultServer()?.currentTransaction() != null
  }
  
  @Throws(Exception::class)
  fun execTransacao(lambda: () -> Unit) {
    try {
      Ebean.beginTransaction()
      lambda()
      Ebean.commitTransaction()
    } catch (e: RollbackException) {
      Ebean.rollbackTransaction()
      throw e
    } catch (exception: Exception) {
      Ebean.rollbackTransaction()
      throw exception
    } catch (error: Error) {
      Ebean.rollbackTransaction()
      throw Exception(error)
    } finally {
      Ebean.endTransaction()
    }
  }
  
  fun commit() {
    if (inTransaction())
      Ebean.commitTransaction()
    Ebean.beginTransaction()
  }
  
  fun rollback() {
    if (inTransaction())
      Ebean.rollbackTransaction()
    Ebean.beginTransaction()
  }
}




