package br.com.tcc.chamada.modelo;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Turma {

	@Id
	@GeneratedValue
	private Long id;

	private String codigoTurma;


	private String serie;

	private Integer ano;

	@OneToMany(mappedBy = "turma")
	private List<Aluno> alunos;

	@OneToMany(mappedBy = "turma")
	private List<Aula> aulas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoTurma() {
		return codigoTurma;
	}

	public void setCodigoTurma(String codigoTurma) {
		this.codigoTurma = codigoTurma;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public List<Aula> getAulas() {
		return aulas;
	}

	public void setAulas(List<Aula> aulas) {
		this.aulas = aulas;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public void inserirAluno(Aluno aluno) {
		if (alunos == null) {
			alunos = new ArrayList<>();
		}

		alunos.add(aluno);
	}

	public Boolean disponivelNaData(LocalTime horarioInicio, LocalTime horarioFim, DiaSemana diaSemanaDeAula) {

		for (Aula aula : aulas) {
			DiaSemana diasDeAula = aula.getDiasDeAula();
			LocalTime horarioInicioAulaRegistrada = aula.getHorarioInicio();
			LocalTime horarioFimAulaRegistrada = aula.getHorarioFim();

			if (diasDeAula.equals(diaSemanaDeAula)) {
				if (horarioInicio.equals(horarioInicioAulaRegistrada) && horarioFim.equals(horarioFimAulaRegistrada)) {
					return Boolean.FALSE;
				}
			}
		}

		return Boolean.TRUE;
	}
}
