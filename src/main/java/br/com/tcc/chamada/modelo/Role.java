package br.com.tcc.chamada.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
	private String name;

	public Role() {

	}

	public Role(String name) {
		this.name = name;
	}

	@Override
	public String getAuthority() {
		return name;
	}

}
