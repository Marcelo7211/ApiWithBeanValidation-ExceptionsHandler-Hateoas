package com.docstudy.api.hateos;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import com.docstudy.api.controller.CategoriaController;
import com.docstudy.api.model.Categoria;

@Service
public class CategoriaHateosHandler {

	public List<Categoria> hateosAll(List<Categoria> categorias) {

		for (Categoria categoria : categorias) {
			long idEvento = categoria.getId();

			Link linkFindId = linkTo(methodOn(CategoriaController.class).findById(idEvento)).withRel("findById");
			Link linkPost = linkTo(methodOn(CategoriaController.class).post(categoria)).withRel("post");
			Link linkPut = linkTo(methodOn(CategoriaController.class).put(categoria)).withRel("put");
			Link linkDelete = linkTo(methodOn(CategoriaController.class).delete(idEvento)).withRel("delete");

			categoria.add(linkFindId, linkPost, linkPut, linkDelete);
		}

		return categorias;

	}

	public Categoria hateosById(Categoria categoria) {

		long idEvento = categoria.getId();

		Link linkPut = linkTo(methodOn(CategoriaController.class).put(categoria)).withRel("put");
		Link linkDelete = linkTo(methodOn(CategoriaController.class).delete(idEvento)).withRel("delete");

		categoria.add(linkPut, linkDelete);

		return categoria;

	}

	public Categoria hateosPost(Categoria categoria) {

		long idEvento = categoria.getId();
		
		Link linkFindId = linkTo(methodOn(CategoriaController.class).findById(idEvento)).withRel("findById");
		Link linkPut = linkTo(methodOn(CategoriaController.class).put(categoria)).withRel("put");
		Link linkDelete = linkTo(methodOn(CategoriaController.class).delete(idEvento)).withRel("delete");

		categoria.add(linkFindId, linkPut, linkDelete);

		return categoria;

	}

	public Categoria hateosPut(Categoria categoria) {

		long idEvento = categoria.getId();

		Link linkFindId = linkTo(methodOn(CategoriaController.class).findById(idEvento)).withRel("findById");
		Link linkDelete = linkTo(methodOn(CategoriaController.class).delete(idEvento)).withRel("delete");

		categoria.add(linkFindId, linkDelete);

		return categoria;

	}
}
