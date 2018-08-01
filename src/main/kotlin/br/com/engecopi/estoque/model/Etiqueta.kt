package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.finder.EtiquetaFinder
import br.com.engecopi.framework.model.BaseModel
import javax.persistence.CascadeType.MERGE
import javax.persistence.CascadeType.PERSIST
import javax.persistence.CascadeType.REFRESH
import javax.persistence.CascadeType.REMOVE
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Lob
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.validation.constraints.Size

@Entity
@Table(name = "etiquetas")
class Etiqueta : BaseModel() {
  @Size(max=60)
  var titulo : String = ""
  @Enumerated(EnumType.STRING)
  var tipoMov: TipoMov? = null
  @Lob
  var template: String = ""
  @OneToMany(mappedBy = "etiqueta", cascade = [PERSIST, MERGE, REFRESH, REMOVE])
  val itensNota: List<ItemNota>? = null
  
  companion object Find : EtiquetaFinder()
}
