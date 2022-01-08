package com.ecommerce.lafamiglia.rest;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.lafamiglia.dao.ProdutoDAO;
import com.ecommerce.lafamiglia.entities.Produto;

@RestController
@RequestMapping("/produto")
public class ProdutoRest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ProdutoDAO dao;
	
	@GetMapping
	public ResponseEntity<List<Produto>> listarProdutos(){
		return ResponseEntity.ok(dao.findAll());
	}
	
	
	

}
