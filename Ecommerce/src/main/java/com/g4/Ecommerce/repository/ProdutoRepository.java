package com.g4.Ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g4.Ecommerce.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	public List<Produto> findAllByDescricaoContainingIgnoreCase(String descricao);

	public List<Produto> findAllByNomeContainingIgnoreCase(String nome);
	
}
