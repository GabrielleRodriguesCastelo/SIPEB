package br.com.tcc.chamada.modelo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class Login implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String username ;
	private String password;
	private boolean isProfessor;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}


	@Override
	public String getPassword() {
		return password;
	}


	@Override
	public String getUsername() {
		return username;
	}


	public boolean isProfessor() {
		return isProfessor;
	}


	public void setIsProfessor(boolean isProfessor) {
		this.isProfessor = isProfessor;
	}


	@Override
	public boolean isAccountNonExpired() {
		return false;
	}


	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}



}
