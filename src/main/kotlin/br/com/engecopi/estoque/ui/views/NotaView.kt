package br.com.engecopi.estoque.ui.views

import br.com.engecopi.estoque.model.LocProduto
import br.com.engecopi.estoque.model.Loja
import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.estoque.model.RegistryUserInfo
import br.com.engecopi.estoque.model.findSufixo
import br.com.engecopi.estoque.viewmodel.NotaViewModel
import br.com.engecopi.estoque.viewmodel.NotaVo
import br.com.engecopi.estoque.viewmodel.ProdutoVO
import br.com.engecopi.framework.ui.view.CrudLayoutView
import br.com.engecopi.framework.ui.view.bindItens
import br.com.engecopi.framework.ui.view.bindVisible
import br.com.engecopi.framework.ui.view.default
import br.com.engecopi.framework.ui.view.expand
import br.com.engecopi.framework.ui.view.integerField
import br.com.engecopi.framework.ui.view.reloadBinderOnChange
import br.com.engecopi.framework.ui.view.row
import br.com.engecopi.utils.mid
import com.github.vok.karibudsl.VAlign
import com.github.vok.karibudsl.VaadinDsl
import com.github.vok.karibudsl.addColumnFor
import com.github.vok.karibudsl.align
import com.github.vok.karibudsl.bind
import com.github.vok.karibudsl.comboBox
import com.github.vok.karibudsl.expandRatio
import com.github.vok.karibudsl.getAll
import com.github.vok.karibudsl.grid
import com.github.vok.karibudsl.h
import com.github.vok.karibudsl.px
import com.github.vok.karibudsl.textField
import com.vaadin.data.Binder
import com.vaadin.event.ShortcutAction.KeyCode.ENTER
import com.vaadin.ui.ComboBox
import com.vaadin.ui.Grid.SelectionMode.MULTI
import com.vaadin.ui.HasComponents
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.renderers.TextRenderer
import org.vaadin.crudui.crud.CrudOperation
import org.vaadin.crudui.crud.CrudOperation.ADD
import org.vaadin.patrik.FastNavigation

abstract class NotaView<VO : NotaVo, MODEL : NotaViewModel<VO>> : CrudLayoutView<VO, MODEL>() {
  val lojaDefault = RegistryUserInfo.lojaDefault
  val usuario = RegistryUserInfo.usuarioDefault
  val isAdmin = usuario.admin

  inline fun <reified V : NotaVo> (@VaadinDsl HasComponents).notaFiscalField(
    operation: CrudOperation?,
    binder: Binder<V>
                                                                            ) {
    textField("Nota Fiscal") {
      expand = 2
      isReadOnly = operation != ADD
      bind(binder).bind("numeroNF")
      reloadBinderOnChange(binder)
    }
  }

  inline fun <reified V : NotaVo> (@VaadinDsl HasComponents).lojaField(
    operation: CrudOperation?,
    binder: Binder<V>
                                                                      ) {
    comboBox<Loja>("Loja") {
      expand = 2
      default { it.sigla }
      isReadOnly = operation != ADD
      setItems(viewModel.findLojas(lojaDefault))

      bind(binder).asRequired("A lojaDefault deve ser informada").bind("lojaNF")
      reloadBinderOnChange(binder)
    }
  }

  inline fun <reified V : NotaVo> VerticalLayout.produtoField(
    operation: CrudOperation?,
    binder: Binder<V>, tipo: String
                                                             ) {
    row {
      this.bindVisible(binder, NotaVo::naoTemGrid.name)
      comboBox<Produto>("Código") {
        expand = 2
        isReadOnly = operation != ADD
        default { "${it.codigo.trim()} ${it.grade}".trim() }
        isTextInputAllowed = true
        bindItens(binder, "produtoNota")
        bind(binder).bind("produto")
        reloadBinderOnChange(binder)
      }
      textField("Descrição") {
        expand = 5
        isReadOnly = true
        bind(binder).bind("descricaoProduto")
      }
      comboBox<LocProduto>("Localizacao") {
        expand = 3
        isReadOnly = operation != ADD
        default { localizacao ->
          localizacao.sufixo
        }
        isTextInputAllowed = true

        bindItens(binder, NotaVo::localizacaoProduto.name)
        bind(binder).bind(NotaVo::localizacao.name)
      }
      textField("Grade") {
        expand = 1
        isReadOnly = true
        bind(binder).bind("grade")
      }
      integerField("Qtd $tipo") {
        expand = 1
        isReadOnly = (isAdmin == false) && (operation != ADD)
        this.bind(binder).bind("quantProduto")
      }
    }
    row {
      this.bindVisible(binder, "temGrid")
      grid(ProdutoVO::class) {
        expand = 2
        this.h = 200.px
        editor.isEnabled = true
        removeAllColumns()
        val selectionModel = setSelectionMode(MULTI)
        selectionModel.addSelectionListener { select ->
          if (select.isUserOriginated) {
            this.dataProvider.getAll().forEach {
              it.selecionado = false
            }
            select.allSelectedItems.forEach {
              it.selecionado = true
            }
          }
        }
        val comboLoc = ComboBox<LocProduto>().apply {
          isEmptySelectionAllowed = false
          isTextInputAllowed = false
        }

        addColumnFor(ProdutoVO::codigo) {
          expandRatio = 1
          caption = "Código"
        }
        addColumnFor(ProdutoVO::descricaoProduto) {
          expandRatio = 5
          caption = "Descrição"
        }
        addColumnFor(ProdutoVO::localizacao) {
          expandRatio = 4
          caption = "Localização"
          setEditorComponent(comboLoc)
        }
        addColumnFor(ProdutoVO::grade) {
          expandRatio = 1
          caption = "Grade"
        }
        addColumnFor(ProdutoVO::quantidade) {
          expandRatio = 1
          caption = "Qtd $tipo"
          align = VAlign.Right
        }
        bindItens(binder, "produtos")
        editor.addOpenListener { event ->
          event.bean.produto?.let { produto ->
            val locSulfixos = produto.sufixosLocalizacaoes()
            comboLoc.setItems(locSulfixos)
            comboLoc.setItemCaptionGenerator { it.sufixo }
          }
        }
        val nav = FastNavigation<ProdutoVO>(this, false, true)
        nav.changeColumnAfterLastRow = true
        nav.openEditorWithSingleClick = true
        nav.allowArrowToChangeRow=true
        nav.openEditorOnTyping=true
        nav.addEditorSaveShortcut(ENTER)
        editor.cancelCaption = "Cancelar"
        editor.saveCaption = "Salvar"
        editor.isBuffered = false
      }
    }
  }
}