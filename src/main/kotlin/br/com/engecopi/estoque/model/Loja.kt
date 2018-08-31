package br.com.engecopi.estoque.model

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
  var sigla: String =""
  @OneToMany(mappedBy = "loja", cascade = [PERSIST, MERGE, REFRESH])
  val notas: List<Nota>? = null
  @OneToMany(mappedBy = "loja", cascade = [PERSIST, MERGE, REFRESH])
  val usuarios: List<Usuario>? = null
  @OneToMany(mappedBy = "loja", cascade = [REFRESH])
  var viewProdutoLoc: List<ViewProdutoLoc>? = null
  
  companion object Find : LojaFinder() {
    const val LOJA_DEFAULT_FIELD = "LOJA_DEFAULT"
    
    fun setLojaDefault(numero : Int){
      Transaction.variable(LOJA_DEFAULT_FIELD, "$numero")
    }
    
    fun findLoja(storeno: Int?): Loja? {
      return if(storeno == 0 || storeno == null) null
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
    
    fun lojaSaldo(storeno: Int): List<Loja> {
      return where().notas.id.gt(0).findList()
              .filter { loja -> loja.numero == storeno || storeno == 0 }
    }
    
    fun findLojaUser(storeno: Int): List<Loja> {
      return where().findList().filter { loja -> loja.numero == storeno || storeno == 0 }
    }
    
    fun carregasLojas() {
      saci.findLojas(0).forEach {lojaSaci->
        lojaSaci.storeno?.let {storeno ->
          val loja = Loja.findLoja(storeno)
          if(loja == null){
            Loja().apply {
              numero = storeno
            }.insert()
          }
        }
      }
    }
  }
}
