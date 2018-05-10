package br.com.engecopi.estoque.model

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.SchemaUtils.createMissingTablesAndColumns
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.Connection.TRANSACTION_SERIALIZABLE

class DB {
  init {
    connect()
  }
  
  companion object {
    fun createTable() = transaction {
      createMissingTablesAndColumns(Produtos, Entradas, Saidas, ItensEntrada, ItensSaida, Saldos)
    }
    
    fun connect() {
      Database.connect(
              url = "jdbc:mysql://localhost/engEstoque",
              driver = "com.mysql.jdbc.Driver"
                      )
    }
  }
}

fun main(args: Array<String>) {
  DB.connect()
  DB.createTable()
}