package br.com.tcc.chamada.modelo;

import org.springframework.stereotype.Component;

@Component
public class BuscaProfessor {

	private String cpf;
	private Long id;
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	
	

}
