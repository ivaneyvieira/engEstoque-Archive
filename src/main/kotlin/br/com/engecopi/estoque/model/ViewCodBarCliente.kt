package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.finder.ViewCodBarClienteFinder
import io.ebean.annotation.Cache
import io.ebean.annotation.View
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Cache(enableQueryCache = false)
@Entity
@View(name = "v_codigo_barra_cliente")
class ViewCodBarCliente {
  @Id
  @Column(name = "id_nota")
  val id: Long = 0
  val codbar: String = ""
  val codbarLimpo: String = ""
  val storeno: Int = 0
  val numero: String = ""
  val sequencia: Int = 0

  companion object Find: ViewCodBarClienteFinder() {
    fun findNota(key: String): ViewCodBarCliente? {
      return where().codbar.eq(key)
        .findList()
        .firstOrNull()
    }

    fun findKeyItemNota(key: String, abreviacao: String, statusNota: StatusNota): List<ItemNota> {
      val nota = findNota(key) ?: return emptyList()
      return ItemNota.where()
        .nota.id.eq(nota.id)
        .localizacao.startsWith(abreviacao)
        .status.eq(statusNota)
        .findList()
    }
  }
}
