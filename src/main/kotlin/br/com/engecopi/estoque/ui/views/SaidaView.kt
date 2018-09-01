package br.com.engecopi.estoque.ui.views

import br.com.engecopi.estoque.model.Loja
import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.estoque.model.TipoNota
import br.com.engecopi.estoque.ui.EstoqueUI
import br.com.engecopi.estoque.viewmodel.SaidaViewModel
import br.com.engecopi.estoque.viewmodel.SaidaVo
import br.com.engecopi.framework.ui.view.CrudLayoutView
import br.com.engecopi.framework.ui.view.bindItens
import br.com.engecopi.framework.ui.view.bindReadOnly
import br.com.engecopi.framework.ui.view.dateFormat
import br.com.engecopi.framework.ui.view.default
import br.com.engecopi.framework.ui.view.grupo
import br.com.engecopi.framework.ui.view.intFormat
import br.com.engecopi.framework.ui.view.integerField
import br.com.engecopi.framework.ui.view.reloadBinderOnChange
import br.com.engecopi.framework.ui.view.row
import com.github.vok.karibudsl.AutoView
import com.github.vok.karibudsl.bind
import com.github.vok.karibudsl.comboBox
import com.github.vok.karibudsl.dateField
import com.github.vok.karibudsl.expandRatio
import com.github.vok.karibudsl.textField
import com.github.vok.karibudsl.verticalLayout
import com.vaadin.data.Binder
import com.vaadin.icons.VaadinIcons
import com.vaadin.ui.Button
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.renderers.TextRenderer
import org.vaadin.crudui.crud.CrudOperation
import org.vaadin.crudui.crud.CrudOperation.ADD
import org.vaadin.crudui.crud.CrudOperation.UPDATE

@AutoView
class SaidaView : CrudLayoutView<SaidaVo, SaidaViewModel>() {
  val lojaDefault
    get() = EstoqueUI.loja
  val usuario = EstoqueUI.user!!
  val isAdmin = usuario.admin ?: false
  
  override fun layoutForm(
          formLayout: VerticalLayout,
          operation: CrudOperation?,
          binder: Binder<SaidaVo>,
          readOnly: Boolean
                         ) {
    if (operation == ADD) {
      binder.bean.lojaNF = lojaDefault
      binder.bean.usuario = usuario
    }
    formLayout.apply {
      grupo("Nota fiscal de saída") {
        verticalLayout {
          row {
            textField("Nota fiscal") {
              expandRatio = 2f
              isReadOnly = operation != ADD
              bind(binder).bind(SaidaVo::numeroNota)
              reloadBinderOnChange(binder)
            }
            comboBox<Loja>("Loja") {
              expandRatio = 2f
              default { it.sigla }
              isReadOnly = operation != ADD
              setItems(viewModel.findLojas(lojaDefault))
              bind(binder).asRequired("A loja deve ser informada").bind(SaidaVo::lojaNF)
              reloadBinderOnChange(binder)
            }
            comboBox<TipoNota>("Tipo") {
              expandRatio = 2f
              default { it.descricao }
              isReadOnly = true
              setItems(TipoNota.valuesSaida())
              bind(binder).bind(SaidaVo::tipoNota)
            }
            dateField("Data") {
              expandRatio = 1f
              isReadOnly = true
              bind(binder).bind(SaidaVo::dataNF.name)
            }
            textField("Rota") {
              expandRatio = 1f
              isReadOnly = true
              bind(binder).bind(SaidaVo::rota)
            }
          }
          row {
            textField("Observação da nota fiscal") {
              expandRatio = 1f
              bind(binder).bind(SaidaVo::observacaoNota)
            }
          }
        }
      }
      /*
      grupo("Código de Barras") {
        verticalLayout {
          row {
            textField() {
              expandRatio = 1f
              isReadOnly = operation != ADD
              bind(binder).bind(SaidaVo::codigoBarra)
              reloadBinderOnChange(binder)
            }
          }
        }
      }
      */
      grupo("Produto") {
        verticalLayout {
          row {
            comboBox<Produto>("Código") {
              expandRatio = 2f
              default { "${it.codigo} ${it.grade}".trim() }
              isReadOnly = operation != ADD
              isTextInputAllowed = true
              bindItens(binder, SaidaVo::listaProdutos)
              
              bind(binder).bind(SaidaVo::produto)
              reloadBinderOnChange(binder)
            }
            textField("Descrição") {
              expandRatio = 5f
              isReadOnly = true
              bind(binder).bind(SaidaVo::descricaoProduto.name)
            }
            textField("Grade") {
              expandRatio = 1f
              isReadOnly = true
              bind(binder).bind(SaidaVo::grade.name)
            }
            integerField("Qtd. Saída") {
              expandRatio = 1f
              
              bindReadOnly(binder, SaidaVo::quantidadeReadOnly) { quantidadeReadOnly ->
                isReadOnly = if (operation == ADD) quantidadeReadOnly else true
              }
              bind(binder)
                      .bind(SaidaVo::quantidade)
              reloadBinderOnChange(binder)
            }
          }
        }
      }
    }
    if (!isAdmin && operation == UPDATE)
      binder.setReadOnly(true)
  }
  
  init {
    form("Expedição") {
      gridCrud(viewModel.crudClass.java) {
        addOnly = !isAdmin
        column(SaidaVo::numeroNota) {
          caption = "Número NF"
          setSortProperty("nota.numero")
        }
        grid.addComponentColumn { item ->
          val button = Button()
          print {
            item.itemNota?.produto?.recalculaSaldos()
            val print = viewModel.imprimir(item.itemNota)
            print
          }.extend(button)
          val impresso = item?.entityVo?.impresso ?: true
          button.isEnabled = impresso == false || isAdmin
          button.icon = VaadinIcons.PRINT
          button.addClickListener {
            val print = item?.entityVo?.impresso ?: true
            it.button.isEnabled = print == false || isAdmin
            refreshGrid()
          }
          button
        }
        column(SaidaVo::lojaNF) {
          caption = "Loja NF"
          setRenderer({ loja -> loja?.sigla ?: "" }, TextRenderer())
        }
        column(SaidaVo::dataNF) {
          caption = "Data"
          dateFormat()
          setSortProperty("nota.data", "data", "hora")
        }
        column(SaidaVo::quantidade) {
          caption = "Quantidade"
          intFormat()
        }
        column(SaidaVo::codigo) {
          caption = "Código"
          setSortProperty("produto.codigo")
        }
        column(SaidaVo::descricaoProduto) {
          caption = "Descrição"
        }
        column(SaidaVo::grade) {
          caption = "Grade"
          setSortProperty("produto.grade")
        }
        column(SaidaVo::localizacao) {
          caption = "Localização"
        }
        column(SaidaVo::usuario) {
          caption = "Usuário"
          setRenderer({ it?.loginName ?: "" }, TextRenderer())
          setSortProperty("usuario.loginName")
        }
        column(SaidaVo::rota) {
          caption = "Rota"
        }
        column(SaidaVo::clienteName) {
          caption = "Cliente"
          setSortProperty("nota.cliente")
        }
      }
    }
  }
  
  override val viewModel
    get() = SaidaViewModel(this, usuario)
}

