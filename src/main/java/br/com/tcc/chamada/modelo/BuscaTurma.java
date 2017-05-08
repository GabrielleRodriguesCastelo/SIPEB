package br.com.tcc.chamada.modelo;

import org.springframework.stereotype.Component;

@Component
public class BuscaTurma {

	private String codigoTurma;
	private String serie;
	private Integer ano;

	public String getCodigoTurma() {
		return codigoTurma;
	}

	public void setCodigoTurma(String codigoTurma) {
		this.codigoTurma = codigoTurma;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

}
