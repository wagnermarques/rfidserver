package br.com.fzlbpms.rfidserver;

import java.util.HashMap;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.mysql.cj.fabric.xmlrpc.base.Value;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Page;
import com.vaadin.server.ServiceException;
import com.vaadin.server.SessionDestroyEvent;
import com.vaadin.server.SessionDestroyListener;
import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.SessionInitListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinServletService;
import com.vaadin.server.VaadinSession;
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
import br.com.fzlbpms.view.vaadin.LoginView;
import br.com.fzlbpms.view.vaadin.MessageView;
import br.com.fzlbpms.view.vaadin.framework.DefaultAppSessionInitListener;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of an HTML page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 * 
 * This Main UI View is just for put main menu on the screen The navigation will
 * ocurr when its View enter to start View
 * 
 */
@Theme("mytheme")
public class Main extends UI implements View {

	public static Navigator navigator;

	@Override
	protected void init(VaadinRequest vaadinRequest) {

		final VerticalLayout mainLayout = new VerticalLayout();

		// header of the page
		final HorizontalLayout menuContent = new HorizontalLayout();
		mainLayout.addComponent(menuContent);

		// body of the page
		final VerticalLayout bodyContent = new VerticalLayout();
		mainLayout.addComponent(bodyContent);

		// in header we will get a main menu
		menuContent.addComponent(MenuPrincipal.getInstance());

		setContent(mainLayout);
		UI.getCurrent().setLocale(new Locale("pt-BR"));

		getPage().setTitle("RFidServer Home");

		configureNavigation(bodyContent);
	}

	private void configureNavigation(final VerticalLayout bodyContent) {
		// Create a navigator to control the views
		navigator = new Navigator(this, bodyContent);

		// Create and register the views
		navigator.addView(StartView.START_VIEW_NAME, new StartView());

		navigator.addView(LoginView.LOGIN_VIEW_NAME, new LoginView());

		navigator.addView(EntityFormView.VIEW_NAME, new EntityFormView(new Pessoa(), "Cadastro de Pessoas"));
		navigator.addView(MessageView.NAME, new MessageView());
	}

	public static void setValueInHttpSession(String attrName, Object value) {
		VaadinSession.getCurrent().setAttribute(attrName, value);
	}

	public static Object getValueFromHttpSession(String attrName) {
		return VaadinSession.getCurrent().getAttribute(attrName);
	}

	@WebServlet(urlPatterns = "/*", name = "MainUiServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = Main.class, productionMode = false)
	public static class MainUiServlet extends VaadinServlet {

		@Override
		protected void servletInitialized() throws ServletException {
			super.servletInitialized();
			getService().addSessionInitListener(new DefaultAppSessionInitListener());
			getService().addSessionInitListener(new DefaultAppSessionInitListener());
		}

	}

}
