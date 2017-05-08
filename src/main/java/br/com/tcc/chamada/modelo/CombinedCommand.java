package br.com.tcc.chamada.modelo;

import org.springframework.stereotype.Component;

@Component
public class CombinedCommand {
	private BuscaAluno aluno;
	private Long id;

	public BuscaAluno getBuscaAluno() {
		return aluno;
	}

	public void setBuscaAluno(BuscaAluno aluno) {
		this.aluno = aluno;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
