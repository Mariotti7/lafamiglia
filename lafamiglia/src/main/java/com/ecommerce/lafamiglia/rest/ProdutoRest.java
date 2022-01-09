package com.ecommerce.lafamiglia.rest;

import java.io.Serializable;
import java.util.List;

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

import com.ecommerce.lafamiglia.dao.ProdutoDAO;
import com.ecommerce.lafamiglia.entities.Produto;

@RestController
@RequestMapping("/produto")
public class ProdutoRest implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ProdutoDAO dao;

	@GetMapping
	public ResponseEntity<List<Produto>> listarProdutos() {
		return ResponseEntity.ok(dao.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> listarPorId(@PathVariable Long id) {

		var find = dao.findById(id);
		
		var resp = find.map(response -> ResponseEntity.ok(response)).orElse(ResponseEntity.notFound().build());

		return resp;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/produto/{nomeProduto}")
	public ResponseEntity<List<Produto>> listarPorNomeProduto(@PathVariable String nomeProduto) {

		var find = dao.findAllByNomeProdutoContainingIgnoreCase(nomeProduto);

		var response = find.isEmpty() || find == null || !find.equals(find) ? ResponseEntity.badRequest().build()
				: ResponseEntity.ok(find);

		return (ResponseEntity<List<Produto>>) response;
	}

	@PostMapping
	public ResponseEntity<Produto> salvarProduto(@RequestBody Produto produto) {

		var response = ResponseEntity.status(HttpStatus.CREATED).body(dao.save(produto));

		return response;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @RequestBody Produto produto){
		
		var find = dao.findById(id);
		
		ResponseEntity<Produto> error = ResponseEntity.badRequest().build();
		
		if(find.isEmpty() || find == null || !find.equals(find)) {
			return error;
		}else {
			produto.setIdProduto(id);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(dao.save(produto));
	}
	
	@DeleteMapping("/id/{id}")
	public void deletar(@PathVariable long id) {
		
		var find = dao.findById(id);
		
		if(!find.isEmpty() || find != null || find.equals(find))
			dao.deleteById(id);
	}

}
