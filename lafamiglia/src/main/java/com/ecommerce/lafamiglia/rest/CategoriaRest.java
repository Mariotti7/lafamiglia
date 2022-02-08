package com.ecommerce.lafamiglia.rest;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.lafamiglia.bo.CategoriaBO;
import com.ecommerce.lafamiglia.entities.Categoria;
import com.ecommerce.lafamiglia.transfer.CategoriaTransfer;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CategoriaRest implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private CategoriaBO categoriaBO;

	@GetMapping
	public ResponseEntity<List<Categoria>> listaCategoria() {
		return ResponseEntity.ok(categoriaBO.findAll());
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Object> listarPorId(@PathVariable(value = "id") Long id) {
		Optional<Categoria> find = categoriaBO.findById(id);

		if (!find.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category ID Not Found!");
		}

		return ResponseEntity.status(HttpStatus.OK).body(categoriaBO.findById(id));

//				find.map(r -> ResponseEntity.ok(r))
//				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/tipo/{tipo}")
	public ResponseEntity<Object> listarPorTipo(@PathVariable String tipo) {

		Optional<Categoria> find = categoriaBO.findAllByTipoContainingIgnoreCase(tipo);

		if (find.isEmpty() || !find.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category type Not Found!");
		}

		return ResponseEntity.status(HttpStatus.OK).body(categoriaBO.findAllByTipoContainingIgnoreCase(tipo));
	}

	@PostMapping
	public ResponseEntity<Object> salvarCategoria(@RequestBody @Valid CategoriaTransfer categoriaTransfer) {

		var categoriaEntity = new Categoria();

		// Conversão Transfer to Entity
		BeanUtils.copyProperties(categoriaTransfer, categoriaEntity);

		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaBO.save(categoriaEntity));
	}

	@SuppressWarnings("unchecked")
	@PutMapping("/{id}")
	public ResponseEntity<Object> atualizarCategoria(@PathVariable(value = "id") Long id,
			@RequestBody @Valid CategoriaTransfer categoriaTransfer) {

		Optional<Categoria> find = categoriaBO.findById(id);

		if (!find.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category ID Not Found!");
		}

		var categoriaEntity = new Categoria();

		BeanUtils.copyProperties(categoriaTransfer, categoriaEntity);
		categoriaEntity.setIdCategoria(find.get().getIdCategoria());

		return ResponseEntity.status(HttpStatus.OK).body(categoriaBO.save(categoriaEntity));
	}

	@DeleteMapping("/id/{id}")
	public void deletar(@PathVariable(value = "id") Long id) {

		Optional<Categoria> find = categoriaBO.findById(id);

		if (!find.isPresent()) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category ID Not Found");
		}

		categoriaBO.delete(id);
	}

}
