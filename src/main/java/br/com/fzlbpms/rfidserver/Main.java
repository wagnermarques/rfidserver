package br.com.fzlbpms.rfidserver;

import java.util.Locale;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.SingleComponentContainer;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import br.com.fzlbpms.model.Pessoa;
import br.com.fzlbpms.view.EntityFormView;
import br.com.fzlbpms.view.MenuPrincipal;
import br.com.fzlbpms.view.StartView;
import br.com.fzlbpms.view.vaadin.CountView;
import br.com.fzlbpms.view.vaadin.MessageView;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of an HTML page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@Theme("mytheme")
public class Main extends UI {

	@Override
	protected void init(VaadinRequest vaadinRequest) {
	
		final VerticalLayout mainLayout = new VerticalLayout();
		
		//header of the page
		final HorizontalLayout menuContent = new HorizontalLayout();
		mainLayout.addComponent(menuContent);
		
		//body of the page
		final VerticalLayout bodyContent = new VerticalLayout();
		mainLayout.addComponent(bodyContent);
		
		  
		//Compose initial bodyContent
		Link lnk = new Link("Count", new ExternalResource("#!" + CountView.NAME));
	    bodyContent.addComponent(lnk);

	    lnk = new Link("Message: Hello", new ExternalResource("#!"
	                + MessageView.NAME + "/Hello"));
	    bodyContent.addComponent(lnk);

	    lnk = new Link("Message: Bye", new ExternalResource("#!"
	               + MessageView.NAME + "/Bye/Goodbye"));
	    bodyContent.addComponent(lnk);
		
		
		//in header we will get a main menu
		menuContent.addComponent(MenuPrincipal.getInstance());
		
		
		

		//Nagitaion pages within bodycontent
		Navigator navigator;
		final String MAINVIEW = "startView";

		getPage().setTitle("Navigation Example");

		// Create a navigator to control the views		
		navigator = new Navigator(this, bodyContent);

		// Create and register the views
		
		navigator.addView(MAINVIEW, new StartView());
		navigator.addView(EntityFormView.VIEW_NAME, new EntityFormView(new Pessoa(), "Cadastro de Pessoas"));
		navigator.addView(MessageView.NAME, new MessageView());

		
		setContent(mainLayout);
		
		
		UI.getCurrent().setLocale(new Locale("pt-BR"));
		Page.getCurrent().setTitle("My Page");

	}

	@WebServlet(urlPatterns = "/*", name = "MainUiServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = Main.class, productionMode = false)
	public static class MainUiServlet extends VaadinServlet {
	}
}
