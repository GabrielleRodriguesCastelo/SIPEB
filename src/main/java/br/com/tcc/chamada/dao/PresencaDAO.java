package br.com.tcc.chamada.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tcc.chamada.modelo.Presenca;

public interface PresencaDAO extends JpaRepository<Presenca, Long> {


	public List<Presenca> findByAulaFinalizadaIsNull();
	
	public List<Presenca> findByAulaId(Long id);
}
