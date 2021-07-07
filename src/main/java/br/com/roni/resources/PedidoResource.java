package br.com.roni.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.roni.model.Pedido;
import br.com.roni.service.PedidoService;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {
	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping("{id}")
	public ResponseEntity<Pedido> find(@PathVariable Long id) {
		try {
			Pedido pedido = pedidoService.buscar(id);
			return ResponseEntity.ok().body(pedido);
			
		} catch (ObjectNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	
	

}
