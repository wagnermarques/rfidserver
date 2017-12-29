package br.com.fzlbpms.service.security;

import java.util.Map;
import java.util.logging.Logger;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.HashMap;
import java.util.List;

import br.com.fzlbpms.model.SistemaUsuario;
import br.com.fzlbpms.persistence.HibernateUtil;
import br.com.fzlbpms.rfidserver.Main;
import br.com.fzlbpms.service.Command;

public class LoginService implements Command<Map<String, String>, SistemaUsuario> {

	private final static Logger logger = Logger.getLogger(LoginService.class.getName());

	
	@Override
	public SistemaUsuario execute(Map<String, String> param) throws Exception {

		String userNameToBeLoggedIn = param.get("userName");
		String userPasswordToBeLoggedIn = param.get("userPassword");

		String adminUserPassword = (String) Main.getValueFromHttpSession("adminPasswod");
		logger.info("Main.getValueFromHttpSession(\"adminPasswod\") = " + adminUserPassword);
		logger.info("userNameToBeLoggedIn to logged in:" + userNameToBeLoggedIn);
		logger.info("userPasswordToBeLoggedIn to logged in:" + userPasswordToBeLoggedIn);

		// none was informed
		if (userNameToBeLoggedIn == null || userPasswordToBeLoggedIn == null) {
			logger.info("User Not Authenticated: userNameToBeLoggedIn or userPasswordToBeLoggedIn was null");
			return null;
		}

		boolean checkUserName = userNameToBeLoggedIn.equals("admin");
		boolean checkPassword = userPasswordToBeLoggedIn.equals(adminUserPassword);
		logger.info("checkUserName :" + checkUserName);
		logger.info("checkPassword :" + checkPassword);
		logger.info("userPasswordToBeLoggedIn=" + userPasswordToBeLoggedIn);
		logger.info("adminUserPassword=" + adminUserPassword);

		return login(userNameToBeLoggedIn,userPasswordToBeLoggedIn);
	}


	private SistemaUsuario login(String userNameToBeLoggedIn, String userPasswordToBeLoggedIn) {
		  SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			CriteriaBuilder critBuilder = session.getCriteriaBuilder();
			CriteriaQuery<SistemaUsuario> criQry = critBuilder.createQuery(SistemaUsuario.class);
			criQry.from(SistemaUsuario.class);
			List<SistemaUsuario> resultList = session.createQuery(criQry).getResultList();
			if(resultList.size() > 1 || resultList.size() == 0) return null;
			return resultList.get(0);
	}
	
	
	

}
