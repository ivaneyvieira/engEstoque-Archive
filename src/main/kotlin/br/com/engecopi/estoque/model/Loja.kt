package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.RegistryUserInfo.loja
import br.com.engecopi.estoque.model.finder.LojaFinder
import br.com.engecopi.framework.model.BaseModel
import br.com.engecopi.framework.model.Transaction
import br.com.engecopi.saci.QuerySaci
import br.com.engecopi.saci.saci
import io.ebean.annotation.Index
import io.ebean.annotation.Length
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
@Table(name = "lojas")
class Loja : BaseModel() {
  @Index(unique = true)
  var numero: Int = 0
  @Length(2)
  var sigla: String = ""
  @OneToMany(mappedBy = "loja", cascade = [PERSIST, MERGE, REFRESH])
  val notas: List<Nota>? = null
  @OneToMany(mappedBy = "loja", cascade = [PERSIST, MERGE, REFRESH])
  val usuarios: List<Usuario>? = null
  @OneToMany(mappedBy = "loja", cascade = [REFRESH])
  var viewProdutoLoc: List<ViewProdutoLoc>? = null

  companion object Find : LojaFinder() {
    fun findLoja(storeno: Int?): Loja? {
      return if (storeno == 0 || storeno == null) null
      else
        where().numero.eq(storeno).findOne()
        ?: saci.findLojas(storeno).firstOrNull()?.let { lojaSaci ->
          val loja = Loja().apply {
            numero = lojaSaci.storeno ?: 0
          }
          loja.insert()
          loja
        }
    }

    fun lojaSaldo(): List<Loja> {
      val loja = RegistryUserInfo.loja
      return where().notas.id.gt(0).findList()
        .filter { it.id == loja.id }
    }

    fun carregasLojas() {
      saci.findLojas(0).forEach { lojaSaci ->
        lojaSaci.storeno?.let { storeno ->
          val loja = Loja.findLoja(storeno)
          if (loja == null) {
            Loja().apply {
              numero = storeno
            }.insert()
          }
        }
      }
    }
  }

  fun findAbreviacores(): List<String> {
    return viewProdutosLoc
      .asSequence()
      .filter { it.storeno == numero }
      .map { it.abreviacao }
      .distinct()
      .toList()
  }
}
