package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.finder.ItemNotaFinder
import br.com.engecopi.framework.model.BaseModel
import io.ebean.annotation.Index
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.persistence.CascadeType.ALL
import javax.persistence.CascadeType.MERGE
import javax.persistence.CascadeType.PERSIST
import javax.persistence.CascadeType.REFRESH
import javax.persistence.CascadeType.REMOVE
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.persistence.Transient

@Entity
@Table(name = "itens_nota")
@Index(unique = true, columnNames = ["nota_id", "produto_id"])
class ItemNota : BaseModel() {
  var data: LocalDate = LocalDate.now()
  var hora: LocalTime = LocalTime.now()
  var quantidade: Int = 0
  var tamanhoLote: Int = 0
  @ManyToOne(cascade = [PERSIST, MERGE, REFRESH])
  var produto: Produto? = null
  @ManyToOne(cascade = [ALL])
  var nota: Nota? = null
  @OneToMany(mappedBy = "itemNota", cascade = [PERSIST, MERGE, REFRESH, REMOVE])
  var movimentacoes: List<Movimentacao>? = null
  
  val descricao: String?
    @Transient get() = produto?.descricao
  
  val codigo: String?
    @Transient get() = produto?.codigo
  
  val grade: String?
    @Transient get() = produto?.grade
  
  val numeroNota: String?
    @Transient get() = nota?.numero
  
  val rota: String?
    @Transient get() = nota?.rota
  
  val tipoMov: TipoMov?
    @Transient get() = nota?.tipoMov
  
  val tipoNota: TipoNota?
    @Transient get() = nota?.tipoNota
  
  val dataNota: LocalDate?
    @Transient get() = nota?.data
  
  @Transient
  var saldoTransient = 0
  
  val quantidadeUnitaria: Int
    @Transient get() = (tipoMov?.multiplicador ?: 0) * (movimentacoes?.sumBy { it.quantidade } ?: 0)
  
  val ultilmaMovimentacao: Boolean
    @Transient
    get() {
      return produto?.ultimaNota()?.let {
        it.id == this.id
      } ?: true
    }
  
  companion object Find : ItemNotaFinder() {
    fun find(nota: Nota?, produto: Produto?): ItemNota? {
      return ItemNota.where().nota.id.eq(nota?.id)
              .produto.id.eq(produto?.id)
              .findOne()
    }
  }
  
  fun ultimoLote(): Lote? {
    val loja = this.nota?.loja
    val produto = this.produto
    return Lote.where().loja.id.eq(loja?.id)
            .produto.id.eq(produto?.id)
            .movimentacoes.itemNota.id.notIn(this.id)
            .orderBy().sequencia.desc()
            .findList().firstOrNull()
  }
  
  fun loteInicial(): Lote? {
    refresh()
    return movimentacoes?.mapNotNull { it.lote }
            .orEmpty().sortedBy { it.sequencia }.firstOrNull()
  }
  
  fun printEtiqueta() = NotaPrint(this)
}

class NotaPrint(item: ItemNota) {
  private val notaSaci = item.nota
  val rota = notaSaci?.rota ?: ""
  val nota = notaSaci?.numero ?: ""
  val tipoNota = notaSaci?.tipoNota?.descricao ?: ""
  val data = notaSaci?.data?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) ?: ""
  val produto = item.produto
  val prdno = produto?.codigo ?: ""
  val grade = produto?.grade ?: ""
  val name = produto?.descricao ?: ""
  val prdnoGrade = "$prdno${if (grade == "") "" else "-$grade"}"
  val lotes = item.movimentacoes.orEmpty().mapIndexed { index, mov ->
    LotePrint(this, mov, index + 1)
  }
  val quantidadeTotal = lotes.sumBy { it.quantidade }
  val quantidadePorLote = lotes.map { it.quantidade }.distinct().joinToString(separator = "/")
  val quantLotes = lotes.size
  val indexSeq = "${lotes.firstOrNull()?.indexSeq} A ${lotes.lastOrNull()?.indexSeq}"
  val indexNot = "${lotes.firstOrNull()?.indexNot} A ${lotes.lastOrNull()?.indexNot}"
  val barras = "$prdnoGrade $quantLotes $quantidadeTotal $indexNot"
}

class LotePrint(nota: NotaPrint, movimentacao: Movimentacao, index: Int) {
  val numSeq = movimentacao.lote?.sequencia ?: 0
  val totSeq = movimentacao.lote?.total ?: 0
  val numNot = index
  val totNot = totSeq - numSeq + numNot
  val indexSeq = "$numSeq/$totSeq"
  val indexNot = "$numNot/$totNot"
  val quantidade = movimentacao.quantidade
  val saldo = movimentacao.lote?.saldo ?: 0
  val barras = "${nota.prdnoGrade} 1 $quantidade $indexNot"
}