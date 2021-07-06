package br.com.roni.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Pagamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_pagamento")
	private Long id;
	private EstadoPagamento estado;
	
	private Pedido pedido;
}
