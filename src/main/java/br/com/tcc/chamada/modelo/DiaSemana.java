package br.com.tcc.chamada.modelo;

public enum DiaSemana {
	SEGUNDA("Segunda-Feira"), TERCA("Terça-Feira"), QUARTA("Quarta-Feira"), QUINTA("Quinta-Feira"), SEXTA(
			"Sexta-Feira"), SABADO("Sabado"), DOMINGO("Domingo");

	private String nome;

	DiaSemana(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

}
