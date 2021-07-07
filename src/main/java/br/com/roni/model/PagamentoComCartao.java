package br.com.roni.model;

import javax.persistence.Entity;

import br.com.roni.enums.EstadoPagamento;

@Entity
public class PagamentoComCartao  extends Pagamento{
	private Integer numeroDeParcelas;

	public PagamentoComCartao() {
		// TODO Auto-generated constructor stub
	}
	
	
	public PagamentoComCartao(Long id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas ) {
		super(id, estado, pedido);
		this.numeroDeParcelas = numeroDeParcelas;
	}

	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}
	
	
	
}
