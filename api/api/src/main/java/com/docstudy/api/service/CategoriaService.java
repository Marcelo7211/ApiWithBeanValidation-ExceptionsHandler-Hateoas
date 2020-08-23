package com.docstudy.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.docstudy.api.model.Categoria;
import com.docstudy.api.repository.CategoriaRepository;
import com.docstudy.api.service.exception.ExceptionService;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;
	
	public Categoria save(Categoria categoria) {
		Optional<Categoria> categoriaExist = repository.findByDescricao(categoria.getDescricao());
		
		if (categoriaExist.isPresent())
			throw new ExceptionService("Ja existe uma categoria com este nome cadastrada!");
		
		return repository.save(categoria);
	}

}
