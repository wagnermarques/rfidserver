package br.com.fzlbpms.view;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Page;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Link;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

import br.com.fzlbpms.model.SistemaUsuario;
import br.com.fzlbpms.rfidserver.Main;
import br.com.fzlbpms.view.vaadin.CountView;
import br.com.fzlbpms.view.vaadin.LoginView;
import br.com.fzlbpms.view.vaadin.MessageView;

public class StartView extends VerticalLayout implements View {

	public static final String START_VIEW_NAME = "";
	
	public StartView() {
		setSizeFull();


		Link lnkHOME = new Link("HOME", new ExternalResource(""));
	  
		Link lnkLogin = new Link("Mostra Login", new ExternalResource("#!" + LoginView.LOGIN_VIEW_NAME));
	    
		Link lnkCount = new Link("Count", new ExternalResource("#!" + CountView.NAME));
	    
	    Link lnkMessageHello = new Link("Message: Hello", new ExternalResource("#!" + MessageView.NAME + "/Hello"));

	    Link lnkMessageBye = new Link("Message: Bye", new ExternalResource("#!" + MessageView.NAME + "/Bye/Goodbye"));
	    
	    Button button = new Button("Go to Main View", new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				//navigator.navigateTo(MAINVIEW);
//				new Notification("This is a warning",
//					    "This is the <i>last</i> warning",
//					    Notification.Type.WARNING_MESSAGE, true)
//					    .show(Page.getCurrent());
				Main.navigator.navigateTo("");
			}

		});
		
	    addComponent(button);
		addComponent(lnkHOME);
		addComponent(lnkCount);
		addComponent(lnkLogin);
		addComponent(lnkMessageBye);
		addComponent(lnkMessageHello);
		
		setComponentAlignment(button, Alignment.MIDDLE_CENTER);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		if(Main.getValueFromHttpSession("userLoggedIn") == null) {
			Main.navigator.navigateTo(LoginView.LOGIN_VIEW_NAME);
		}else {
			SistemaUsuario userLoggedIn =  (SistemaUsuario) Main.getValueFromHttpSession("userLoggedIn");		
			Notification not = new Notification("Ola "+ userLoggedIn.getNome());
		}
	}
}