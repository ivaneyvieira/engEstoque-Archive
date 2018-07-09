package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.finder.LabelFinder
import br.com.engecopi.estoque.model.TipoProduto.NORMAL
import br.com.engecopi.framework.model.BaseModel
import io.ebean.annotation.Index
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Lob
import javax.persistence.Table

@Entity
@Table(name = "labels")
class Label : BaseModel() {
  @Index(unique = true)
  @Enumerated(EnumType.STRING)
  var tipo: TipoProduto = NORMAL
  @Lob
  var layout: String = ""

  companion object Find : LabelFinder() {
    fun find(tipoProduto: TipoProduto?): Label? {
      return where().tipo.eq(tipoProduto).findOne()
    }
  }
}
