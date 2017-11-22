package br.com.fzlbpms.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//http://docs.jboss.org/hibernate/orm/5.0/quickstart/html/#tutorial_jpa
public class JPAUtil {
		
	private static EntityManagerFactory entityManagerFactory;
	
	//Apenas para exercicios escolares, nao utilizamos gerenciamento de transacoes ou theadlocals
	private static EntityManager entityManager;


	public static EntityManager getEntityManager() {
		
		if(JPAUtil.entityManagerFactory == null) JPAUtil.entityManagerFactory = Persistence.createEntityManagerFactory("mariadbRfidserverPU");

		if(JPAUtil.entityManager != null) {
			return JPAUtil.entityManager;
		}else {
			return JPAUtil.entityManagerFactory.createEntityManager();
		}
	}
	
}
