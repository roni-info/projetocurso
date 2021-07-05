package br.com.roni.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.roni.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}
