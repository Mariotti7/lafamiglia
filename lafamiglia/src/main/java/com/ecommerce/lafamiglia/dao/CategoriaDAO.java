package com.ecommerce.lafamiglia.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.lafamiglia.entities.Categoria;

@Repository
public interface CategoriaDAO extends JpaRepository<Categoria, Long> {

	public Optional<Categoria> findAllByTipoContainingIgnoreCase(String tipo);

}
