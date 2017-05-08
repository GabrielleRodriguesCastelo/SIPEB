package br.com.tcc.chamada.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tcc.chamada.modelo.Professor;

public interface ProfessorDAO extends JpaRepository<Professor, Long> {
	public Professor findByEmail(String email);
	
	public Professor findByCpf(String cpf);
	
	

}
