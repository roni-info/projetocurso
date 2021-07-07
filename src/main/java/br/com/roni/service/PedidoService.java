package br.com.roni.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.roni.model.Pedido;
import br.com.roni.repository.PedidoRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido buscar(Long id) throws ObjectNotFoundException{
		Optional<Pedido> obj = pedidoRepository.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException(
				"obj nao encontrado  id" +id+ ", tipo: " + Pedido.class.getName()));

	}
	
}
