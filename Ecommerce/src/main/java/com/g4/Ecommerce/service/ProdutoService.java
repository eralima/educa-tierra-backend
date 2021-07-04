package com.g4.Ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g4.Ecommerce.model.Produto;
import com.g4.Ecommerce.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired ProdutoRepository produtoRepository;
	
	public List<Produto> listarProdutosPorDescricao (String descricaoProduto){
		return produtoRepository.findAllByDescricaoContainingIgnoreCase(descricaoProduto);
	}
	
	public List<Produto> listarTodosProdutos (){
		return produtoRepository.findAll();
	}
}
