package br.com.engecopi.estoque.ui.views

import br.com.engecopi.estoque.model.Loja
import br.com.engecopi.estoque.viewmodel.SaidaViewModel
import br.com.engecopi.estoque.viewmodel.SaidaVo
import br.com.engecopi.framework.ui.view.CrudLayoutView
import br.com.engecopi.framework.ui.view.row
import com.github.vok.karibudsl.AutoView
import com.github.vok.karibudsl.comboBox
import com.github.vok.karibudsl.cssLayout
import com.github.vok.karibudsl.dateField
import com.github.vok.karibudsl.perc
import com.github.vok.karibudsl.textField
import com.github.vok.karibudsl.verticalLayout
import com.github.vok.karibudsl.w
import com.vaadin.data.Binder
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.themes.ValoTheme
import org.vaadin.crudui.crud.CrudOperation
import kotlin.reflect.KProperty

@AutoView
class SaidaView : CrudLayoutView<SaidaVo, SaidaViewModel>(){
  override fun fieldsRead(): List<KProperty<*>> {
    return listOf(SaidaVo::numeroNota, SaidaVo::lojaNF, SaidaVo::lojaTransf,
                  SaidaVo::codigo, SaidaVo::descricaoProduto, SaidaVo::grade,
                  SaidaVo::quantidade)
  }
  
  override fun layoutForm(
          formLayout: VerticalLayout,
          operation: CrudOperation?,
          binder: Binder<SaidaVo>,
          readOnly: Boolean
                         ) {
    formLayout.apply {
      cssLayout("Nota fiscal de saída") {
        w = 100.perc
        addStyleName(ValoTheme.LAYOUT_CARD)
        verticalLayout {
          row {
            textField("Nota fiscal")
            comboBox<Loja>("Loja")
            dateField ("Data")
            textField("Loja Transferencia")
          }
          row{
            textField("Observação da nota fiscal")
          }
        }
      }
      cssLayout("Código de Barras") {
        w = 100.perc
        addStyleName(ValoTheme.LAYOUT_CARD)
        verticalLayout {
          row {
            textField()
          }
        }
      }
      cssLayout("Produto") {
        w = 100.perc
        addStyleName(ValoTheme.LAYOUT_CARD)
        verticalLayout {
          row {
            textField("Código")
            textField("Descrição")
            textField("Quantidade")
          }
          row {
            textField("Sequencia")
            textField("Saldo")
          }
        }
      }
  
    }
  }
  
  override val viewModel = SaidaViewModel(this)
}

