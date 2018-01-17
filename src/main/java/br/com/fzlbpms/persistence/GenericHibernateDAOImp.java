package br.com.fzlbpms.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Criteria;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.query.Query;

import br.com.fzlbpms.model.common.Pessoa;

public class GenericHibernateDAOImp<T,Pk extends Long> extends AbstrGenericHibernateDAO<T, Pk>{

	
	public GenericHibernateDAOImp(Session session, Class entityClass, Class pkClass) {
		super(session, entityClass, pkClass);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public List<T> ListarTodos() {
		CriteriaBuilder cB = this.session.getCriteriaBuilder();
		CriteriaQuery<Pessoa> qry = cB.createQuery(this.entityClass);
		qry.select(qry.from(entityClass));		
		Query<T> createdQuery = (Query<T>) this.session.createQuery(qry);
		return createdQuery.getResultList();		
	}
	
	//TODO: FIX deprecated 
	@Override
	public List<T> listByExample(T e, String[] excludeProperty) {
		Criteria crit = this.session.createCriteria(this.entityClass);  
        Example example =  Example.create(e);  
        for (String exclude : excludeProperty) {  
            example.excludeProperty(exclude);  
        }  
        crit.add(example);  
        return crit.list();  
	}

	@Override
	public Pk inserir(T e) {						
		return (Pk) this.session.save(e);
	}

	@Override
	public void alterar(T e) {
		this.session.merge(e);		
	}

	@Override
	public void excluir(T e) {
		this.session.remove(e);
	}

	@Override
	public T getById(Long pk) {		
		return (T) this.session.byId(this.entityClass).load(pk);
	}

}
