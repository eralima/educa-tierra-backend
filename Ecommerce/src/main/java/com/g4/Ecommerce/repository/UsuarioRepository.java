package com.g4.Ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g4.Ecommerce.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	public List<Usuario> findAllBynomeCompletoContainingIgnore(String nomeCompleto);
	public List<Usuario> findAllBytipoUsuarioContainingIgnore(String tipoUsuario);
}
