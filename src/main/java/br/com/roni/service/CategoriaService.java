package br.com.roni.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.roni.dto.CategoriaDTO;
import br.com.roni.model.Categoria;
import br.com.roni.repository.CategoriaRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return categoriaRepository.save(obj);
	}
	

	public Categoria find(Long id) throws ObjectNotFoundException {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		System.out.println(categoria.get().getId());
		return categoria.orElseThrow(() -> new ObjectNotFoundException(
				"obj nao encontrado  id" + id + ", tipo: " + Categoria.class.getName()));
	}
	
	
	public Categoria update(Categoria obj) throws ObjectNotFoundException{
		find(obj.getId());
		return categoriaRepository.save(obj);
	}

	public void delete(Long id) throws ObjectNotFoundException{
		find(id);
		categoriaRepository.deleteById(id);
	}

	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
		return categoriaRepository.findAll(pageRequest);
	}

	public Categoria fromDTO(CategoriaDTO objDTO) {
		return new Categoria(objDTO.getId(),objDTO.getNome());
	}

}
