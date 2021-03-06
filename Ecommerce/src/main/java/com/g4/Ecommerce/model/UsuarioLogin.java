package com.g4.Ecommerce.model;


public class UsuarioLogin {
	
	private long id;

	private String nomeCompleto;
		
	private String email;
	
	private String foto;
		
	private String usuario;
	
	private String senha;
	
	private String token;
	
	private boolean adminUsuario;
	
	private int pontuacao;
	
	
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public boolean isAdminUsuario() {
		return adminUsuario;
	}

	public void setAdminUsuario(boolean adminUsuario) {
		this.adminUsuario = adminUsuario;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}
	
	
}
