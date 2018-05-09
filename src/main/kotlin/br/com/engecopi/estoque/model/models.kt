package br.com.engecopi.estoque.model

import br.com.engecopi.saci.QuerySaci
import br.com.engecopi.saci.beans.ProdutoSaci
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable

object Produtos : IntIdTable() {
  val codigo = varchar("codigo", 16).uniqueIndex()
  val data_cadastro = date("data_cadastro")
}

object Entradas : IntIdTable() {
  val numero = varchar("numero", 15)
  val loja = integer("loja")
  val data = date("data")
  val hora = datetime("hora")
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
  val hora = datetime("hora")
}

object ItensSaida : IntIdTable() {
  val quantidade = integer("quantidade")
  val custo_unitario = decimal("custo_unitario", 15, 4)
  val produto = reference("idProduto", Produtos)
  val notaEntrada = reference("idNotaEntrada", Saidas)
}

object Saldos : IntIdTable() {
  val loja = integer("loja")
  val quantidade = integer("quantidade")
  val produto = reference("idProduto", Produtos)
}

class Produto(id: EntityID<Int>) : IntEntity(id) {
  companion object : IntEntityClass<Produto>(Produtos)
  
  val codigo by Produtos.codigo
  val data_cadastro by Produtos.data_cadastro
  val produtoSaci: ProdutoSaci? by lazy {
    QuerySaci.querySaci.findProduto(codigo)
  }
  
  val nome by lazy { produtoSaci?.nome ?: "" }
  val custo by lazy { produtoSaci?.custo ?: 0.0000 }
}

class Entrada(id: EntityID<Int>) : IntEntity(id) {
  companion object : IntEntityClass<Entrada>(Entradas)
  
  val numero by Entradas.numero
  val loja by Entradas.loja
  val data by Entradas.data
  val hora by Entradas.hora
}

class ItemEntrada(id: EntityID<Int>) : IntEntity(id) {
  companion object : IntEntityClass<ItemEntrada>(ItensEntrada)
  
  val quantidade by ItensEntrada.quantidade
  val custo_unitario by ItensEntrada.custo_unitario
}

class Saida(id: EntityID<Int>) : IntEntity(id) {
  companion object : IntEntityClass<Saida>(Saidas)
  
  val loja by Saidas.loja
  val data by Saidas.data
  val hora by Saidas.hora
}

class ItemSaida(id: EntityID<Int>) : IntEntity(id) {
  companion object : IntEntityClass<ItemSaida>(ItensSaida)
  
  val quantidade by ItensSaida.quantidade
  val custo_unitario by ItensSaida.custo_unitario
}

class Saldo(id: EntityID<Int>) : IntEntity(id) {
  companion object : IntEntityClass<Saldo>(Saldos)
  
  val loja by Saldos.loja
  val quantidade by Saldos.quantidade
  val produto by Saldos.produto
}
