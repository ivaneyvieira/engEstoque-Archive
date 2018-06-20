package br.com.engecopi.estoque.ui.views

import br.com.engecopi.estoque.model.ItemNota
import br.com.engecopi.estoque.model.Nota
import br.com.engecopi.estoque.ui.LoginService
import br.com.engecopi.estoque.viewmodel.EntradaViewModel
import br.com.engecopi.estoque.viewmodel.NotaEntradaVo
import br.com.engecopi.framework.ui.view.DialogPopup
import br.com.engecopi.framework.ui.view.LayoutView
import br.com.engecopi.framework.ui.view.MessageDialog
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
import com.vaadin.ui.Grid
import com.vaadin.ui.Notification
import com.vaadin.ui.Notification.Type.WARNING_MESSAGE
import com.vaadin.ui.renderers.NumberRenderer
import java.text.DecimalFormat

@AutoView("")
class EntradaView : LayoutView<EntradaViewModel>() {
  val lojaDefault = LoginService.currentUser?.storeno ?: 0
  override val viewModel = EntradaViewModel(lojaDefault) {
    grid?.dataProvider?.refreshAll()
  }
  var grid: Grid<Nota>? = null
  var gridProduto: Grid<ItemNota>? = null
  
  val dialogNotaEntrada = DialogNotaEntrada()
  
  init {
    form("Entrada de produtos") {
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
            grid?.actionSelected("Selecione uma Nota de Entrada") { entrada ->
              MessageDialog.question(
                      message = "Remove a Nota de Entrada (${entrada.numero})?",
                      execYes = { viewModel.removeEntrada(entrada) }
                                    )
            }
          }
        }
      }
      grid = grid(Nota::class) {
        dataProvider = ListDataProvider(viewModel.listaGrid)
        removeAllColumns()
        setSizeFull()
        expandRatio = 1.0f
        addColumnFor(Nota::numero) {
          caption = "Número"
        }
        addColumnFor(Nota::loja) {
          caption = "Loja"
        }
        addColumnFor(Nota::data) {
          caption = "Data"
          
        }
        addColumnFor(Nota::hora) {
          caption = "Hora"
        }
        addSelectionListener {
          it.firstSelectedItem?.let {
            if (it.isPresent) {
              // gridProduto?.dataProvider = ListDataProvider(it.get().cacheItens().toList())
            }
          }
        }
      }
      gridProduto = grid(ItemNota::class) {
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
    }
    viewModel.updateModel()
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


