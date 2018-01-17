package br.com.fzlbpms.persistence;

import java.io.Serializable;
import java.util.List;

public interface IGenericDAO<T, Pk extends Serializable> {

	List<T> ListarTodos();
	List<T> listByExample(T e, String[] excludeProperty);
	
	T getById(Pk pk);
    Pk inserir(T e);    
    void alterar(T e);
	void excluir(T e);
	

	void flushSession();
	void clearSession();
	void closeSession();
	void beginTransaction();
	void commit();

    
}
