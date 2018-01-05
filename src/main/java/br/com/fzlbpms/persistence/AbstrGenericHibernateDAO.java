package br.com.fzlbpms.persistence;

import org.hibernate.Session;

public abstract class AbstrGenericHibernateDAO<T> implements IGenericDAO<T> {

	
	Session session;

	public AbstrGenericHibernateDAO(Session session) {
		this.session = session;
	}
	
	
	
}
