package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.TipoMov.ENTRADA
import br.com.engecopi.estoque.model.TipoMov.SAIDA
import br.com.engecopi.estoque.model.finder.NotaFinder
import br.com.engecopi.framework.model.BaseModel
import java.time.LocalDate
import java.time.LocalTime
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.validation.constraints.Size

@Entity
@Table(name = "notas")
class Nota : BaseModel() {
  @Size(max = 15)
  var numero: String = ""
  @Enumerated(EnumType.STRING)
  var tipoMov: TipoMov = ENTRADA
  var data: LocalDate = LocalDate.now()
  var hora: LocalTime = LocalTime.now()
  @Size(max = 100)
  var observacao: String = ""
  @ManyToOne
  var loja: Loja? = null
  @OneToMany(mappedBy = "nota")
  val itensNota: List<ItemNota>? = null
  
  companion object Find : NotaFinder() {
    fun findEntrada(numero: String): Nota? {
      return Nota.where()
              .tipoMov.eq(ENTRADA)
              .numero.eq(numero)
              .findOne()
    }
    
    fun findSaida(numero: String): Nota? {
      return Nota.where()
              .tipoMov.eq(SAIDA)
              .numero.eq(numero)
              .findOne()
    }
    
    fun findEntradas(loja: Int): List<Nota> {
      return Nota.where()
              .tipoMov.eq(ENTRADA)
              .findList()
              .filter { (it.loja?.numero ?: 0) == loja || loja == 0 }
    }
    
    fun findSaidas(): List<Nota> {
      where().tipoMov.eq(SAIDA).findList()
    }
    
    fun novoNumero(): Int {
      val regex = "[0-9]+".toRegex()
      val max = where().findList().map { it.numero }.filter { regex.matches(it) }.max() ?: "0"
      val numMax = max.toInt()
      numMax + 1
    }
  }
  
  fun findItem(produto: Produto): ItemNota? {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}

enum class TipoMov {
  ENTRADA,
  SAIDA
}
