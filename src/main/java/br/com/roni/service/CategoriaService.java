package br.com.roni.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.roni.model.Categoria;
import br.com.roni.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria buscar(Long id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		System.out.println(categoria.get().getId());
		return categoria.orElse(null);
	}
}
