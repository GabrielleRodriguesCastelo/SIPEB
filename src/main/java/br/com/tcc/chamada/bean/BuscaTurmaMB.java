package br.com.tcc.chamada.bean;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tcc.chamada.dao.TurmaDAO;
import br.com.tcc.chamada.modelo.BuscaTurma;
import br.com.tcc.chamada.modelo.Turma;

@Component
public class BuscaTurmaMB {

	@Autowired
	private TurmaDAO turmaDAO;

	public List<Turma> getTurmas(BuscaTurma buscaTurma) {
		String codigoTurma = buscaTurma.getCodigoTurma();
		String serie = buscaTurma.getSerie();
		Integer ano = buscaTurma.getAno();
		
		if (codigoTurma != null && !codigoTurma.isEmpty()) {
			Turma turmaEncontrado = turmaDAO.findByCodigoTurma(codigoTurma);
			
			if(turmaEncontrado == null){
				return null;
			}
			
			return Arrays.asList(turmaEncontrado);
		}
		
		if (serie != null && !serie.isEmpty()) {
			List<Turma> turmaEncontrado = turmaDAO.findAllBySerie(serie);
			
			if(turmaEncontrado == null || turmaEncontrado.isEmpty()){
				return null;
			}
			
			return turmaEncontrado;
		}	
		
		if (ano != null) {
			List<Turma> turmaEncontrado = turmaDAO.findAllByAno(ano);
			
			if(turmaEncontrado == null || turmaEncontrado.isEmpty()){
				return null;
			}
			
			return turmaEncontrado;
		}	
		return null;
	}

}
