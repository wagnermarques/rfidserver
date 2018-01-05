package br.com.fzlbpms.persistence;

import java.util.List;

public interface IGenericDAO<T> {

	List<T> ListarTodos();
	
	
}
