package com.g4.Ecommerce.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.g4.Ecommerce.model.Usuario;

public class UserDetailsImpl implements UserDetails {

	
	private static final long serialVersionUID = 1L;
	
	private String password;
	private String userName;
	private List<GrantedAuthority> authorities;
	
	
	public UserDetailsImpl(Usuario usuario) {
		super();
		this.password = usuario.getSenha();
		this.userName = usuario.getUsuario();
	}
	
	public UserDetailsImpl() {}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	
}
