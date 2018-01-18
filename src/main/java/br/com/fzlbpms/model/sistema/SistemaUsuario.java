package br.com.fzlbpms.model.sistema;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import br.com.fzlbpms.model.common.Pessoa;

@Entity
public class SistemaUsuario{

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String login;
	private String senha;
	
	public SistemaUsuario() {		
	}

	public SistemaUsuario(String login, String senha) {		
		this.login = login;
		this.senha = senha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
