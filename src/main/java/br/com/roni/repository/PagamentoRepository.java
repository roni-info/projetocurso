package br.com.roni.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.roni.model.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long>{

}
