package com.g4.Ecommerce.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	private String nomeCompleto;

	@NotNull
	@Email
	private String email;
	
	@NotNull
	private String usuario;

	@NotNull
	private String senha;
	
	@ManyToMany (cascade = CascadeType.ALL)
	@JoinTable (name = "tabelaFavorita", joinColumns = {@JoinColumn (name = "usuarioId")},
	inverseJoinColumns = {@JoinColumn (name = "produtoId")})
	
	@JsonIgnoreProperties("favoritos")
	private List<Produto> meusFavoritos = new ArrayList<>();
	
	//@NotNull private String tipoUsuario; 

	@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnoreProperties
	private List<Produto> produto = new ArrayList<>();
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}

	public List<Produto> getMeusFavoritos() {
		return meusFavoritos;
	}

	public void setMeusFavoritos(List<Produto> meusFavoritos) {
		this.meusFavoritos = meusFavoritos;
	}
	
	
	
	
	
	
	

	/*public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}*/

}
