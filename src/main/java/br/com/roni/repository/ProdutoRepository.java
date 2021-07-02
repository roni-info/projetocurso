package br.com.roni.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.roni.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto,Long>{

}
