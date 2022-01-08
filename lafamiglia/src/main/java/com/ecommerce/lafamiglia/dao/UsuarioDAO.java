package com.ecommerce.lafamiglia.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.lafamiglia.entities.Usuario;

public interface UsuarioDAO extends JpaRepository<Usuario, Long>{

}
