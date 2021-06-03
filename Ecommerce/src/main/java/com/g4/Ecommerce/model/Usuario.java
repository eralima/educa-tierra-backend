package com.g4.Ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="tb_usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	private String nomeCompleto;
	
	@NotNull
	private String email;
	
	@NotNull
	private String senha;
	
	@NotNull
	private String tipoUsuario; //se é prof ou aluno

}
