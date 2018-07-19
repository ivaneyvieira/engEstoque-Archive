package br.com.engecopi.estoque.ui.views

import br.com.engecopi.estoque.model.Loja
import br.com.engecopi.estoque.model.Lote
import br.com.engecopi.estoque.model.Produto
import br.com.engecopi.estoque.model.TipoNota
import br.com.engecopi.estoque.ui.EstoqueUI
import br.com.engecopi.estoque.viewmodel.EntradaVo
import br.com.engecopi.estoque.viewmodel.SaidaViewModel
import br.com.engecopi.estoque.viewmodel.SaidaVo
import br.com.engecopi.framework.ui.view.CrudLayoutView
import br.com.engecopi.framework.ui.view.bindCaption
import br.com.engecopi.framework.ui.view.bindItens
import br.com.engecopi.framework.ui.view.bindReadOnly
import br.com.engecopi.framework.ui.view.default
import br.com.engecopi.framework.ui.view.intFormat
import br.com.engecopi.framework.ui.view.integerField
import br.com.engecopi.framework.ui.view.reloadBinderOnChange
import br.com.engecopi.framework.ui.view.row
import com.github.vok.karibudsl.AutoView
import com.github.vok.karibudsl.bind
import com.github.vok.karibudsl.comboBox
import com.github.vok.karibudsl.cssLayout
import com.github.vok.karibudsl.dateField
import com.github.vok.karibudsl.expandRatio
import com.github.vok.karibudsl.label
import com.github.vok.karibudsl.perc
import com.github.vok.karibudsl.textField
import com.github.vok.karibudsl.verticalLayout
import com.github.vok.karibudsl.w
import com.vaadin.data.Binder
import com.vaadin.data.converter.StringToIntegerConverter
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.renderers.TextRenderer
import com.vaadin.ui.themes.ValoTheme
import org.vaadin.crudui.crud.CrudOperation
import org.vaadin.crudui.crud.CrudOperation.ADD
import org.vaadin.crudui.crud.CrudOperation.UPDATE
import kotlin.reflect.KProperty

@AutoView
class SaidaView : CrudLayoutView<SaidaVo, SaidaViewModel>() {
  val lojaDefault
    get() = EstoqueUI.loja
  val isAdmin
    get() = EstoqueUI.user?.isAdmin ?: false
  
  override fun layoutForm(
          formLayout: VerticalLayout,
          operation: CrudOperation?,
          binder: Binder<SaidaVo>,
          readOnly: Boolean
                         ) {
    if(operation == ADD)
      binder.bean.lojaNF = lojaDefault
    formLayout.apply {
      cssLayout("Nota fiscal de saída") {
        w = 100.perc
        addStyleName(ValoTheme.LAYOUT_CARD)
        verticalLayout {
          row {
            textField("Nota fiscal") {
              expandRatio = 1f
              isReadOnly = operation != ADD
              bind(binder).bind(SaidaVo::numeroNota)
              reloadBinderOnChange(binder)
            }
            comboBox<Loja>("Loja") {
              expandRatio = 1f
              default { it.sigla }
              isReadOnly = operation != ADD
              setItems(viewModel.findLojas(lojaDefault))
              bind(binder).asRequired("A loja deve ser informada").bind(SaidaVo::lojaNF)
              reloadBinderOnChange(binder)
            }
            comboBox<TipoNota>("Tipo") {
              expandRatio = 1f
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
      cssLayout("Código de Barras") {
        w = 100.perc
        addStyleName(ValoTheme.LAYOUT_CARD)
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
      cssLayout("Produto") {
        w = 100.perc
        addStyleName(ValoTheme.LAYOUT_CARD)
        verticalLayout {
          row {
            comboBox<Produto>("Código") {
              expandRatio = 2f
              default { "${it.codigo} ${it.grade}".trim() }
              isReadOnly = operation != ADD
              isTextInputAllowed = true
              setItems(viewModel.findProdutos())
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
          }
          row {
            integerField("Qtd. Saída") {
              expandRatio = 1f
              isReadOnly = operation != ADD
              bindCaption(binder, SaidaVo::quantidadeCaption)
              bind(binder)
                      .bind(SaidaVo::quantidade)
              reloadBinderOnChange(binder)
            }
            comboBox<Lote>("Lote Inicial") {
              expandRatio = 1f
              isReadOnly = true
              default { lote ->
                lote.sequenciaStr ?: ""
              }
              bind(binder).bind(SaidaVo::loteInicial)
              bindItens(binder, SaidaVo::lotes)
              reloadBinderOnChange(binder)
            }
            textField("Lote Final") {
              expandRatio = 1f
              isReadOnly = true
              bind(binder).bind(SaidaVo::loteFinalStr.name)
            }
            integerField("Quantidade de Unidade") {
              expandRatio = 1f
              isReadOnly = true
              bind(binder)
                      .bind(SaidaVo::quantiadeUnidade.name)
            }
          }
        }
      }
    }
    if(!isAdmin && operation == UPDATE)
      binder.setReadOnly(true)
  }
  
  init {
    form("Expedição") {
      gridCrud(viewModel.crudClass.java) {
        setDeleteOperationVisible(EstoqueUI.user?.isAdmin ?: false)
        column(SaidaVo::numeroNota) {
          caption = "Número NF"
        }
        column(SaidaVo::lojaNF) {
          caption = "Loja NF"
          setRenderer({ loja -> loja?.sigla ?: "" }, TextRenderer())
        }
        column(SaidaVo::rota) {
          caption = "Rota"
        }
        column(SaidaVo::codigo) {
          caption = "Código"
        }
        column(SaidaVo::descricaoProduto) {
          caption = "Descrição"
        }
        column(SaidaVo::grade) {
          caption = "Grade"
        }
        column(SaidaVo::quantidade) {
          caption = "Quantidade"
          intFormat()
        }
      }
    }
  }
  
  override val viewModel
    get() = SaidaViewModel(this, lojaDefault)
}

