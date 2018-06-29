package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.TipoMov.ENTRADA
import br.com.engecopi.estoque.model.TipoMov.SAIDA
import br.com.engecopi.estoque.model.finder.NotaFinder
import br.com.engecopi.framework.model.BaseModel
import br.com.engecopi.saci.QuerySaci
import br.com.engecopi.saci.beans.NotaEntradaSaci
import io.ebean.annotation.Index
import java.time.LocalDate
import java.time.LocalTime
import javax.persistence.CascadeType.ALL
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
  @Index(unique=false)
  var numero: String = ""
  @Enumerated(EnumType.STRING)
  var tipoMov: TipoMov = ENTRADA
  var data: LocalDate = LocalDate.now()
  var hora: LocalTime = LocalTime.now()
  @Size(max = 100)
  var observacao: String = ""
  @ManyToOne(cascade = [ALL])
  var loja: Loja? = null
  @OneToMany(mappedBy = "nota", cascade = [ALL])
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
      return where().tipoMov.eq(SAIDA).findList()
    }
    
    fun novoNumero(): Int {
      val regex = "[0-9]+".toRegex()
      val max = where().findList().map { it.numero }.filter { regex.matches(it) }.max() ?: "0"
      val numMax = max.toInt()
      return numMax + 1
    }
  
    fun findNotaEntradaSaci(numeroNF: String?, lojaNF: Loja?): List<NotaEntradaSaci> {
      numeroNF ?: return emptyList()
      lojaNF ?: return emptyList()
      val numero = numeroNF.split("/").getOrNull(0) ?: ""
      val serie = numeroNF.split("/").getOrNull(1) ?: ""
      return QuerySaci.querySaci.findNotaEntrada(lojaNF.numero, numero, serie)
    }
  }
  
  fun findItem(produto: Produto): ItemNota? {
    refresh()
    return itensNota?.firstOrNull { it.produto == produto }
  }
}

enum class TipoMov {
  ENTRADA,
  SAIDA
}
