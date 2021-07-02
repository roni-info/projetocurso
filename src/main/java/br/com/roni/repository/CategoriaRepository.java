package br.com.roni.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.roni.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
