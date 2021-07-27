package br.com.roni.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.roni.dto.ProdutoDTO;
import br.com.roni.model.Produto;
import br.com.roni.resources.utils.URL;
import br.com.roni.service.ProdutoService;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {
	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping("{id}")
	public ResponseEntity<Produto> find(@PathVariable Long id) {
		try {
			Produto produto = produtoService.find(id);
			return ResponseEntity.ok().body(produto);
			
		} catch (ObjectNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	
	@GetMapping
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value="nome", defaultValue = "0") String nome,
			@RequestParam(value="categorias", defaultValue = "0") String categorias,
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction  ) {
		List<Long> ids = URL.decodeLongList(categorias);
		Page<Produto> produtos = produtoService.search(nome,ids,page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listaDto = produtos.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.ok().body(listaDto);
	}


}
