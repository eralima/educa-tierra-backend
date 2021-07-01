package com.g4.Ecommerce.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.g4.Ecommerce.model.Categoria;
import com.g4.Ecommerce.model.Produto;
import com.g4.Ecommerce.model.Usuario;
import com.g4.Ecommerce.model.UsuarioLogin;
import com.g4.Ecommerce.repository.CategoriaRepository;
import com.g4.Ecommerce.repository.ProdutoRepository;
import com.g4.Ecommerce.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public Usuario cadastrarUsuario(Usuario novoUsuario) {
		Optional<Usuario> usuario = usuarioRepository.findByUsuario(novoUsuario.getUsuario());
		
		if(usuario.isPresent()) {
			return null;
		}
		else {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

			String senhaEncoder = encoder.encode(novoUsuario.getSenha());
			novoUsuario.setSenha(senhaEncoder);

			return usuarioRepository.save(novoUsuario);	
		}
	}
	
	public Optional<UsuarioLogin> logar(Optional<UsuarioLogin> user) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = usuarioRepository.findByUsuario(user.get().getUsuario());

		if (usuario.isPresent()) {
			if (encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {

				String auth = user.get().getUsuario() + ":" + user.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);

				user.get().setToken(authHeader);

				return user;
			}
		}
		return null;
	}
	
	//cadastrar um novo produto
	public Optional<Produto> cadastrarProduto (long usuarioId, long categoriaId, Produto produto){
		Produto produtoCadastrado = produtoRepository.save(produto);
		Optional<Categoria> categoria = categoriaRepository.findById(categoriaId);
		Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
		
		if (categoria.isPresent() && usuario.isPresent()) {
			produtoCadastrado.setCategoria(categoria.get());
			produtoCadastrado.setUsuario(usuario.get());
			return Optional.ofNullable(produtoRepository.save(produto));
		} else {
			return Optional.empty();
		}
	}
	
	//deletar um produto
	public Usuario excluirProduto (long usuarioId, long produtoId){
		Optional<Produto> produtoDeletado = produtoRepository.findById(produtoId);
		Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
		
		if(produtoDeletado.isPresent() && usuario.isPresent()) {
			produtoDeletado.get().setUsuario(null);
			produtoRepository.save(produtoDeletado.get());
			produtoRepository.deleteById(produtoDeletado.get().getId());
			return usuarioRepository.findById(usuario.get().getId()).get();
		}
		else {
			return null;
		}
	}
	
	//salvar produtos na lista de favoritos
	public Usuario salvarFavoritos (long produtoId, long usuarioId) {
		Optional <Produto> produto = produtoRepository.findById(produtoId);
		Optional <Usuario> usuario = usuarioRepository.findById(usuarioId);
		if(produto.isPresent() && usuario.isPresent()) {
			 usuario.get().getMeusFavoritos().add(produto.get());
			return usuarioRepository.save(usuario.get());
		}else {
			
			return null;
		}
	}
	
}
