package br.com.engecopi.framework.model

import io.ebean.Ebean
import javax.persistence.RollbackException

object Transaction {
  private fun isNestedTransaction(): Boolean {
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
    if (!isNestedTransaction())
      Ebean.commitTransaction()
    Ebean.beginTransaction()
  }
  
  fun rollback() {
    if (!isNestedTransaction())
      Ebean.rollbackTransaction()
    Ebean.beginTransaction()
  }
}




