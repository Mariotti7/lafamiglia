package com.ecommerce.lafamiglia.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UsuarioLogin implements Serializable{

	private static final long serialVersionUID = 1L;

	private String email;

	private String senha;

	private String token;

	private String nome;

}
