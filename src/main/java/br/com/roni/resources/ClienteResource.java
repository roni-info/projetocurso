package br.com.roni.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.roni.dto.ClienteDTO;
import br.com.roni.dto.ClienteNewDTO;
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
			Cliente cliente = clienteService.find(id);
			return ResponseEntity.ok().body(cliente);
			
		} catch (ObjectNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDto) {
		Cliente obj = clienteService.fromDTO(objDto);
		obj = clienteService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();

	}

	@PutMapping("{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDTO, @PathVariable Long id) {
		try {
			System.out.println("teste1");
			Cliente obj = clienteService.fromDTO(objDTO);
			System.out.println("teste2");
			obj.setId(id);
			obj = clienteService.update(obj);
			return ResponseEntity.noContent().build();
		} catch (ObjectNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Cliente> delete(@PathVariable Long id) {
		try {
			clienteService.delete(id);
			return ResponseEntity.noContent().build();

		} catch (ObjectNotFoundException e) {
			return ResponseEntity.notFound().build();
		}

		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é possível excluir cliente que possui produto");
		}
	}


	@GetMapping("/page")
	public ResponseEntity<Page<ClienteDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction  ) {
		Page<Cliente> clientes = clienteService.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> listaDto = clientes.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(listaDto);
	}

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<Cliente> clientes = clienteService.findAll();
		List<ClienteDTO> listaDto = clientes.stream().
				map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaDto);
	}
}
