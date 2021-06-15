package com.g4.Ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.g4.Ecommerce.model.Categoria;
import com.g4.Ecommerce.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	public Optional<Categoria> inserirCategoria(Categoria categoria) {
		Categoria categoriaNova = repository.findByMateriaContainingIgonoreCase(categoria.getMateria());
		if (categoriaNova == null) {
			return Optional.ofNullable(repository.save(categoriaNova));
		} else {
			return Optional.empty();
		}

	}

}
