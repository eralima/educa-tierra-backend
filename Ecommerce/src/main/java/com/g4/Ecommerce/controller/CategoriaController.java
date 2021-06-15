package com.g4.Ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.g4.Ecommerce.model.Categoria;
import com.g4.Ecommerce.repository.CategoriaRepository;
import com.g4.Ecommerce.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {
	@Autowired
	private CategoriaRepository repository;

	@Autowired
	private CategoriaService service;

	@GetMapping
	ResponseEntity<List<Categoria>> todasCategorias() {
		return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
	}

	@GetMapping("/{id)")
	ResponseEntity<Categoria> categoriaPeloId(@PathVariable long id) {
		return repository.findById(id).map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/materia/{materia}")
	ResponseEntity<List<Categoria>> categoriaPelaMateria(@PathVariable String materia) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.findAllByMateriaContainingIgnoreCase(materia));
	}

	@GetMapping("/descricao/{descricao}")
	ResponseEntity<List<Categoria>> categoriaPelaDescricao(@PathVariable String descricao) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.findAllByDescricaoContainingIgnoreCase(descricao));
	}

	@PostMapping ("/materia")
	ResponseEntity<Categoria> inserirCategoria(@RequestBody Categoria categoria) {
		Optional<Categoria> categoriaInserida = service.inserirCategoria(categoria);
		if (categoriaInserida.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		else {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(categoriaInserida.get());
		}
	}

	@PutMapping
	ResponseEntity<Categoria> alterarCategoria(@RequestBody Categoria categoria) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(categoria));
	}

	@DeleteMapping("/{id}")
	void deletarCategoria(@PathVariable long id) {
		repository.deleteById(id);
	}
}
