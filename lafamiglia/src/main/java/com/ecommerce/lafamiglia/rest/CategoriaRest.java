package com.ecommerce.lafamiglia.rest;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.lafamiglia.dao.CategoriaDAO;
import com.ecommerce.lafamiglia.entities.Categoria;

@RestController
@RequestMapping("/categoria")
public class CategoriaRest implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private CategoriaDAO dao;

	@GetMapping
	public ResponseEntity<List<Categoria>> listaCategoria() {
		return ResponseEntity.ok(dao.findAll());
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Optional<Categoria>> listarPorId(@PathVariable Long id) {
		var find = dao.findById(id);
		var response = ResponseEntity.ok(find);

		if (find.isEmpty() || find == null) {
			find.orElseThrow();
		}
		return response;
	}

	@PostMapping
	public ResponseEntity<Categoria> salvarCategoria(@RequestBody Categoria categoria) {
		return ResponseEntity.status(HttpStatus.CREATED).body(dao.save(categoria));
	}

	@SuppressWarnings("unchecked")
	@PutMapping("/{id}")
	public ResponseEntity<Categoria> atualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {

		var find = dao.findById(id);

		var error = ResponseEntity.badRequest();

		if (find.isEmpty() || find == null) {
			return (ResponseEntity<Categoria>) error;
		} else {
			categoria.setIdCategoria(id);
		}

		return ResponseEntity.status(HttpStatus.OK).body(dao.save(categoria));
	}

	@DeleteMapping("/id/{id}")
	public void deletar(@PathVariable Long id) {

		var find = dao.findById(id);

		if (!find.isEmpty() || find != null) {
			dao.deleteById(id);
		}
	}

}
