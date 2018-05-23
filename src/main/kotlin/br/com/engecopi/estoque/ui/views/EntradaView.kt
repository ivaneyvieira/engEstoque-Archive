package br.com.engecopi.estoque.ui.views

import br.com.engecopi.estoque.model.Entrada
import br.com.engecopi.estoque.model.ItemEntrada
import br.com.engecopi.estoque.ui.LoginService
import br.com.engecopi.estoque.ui.title
import br.com.engecopi.estoque.viewmodel.EntradaViewModel
import br.com.engecopi.estoque.viewmodel.NotaEntradaVo
import br.com.engecopi.framework.ui.view.DialogPopup
import br.com.engecopi.framework.ui.view.LayoutView
import br.com.engecopi.saci.QuerySaci
import com.github.vok.karibudsl.AutoView
import com.github.vok.karibudsl.ModifierKey.Ctrl
import com.github.vok.karibudsl.VAlign
import com.github.vok.karibudsl.addColumnFor
import com.github.vok.karibudsl.align
import com.github.vok.karibudsl.bind
import com.github.vok.karibudsl.button
import com.github.vok.karibudsl.clickShortcut
import com.github.vok.karibudsl.comboBox
import com.github.vok.karibudsl.expandRatio
import com.github.vok.karibudsl.fillParent
import com.github.vok.karibudsl.grid
import com.github.vok.karibudsl.horizontalLayout
import com.github.vok.karibudsl.isMargin
import com.github.vok.karibudsl.textField
import com.github.vok.karibudsl.w
import com.vaadin.data.ValidationResult
import com.vaadin.data.Validator
import com.vaadin.data.ValueContext
import com.vaadin.data.provider.ListDataProvider
import com.vaadin.event.ShortcutAction.KeyCode
import com.vaadin.navigator.View
import com.vaadin.ui.Grid
import com.vaadin.ui.Notification
import com.vaadin.ui.Notification.Type.WARNING_MESSAGE
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.renderers.DateRenderer
import com.vaadin.ui.renderers.NumberRenderer
import org.jetbrains.exposed.sql.transactions.transaction
import java.text.DecimalFormat
import java.text.SimpleDateFormat

@AutoView("")
class EntradaView : LayoutView() {
  val lojaDefault = LoginService.currentUser?.storeno ?: 0
  override val viewModel = EntradaViewModel(lojaDefault) {
    grid?.dataProvider?.refreshAll()
  }
  val grid: Grid<Entrada>?
  var gridProduto: Grid<ItemEntrada>? = null
  
  val dialogNotaEntrada = DialogNotaEntrada()
  
  init {
    isMargin = true
    setSizeFull()
    title("Entrada de produtos")
    
    horizontalLayout {
      isVisible = false
      w = fillParent
      isMargin = false
      textField("Pesquisa") {
        w = fillParent
      }
    }
    
    horizontalLayout {
      button("Carregar Nota de Entrada") {
        clickShortcut = Ctrl + KeyCode.INSERT
        addClickListener {
          dialogNotaEntrada.binder.readBean(viewModel.notaEntradaVo)
          dialogNotaEntrada.show()
        }
      }
      button("Remover Nota de Entrada") {
        clickShortcut = Ctrl + KeyCode.DELETE
        addClickListener {
        }
      }
    }
    grid = grid(Entrada::class) {
      dataProvider = ListDataProvider(viewModel.listaGrid)
      removeAllColumns()
      setSizeFull()
      expandRatio = 1.0f
      addColumnFor(Entrada::numero) {
        caption = "Número"
      }
      addColumnFor(Entrada::loja) {
        caption = "Loja"
      }
      addColumnFor(Entrada::dataEntrada) {
        caption = "Data"
        setRenderer(DateRenderer(SimpleDateFormat("dd/MM/yyyy")))
      }
      addColumnFor(Entrada::hora) {
        caption = "Hora"
      }
      addSelectionListener {
        transaction {
          it.firstSelectedItem?.let {
            if (it.isPresent) {
              gridProduto?.dataProvider = ListDataProvider(it.get().cacheItens().toList())
            }
          }
        }
      }
    }
    gridProduto = grid(ItemEntrada::class) {
      caption = "Itens da nota de entrada"
      removeAllColumns()
      setSizeFull()
      expandRatio = 1.0f
      addColumn { it.codigo }.apply {
        caption = "Código"
      }
      addColumn { it.nome }.apply {
        caption = "Descrição"
      }
      addColumn { it.grade }.apply {
        caption = "Grade"
      }
      addColumn { it.quantidade }.apply {
        caption = "Quantidade"
        setRenderer(NumberRenderer(DecimalFormat("0")))
        align = VAlign.Right
      }
    }
    viewModel.execPesquisa()
  }
  
  inner class DialogNotaEntrada : DialogPopup<NotaEntradaVo>("Pesquisa Nota de Entrada", NotaEntradaVo::class) {
    val numero = form.textField("Número") {
      bind(binder)
              .withValidator(RequiredString("O número deve ser informado"))
              .bind(NotaEntradaVo::numero)
      
    }
    val serie = form.textField("Série") {
      bind(binder)
              .withValidator(RequiredString("A série deve ser informada"))
              .bind(NotaEntradaVo::serie)
      
    }
    val loja = form.comboBox<Int>("Loja") {
      val lojas = QuerySaci.querySaci.findLojas(lojaDefault)
      setItems(lojas.map { it.storeno })
      setItemCaptionGenerator { storeno ->
        lojas.firstOrNull { it.storeno ?: 0 == storeno }?.sigla ?: ""
      }
      value = lojas.map { it.storeno }.firstOrNull()
      isTextInputAllowed = false
      isEmptySelectionAllowed = false
      this.isScrollToSelectedItem = true
      bind(binder).bind(NotaEntradaVo::loja)
    }
    
    init {
      addClickListenerOk {
        val saved = binder.writeBeanIfValid(viewModel.notaEntradaVo)
        if (saved)
          viewModel.processaNotaEntrada()
        else
          Notification.show("Foram encontrados erros", WARNING_MESSAGE)
      }
    }
  }
}

class RequiredString(val errorMsg: String) : Validator<String> {
  override fun apply(value: String?, context: ValueContext?): ValidationResult {
    return if (value == null || value == "")
      ValidationResult.error(errorMsg)
    else
      ValidationResult.ok()
  }
}

