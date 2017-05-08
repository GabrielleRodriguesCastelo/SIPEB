package br.com.tcc.chamada.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tcc.chamada.modelo.ResponsavelAluno;

public interface ResponsavelAlunoDAO extends JpaRepository<ResponsavelAluno, Long>{

	public ResponsavelAluno findByCpf(String cpf);
}
