package br.com.roni.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.roni.model.Cliente;
import br.com.roni.service.ClienteService;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("{id}")
	public ResponseEntity<Cliente> find(@PathVariable Long id) {
		try {
			Cliente cliente = clienteService.buscar(id);
			return ResponseEntity.ok().body(cliente);
			
		} catch (ObjectNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
