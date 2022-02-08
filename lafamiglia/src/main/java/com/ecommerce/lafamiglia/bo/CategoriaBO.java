package com.ecommerce.lafamiglia.bo;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.lafamiglia.dao.CategoriaDAO;
import com.ecommerce.lafamiglia.entities.Categoria;

@Service
public class CategoriaBO {
	
	@Autowired
	private CategoriaDAO dao;

	public List<Categoria> findAll() {
		return dao.findAll();
	}

	public Optional<Categoria> findById(Long id) {
		return dao.findById(id);
	}

	public Optional<Categoria> findAllByTipoContainingIgnoreCase(String tipo) {
		return dao.findAllByTipoContainingIgnoreCase(tipo);
	}
	
	@Transactional
	public Categoria save(Categoria categoria) {
		
		return dao.save(categoria);
	}
	
	@Transactional
	public void delete(Long id) {
		
		dao.deleteById(id);
		
	}

}
