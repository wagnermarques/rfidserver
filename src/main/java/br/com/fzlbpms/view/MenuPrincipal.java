
package br.com.fzlbpms.view;

//import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar;

import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;

import br.com.fzlbpms.model.common.Pessoa;
import br.com.fzlbpms.rfidserver.Main;
import br.com.fzlbpms.view.crudview.PessoaFormInserirView;
import br.com.fzlbpms.view.crudview.PessoaListView;
import br.com.fzlbpms.view.crudview.PessoaListViewForDeletion;
import br.com.fzlbpms.view.crudview.SistemaUsuarioFormViewFoInsertion;
import br.com.fzlbpms.view.crudview.SistemaUsuarioListView;
import br.com.fzlbpms.view.crudview.SistemaUsuarioListViewForDeletion;
import br.com.fzlbpms.view.crudview.SistemaUsuarioListViewForUpdate;

public class MenuPrincipal {

    public static MenuBar getInstance(){
        
    	MenuBar barmenu = new MenuBar();        
        
        MenuItem mnuItemcadastro = barmenu.addItem("Cadastro", null, null);
        
        //Itens (Nivel 1) da barra de menus
        MenuItem barItem_UsuarioDoSistema = mnuItemcadastro.addItem("Usuario do Sistema",null,null);
        MenuItem barItem_Pessoa = mnuItemcadastro.addItem("Pessoas",null,null);
        MenuItem barItem_Ajuda = barmenu.addItem("Ajuda", null, null);
        MenuItem barItem_Sobre = barItem_Ajuda.addItem("Sobre", null, null);
        
        
        //Items do menu CADASTRO
        //Sistema Usuario
        MenuItem mnuItem_UsuarioDoSistema_INSERIR = barItem_UsuarioDoSistema.addItem("Inserir (funciona)",null,null);
        mnuItem_UsuarioDoSistema_INSERIR.setCommand((selectedItem) -> Main.navigator.navigateTo(SistemaUsuarioFormViewFoInsertion.VIEW_NAME));
        
        MenuItem mnuItem_UsuarioDoSistema_ALTERAR = barItem_UsuarioDoSistema.addItem("Alterar (em desenvolvimento)",null,null);
        mnuItem_UsuarioDoSistema_ALTERAR.setCommand((selecteditem) -> Main.navigator.navigateTo(SistemaUsuarioListViewForUpdate.VIEW_NAME));
        
        MenuItem mnuItem_UsuarioDoSistema_EXCLUIR = barItem_UsuarioDoSistema.addItem("Excluir (funciona)",null,null);
        mnuItem_UsuarioDoSistema_EXCLUIR.setCommand((selecteditem) -> Main.navigator.navigateTo(SistemaUsuarioListViewForDeletion.VIEW_NAME));
        
        MenuItem mnuItem_UsuarioDoSistema_PESQUISAR = barItem_UsuarioDoSistema.addItem("Pesquisar  (funciona)",null,null);
        mnuItem_UsuarioDoSistema_PESQUISAR.setCommand((selectedItem) -> Main.navigator.navigateTo(SistemaUsuarioListView.VIEW_NAME));
        
        //Pessoa
        MenuItem mnuItem_Pessoa_INSERIR = barItem_Pessoa.addItem("Inserir  (funciona)",null,null);
        mnuItem_Pessoa_INSERIR.setCommand((selectedItem) -> Main.navigator.navigateTo(PessoaFormInserirView.VIEW_NAME));        
        MenuItem mnuItem_Pessoa_ALTERAR = barItem_Pessoa.addItem("Alterar  (em desenvolvimento)",null,null);
        MenuItem mnuItem_Pessoa_EXCLUIR = barItem_Pessoa.addItem("Excluir (funciona)",null,null);
        mnuItem_Pessoa_EXCLUIR.setCommand((selecteditem) -> Main.navigator.navigateTo(PessoaListViewForDeletion.VIEW_NAME));
        
        MenuItem mnuItem_Pessoa_PESQUISAR = barItem_Pessoa.addItem("Pesquisar  (funciona)",null,null);
        mnuItem_Pessoa_PESQUISAR.setCommand((selectedItem) -> Main.navigator.navigateTo(PessoaListView.VIEW_NAME));
        
        
        return barmenu;
    }
}
    
