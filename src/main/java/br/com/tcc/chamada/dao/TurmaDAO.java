package br.com.tcc.chamada.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tcc.chamada.modelo.Turma;

public interface TurmaDAO extends JpaRepository<Turma, Long> {

	Turma findByCodigoTurma(String codigoTurma);

	List<Turma> findAllBySerie(String serie);

	List<Turma> findAllByAno(Integer ano);

}
