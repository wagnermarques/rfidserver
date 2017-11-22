package br.com.fzlbpms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SistemaUsuario extends Pessoa{

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String login;
    private String senha;

    public String getLogin() {
        return login;
    }

    public Integer getId() {
        return id;
    }

    public String getSenha() {
        return senha;
    }

    public void setLogin(String login){
        this.login = login;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }

    public void setId(Integer id){
        this.id = id;
    }
}
