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

import br.com.fzlbpms.model.common.Pessoa;
import br.com.fzlbpms.model.sistema.SistemaUsuario;

@DisplayName(" >>>>>>>>>> Rfid Tests (JUnit5)")
class HibernateFunctionalitiesTest {
	
	Logger logger = Logger.getLogger(HibernateFunctionalitiesTest.class.getName());
 
	private static SessionFactory sessionFactory;
	private static Session session;
	
	private SistemaUsuarioHibernateDAO suDAO;		
	private GenericHibernateDAOImp<Pessoa,Long> pessoaDAO;
	
	
	public HibernateFunctionalitiesTest(TestInfo testInfo) {
	
	}
	
        
	@BeforeAll
	public static void before() {
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();		
		sessionFactory = metadata.getSessionFactoryBuilder().build();
		session = sessionFactory.openSession();
	}
	
	/**
	 * O beforeAll fornece a session, mas com a session a gente cria nossos hibernate daos
	 * 
	 */
	{
		this.suDAO = new SistemaUsuarioHibernateDAO(session, SistemaUsuario.class, Long.class);
		this.pessoaDAO = new GenericHibernateDAOImp<>(session, Pessoa.class, Long.class);
		sistemaUsuarioDAO_createDefaultUsuers();
	}
	
	
	
	/**
	 * O metodo SistemaUsuario_createDefaultUsuers tem a funcao de criar 
	 * um usuario padrao admin senha admin123 
	 * e outro usuario padrao guest senha guest123
	 * Esse teste roda o o metodo create DefaultUsers() do DAO do sistema usuario e depois
	 * testa usando o metodo tem quantos pra ver se tem um usuario de cada
	 */
	private void sistemaUsuarioDAO_createDefaultUsuers() {
		
		suDAO.beginTransaction();
		suDAO.createDefaultUsers();
		suDAO.commit();
		int temQuantosAdminUsers = suDAO.temQuantos("admin", "admin123");
		int temQuantosGuestUsers = suDAO.temQuantos("guest", "guest123");
		assertEquals(1, temQuantosAdminUsers);
		assertEquals(1, temQuantosGuestUsers);
	}
	
	@Test
	@DisplayName(" >>>>>>>>>> public void SistemaUsuario_temQuanto_Test() {...")
	public void sistemaUsuarioDAO_temQuanto_Test() {		
		SistemaUsuarioHibernateDAO suDAO = new SistemaUsuarioHibernateDAO(session, SistemaUsuario.class, Long.class);		
		int temQuantosAdminUsers = suDAO.temQuantos("admin", "admin123");
		int temQuantosGuestUsers = suDAO.temQuantos("guest", "guest123");
		assertEquals(1, temQuantosAdminUsers);
		assertEquals(1, temQuantosGuestUsers);
	}

	@Test
	@DisplayName("")
	public void SistemaUsuarioDAO_inserir_Test() {
		this.session.beginTransaction();
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("pessTest");
		pessoa.setIdentificador("pessTest123");
		this.session.getTransaction().commit();
	}
	
	@AfterAll
	public static void after() {
		session.close();
		sessionFactory.close();
	}
    
}
