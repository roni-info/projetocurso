package br.com.roni.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.roni.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
