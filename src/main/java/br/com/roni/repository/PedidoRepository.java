package br.com.roni.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.roni.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

}
