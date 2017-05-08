package br.com.tcc.chamada.bean;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tcc.chamada.dao.AlunoDAO;
import br.com.tcc.chamada.modelo.Aluno;
import br.com.tcc.chamada.modelo.BuscaAluno;

@Component
public class BuscaAlunoMB {

	@Autowired
	private AlunoDAO alunoDAO;

	public List<Aluno> getAlunos(BuscaAluno buscaAluno) {
		String cpf = buscaAluno.getCpf();
		Long ra = buscaAluno.getRa();
		
		if (cpf != null && !cpf.isEmpty()) {
			Aluno alunoEncontrado = alunoDAO.findByCpf(cpf);
			
			if(alunoEncontrado == null){
				return null;
			}
			
			return Arrays.asList(alunoEncontrado);
		}
		
		if (ra != null) {
			Aluno alunoEncontrado = alunoDAO.findOne(ra);
			
			if(alunoEncontrado == null){
				return null;
			}
			return Arrays.asList(alunoEncontrado);
		}
		
		return null;
	}
}
