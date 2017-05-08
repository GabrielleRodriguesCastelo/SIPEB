package br.com.tcc.chamada.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tcc.chamada.modelo.Aula;

public interface AulaDAO extends JpaRepository<Aula, Long> {
	
	

}
