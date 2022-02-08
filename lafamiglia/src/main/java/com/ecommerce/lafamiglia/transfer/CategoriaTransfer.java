package com.ecommerce.lafamiglia.transfer;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.ecommerce.lafamiglia.entities.Produto;

public class CategoriaTransfer{
	
	@NotBlank
	@Size(max=70)
	private String tipo;

	@NotBlank
	private List<Produto> produto;

}
