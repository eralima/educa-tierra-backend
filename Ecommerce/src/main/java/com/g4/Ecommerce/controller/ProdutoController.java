package com.g4.Ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.g4.Ecommerce.model.Produto;
import com.g4.Ecommerce.repository.ProdutoRepository;
import com.g4.Ecommerce.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class ProdutoController {
	
	@Autowired ProdutoService produtoService;
	@Autowired
	private ProdutoRepository repository;
	
	
	@GetMapping
	public ResponseEntity<?> listaTodosProdutos(){
		List<Produto> listaProdutos = produtoService.listarTodosProdutos();
				
		if (!listaProdutos.isEmpty()) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(listaProdutos);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Nenhum produto foi encontrado!");
		}
	}
	
	@GetMapping("/lista-produtos")
	public ResponseEntity<?> listaDeProdutosPorDescricao (@RequestParam String descricaoProduto){
		List<Produto> listaProdutos = produtoService.listarProdutosPorDescricao(descricaoProduto);
				
		if (!listaProdutos.isEmpty()) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(listaProdutos);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Nenhum produto foi encontrado a partir da sua pesquisa");
		}
	}
	
	 //FindById
	@GetMapping("/{id}")
	public ResponseEntity<Produto> FindById(@PathVariable long id) {
		return repository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}

}
