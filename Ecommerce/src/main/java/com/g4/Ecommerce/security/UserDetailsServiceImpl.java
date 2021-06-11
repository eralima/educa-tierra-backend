package com.g4.Ecommerce.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.g4.Ecommerce.model.Usuario;
import com.g4.Ecommerce.repository.UsuarioRepository;

import kotlin.jvm.Throws;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioRepository.findByUsuario(userName);
		if (usuario.isPresent()) {
			return UserDetailsImpl(usuario.get());
		}

		else {
			throw new UsernameNotFoundException("Usuário não existe");
		}

	}

}