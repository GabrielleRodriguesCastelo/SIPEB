package br.com.tcc.chamada.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.tcc.chamada.dao.ResponsavelAlunoDAO;
import br.com.tcc.chamada.modelo.Aluno;
import br.com.tcc.chamada.modelo.ResponsavelAluno;

@Component
public class AlunoValidator implements Validator {

	@Autowired
	private ResponsavelAlunoDAO responsavelAlunoDAO;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(Aluno.class);
	}

	@Override
	public void validate(Object obj, Errors erros) {
		Aluno aluno = (Aluno) obj;

		ResponsavelAluno responsavel = aluno.getResponsavelAluno();
		String cpf = responsavel.getCpf();

		ResponsavelAluno responsavelAluno = responsavelAlunoDAO.findByCpf(cpf);
		
		if (responsavelAluno != null) {
			aluno.setResponsavelAluno(responsavelAluno);
		} else {
			if (responsavel.getNome() == null || responsavel.getNome().isEmpty()) {
				erros.rejectValue("responsavelAluno.nome", "aluno.responsavel.atributo_invalido");
			}
			if (responsavel.getEmail() == null || responsavel.getEmail().isEmpty()) {
				erros.rejectValue("responsavelAluno.email", "aluno.responsavel.atributo_invalido");
			}
			if (responsavel.getCpf() == null || responsavel.getCpf().isEmpty()) {
				erros.rejectValue("responsavelAluno.cpf", "aluno.responsavel.atributo_invalido");
			}
			if (responsavel.getPassword() == null || responsavel.getPassword().isEmpty()) {
				erros.rejectValue("responsavelAluno.password", "aluno.responsavel.atributo_invalido");
			}
			if (responsavel.getTelefone() == null || responsavel.getTelefone().isEmpty()) {
				erros.rejectValue("responsavelAluno.telefone", "aluno.responsavel.atributo_invalido");
			}
			System.out.println("Responsavel do aluno " + aluno.getNome() + " não cadastrado na base");

		}
	}

}
