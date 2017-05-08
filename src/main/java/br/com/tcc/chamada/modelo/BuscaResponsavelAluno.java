package br.com.tcc.chamada.modelo;

import org.springframework.stereotype.Component;

@Component
public class BuscaResponsavelAluno {

	private String cpf;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
