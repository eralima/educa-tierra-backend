package com.g4.Ecommerce.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;

//import java.util.List;

//import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	private String nome;

	@NotNull
	private String descricao;

	@NotNull
	private String linkImagem; 
	
	@NotNull
	private String linkAcesso; 
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "categoria")
	@JsonIgnoreProperties ({"produto"})
	private Categoria categoria;

	@ManyToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario")
	@JsonIgnoreProperties ({"produto", "senha"})
	private Usuario usuario;
	
	@ManyToMany (mappedBy = "meusFavoritos")
	private List<Usuario> favoritos = new ArrayList<>();

	//@NotNull private boolean statusTermo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLinkImagem() {
		return linkImagem;
	}

	public void setLinkImagem(String linkImagem) {
		this.linkImagem = linkImagem;
	}

	public String getLinkAcesso() {
		return linkAcesso;
	}

	public void setLinkAcesso(String linkAcesso) {
		this.linkAcesso = linkAcesso;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getFavoritos() {
		return favoritos;
	}

	public void setFavoritos(List<Usuario> favoritos) {
		this.favoritos = favoritos;
	}

	
	
	

	/*public boolean isStatusTermo() {
		return statusTermo;
	}

	public void setStatusTermo(boolean statusTermo) {
		this.statusTermo = statusTermo;
	}*/

	
}
