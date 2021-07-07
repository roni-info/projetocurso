package br.com.roni.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.roni.model.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

}
