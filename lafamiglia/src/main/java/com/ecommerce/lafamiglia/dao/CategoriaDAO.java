package com.ecommerce.lafamiglia.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.lafamiglia.entities.Categoria;

public interface CategoriaDAO extends JpaRepository<Categoria, Long> {

}
