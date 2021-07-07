package br.com.roni.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.roni.model.Cliente;
import br.com.roni.repository.ClienteRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente buscar(Long id) throws ObjectNotFoundException {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		System.out.println(cliente.get().getId());
		return cliente.orElseThrow(()-> new ObjectNotFoundException(
				"obj nao encontrado  id" +id+ ", tipo: " + Cliente.class.getName()));
	}
}
