package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.TipoMov.ENTRADA
import br.com.engecopi.estoque.model.TipoNota.OUTROS_E
import br.com.engecopi.estoque.model.finder.ItemNotaFinder
import br.com.engecopi.framework.model.BaseModel
import br.com.engecopi.saci.beans.NotaSaci
import io.ebean.annotation.Cache
import io.ebean.annotation.CacheQueryTuning
import io.ebean.annotation.Index
import io.ebean.annotation.Length
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.persistence.CascadeType.MERGE
import javax.persistence.CascadeType.PERSIST
import javax.persistence.CascadeType.REFRESH
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.persistence.Transient
import kotlin.reflect.full.memberProperties

@Entity
@Cache(enableQueryCache = true)
@CacheQueryTuning(maxSecsToLive = 30)
@Table(name = "itens_nota")
@Index(unique = true, columnNames = ["nota_id", "produto_id", "localizacao"])
class ItemNota: BaseModel() {
  var data: LocalDate = LocalDate.now()
  var hora: LocalTime = LocalTime.now()
  var quantidade: Int = 0
  // @FetchPreference(1)
  @ManyToOne(cascade = [PERSIST, MERGE, REFRESH])
  var produto: Produto? = null
  @ManyToOne(cascade = [PERSIST, MERGE, REFRESH])
  // @FetchPreference(2)
  var nota: Nota? = null
  @ManyToOne(cascade = [PERSIST, MERGE, REFRESH])
  // @FetchPreference(3)
  var etiqueta: Etiqueta? = null
  @ManyToOne(cascade = [PERSIST, MERGE, REFRESH])
  // @FetchPreference(4)
  var usuario: Usuario? = null
  var saldo: Int? = 0
  var impresso: Boolean = false
  @Length(60)
  var localizacao: String = ""
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
  val ultilmaMovimentacao: Boolean
    @Transient get() {
      return produto?.ultimaNota()?.let {
        it.id == this.id
      } ?: true
    }
  val template: String
    @Transient get() = Etiqueta.where().tipoMov.eq(tipoMov).findList().firstOrNull()?.template ?: ""

  companion object Find: ItemNotaFinder() {
    fun find(nota: Nota?, produto: Produto?): ItemNota? {
      nota ?: return null
      produto ?: return null
      return ItemNota.where().nota.id.eq(nota.id).produto.id.eq(produto.id).findOne()
    }

    fun isInsert(notaSaci: NotaSaci): Boolean {
      val tipoNota = TipoNota.values().find {it.toString() == notaSaci.tipo} ?: OUTROS_E
      val numeroSerie = if(notaSaci.serie == "") notaSaci.numero ?: "" else "${notaSaci.numero}/${notaSaci.serie}"
      val nota = if(tipoNota.tipoMov == ENTRADA) Nota.findEntrada(numeroSerie)
      else Nota.findSaida(numeroSerie)
      if(nota == null) println("################ Nota nula ${nota?.numero}")
      nota ?: return false
      val prd = Produto.findProduto(notaSaci.prdno, notaSaci.grade)
      if(prd == null)
        println("################ Produto nula ${prd?.codigo} ${prd?.grade}")
      prd ?: return false
      return where().produto.equalTo(prd).nota.equalTo(nota).exists()
    }
  }

  fun printEtiqueta() = NotaPrint(this)

  fun recalculaSaldos() {
    produto?.recalculaSaldos(localizacao = localizacao)
  }
}

class NotaPrint(item: ItemNota) {
  val notaSaci = item.nota
  val rota = notaSaci?.rota ?: ""
  val nota = notaSaci?.numero ?: ""
  val tipoObservacao = notaSaci?.observacao?.split(" ")?.get(0) ?: ""
  val isNotaSaci = when(notaSaci?.tipoNota) {
    TipoNota.OUTROS_E -> false
    TipoNota.OUTROS_S -> false
    null              -> false
    else              -> true
  }
  val tipoNota = if(isNotaSaci) notaSaci?.tipoNota?.descricao ?: ""
  else tipoObservacao
  val dataLocal = if(isNotaSaci) notaSaci?.data
  else item.data
  val data = dataLocal?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) ?: ""
  val produto = item.produto
  val sd = item.saldo ?: 0
  val quant = item.quantidade
  val prdno = produto?.codigo?.trim() ?: ""
  val grade = produto?.grade ?: ""
  val name = produto?.descricao ?: ""
  val prdnoGrade = "$prdno${if(grade == "") "" else "-$grade"}"
  val un
    get() = produto?.vproduto?.unidade ?: "UN"
  val loc = item.localizacao
  val numeroLoja = notaSaci?.loja?.numero ?: 0

  fun print(template: String): String {
    return NotaPrint::class.memberProperties.fold(template) {reduce, prop ->
      reduce.replace("[${prop.name}]", "${prop.get(this)}")
    }
  }
}

