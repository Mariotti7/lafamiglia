package com.ecommerce.lafamiglia.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.lafamiglia.entities.Categoria;

public interface CategoriaDAO extends JpaRepository<Categoria, Long> {

	public List<Categoria>findAllByTipoContainingIgnoreCase(String tipo);

}
