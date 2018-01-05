package br.com.fzlbpms.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import br.com.fzlbpms.model.sistema.SistemaUsuario;

@DisplayName(" >>>>>>>>>> Rfid Tests (JUnit5)")
class HibernateFunctionalitiesTest {
	Logger logger = Logger.getLogger(HibernateFunctionalitiesTest.class.getName());
 
	private static SessionFactory sessionFactory;
	private static Session session;
	
	
	public HibernateFunctionalitiesTest(TestInfo testInfo) {
		// TODO Auto-generated constructor stub
	}
	
        
	@BeforeAll
	public static void before() {
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();		
		sessionFactory = metadata.getSessionFactoryBuilder().build();
		session = sessionFactory.openSession();
	}

	@Test
	@DisplayName(" >>>>>>>>>> public void SistemaUsuario_createDefaultUsuers_Test() {...")
	public void SistemaUsuario_createDefaultUsuers_Test() {
		SistemaUsuarioHibernateDAO suDAO = new SistemaUsuarioHibernateDAO(session);		
		suDAO.createDefaultUsers();
		int temQuantosAdminUsers = suDAO.temQuantos("admin", "admin123");
		int temQuantosGuestUsers = suDAO.temQuantos("guest", "guest123");
		assertEquals(1, temQuantosAdminUsers);
		assertEquals(1, temQuantosGuestUsers);
	}
	
	@Test
	@DisplayName(" >>>>>>>>>> public void SistemaUsuario_temQuanto_Test() {...")
	public void SistemaUsuario_temQuanto_Test() {		
		SistemaUsuarioHibernateDAO suDAO = new SistemaUsuarioHibernateDAO(session);		
		int temQuantosAdminUsers = suDAO.temQuantos("admin", "admin123");
		int temQuantosGuestUsers = suDAO.temQuantos("guest", "guest123");
		assertEquals(1, temQuantosAdminUsers);
		assertEquals(1, temQuantosGuestUsers);
	}

	@AfterAll
	public static void after() {
		session.close();
		sessionFactory.close();
	}
    
}
