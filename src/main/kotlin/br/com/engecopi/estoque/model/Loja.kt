package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.finder.LojaFinder
import br.com.engecopi.framework.model.BaseModel
import br.com.engecopi.saci.QuerySaci
import javax.persistence.Entity
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.persistence.Transient

@Entity
@Table(name = "lojas")
class Loja : BaseModel() {
  var numero: Int = 0
  @OneToMany(mappedBy = "loja")
  val notas: List<Nota>? = null
  @OneToMany(mappedBy = "loja")
  val usuarios: List<Usuario>? = null
  
  fun lojaSaci() = QuerySaci.querySaci.findLojas(numero).firstOrNull()
  
  val sigla
    @Transient
    get() = lojaSaci()?.sigla ?: ""
  
  companion object Find : LojaFinder() {
    fun findLoja(storeno: Int): Loja? {
      return if(storeno == 0) null
      else
        where().numero.eq(storeno).findOne()
        ?: QuerySaci.querySaci.findLojas(storeno).firstOrNull()?.let { lojaSaci ->
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
      QuerySaci.querySaci.findLojas(0).forEach {lojaSaci->
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
