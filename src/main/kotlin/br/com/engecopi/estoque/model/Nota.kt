package br.com.engecopi.estoque.model

import br.com.engecopi.estoque.model.TipoMov.ENTRADA
import br.com.engecopi.estoque.model.TipoMov.SAIDA
import br.com.engecopi.estoque.model.finder.NotaFinder
import br.com.engecopi.estoque.model.query.QNota
import br.com.engecopi.framework.model.BaseModel
import br.com.engecopi.saci.beans.NotaSaci
import br.com.engecopi.saci.saci
import br.com.engecopi.utils.localDate
import io.ebean.annotation.Aggregation
import io.ebean.annotation.Cache
import io.ebean.annotation.CacheQueryTuning
import io.ebean.annotation.Index
import io.ebean.annotation.Length
import java.time.LocalDate
import java.time.LocalTime
import javax.persistence.CascadeType.MERGE
import javax.persistence.CascadeType.PERSIST
import javax.persistence.CascadeType.REFRESH
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.validation.constraints.Size

@Entity
@Cache(enableQueryCache=true)
@CacheQueryTuning(maxSecsToLive = 30)
@Table(name = "notas")
@Index(columnNames = ["loja_id", "tipo_mov"])
class Nota : BaseModel() {
  @Size(max = 40)
  @Index(unique = false)
  var numero: String = ""
  @Enumerated(EnumType.STRING)
  var tipoMov: TipoMov = ENTRADA
  @Enumerated(EnumType.STRING)
  var tipoNota: TipoNota? = null
  @Size(max = 6)
  var rota: String = ""
  @Length(60)
  var fornecedor: String = ""
  @Length(60)
  var cliente: String = ""
  var data: LocalDate = LocalDate.now()
  var dataEmissao: LocalDate = LocalDate.now()
  var hora: LocalTime = LocalTime.now()
  @Size(max = 100)
  var observacao: String = ""
  @ManyToOne(cascade = [PERSIST, MERGE, REFRESH])
  var loja: Loja? = null
  @OneToMany(mappedBy = "nota", cascade = [PERSIST, MERGE, REFRESH])
  val itensNota: List<ItemNota>? = null
  @Column(name = "sequencia", columnDefinition = "int(11) default 0")
  var sequencia : Int = 0
  @ManyToOne(cascade = [PERSIST, MERGE, REFRESH])
  var usuario: Usuario? = null
  @Aggregation("max(sequencia)")
  var maxSequencia : Int = 0

  companion object Find : NotaFinder() {
    fun createNota(notasaci : NotaSaci?) : Nota?{
      notasaci ?: return null
      return Nota().apply {
        numero = "${notasaci.numero}/${notasaci.serie}"
        tipoNota = TipoNota.values().find { it.toString() == notasaci.tipo }
        tipoMov = tipoNota?.tipoMov ?: ENTRADA
        rota = notasaci.rota ?: ""
        fornecedor = notasaci.vendName ?: ""
        cliente = notasaci.clienteName ?: ""
        data = notasaci.date?.localDate() ?: LocalDate.now()
        dataEmissao = notasaci.dt_emissao?.localDate() ?: LocalDate.now()
        loja = Loja.findLoja(notasaci.storeno)
      }
    }

    fun maxSequencia() : Int {
      return where().select(QNota._alias.maxSequencia).findOne()?.maxSequencia ?: 0
    }

    fun findEntrada(numero: String?): Nota? {
      val loja = RegistryUserInfo.lojaDefault
      return if (numero.isNullOrBlank()) null
      else Nota.where()
        .tipoMov.eq(ENTRADA)
        .numero.eq(numero)
        .loja.id.eq(loja.id)
        .findOne()
    }

    fun findSaida(numero: String?): Nota? {
      val loja = RegistryUserInfo.lojaDefault
      return if (numero.isNullOrBlank()) null
      else Nota.where()
        .tipoMov.eq(SAIDA)
        .numero.eq(numero)
        .loja.id.eq(loja.id)
        .findOne()
    }

    fun findEntradas(): List<Nota> {
      val numero = RegistryUserInfo.lojaDefault.numero
      return Nota
        .where()
        .tipoMov.eq(ENTRADA)
        .loja.numero.eq(numero)
        .findList()
    }

    fun findSaidas(): List<Nota> {
      val numero = RegistryUserInfo.lojaDefault.numero
      return where()
        .tipoMov.eq(SAIDA)
        .loja.numero.eq(numero)
        .findList()
    }

    fun novoNumero(): Int {
      val regex = "[0-9]+".toRegex()
      val max = where()
                  .findList()
                  .asSequence()
                  .map { it.numero }
                  .filter { regex.matches(it) }
                  .max() ?: "0"
      val numMax = max.toIntOrNull() ?: 0
      return numMax + 1
    }

    fun findNotaEntradaSaci(numeroNF: String?): List<NotaSaci> {
      numeroNF ?: return emptyList()
      val loja = RegistryUserInfo.lojaDefault
      val numero = numeroNF.split("/").getOrNull(0) ?: return emptyList()
      val serie = numeroNF.split("/").getOrNull(1) ?: ""
      val notas = saci.findNotaEntrada(loja.numero, numero, serie)
      return notas
    }

    fun findNotaSaidaSaci(numeroNF: String?): List<NotaSaci> {
      numeroNF ?: return emptyList()
      val loja = RegistryUserInfo.lojaDefault
      val numero = numeroNF.split("/").getOrNull(0) ?: return emptyList()
      val serie = numeroNF.split("/").getOrNull(1) ?: ""
      return saci.findNotaSaida(loja.numero, numero, serie)
    }

    fun itemDuplicado(nota: Nota?, produto: Produto?): Boolean {
      val loja_id = nota?.loja?.id ?: return false
      val numero = nota.numero
      val tipoMov = nota.tipoMov
      val produto_id = produto?.id ?: return false
      return ItemNota.where()
               .nota.loja.id.eq(loja_id)
               .nota.numero.eq(numero)
               .nota.tipoMov.eq(tipoMov)
               .produto.id.eq(produto_id)
               .findCount() > 0
    }

    fun findNotaSaidaPXA(nfeKey: String): List<NotaSaci>{
      return saci.findNotaSaidaPXA(nfeKey)
    }
  }

  fun findItem(produto: Produto): ItemNota? {
    refresh()
    return itensNota?.firstOrNull { it.produto == produto }
  }

  fun existe() : Boolean{
    return where().loja.equalTo(loja)
      .numero.eq(numero)
      .findCount() > 0
  }
}

enum class TipoMov(val multiplicador: Int, val descricao: String) {
  ENTRADA(1, "Entrada"), SAIDA(-1, "Saida")
}

enum class TipoNota(val tipoMov: TipoMov, val descricao: String, val descricao2: String, val isFree: Boolean = false) {
  //Entrada
  COMPRA(ENTRADA, "Compra", "Compra"),
  TRANSFERENCIA_E(ENTRADA, "Transferencia", "Transferencia Entrada"), DEV_CLI(ENTRADA, "Dev Cliente", "Dev Cliente"),
  ACERTO_E(ENTRADA, "Acerto", "Acerto Entrada"), PEDIDO_E(ENTRADA, "Pedido", "Pedido Entrada"),
  OUTROS_E(ENTRADA, "Outros", "Outras Entradas", true), //Saída
  RECLASSIFICACAO_E(ENTRADA, "Reclassificação", "Reclassificação Entrada"),
  VENDA(SAIDA, "Venda", "Venda"), TRANSFERENCIA_S(SAIDA, "Transferencia", "Transferencia Saida"),
  ENT_RET(SAIDA, "Ent/Ret", "Ent/Ret"), DEV_FOR(SAIDA, "Dev Fornecedor", "Dev Fornecedor"),
  ACERTO_S(SAIDA, "Acerto", "Acerto Saida"), PEDIDO_S(SAIDA, "Pedido", "Pedido Saida"),
  OUTROS_S(SAIDA, "Outros", "Outras Saidas", true),
  OUTRAS_NFS(SAIDA, "Outras NFS", "Outras NF Saida", true),
  SP_REME(SAIDA, "Simples Remessa", "Simples Remessa", true);


  companion object {
    fun valuesEntrada(): List<TipoNota> {
      return values().filter { it.tipoMov == ENTRADA }
    }

    fun valuesSaida(): List<TipoNota> {
      return values().filter { it.tipoMov == SAIDA }
    }
  }
}


