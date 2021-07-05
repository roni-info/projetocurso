package br.com.roni.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.roni.model.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

}
