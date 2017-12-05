
package br.com.fzlbpms.view;

//import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;

public class MenuPrincipal {

    public static MenuBar getInstance(){
        MenuBar barmenu = new MenuBar();        
        MenuItem mnuItemcadastro = barmenu.addItem("Cadastro", null, null);

        MenuItem mnuItemUsuarioDoSistema = mnuItemcadastro.addItem("Usuario do Sistema",null,null);        
        MenuItem mnuItemUsuarioDoSistemaINSERIR = mnuItemUsuarioDoSistema.addItem("Inserir",null,null);
        mnuItemUsuarioDoSistemaINSERIR.setCommand(new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
	
//				Notification.show("This is the caption",
//		                  selectedItem.getText(),
//		                  Notification.Type.HUMANIZED_MESSAGE);

				
			}
		});
        
        MenuItem mnuItemPessoa = mnuItemcadastro.addItem("Pessoas",null,null);

        MenuItem mnuItemAjuda = barmenu.addItem("Ajuda", null, null);
        MenuItem mnuItemSobre = mnuItemAjuda.addItem("Sobre", null, null);

        return barmenu;
    }
}
    
