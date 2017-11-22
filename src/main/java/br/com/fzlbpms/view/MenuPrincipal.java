
package br.com.fzlbpms.view;

//import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;

public class MenuPrincipal {

    public static MenuBar getInstance(){
        MenuBar barmenu = new MenuBar();        
        MenuItem mnuItemcadastro = barmenu.addItem("Cadastro", null, null);

        MenuItem mnuItemUsuarioDoSistema = mnuItemcadastro.addItem("Usuario do Sistema",null,null);
        MenuItem mnuItemUsuarioDoSistemaINSERIR = mnuItemUsuarioDoSistema.addItem("Inserir",null,null);
        MenuItem mnuItemPessoa = mnuItemcadastro.addItem("Pessoas",null,null);

        MenuItem mnuItemAjuda = barmenu.addItem("Ajuda", null, null);
        MenuItem mnuItemSobre = mnuItemAjuda.addItem("Sobre", null, null);

        return barmenu;
    }
}
    
