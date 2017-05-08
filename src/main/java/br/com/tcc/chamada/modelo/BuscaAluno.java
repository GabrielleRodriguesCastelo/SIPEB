package br.com.tcc.chamada.modelo;

import org.springframework.stereotype.Component;

@Component
public class BuscaAluno {

	private String cpf;
	private Long ra;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Long getRa() {
		return ra;
	}

	public void setRa(Long ra) {
		this.ra = ra;
	}

}
