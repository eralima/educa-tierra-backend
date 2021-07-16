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

	/**
	 * Cadastra no banco de dados um novo usuario para acessar o sistema caso não
	 * exista, retornando um Optional com a entidade
	 * 
	 * @param novoUsuario uma entidade Usuario
	 * @return Optional com Usuario se a entidade não existir no banco de dados,
	 *         caso contrario null
	 * @since 1.0
	 * @author EducaTierra
	 */

	public Usuario cadastrarUsuario(Usuario novoUsuario) {
		Optional<Usuario> usuario = usuarioRepository.findByUsuario(novoUsuario.getUsuario());

		if (usuario.isPresent()) {
			return null;
		} else {			
			if (novoUsuario.getEmail().equals("contato.educa.tierra@gmail.com")) {
				novoUsuario.setAdminUsuario(true);
			} else {
				novoUsuario.setAdminUsuario(false);
			}

			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

			String senhaEncoder = encoder.encode(novoUsuario.getSenha());
			novoUsuario.setSenha(senhaEncoder);

			// assim que o usuario cadastra ele já ganha 10 pontos
			int pontuacao = 10;
			novoUsuario.setPontuacao(pontuacao);

			return usuarioRepository.save(novoUsuario);
		}

	}

	public Optional<UsuarioLogin> logar(Optional<UsuarioLogin> usuarioLogin) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuarioLogado = usuarioRepository.findByUsuario(usuarioLogin.get().getUsuario());

		if (usuarioLogado.isPresent()) {
			if (encoder.matches(usuarioLogin.get().getSenha(), usuarioLogado.get().getSenha())) {

				String auth = usuarioLogin.get().getUsuario() + ":" + usuarioLogin.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);

				usuarioLogin.get().setToken(authHeader);
				usuarioLogin.get().setNomeCompleto(usuarioLogado.get().getNomeCompleto());
				usuarioLogin.get().setEmail(usuarioLogado.get().getEmail());
				usuarioLogin.get().setFoto(usuarioLogado.get().getFoto());
				usuarioLogin.get().setId(usuarioLogado.get().getId());
				usuarioLogin.get().setPontuacao(usuarioLogado.get().getPontuacao());
        
				return usuarioLogin;
			}
		}
		return null;
	}

	/**
	 * Retorna do banco de dados usuario pelo id, retornando um Optional com a
	 * entidade
	 * 
	 * @param usuarioId tipo long
	 * @return Optional com Usuario se os parametos estiverem devidamente escritos e
	 *         existirem, ou Optional vazio (empty)
	 * @since 1.0
	 * @author EducaTierra
	 */

	public Optional<Usuario> meusDados(long usuarioId) {
		Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
		if (usuario.isPresent()) {
			return usuario;
		} else {
			return Optional.empty();
		}
	}

	/**
	 * Registra no banco de dados um novo cadastro de produto e atualiza a pontuacao
	 * para um determinado usuario e retorna um Optional com uma entidade do Usuario
	 * 
	 * @param usuarioId   tipo long
	 * @param categoriaId tipo long
	 * @param produto     entidade do tipo Produto
	 * @return Optional com Produto se os parametos estiverem devidamente escritos e
	 *         existirem, ou Optional vazio (empty)
	 * @since 1.0
	 * @author EducaTierra
	 */

	public Optional<Produto> cadastrarProduto(long usuarioId, long categoriaId, Produto produto) {
		Produto produtoCadastrado = produtoRepository.save(produto);
		Optional<Categoria> categoria = categoriaRepository.findById(categoriaId);
		Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);

		if (categoria.isPresent() && usuario.isPresent()) {
			produtoCadastrado.setCategoria(categoria.get());
			produtoCadastrado.setUsuario(usuario.get());

			int novaPontuação = usuario.get().getPontuacao() + 10;
			usuario.get().setPontuacao(novaPontuação);

			return Optional.ofNullable(produtoRepository.save(produto));
		} else {
			return Optional.empty();
		}
	}


	/**
	 * Deleta o registro de um produto no banco de dados de um determinado usuario e
	 * retorna uma entidade do Usuario
	 * 
	 * @param usuarioId tipo long
	 * @param produtoId tipo long
	 * @return Usuario se os parametos estiverem devidamente escritos e existirem,
	 *         ou null
	 * @since 1.0
	 * @author EducaTierra
	 */

	public Optional<Produto> alterarProduto(long produtoId, long categoriaId, Produto produto) {
		Optional<Produto> produtoParaAlterar = produtoRepository.findById(produtoId);
		Optional<Categoria> novaCategoria = categoriaRepository.findById(categoriaId);
		
        if (produtoParaAlterar.isPresent()) {
        	produtoParaAlterar.get().setCategoria(null);
        	produtoParaAlterar.get().setCategoria(novaCategoria.get());
        	produtoParaAlterar.get().setNome(produto.getNome());
        	produtoParaAlterar.get().setDescricao(produto.getDescricao());
        	produtoParaAlterar.get().setLinkAcesso(produto.getLinkAcesso());
        	produtoParaAlterar.get().setLinkImagem(produto.getLinkImagem());
        	produtoParaAlterar.get().setTipoProduto(produto.getTipoProduto());
        
            return Optional.ofNullable(produtoRepository.save(produtoParaAlterar.get()));
        } else {
            return Optional.empty();
        }
    }
	
	/*public Usuario excluirProduto(long usuarioId, long produtoId) {
		Optional<Produto> produtoDeletado = produtoRepository.findById(produtoId);
		Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);

		if (produtoDeletado.isPresent() && usuario.isPresent()) {
			produtoDeletado.get().setUsuario(null);
			produtoRepository.save(produtoDeletado.get());
			produtoRepository.deleteById(produtoDeletado.get().getId());
			return usuarioRepository.findById(usuario.get().getId()).get();
		} else {
			return null;
		}
	}*/
	
	
	
	/**
	 * Registra no banco de dados um produto a lista de favoritos de um determinado
	 * usuario e retorna uma entidade do Usuario
	 * 
	 * @param produtoId tipo long
	 * @param usuarioId tipo long
	 * @return Usuario se os parametos estiverem devidamente escritos e existirem,
	 *         ou null
	 * @since 1.0
	 * @author EducaTierra
	 */

	public Usuario favoritarProduto(long produtoId, long usuarioId) {
		Optional<Produto> produto = produtoRepository.findById(produtoId);
		Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
		if (produto.isPresent() && usuario.isPresent()) {
			usuario.get().getMeusFavoritos().add(produto.get());
			return usuarioRepository.save(usuario.get());
		} else {

			return null;
		}
	}

}
