package com.generation.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.crm.model.Plano;

public interface PlanoRepository extends JpaRepository <Plano, Long> {
	
	public List <Plano> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);
	
}
