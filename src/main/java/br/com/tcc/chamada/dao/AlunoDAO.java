package br.com.tcc.chamada.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tcc.chamada.modelo.Aluno;

public interface AlunoDAO extends JpaRepository<Aluno, Long> {

	public Aluno findByEmail(String email);
	
	public Aluno findByCpf(String cpf);
}
