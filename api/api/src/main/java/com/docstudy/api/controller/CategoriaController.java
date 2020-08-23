package com.docstudy.api.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.docstudy.api.hateos.CategoriaHateosHandler;
import com.docstudy.api.model.Categoria;
import com.docstudy.api.repository.CategoriaRepository;
import com.docstudy.api.service.CategoriaService;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(value = "*", allowedHeaders = "*")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository repository;
	
	@Autowired
	private CategoriaHateosHandler hateoasService;
	
	@Autowired
	private CategoriaService service;
	
	@GetMapping 
	public ResponseEntity<List<Categoria>> findAll() {
		List<Categoria> categorias = repository.findAll();
		return ResponseEntity.ok(hateoasService.hateosAll(categorias));
	}
	
	@GetMapping("/{id}") 
	public ResponseEntity<Categoria> findById(@PathVariable long id) {
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(hateoasService.hateosById(resp)))
				.orElse(ResponseEntity.notFound().build());
	}
		
	@PostMapping 
	public ResponseEntity<Categoria> post(@Valid @RequestBody Categoria categoria) {
		
		Categoria categoriaPost = service.save(categoria);		
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(hateoasService.hateosPost(categoriaPost));
	}
	
	@PutMapping 
	public ResponseEntity<Categoria> put(@Valid @RequestBody Categoria categoria) {
		
		Categoria categoriaPut = repository.save(categoria);	
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(hateoasService.hateosPut(categoriaPut));
	}
	
	@DeleteMapping("/{id}") 
	public ResponseEntity<?> delete(@PathVariable long id) {
		repository.deleteById(id);
		return ResponseEntity.noContent().build(); 
	}
	


}
