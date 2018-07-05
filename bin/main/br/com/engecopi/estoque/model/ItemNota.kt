package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.finder.ItemNotaFinder
import br.com.engecopi.framework.model.BaseModel
import io.ebean.Expr.ne
import io.ebean.annotation.Index
import java.time.LocalDate
import java.time.LocalTime
import javax.persistence.CascadeType.ALL
import javax.persistence.CascadeType.MERGE
import javax.persistence.CascadeType.PERSIST
import javax.persistence.CascadeType.REFRESH
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
  @OneToMany(mappedBy = "itemNota", cascade = [PERSIST, MERGE, REFRESH])
  var movimentacoes: List<Movimentacao>? = null
  
  val descricao: String?
    @Transient get() = produto?.descricao
  
  val codigo: String?
    @Transient get() = produto?.codigo
  
  val grade: String?
    @Transient get() = produto?.grade
  
  companion object Find : ItemNotaFinder()
  
  fun ultimoLote(): Lote? {
    val loja = this.nota?.loja
    val produto = this.produto
    return Lote.where().loja.id.eq(loja?.id)
            .produto.id.eq(produto?.id)
            .movimentacoes.itemNota.id.ne(this)
            .orderBy().sequencia.desc()
            .findList().firstOrNull()
  }
  
}
