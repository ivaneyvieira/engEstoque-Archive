package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.finder.ViewCodBarConferenciaFinder
import io.ebean.annotation.Cache
import io.ebean.annotation.View
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Cache(enableQueryCache = false)
@Entity
@View(name = "v_codigo_barra_conferencia")
class ViewCodBarConferencia {
  @Id
  @Column(name = "id_itens_nota")
  val id : Long? = null
  val codbar : String? = ""

  companion object Find : ViewCodBarConferenciaFinder() {
    fun findNota(key: String): ItemNota? {
      val id = where().codbar.eq(key)
        .findList()
        .firstOrNull()
        ?.id ?: return null
      return ItemNota.byId(id) ?: return null
    }
  }
}
