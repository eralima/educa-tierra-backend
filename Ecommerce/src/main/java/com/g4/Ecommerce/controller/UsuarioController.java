package com.g4.Ecommerce.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.g4.Ecommerce.model.Produto;
import com.g4.Ecommerce.model.Usuario;
import com.g4.Ecommerce.model.UsuarioLogin;
import com.g4.Ecommerce.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("/login")
	public ResponseEntity<UsuarioLogin> logar (@RequestBody Optional<UsuarioLogin> user){
		return usuarioService.logar(user).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@PostMapping("/cadastro")
	public ResponseEntity<?> cadastrarUsuario (@RequestBody Usuario usuario){
		Usuario usuarioCadastro = usuarioService.cadastrarUsuario(usuario);
		
		if(usuarioCadastro == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("usuario ja casdastrado");
		}else{
			return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCadastro);
		}
	}

	//cadastro de produtos
	@PostMapping("/cadastro-produto/usuario/{usuarioId}/categoria/{categoriaId}")
	ResponseEntity<?> cadastrarProduto (@PathVariable long usuarioId, @PathVariable long categoriaId, @RequestBody Produto produtoNovo){
		Optional<Produto> produtoCadastro = usuarioService.cadastrarProduto(usuarioId, categoriaId, produtoNovo);
		
		if (!produtoCadastro.isEmpty()) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(produtoCadastro.get());
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário ou categoria não encontrada");
		}	
	} 
	
	//excluir produto
	@DeleteMapping("/exclusao-produto/usuario/{usuarioId}/produto/{produtoId}")
	ResponseEntity<String> excluirProduto(@PathVariable long usuarioId, @PathVariable long produtoId){
		Usuario retorno = usuarioService.excluirProduto(usuarioId, produtoId);
		if(retorno == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário ou produto não encontrado");
		}
		else {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Produto excluído");
		}
	}
	@PutMapping("/favoritos/{usuarioId}/{produtoId}")
	ResponseEntity<?> salvarFavorito(@PathVariable long usuarioId, @PathVariable long produtoId){
		 Usuario salvaFavorito = usuarioService.salvarFavoritos(produtoId, usuarioId);
		 if(salvaFavorito == null) {
			 return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Usuario ou produto n encontrado");
		 }else {
			 return ResponseEntity.status(HttpStatus.CREATED).body(salvaFavorito);
		 }
	}
}