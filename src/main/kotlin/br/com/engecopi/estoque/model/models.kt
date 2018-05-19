package br.com.engecopi.estoque.model

import br.com.engecopi.saci.QuerySaci
import br.com.engecopi.saci.beans.ProdutoSaci
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

object Produtos : IntIdTable() {
  val codigo = varchar("codigo", 16).uniqueIndex()
  val grade = varchar("grade", 8)
  var codebar = varchar("codebar", 16)
  val data_cadastro = date("data_cadastro")
}

object Entradas : IntIdTable() {
  val numero = varchar("numero", 15)
  val loja = integer("loja")
  val data = date("data")
  val hora = varchar("hora", 5)
}

object ItensEntrada : IntIdTable() {
  val quantidade = integer("quantidade")
  val custo_unitario = decimal("custo_unitario", 15, 4)
  val produto = reference("idProduto", Produtos)
  val notaEntrada = reference("idNotaEntrada", Entradas)
}

object Saidas : IntIdTable() {
  val loja = integer("loja")
  val data = date("data")
  val hora = varchar("hora", 5)
}

object ItensSaida : IntIdTable() {
  val quantidade = integer("quantidade")
  val custo_unitario = decimal("custo_unitario", 15, 4)
  val produto = reference("idProduto", Produtos)
  val notaSaida = reference("idNotaSaida", Saidas)
}

object Saldos : IntIdTable() {
  val loja = integer("loja")
  val quantidade = integer("quantidade")
  val produto = reference("idProduto", Produtos)
}

class Produto(id: EntityID<Int>) : IntEntity(id) {
  companion object : IntEntityClass<Produto>(Produtos)
  
  var codigo by Produtos.codigo
  var grade by Produtos.grade
  var codebar by Produtos.codebar
  var data_cadastro by Produtos.data_cadastro
  val produtoSaci: ProdutoSaci? by lazy {
    QuerySaci.querySaci.findProduto(codigo).first { it.grade == grade }
  }
  
  val nome by lazy { produtoSaci?.nome ?: "" }
  val custo by lazy { produtoSaci?.custo ?: 0.0000 }
  
  val saldos by Saldo referrersOn Saldos.produto
  val entradas by ItemEntrada referrersOn ItensEntrada.produto
  val saidas by ItemSaida referrersOn ItensSaida.produto
  
  fun recalcula(loja: Int) = transaction {
    Saldos.deleteWhere { (Saldos.produto eq this@Produto.id) and (Saldos.loja eq loja) }
    
    val totalEntradas = entradas.filter { it.notaEntrada.loja == loja }.sumBy { it.quantidade }
    val totalSaidas = saidas.filter { it.notaSaida.loja == loja }.sumBy { it.quantidade }
    val saldo = totalEntradas - totalSaidas
    Saldo.new {
      this.loja = loja
      this.quantidade = saldo
      this.produto = this@Produto
    }
  }
}

class Entrada(id: EntityID<Int>) : IntEntity(id) {
  companion object : IntEntityClass<Entrada>(Entradas)
  
  var numero by Entradas.numero
  var loja by Entradas.loja
  var data by Entradas.data
  var hora by Entradas.hora
  val itens by ItemEntrada referrersOn ItensEntrada.notaEntrada
  
  fun cacheItens() = Cache{ itens  }.value()
  
  val dataEntrada: Date
    get() = transaction { data.toDate() }
}

class ItemEntrada(id: EntityID<Int>) : IntEntity(id) {
  companion object : IntEntityClass<ItemEntrada>(ItensEntrada)
  
  var quantidade by ItensEntrada.quantidade
  var custo_unitario by ItensEntrada.custo_unitario
  var notaEntrada by Entrada referencedOn ItensEntrada.notaEntrada
  var produto by Produto referencedOn ItensEntrada.produto
  
  val codigo by lazy {
    transaction { produto.codigo }
  }
  val nome by lazy {
    transaction { produto.nome }
  }
  val grade by lazy {
    transaction { produto.grade }
  }
  
}

class Saida(id: EntityID<Int>) : IntEntity(id) {
  companion object : IntEntityClass<Saida>(Saidas)
  
  var loja by Saidas.loja
  var data by Saidas.data
  var hora by Saidas.hora
  val dataSaida: Date
    get() = data.toDate()
  
}

class ItemSaida(id: EntityID<Int>) : IntEntity(id) {
  companion object : IntEntityClass<ItemSaida>(ItensSaida)
  
  var quantidade by ItensSaida.quantidade
  var custo_unitario by ItensSaida.custo_unitario
  var notaSaida by Saida referencedOn ItensSaida.notaSaida
  var produto by Produto referencedOn ItensSaida.produto
}

class Saldo(id: EntityID<Int>) : IntEntity(id) {
  companion object : IntEntityClass<Saldo>(Saldos) {
    fun lojas() = Saldo.all().filter { it.quantidade != 0 }.map { it.loja }.distinct().sorted()
  }
  
  var loja by Saldos.loja
  var quantidade by Saldos.quantidade
  var produto by Produto referencedOn Saldos.produto
}

class Cache<T>(val exec: () -> T) {
  val millisecond = System.currentTimeMillis()
  var lastValue: T = exec()
  
  fun value() {
    if (millisecond - System.currentTimeMillis() > 5000) {
      lastValue = transaction { exec() }
      return lastValue
    } else {
      return lastValue
    }
  }
  
}