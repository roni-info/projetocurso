package br.com.roni.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.roni.dto.CategoriaDTO;
import br.com.roni.model.Categoria;
import br.com.roni.service.CategoriaService;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping("{id}")
	public ResponseEntity<Categoria> find(@PathVariable Long id) {
		try {
			Categoria categoria = categoriaService.find(id);
			return ResponseEntity.ok().body(categoria);

		} catch (ObjectNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody Categoria obj) {
		obj = categoriaService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();

	}

	@PutMapping("{id}")
	public ResponseEntity<Void> update(@RequestBody Categoria obj, @PathVariable Long id) {
		try {
			obj.setId(id);
			obj = categoriaService.update(obj);
			return ResponseEntity.noContent().build();
		} catch (ObjectNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Categoria> delete(@PathVariable Long id) {
		try {
			categoriaService.delete(id);
			return ResponseEntity.noContent().build();

		} catch (ObjectNotFoundException e) {
			return ResponseEntity.notFound().build();
		}

		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é possível excluir categoria que possui produto");
		}
	}

	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		List<Categoria> categorias = categoriaService.findAll();
		List<CategoriaDTO> listaDto = categorias.stream().
				map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaDto);
	}

}