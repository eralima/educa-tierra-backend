package com.g4.Ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g4.Ecommerce.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	//Categoria findByMateriaContainingIgonoreCase (String materia); 
	List<Categoria> findAllByMateriaContainingIgnoreCase (String materia); 
	List<Categoria> findAllByDescricaoContainingIgnoreCase (String descricao);


}
