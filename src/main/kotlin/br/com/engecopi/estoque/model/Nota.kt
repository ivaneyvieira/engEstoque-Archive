package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.TipoMov.ENTRADA
import br.com.engecopi.estoque.model.TipoMov.SAIDA
import br.com.engecopi.estoque.model.finder.NotaFinder
import br.com.engecopi.framework.model.BaseModel
import br.com.engecopi.saci.beans.NotaEntradaSaci
import br.com.engecopi.saci.beans.NotaSaidaSaci
import br.com.engecopi.saci.saci
import io.ebean.annotation.Index
import java.time.LocalDate
import java.time.LocalTime
import javax.persistence.CascadeType.MERGE
import javax.persistence.CascadeType.PERSIST
import javax.persistence.CascadeType.REFRESH
import javax.persistence.CascadeType.REMOVE
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
  @Enumerated(EnumType.STRING)
  var tipoNota: TipoNota? = null
  @Size(max = 6)
  var rota: String = ""
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
      val max = where()
                        .findList()
                        .map { it.numero }
                        .filter { regex.matches(it) }
                        .max() ?: "0"
      val numMax = max.toIntOrNull() ?: 0
      return numMax + 1
    }
    
    fun findNotaEntradaSaci(numeroNF: String?, lojaNF: Loja?): List<NotaEntradaSaci> {
      numeroNF ?: return emptyList()
      lojaNF ?: return emptyList()
      val numero = numeroNF.split("/").getOrNull(0) ?: return emptyList()
      val serie = numeroNF.split("/").getOrNull(1) ?: ""
      return saci.findNotaEntrada(lojaNF.numero, numero, serie)
    }
    
    fun findNotaSaidaSaci(numeroNF: String?, lojaNF: Loja?): List<NotaSaidaSaci> {
      numeroNF ?: return emptyList()
      lojaNF ?: return emptyList()
      val numero = numeroNF.split("/").getOrNull(0) ?: return emptyList()
      val serie = numeroNF.split("/").getOrNull(1) ?: ""
      return saci.findNotaSaida(lojaNF.numero, numero, serie)
    }
  }
  
  fun findItem(produto: Produto): ItemNota? {
    refresh()
    return itensNota?.firstOrNull { it.produto == produto }
  }
}

enum class TipoMov(val multiplicador: Int, val descricao : String) {
  ENTRADA(1, "Entrada"),
  SAIDA(-1, "Saída")
}

enum class TipoNota(val tipoMov: TipoMov, val descricao: String, val descricao2: String, val isFree: Boolean = false) {
  //Entrada
  COMPRA(ENTRADA, "Compra", "Compra"),
  TRANSFERENCIA_E(ENTRADA, "Transferência", "Transferência Entrada"),
  DEV_CLI(ENTRADA, "Dev Cliente", "Dev Cliente"),
  ACERTO_E(ENTRADA, "Acerto", "Acerto Entrada"),
  PEDIDO_E(ENTRADA, "Pedido", "Pedido Entrada"),
  OUTROS_E(ENTRADA, "Outros", "Outras Entradas", true),
  //Saída
  VENDA(SAIDA, "Venda", "Venda"),
  TRANSFERENCIA_S(SAIDA, "Transferência", "Transferência Saída"),
  ENT_RET(SAIDA, "Ent/Ret", "Ent/Ret"),
  DEV_FOR(SAIDA, "Dev Fornecedor","Dev Fornecedor"),
  ACERTO_S(SAIDA, "Acerto", "Acerto Saída"),
  PEDIDO_S(SAIDA, "Pedido", "Pedido Saída"),
  OUTROS_S(SAIDA, "Outros", "Outras Saídas", true);
  
  companion object {
    fun valuesEntrada(): List<TipoNota> {
      return values().filter { it.tipoMov == ENTRADA }
    }
    
    fun valuesSaida(): List<TipoNota> {
      return values().filter { it.tipoMov == SAIDA }
    }
  }
}