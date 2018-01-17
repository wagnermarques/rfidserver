package br.com.fzlbpms.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.jboss.logging.Logger;

import br.com.fzlbpms.model.sistema.SistemaUsuario;

/**
 * @author wagner
 *
 * @param <T>
 */
@NamedQueries(
		@NamedQuery(name="temQuantos",query="select count(*) from SistemaUsuario u where u.login = ? and u.senha = ?")
		)
public class SistemaUsuarioHibernateDAO extends GenericHibernateDAOImp<SistemaUsuario, Long>{	
	
	Logger logger = Logger.getLogger(SistemaUsuarioHibernateDAO.class.getName());

	public SistemaUsuarioHibernateDAO(Session session, Class entityClass, Class pkClass) {
		super(session,SistemaUsuario.class, pkClass);
		this.session = session;
	}


	/**
	 * 
	 * @param login
	 * @param senha
	 * @return
	 * Util pra quando precisar saber se quantos usuario existem 
	 * com mesmo nome de login e senha
	 * Util tambe pra logar um um usuario, ja que term que ter somente um usuario e senha
	 * e nao mais de um.
	 */
	public int temQuantos(String login, String senha) {
		logger.info("public int temQuantos(String login, String senha) {...");
		System.out.println("this.session : " +this.session );
		CriteriaBuilder criteriaBuilder = this.session.getCriteriaBuilder();
		CriteriaQuery<SistemaUsuario> qry = criteriaBuilder.createQuery(SistemaUsuario.class);		
		Root<SistemaUsuario> from = qry.from(SistemaUsuario.class);
		qry.select(from);
		qry.where(criteriaBuilder.and(
				criteriaBuilder.equal(from.get("login"), login),
				criteriaBuilder.equal(from.get("senha"), senha)
				));
		
		Query<SistemaUsuario> createdQuery = session.createQuery(qry);
		List<SistemaUsuario> resultList = createdQuery.getResultList();
		return resultList.size();		
	}
	
	/**
	 * Cria usuario Admin com senha admin123
	 * e
	 * Cria usuario Guest com senha guest123
	 */
	public void createDefaultUsers() {
		logger.debug("public void createDefaultUsers() {...");
		logger.info("public void createDefaultUsers() {...");
		int qtdeDeUsuariosAdmin = this.temQuantos("admin", "admin123");
		int qtdeDeUsuariosGuest = this.temQuantos("guest", "guest123");
		
		if (qtdeDeUsuariosAdmin == 0) {

			SistemaUsuario userAdmin = new SistemaUsuario("admin", "admin123");
			SistemaUsuario userTest = new SistemaUsuario("test", "test123");
			this.session.save(userAdmin);
			this.session.save(userTest);						
			
		} else if (qtdeDeUsuariosAdmin > 1) {
			throw new RuntimeException("Nao deveria haver mais de um usuario padrao Admin");
			// lembrando que se tem um apenas, entao tudo ok
		}

		if (qtdeDeUsuariosGuest == 0) {
			SistemaUsuario userGuest = new SistemaUsuario("guest", "guest123");
			this.session.save(userGuest);			
		} else if (qtdeDeUsuariosGuest > 1) {
			throw new RuntimeException("Nao deveria haver mais de um usuario padrao Guest");
			// lembrando que se tem um apenas, entao tudo ok
		}

	}
	
	public void excluiTodos() {				
		CriteriaBuilder criteriaBuilder = this.session.getCriteriaBuilder();
		CriteriaDelete<SistemaUsuario> qryDelete = criteriaBuilder.createCriteriaDelete(SistemaUsuario.class);
		Root<SistemaUsuario> deleteFrom = qryDelete.from(SistemaUsuario.class);
		this.session.createQuery(qryDelete).executeUpdate();
	}

}
