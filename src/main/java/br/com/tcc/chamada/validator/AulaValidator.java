package br.com.tcc.chamada.validator;

import java.time.LocalTime;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.tcc.chamada.modelo.Aula;
import br.com.tcc.chamada.modelo.Professor;
import br.com.tcc.chamada.modelo.Turma;

public class AulaValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(Aula.class);
	}

	@Override
	public void validate(Object obj, Errors erros) {
		Aula aula = (Aula) obj;

		if (existeAtributoNulo(aula, erros)) {
			return;
		}


		Boolean horariosCorretos = horarioInicioAnteriorHorarioFim(aula.getHorarioInicio(), aula.getHorarioFim());
		if (!horariosCorretos) {
			erros.reject("aula.cadastro.horario_invalido");
		}

		Professor professor = aula.getProfessor();
		Boolean professorEstaDisponivel = professor.disponivelNaData(aula.getHorarioInicio(), aula.getHorarioFim(),
				aula.getDiasDeAula());
		if (!professorEstaDisponivel) {
			erros.rejectValue("professor", "aula.cadastro.professor_indisponivel", new String[] { professor.getNome() },
					null);
		}

		Turma turma = aula.getTurma();
		Boolean horarioDeAulaDisponivel = turma.disponivelNaData(aula.getHorarioInicio(), aula.getHorarioFim(),
				aula.getDiasDeAula());
		
		if (!horarioDeAulaDisponivel) {
			erros.rejectValue("professor", "aula.cadastro.horario_de_aula_indisponivel", new String[] { turma.getCodigoTurma() },
					null);
		}

	}

	private boolean existeAtributoNulo(Aula aula, Errors erros) {
		Boolean existeAtributoNulo = false;

		
		
		if (aula.getHorarioInicio() == null) {
			erros.reject("aula.cadastro.horario_inicio.not_null");
			existeAtributoNulo = true;
		}
		if (aula.getHorarioFim() == null) {
			erros.reject("aula.cadastro.horario_fim.not_null");
			existeAtributoNulo = true;
		}
		if (aula.getMateria() == null) {
			erros.reject("aula.cadastro.materia.not_null");
			existeAtributoNulo = true;
		}
		if (aula.getProfessor() == null) {
			erros.reject("aula.cadastro.professor.not_null");
			existeAtributoNulo = true;
		}
		if (aula.getDiasDeAula() == null) {
			erros.reject("aula.cadastro.dia_da_semana.not_null");
			existeAtributoNulo = true;
		}
		
		return existeAtributoNulo;
	}

	private Boolean horarioInicioAnteriorHorarioFim(LocalTime horarioInicio, LocalTime horarioFim) {
		if (horarioInicio.isAfter(horarioFim))
			return false;
		return true;
	}

	

}
