package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.finder.EtiquetaFinder
import br.com.engecopi.framework.model.BaseModel
import javax.persistence.Entity
import javax.persistence.Lob
import javax.persistence.Table
import javax.validation.constraints.Size

@Entity
@Table(name = "etiquetas")
class Etiqueta : BaseModel() {
  @Size(max=60)
  var titulo : String = ""
  @Lob
  var template: String = ""

  companion object Find : EtiquetaFinder()
}
