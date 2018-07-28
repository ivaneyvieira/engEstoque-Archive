package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.finder.ItemNotaFinder
import br.com.engecopi.framework.model.BaseModel
import io.ebean.annotation.Index
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.persistence.CascadeType.ALL
import javax.persistence.CascadeType.MERGE
import javax.persistence.CascadeType.PERSIST
import javax.persistence.CascadeType.REFRESH
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.persistence.Transient

@Entity
@Table(name = "itens_nota")
@Index(unique = true, columnNames = ["nota_id", "produto_id"])
class ItemNota : BaseModel() {
  var data: LocalDate = LocalDate.now()
  var hora: LocalTime = LocalTime.now()
  var quantidade: Int = 0
  @ManyToOne(cascade = [PERSIST, MERGE, REFRESH])
  var produto: Produto? = null
  @ManyToOne(cascade = [ALL])
  var nota: Nota? = null
  @ManyToOne(cascade = [ALL])
  var etiqueta: Etiqueta? = null
  @ManyToOne(cascade = [ALL])
  var usuario: Usuario? = null
  
  val quantidadeSaldo: Int
    get() = (nota?.tipoMov?.multiplicador ?: 0) * quantidade
  
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
  
  val ultilmaMovimentacao: Boolean
    @Transient
    get() {
      return produto?.ultimaNota()?.let {
        it.id == this.id
      } ?: true
    }
  val template: String
    @Transient get() = etiqueta?.template ?: ""
  
  companion object Find : ItemNotaFinder() {
    fun find(nota: Nota?, produto: Produto?): ItemNota? {
      return ItemNota.where().nota.id.eq(nota?.id)
              .produto.id.eq(produto?.id)
              .findOne()
    }
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
  
  val barras = "$prdnoGrade quantidadeMov saldoNovo loja numeroNota"
}

