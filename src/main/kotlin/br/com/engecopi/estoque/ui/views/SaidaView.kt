package br.com.engecopi.estoque.ui.views

import br.com.engecopi.estoque.model.ItemNota
import br.com.engecopi.estoque.model.Loja
import br.com.engecopi.estoque.model.Nota
import br.com.engecopi.estoque.ui.LoginService
import br.com.engecopi.estoque.viewmodel.NotaSaidaItemVo
import br.com.engecopi.estoque.viewmodel.NotaSaidaVo
import br.com.engecopi.estoque.viewmodel.SaidaViewModel
import br.com.engecopi.framework.ui.view.DialogPopup
import br.com.engecopi.framework.ui.view.LayoutView
import br.com.engecopi.framework.ui.view.MessageDialog
import br.com.engecopi.framework.viewmodel.ViewModel
import com.github.vok.karibudsl.AutoView
import com.github.vok.karibudsl.VAlign
import com.github.vok.karibudsl.addColumnFor
import com.github.vok.karibudsl.align
import com.github.vok.karibudsl.bind
import com.github.vok.karibudsl.button
import com.github.vok.karibudsl.comboBox
import com.github.vok.karibudsl.expandRatio
import com.github.vok.karibudsl.fillParent
import com.github.vok.karibudsl.grid
import com.github.vok.karibudsl.horizontalLayout
import com.github.vok.karibudsl.isMargin
import com.github.vok.karibudsl.perc
import com.github.vok.karibudsl.refresh
import com.github.vok.karibudsl.textField
import com.github.vok.karibudsl.w
import com.vaadin.data.converter.StringToIntegerConverter
import com.vaadin.data.provider.DataProvider
import com.vaadin.data.provider.ListDataProvider
import com.vaadin.ui.ComboBox
import com.vaadin.ui.Grid
import com.vaadin.ui.TextField
import com.vaadin.ui.renderers.LocalDateRenderer
import com.vaadin.ui.renderers.NumberRenderer
import com.vaadin.ui.themes.ValoTheme
import org.vaadin.patrik.FastNavigation
import java.text.DecimalFormat

@AutoView
class SaidaView : LayoutView<SaidaViewModel>() {
  val lojaDefault = LoginService.currentUser?.storeno ?: 0
  override val viewModel = SaidaViewModel(lojaDefault, this)
  
  var grid: Grid<Nota>? = null
  var gridProduto: Grid<ItemNota>? = null
  
  val dialogNotaSaida = DialogNotaSaida()
  
  init {
    form("Saída de produtos") {
      horizontalLayout {
        isVisible = false
        w = fillParent
        isMargin = false
        textField("Pesquisa") {
          w = fillParent
        }
      }
      
      horizontalLayout {
        button("Adiciona Nota de Saída") {
          addClickListener {
            viewModel.novaNotaEntrada()
            dialogNotaSaida.binder.readBean(viewModel.notaSaidaVo)
            dialogNotaSaida.show()
          }
        }
        button("Remover Nota de Saída") {
          addClickListener {
            grid?.actionSelected("Selecione uma Nota de Saída") { saida ->
              MessageDialog.question(
                      message = "Remove a Nota de Entrada (${saida.numero})?",
                      execYes = { viewModel.removeSaida(saida) }
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
          setRenderer(LocalDateRenderer("dd/MM/yyyy"))
        }
        addColumnFor(Nota::hora) {
          caption = "Hora"
        }
        addSelectionListener {
          val saida = it.firstSelectedItem?.orElse(null)
          gridProduto?.dataProvider = if (saida == null)
            ListDataProvider(emptyList())
          else
            ListDataProvider(saida.itensNota.orEmpty())
          
        }
      }
      gridProduto = grid(ItemNota::class) {
        caption = "Itens da nota de saída"
        removeAllColumns()
        setSizeFull()
        expandRatio = 1.0f
        addColumn { it.codigo }.apply {
          caption = "Código"
        }
        addColumn { it.descricao }.apply {
          expandRatio = 1
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
  }
  
  inner class DialogNotaSaida : DialogPopup<NotaSaidaVo>("Pesquisa Nota de Entrada", NotaSaidaVo::class) {
    init {
      w = 75.perc
      form.w = 100.perc
      form.textField(caption = "Número") {
        isReadOnly = true
        addStyleName(ValoTheme.TEXTFIELD_ALIGN_RIGHT)
        bind(binder)
                .withConverter(StringToIntegerConverter("Número inválido"))
                .bind(NotaSaidaVo::numero)
      }
      form.comboBox<Loja>("Loja") {
        val lojas = viewModel.lojasSaida
        setItems(lojas)
        setItemCaptionGenerator { loja ->
          loja?.sigla ?: ""
        }
        value = lojas.firstOrNull()
        isTextInputAllowed = false
        isEmptySelectionAllowed = false
        this.isScrollToSelectedItem = true
        bind(binder).bind(NotaSaidaVo::loja)
      }
      form.grid(NotaSaidaItemVo::class) {
        val nav = FastNavigation(this, false, true)
        
        nav.changeColumnAfterLastRow = true
        nav.openEditorOnTyping = true
        nav.openEditorWithSingleClick = true
        nav.setRowValidation(true)
        nav.selectTextOnEditorOpen = true
        nav.allowArrowToChangeRow = true
        
        caption = "Produtos"
        w = fillParent
        val edtDescricao = TextField().apply {
          isReadOnly = true
          isEnabled = false
        }
        val cmbGrade = ComboBox<String>().apply {
          isTextInputAllowed = true
          isEmptySelectionAllowed = false
          this.isScrollToSelectedItem = true
        }
        val edtQuant = TextField().apply {
          addStyleName(ValoTheme.TEXTFIELD_ALIGN_RIGHT)
        }
        val edtCodigo = TextField().apply {
          addValueChangeListener {
            if (it.isUserOriginated) {
              val descricao = viewModel.descricao(it.value)
              edtDescricao.value = descricao
              val grades = viewModel.grades(it.value)
              cmbGrade.setItems(grades)
              if (grades.isNotEmpty())
                cmbGrade.value = grades.first()
            }
          }
        }
        val binder = editor.binder
        removeAllColumns()
        dataProvider = ListDataProvider(viewModel.notaSaidaVo.itensSaida)
        addColumnFor(NotaSaidaItemVo::codigo) {
          caption = "Código"
          editorBinding = binder
                  .forField(edtCodigo)
                  .bind(NotaSaidaItemVo::codigo)
          
        }
        addColumnFor(NotaSaidaItemVo::descricao) {
          expandRatio = 1
          caption = "Descrição"
          editorBinding = binder
                  .forField(edtDescricao)
                  .bind(NotaSaidaItemVo::descricao)
        }
        addColumnFor(NotaSaidaItemVo::grade) {
          caption = "Grade"
          editorBinding = binder.bind(cmbGrade, NotaSaidaItemVo::grade.getter, NotaSaidaItemVo::grade.setter)
          this.width = 150.0
        }
        addColumnFor(NotaSaidaItemVo::quantidade) {
          caption = "Quantidade"
          editorBinding = binder
                  .forField(edtQuant)
                  .withConverter(StringToIntegerConverter("Quantidade Inválida"))
                  .bind(NotaSaidaItemVo::quantidade)
          align = VAlign.Right
        }
        editor.isEnabled = true
        editor.isBuffered = true
        editor.cancelCaption = "Cancela"
        editor.saveCaption = "Salva"
        nav.addEditorCloseListener {
          viewModel.notaSaidaVo.addNewItem()
          dataProvider.refreshAll()
          setDataProvider(dataProvider)
        }
        editor.addSaveListener {
          viewModel.notaSaidaVo.addNewItem()
          dataProvider.refreshAll()
          setDataProvider(dataProvider)
        }
        editor.addOpenListener {
          if (it.bean == null)
            it.grid.editor.cancel()
        }
      }
      addClickListenerOk {
        dialogNotaSaida.binder.writeBean(viewModel.notaSaidaVo)
        viewModel.salvaSaida()
      }
    }
  }
  
  override fun updateModel() {
  
  }
  
  override fun updateView(viewModel: ViewModel) {
    gridProduto?.dataProvider = DataProvider.ofItems()
    gridProduto?.refresh()
    grid?.refresh()
  }
}
