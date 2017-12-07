package br.com.fzlbpms.view.vaadin.framework;

import com.vaadin.server.ServiceException;
import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.SessionInitListener;

import br.com.fzlbpms.rfidserver.Main;

public class DefaultAppSessionInitListener implements SessionInitListener{

	@Override
	public void sessionInit(SessionInitEvent event) throws ServiceException {
		Main.setValueInHttpSession("adminPasswod", "admin123");
		Main.setValueInHttpSession("userLoggedIn", null);
		Main.setValueInHttpSession("usersLoggedIn", null);
	}

}
