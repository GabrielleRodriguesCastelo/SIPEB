package br.com.tcc.chamada.modelo;

import java.time.LocalDateTime;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
public class Presenca {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Aula aula;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	@NotNull
	private LocalDateTime data;
	
	private Boolean aulaFinalizada;

	@ElementCollection(fetch = FetchType.EAGER)
	private Map<Aluno, Boolean> alunoPresente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Map<Aluno, Boolean> getAlunoPresente() {
		return alunoPresente;
	}

	public void setAlunoPresente(Map<Aluno, Boolean> alunoPresente) {
		this.alunoPresente = alunoPresente;
	}

	public Boolean getAulaFinalizada() {
		return aulaFinalizada;
	}

	public void setAulaFinalizada(Boolean aulaFinalizada) {
		this.aulaFinalizada = aulaFinalizada;
	}


}
