package br.com.engecopi.estoque.model

import org.apache.commons.dbcp2.BasicDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils.createMissingTablesAndColumns
import org.jetbrains.exposed.sql.transactions.transaction

class DB {
  init {
    connect()
  }
  
  companion object {
    fun createTable() = transaction {
      createMissingTablesAndColumns(Produtos, Entradas, Saidas, ItensEntrada, ItensSaida, Saldos)
    }
    
    fun connect() {
      val db = br.com.engecopi.utils.DB("est")
      val ds = BasicDataSource()
      ds.driverClassName = db.driver
      ds.url = db.url
      Database.connect(ds)
    }
  }
}

fun main(args: Array<String>) {
  DB.connect()
  DB.createTable()
}