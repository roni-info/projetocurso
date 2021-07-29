package br.com.roni.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.roni.enums.EstadoPagamento;
import br.com.roni.model.ItemPedido;
import br.com.roni.model.PagamentoComBoleto;
import br.com.roni.model.Pedido;
import br.com.roni.repository.ItemPedidoRepository;
import br.com.roni.repository.PagamentoRepository;
import br.com.roni.repository.PedidoRepository;
import br.com.roni.repository.ProdutoRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ProdutoService produtoService;
	
	
	public Pedido buscar(Long id) throws ObjectNotFoundException{
		Optional<Pedido> obj = pedidoRepository.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException(
				"obj nao encontrado  id" +id+ ", tipo: " + Pedido.class.getName()));

	}
	
	@Transactional
	public Pedido insert(Pedido obj) throws ObjectNotFoundException {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.Pendente);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pgto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pgto, obj.getInstante());
		}
		
		obj = pedidoRepository.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		System.out.println(obj);
		return obj;
	}
	
}
