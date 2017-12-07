package br.com.fzlbpms.service.security;


import java.util.Map;
import java.util.logging.Logger;
import java.util.HashMap;

import br.com.fzlbpms.model.SistemaUsuario;
import br.com.fzlbpms.rfidserver.Main;
import br.com.fzlbpms.service.Command;

public class LoginService implements Command<Map<String,String>,SistemaUsuario> {

	private final static Logger logger =
	          Logger.getLogger(LoginService.class.getName());
	
	
	@Override
	public SistemaUsuario execute(Map<String, String> param) throws Exception {
		String userNameToBeLoggedIn = param.get("userName");
		String userPasswordToBeLoggedIn = param.get("userPassword");
		
		String adminUserPassword = (String) Main.getValueFromHttpSession("adminPasswod");
		logger.info("Main.getValueFromHttpSession(\"adminPasswod\") = "+adminUserPassword);
		logger.info("userNameToBeLoggedIn to logged in:"+userNameToBeLoggedIn);
		logger.info("userPasswordToBeLoggedIn to logged in:"+userPasswordToBeLoggedIn);

                //none was informed 
                if(userNameToBeLoggedIn == null || userPasswordToBeLoggedIn == null){
                    logger.info("User Not Authenticated: userNameToBeLoggedIn or userPasswordToBeLoggedIn was null");
                    return null;
                }

                boolean checkUserName = userNameToBeLoggedIn.equals("admin");
                boolean checkPassword = userPasswordToBeLoggedIn.equals(adminUserPassword);
                logger.info("checkUserName :"+checkUserName);
                logger.info("checkPassword :"+checkPassword);
                logger.info("userPasswordToBeLoggedIn="+userPasswordToBeLoggedIn);
                logger.info("adminUserPassword="+adminUserPassword);
                
                //if userToBeLogged in was informed, lets comparte if its a admin use data
		if( checkUserName && checkPassword) {
                    
			SistemaUsuario sistemaUsuario = new SistemaUsuario();
			sistemaUsuario.setNome("Administrador");
                        //TODO:Set privileges service
                        logger.info("User AUTHENTICATED: Returning sistemaUsuario authenticated: "+ sistemaUsuario);
			return sistemaUsuario;
		}
                
                logger.info("Returning null, user NOT authenticated: ");
		return null;
	}


}
