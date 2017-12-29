package br.com.fzlbpms.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SistemaUsuarioRole {

	@Id
	String roleName;

	public SistemaUsuarioRole(String role) {
		this.roleName = roleName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
		
}
