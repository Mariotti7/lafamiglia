package com.ecommerce.lafamiglia.rest;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.lafamiglia.dao.UsuarioDAO;
import com.ecommerce.lafamiglia.entities.Usuario;

@RestController
@RequestMapping("/usuario")
public class UsuarioRest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UsuarioDAO dao;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> listarUsuarios(){
		return ResponseEntity.ok(dao.findAll());
	}

}
