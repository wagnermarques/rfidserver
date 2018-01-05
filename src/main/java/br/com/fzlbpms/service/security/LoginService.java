package br.com.fzlbpms.service.security;

import java.util.Map;

import java.util.logging.Logger;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.HashMap;
import java.util.List;

import br.com.fzlbpms.model.sistema.SistemaUsuario;
import br.com.fzlbpms.persistence.HibernateUtil;
import br.com.fzlbpms.persistence.SistemaUsuarioHibernateDAO;
import br.com.fzlbpms.rfidserver.Main;
import br.com.fzlbpms.service.Command;

public class LoginService implements Command<Map<String, String>, SistemaUsuario> {

	private final static Logger logger = Logger.getLogger(LoginService.class.getName());

	
	@Override
	public SistemaUsuario execute(Map<String, String> param) throws Exception {

		String userNameToBeLoggedIn = param.get("userName");
		String userPasswordToBeLoggedIn = param.get("userPassword");

//		String adminUserPassword = (String) Main.getValueFromHttpSession("adminPasswod");
//		logger.info("Main.getValueFromHttpSession(\"adminPasswod\") = " + adminUserPassword);
//		logger.info("userNameToBeLoggedIn to logged in:" + userNameToBeLoggedIn);
//		logger.info("userPasswordToBeLoggedIn to logged in:" + userPasswordToBeLoggedIn);

		// none was informed
		if (userNameToBeLoggedIn == null || userPasswordToBeLoggedIn == null) {
			logger.info("User Not Authenticated: userNameToBeLoggedIn or userPasswordToBeLoggedIn was null");
			return null;
		}

		Session session = HibernateUtil.getSessionFactory().openSession();
		SistemaUsuarioHibernateDAO sistemaUsuarioDAO = new SistemaUsuarioHibernateDAO(session);
		int temQuantosUsuariosComEsseNomeDeUsuarioESenha = sistemaUsuarioDAO.temQuantos(userNameToBeLoggedIn,userPasswordToBeLoggedIn);

		
		if(temQuantosUsuariosComEsseNomeDeUsuarioESenha == 1) {
			//FIX: Identificar quem e a pessoa desse usuario
			//FIX: Aplicar roles no usuario
			SistemaUsuario sistemaUsuario = new SistemaUsuario();
			sistemaUsuario.setLogin(userNameToBeLoggedIn);
			
			return sistemaUsuario;
		}else {
			return null;
		}	
	}//execute
}//class
