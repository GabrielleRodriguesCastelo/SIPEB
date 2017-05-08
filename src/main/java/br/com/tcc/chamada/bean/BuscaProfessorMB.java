package br.com.tcc.chamada.bean;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tcc.chamada.dao.ProfessorDAO;
import br.com.tcc.chamada.modelo.Professor;
import br.com.tcc.chamada.modelo.BuscaProfessor;

@Component
public class BuscaProfessorMB {

	@Autowired
	private ProfessorDAO professorDAO;

	public List<Professor> getProfessores(BuscaProfessor buscaProfessor) {
		String cpf = buscaProfessor.getCpf();
		Long id = buscaProfessor.getId();
		
		if (cpf != null && !cpf.isEmpty()) {
			Professor professorEncontrado = professorDAO.findByCpf(cpf);
			
			if(professorEncontrado == null){
				return null;
			}
			
			return Arrays.asList(professorEncontrado);
		}
		
		if (id != null) {
			Professor professorEncontrado = professorDAO.findOne(id);
			
			if(professorEncontrado == null){
				return null;
			}
			return Arrays.asList(professorEncontrado);
		}
		
		return null;
	}
}
