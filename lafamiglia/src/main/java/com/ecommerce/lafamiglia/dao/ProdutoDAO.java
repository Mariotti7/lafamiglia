package com.ecommerce.lafamiglia.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.lafamiglia.entities.Produto;

public interface ProdutoDAO extends JpaRepository<Produto, Long>{

	public List<Produto> findAllByNomeProdutoContainingIgnoreCase(String nomeProduto);

}
