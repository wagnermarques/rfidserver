package br.com.fzlbpms.persistence;

import java.io.Serializable;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.query.Query;

import br.com.fzlbpms.model.common.Pessoa;

public abstract class AbstrGenericHibernateDAO<T, Pk extends Serializable> implements IGenericDAO<T,Pk> {
	
	Session session;
	protected Class entityClass;
	private Class pkClass;
	private Transaction currentTransaction;

	public AbstrGenericHibernateDAO(Session session, Class entityClass, Class pkClass) {
		this.session = session;
		this.entityClass = entityClass;
		this.pkClass = pkClass;
	}
	
	public void flushSession() {
		this.session.flush();
	}
	public void clearSession() {
		this.session.clear();
	}
	public void closeSession() {
		this.session.close();
	}
	public void beginTransaction() {
		this.currentTransaction = this.session.beginTransaction();
	}
	public void commit() {
		this.currentTransaction.commit();
	}


	@Override
	protected void finalize() throws Throwable {
		//Ainda to em duvida se deve-se commitar alguma transacao que o programador esquece de comitar...
		//mas vou deixar assim por enquanto
		if(this.currentTransaction != null) this.currentTransaction.commit();
		
		//Ja com relacao a sessao, tem que liberar a conecao do pool, entao a gente fecha
		//Nao dei flush na sessao porque o flush pode dar algum problema com objetos detachados e isso pode invializar o finalize
		//Mas o close da sessao existente é sempre possivel
		if(this.session != null) this.session.close();
		super.finalize();
	}
	
}
