package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.TipoMov.ENTRADA
import br.com.engecopi.estoque.model.TipoMov.SAIDA
import br.com.engecopi.estoque.model.finder.NotaFinder
import br.com.engecopi.framework.model.BaseModel
import br.com.engecopi.saci.QuerySaci
import br.com.engecopi.saci.beans.NotaEntradaSaci
import br.com.engecopi.saci.beans.NotaSaidaSaci
import br.com.engecopi.saci.saci
import io.ebean.annotation.Index
import java.time.LocalDate
import java.time.LocalTime
import javax.persistence.CascadeType.MERGE
import javax.persistence.CascadeType.PERSIST
import javax.persistence.CascadeType.REFRESH
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
  @Index(unique = false)
  var numero: String = ""
  @Enumerated(EnumType.STRING)
  var tipoMov: TipoMov = ENTRADA
  var data: LocalDate = LocalDate.now()
  var hora: LocalTime = LocalTime.now()
  @Size(max = 100)
  var observacao: String = ""
  @ManyToOne(cascade = [PERSIST, MERGE, REFRESH])
  var loja: Loja? = null
  @OneToMany(mappedBy = "nota", cascade = [PERSIST, MERGE, REFRESH])
  val itensNota: List<ItemNota>? = null
  
  companion object Find : NotaFinder() {
    fun findEntrada(numero: String?, loja: Loja?): Nota? {
      return Nota.where()
              .tipoMov.eq(ENTRADA)
              .numero.eq(numero)
              .loja.id.eq(loja?.id)
              .findOne()
    }
    
    fun findSaida(numero: String?, loja: Loja?): Nota? {
      return if (numero.isNullOrBlank() || loja == null)
        null
      else
        Nota.where()
                .tipoMov.eq(SAIDA)
                .numero.eq(numero)
                .loja.id.eq(loja.id)
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
      val numMax = max.toIntOrNull() ?: 0
      return numMax + 1
    }
    
    fun findNotaEntradaSaci(numeroNF: String?, lojaNF: Loja?): List<NotaEntradaSaci> {
      numeroNF ?: return emptyList()
      lojaNF ?: return emptyList()
      val numero = numeroNF.split("/").getOrNull(0) ?: return emptyList()
      val serie = numeroNF.split("/").getOrNull(1) ?: return emptyList()
      return saci.findNotaEntrada(lojaNF.numero, numero, serie)
    }
    
    fun findNotaSaidaSaci(numeroNF: String?, lojaNF: Loja?): List<NotaSaidaSaci> {
      numeroNF ?: return emptyList()
      lojaNF ?: return emptyList()
      val numero = numeroNF.split("/").getOrNull(0) ?: return emptyList()
      val serie = numeroNF.split("/").getOrNull(1) ?: return emptyList()
      return saci.findNotaSaida(lojaNF.numero, numero, serie)
    }
  }
  
  fun findItem(produto: Produto): ItemNota? {
    refresh()
    return itensNota?.firstOrNull { it.produto == produto }
  }
}

enum class TipoMov(val multiplicador: Int) {
  ENTRADA(1),
  SAIDA(-1)
}
