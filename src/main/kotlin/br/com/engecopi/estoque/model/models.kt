package br.com.engecopi.estoque.model

import br.com.engecopi.framework.model.cacheValue
import br.com.engecopi.saci.QuerySaci
import br.com.engecopi.saci.beans.ProdutoSaci
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

enum class TipoProduto { NORMAL,
  PECA,
  BOBINA,
  CAIXA
}

enum class TipoMov { ENTRADA,
  SAIDA
}

object Produtos : IntIdTable() {
  val codigo = varchar("codigo", 16)
  val grade = varchar("grade", 8)
  var codebar = varchar("codebar", 16)
  val data_cadastro = date("data_cadastro")
  val tipo = enumerationByName("tipo", 10, TipoProduto::class.java)
  val quant_lote = integer("quant_lote")
  val quant_bobina = integer("quant_bobina")
  
  init {
    uniqueIndex(codigo, grade)
  }
}

object Notas : IntIdTable() {
  val numero = varchar("numero", 15)
  val tipoMov = enumerationByName("tipoMov", 10, TipoMov::class.java)
  val loja = integer("loja")
  val data = date("data")
  val hora = varchar("hora", 5)
  val observacao = varchar("observacao", 100)
}

object ItensNota : IntIdTable() {
  val quantidade = integer("quantidade")
  val produto = reference("idProduto", Produtos)
  val nota = reference("idNota", Notas)
}

object Lotes : IntIdTable() {
  val sequencia = integer("sequencia")
  val total = integer("total")
  val produto = reference("idProduto", Produtos)
}

object Movimentacoes : IntIdTable() {
  val quantidade = integer("quantidade")
  val saldo = integer("saldo")
  val lote = reference("idLote", Lotes)
  val itemNota = reference("idItemNota", ItensNota)
}

/******************************* ENTIDADES ***************************/

class Produto(id: EntityID<Int>) : IntEntity(id) {
  companion object : IntEntityClass<Produto>(Produtos)
  
  var codigo by Produtos.codigo
  var grade by Produtos.grade
  var codebar by Produtos.codebar
  var data_cadastro by Produtos.data_cadastro
  val produtoSaci: ProdutoSaci? by cacheValue {
    QuerySaci.querySaci.findProduto(codigo).first { it.grade == grade }
  }
  
  val nome by cacheValue { produtoSaci?.nome ?: "" }
  val custo by cacheValue { produtoSaci?.custo ?: 0.0000 }
  
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
  
  val cacheItens = Cache { itens }
  
  fun cacheItens() = cacheItens.value()
  
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
  companion object : IntEntityClass<Saida>(Saidas) {
    fun novoNumero(): Int = transaction {
      val maxNum = Saida.all().map { it.numero }.max() ?: 0
      maxNum + 1
    }
  }
  
  var numero by Saidas.numero
  var loja by Saidas.loja
  var data by Saidas.data
  var hora by Saidas.hora
  
  val itens by ItemSaida referrersOn ItensSaida.notaSaida
  
  val cacheItens = Cache { transaction { itens } }
  
  fun cacheItens() = transaction { cacheItens.value() }
  
  val dataSaida: Date
    get() = data.toDate()
}

class ItemSaida(id: EntityID<Int>) : IntEntity(id) {
  companion object : IntEntityClass<ItemSaida>(ItensSaida)
  
  var quantidade by ItensSaida.quantidade
  var custo_unitario by ItensSaida.custo_unitario
  var notaSaida by Saida referencedOn ItensSaida.notaSaida
  var produto by Produto referencedOn ItensSaida.produto
  
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

class Saldo(id: EntityID<Int>) : IntEntity(id) {
  companion object : IntEntityClass<Saldo>(Saldos) {
    fun lojas() = Saldo.all().filter { it.quantidade != 0 }.map { it.loja }.distinct().sorted()
  }
  
  var loja by Saldos.loja
  var quantidade by Saldos.quantidade
  var produto by Produto referencedOn Saldos.produto
}

class Cache<T>(val exec: () -> T) {
  var millisecond = System.currentTimeMillis()
  var lastValue: T? = null
  
  fun execValue(delay: Long = 0) = transaction {
    println("cache $delay")
    transaction { exec() }
  }
  
  fun value(): T {
    val currentTimeMillis = System.currentTimeMillis()
    val delay = currentTimeMillis - millisecond
    millisecond = System.currentTimeMillis()
    if (delay > 30000) {
      lastValue = execValue(delay)
    }
    val ret = lastValue ?: execValue(delay)
    lastValue = ret
    return ret
  }
}