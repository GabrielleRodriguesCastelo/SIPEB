package br.com.tcc.chamada.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tcc.chamada.modelo.Materia;

public interface MateriaDAO extends JpaRepository<Materia, Long> {

}
