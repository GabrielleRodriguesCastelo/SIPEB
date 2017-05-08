package br.com.tcc.chamada.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.tcc.chamada.modelo.Turma;

@Component
public class TurmaValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(Turma.class);
	}

	@Override
	public void validate(Object obj, Errors erros) {

	}
}
