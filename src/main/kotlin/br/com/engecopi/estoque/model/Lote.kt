package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.TipoMov.ENTRADA
import br.com.engecopi.estoque.model.TipoMov.SAIDA
import br.com.engecopi.estoque.model.finder.LoteFinder
import br.com.engecopi.framework.model.BaseModel
import io.ebean.annotation.Index
import javax.persistence.CascadeType.MERGE
import javax.persistence.CascadeType.PERSIST
import javax.persistence.CascadeType.REFRESH
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.persistence.Transient

@Entity
@Table(name = "lotes")
@Index(unique = true, columnNames = ["loja_id", "produto_id", "sequencia"])
class Lote : BaseModel() {
  @Index(unique = true)
  var sequencia: Int = 0
  var total: Int = 0
  var saldo: Int = 0
  @ManyToOne(cascade = [PERSIST, MERGE, REFRESH])
  var produto: Produto? = null
  @ManyToOne(cascade = [PERSIST, MERGE, REFRESH])
  var loja: Loja? = null
  @OneToMany(mappedBy = "lote", cascade = [PERSIST, MERGE, REFRESH])
  val movimentacoes: List<Movimentacao>? = null
  
  val sequenciaStr: String?
    @Transient get() = "$sequencia/$total"
  
  companion object Find : LoteFinder() {
    fun find(loja: Loja?, produto: Produto?, sequencia: Int): Lote? {
      return where()
              .loja.id.eq(loja?.id)
              .produto.id.eq(produto?.id)
              .sequencia.eq(sequencia)
              .findOne()
    }
  }
  
  fun atualizaSaldo() {
    refresh()
    val movimentacoesTipo = movimentacoes?.groupBy { it.itemNota?.nota?.tipoMov }
    val entradas: Int = movimentacoesTipo?.get(ENTRADA)?.sumBy { it.quantidade } ?: 0
    val saidas: Int = movimentacoesTipo?.get(SAIDA)?.sumBy { it.quantidade } ?: 0
    val total = entradas - saidas
    this.saldo = total
    save()
  }
}
