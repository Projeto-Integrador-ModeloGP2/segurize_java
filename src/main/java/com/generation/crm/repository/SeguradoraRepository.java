package com.generation.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.crm.model.Seguradora;

public interface SeguradoraRepository extends JpaRepository<Seguradora, Long> {
	
	public List<Seguradora> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);

}
