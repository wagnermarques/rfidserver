package br.com.fzlbpms.persistence;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.fzlbpms.model.Pessoa;

public class PessoaJPADAO {

	EntityManager entityManager;
	public PessoaJPADAO(EntityManager em) {
		entityManager = em;
	}
	
	public void inserir(Pessoa p) {
		entityManager.getTransaction().begin();
		entityManager.persist(p);		
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	public List<Pessoa> listar(){		
		entityManager.getTransaction().begin();
		List<Pessoa> result = 
				entityManager.createQuery( "from Pessoas", Pessoa.class ).getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		return result;
	}
}
