package br.com.roni.service;

import java.util.List;
import java.util.Optional;

import org.aspectj.weaver.tools.UnsupportedPointcutPrimitiveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.roni.dto.ClienteDTO;
import br.com.roni.model.Categoria;
import br.com.roni.model.Cliente;
import br.com.roni.model.Cliente;
import br.com.roni.repository.ClienteRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente insert(Cliente obj) {
		obj.setId(null);
		return clienteRepository.save(obj);
	}
	
	public Cliente find(Long id) throws ObjectNotFoundException {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		System.out.println(cliente.get().getId());
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
				"obj nao encontrado  id" + id + ", tipo: " + Cliente.class.getName()));
	}
	
	
	public Cliente update(Cliente obj) throws ObjectNotFoundException{
		Cliente newObj = find(obj.getId());
		updateData(newObj,obj);
		return clienteRepository.save(obj);
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		
	}

	public void delete(Long id) throws ObjectNotFoundException{
		find(id);
		try {
			clienteRepository.deleteById(id);	
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é possível excluir um cliente");
		}
		
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
		return clienteRepository.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(),objDTO.getEmail(),null,null);
	}

}
