package br.com.engecopi.estoque.model

import br.com.engecopi.framework.model.cacheValue
import br.com.engecopi.saci.QuerySaci
import br.com.engecopi.saci.beans.ProdutoSaci
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable

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
  val data = date("data")
  val hora = varchar("hora", 5)
  val observacao = varchar("observacao", 100)
  
  val loja = reference("idLoja", Lojas)
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

object Lojas : IntIdTable() {
  val numero = integer("numero")
}

object Usuarios : IntIdTable() {
  val numero = integer("numero")
  
  val loja = reference("idLoja", Lojas)
}

/******************************* ENTIDADES ***************************/

class Produto(id: EntityID<Int>) : IntEntity(id) {
  companion object : IntEntityClass<Produto>(Produtos)
  
  var codigo by Produtos.codigo
  var grade by Produtos.grade
  var codebar by Produtos.codebar
  var data_cadastro by Produtos.data_cadastro
  var tipo by Produtos.tipo
  var quant_lote by Produtos.quant_lote
  var quant_bobina by Produtos.quant_bobina
  
  val produtoSaci: ProdutoSaci? by cacheValue {
    QuerySaci.querySaci.findProduto(codigo).first { it.grade == grade }
  }
  
  val nome by cacheValue { produtoSaci?.nome ?: "" }
  val custo by cacheValue { produtoSaci?.custo ?: 0.0000 }
  
  val itensNota by ItemNota referrersOn ItensNota.produto
  val lotes by Lote referrersOn Lotes.produto
  
  fun saldo(loja: Int): Int {
    return itensNota.filter { it.nota.loja.numero == loja }.map { it.quantidade }.sum()
  }
}

class Nota(id: EntityID<Int>) : IntEntity(id) {
  companion object : IntEntityClass<Nota>(Notas)
  
  var numero by Notas.numero
  var tipoMov by Notas.tipoMov
  
  var data by Notas.data
  var hora by Notas.hora
  var observacao by Notas.observacao
  
  val itens by ItemNota referrersOn ItensNota.nota
  val loja by Loja referencedOn Notas.loja
 
  val dataEntrada
    get() = data.toDate()
}

class ItemNota(id: EntityID<Int>) : IntEntity(id) {
  companion object : IntEntityClass<ItemNota>(ItensNota)
  
  var quantidade by ItensNota.quantidade
  
  var nota by Nota referencedOn ItensNota.nota
  var produto by Produto referencedOn ItensNota.produto
  val movimentacoes by Movimentacao referrersOn Movimentacoes.itemNota
  
  val codigo
    get() = produto.codigo
  
  val nome
    get() = produto.nome
  
  val grade
    get() = produto.grade
  
}

class Movimentacao(id: EntityID<Int>) : IntEntity(id) {
  companion object : IntEntityClass<Movimentacao>(Movimentacoes)
  
  var quantidade by Movimentacoes.quantidade
  var saldo by Movimentacoes.saldo

  var lote by Lote referencedOn Movimentacoes.lote
  var itemNota by ItemNota referencedOn Movimentacoes.itemNota
}

class Lote(id: EntityID<Int>) : IntEntity(id) {
  companion object : IntEntityClass<Lote>(Lotes)
  
  var sequencia by Lotes.sequencia
  var total by Lotes.total
  
  val movimentaccoes by Movimentacao referrersOn  Movimentacoes.lote
  var produto by Produto referencedOn  Lotes.produto
}

class Loja(id: EntityID<Int>) : IntEntity(id) {
  companion object : IntEntityClass<Loja>(Lojas)
  
  var numero by Lojas.numero
  
  val notas by Nota referrersOn Notas.loja
  val usuarios by Usuario referrersOn Usuarios.loja
}

class Usuario(id: EntityID<Int>) : IntEntity(id) {
  companion object : IntEntityClass<Usuario>(Usuarios)
  
  var numero by Usuarios.numero
  
  var loja by Loja referencedOn Usuarios.loja
}