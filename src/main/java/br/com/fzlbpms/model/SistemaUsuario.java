package br.com.fzlbpms.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.mysql.cj.api.x.Collection;

@Entity
public class SistemaUsuario extends Pessoa{

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String login;
    private String senha;
    
    @OneToMany(fetch = FetchType.EAGER)
    private List<SistemaUsuarioRole> roles = new ArrayList<SistemaUsuarioRole>();

        
	public SistemaUsuario(String nomePessoa, String login, String senha) {
		super(nomePessoa);
		this.login = login;
		this.senha = senha;
	}


	public void setRole(String role){
		SistemaUsuarioRole roleObj = new SistemaUsuarioRole(role);
		this.roles.add(roleObj);		
	}


	//getters and setters
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
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


	public List<SistemaUsuarioRole> getRoles() {
		return roles;
	}


	public void setRoles(List<SistemaUsuarioRole> roles) {
		this.roles = roles;
	}
	

}
