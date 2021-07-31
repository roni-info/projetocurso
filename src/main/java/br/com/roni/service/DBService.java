package br.com.roni.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.roni.enums.EstadoPagamento;
import br.com.roni.enums.TipoCliente;
import br.com.roni.model.Categoria;
import br.com.roni.model.Cidade;
import br.com.roni.model.Cliente;
import br.com.roni.model.Endereco;
import br.com.roni.model.Estado;
import br.com.roni.model.ItemPedido;
import br.com.roni.model.Pagamento;
import br.com.roni.model.PagamentoComBoleto;
import br.com.roni.model.PagamentoComCartao;
import br.com.roni.model.Pedido;
import br.com.roni.model.Produto;
import br.com.roni.repository.CategoriaRepository;
import br.com.roni.repository.CidadeRepository;
import br.com.roni.repository.ClienteRepository;
import br.com.roni.repository.EnderecoRepository;
import br.com.roni.repository.EstadoRepository;
import br.com.roni.repository.ItemPedidoRepository;
import br.com.roni.repository.PagamentoRepository;
import br.com.roni.repository.PedidoRepository;
import br.com.roni.repository.ProdutoRepository;

@Service
public class DBService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	
	public void instantiateTestDatabase() throws ParseException {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Casa mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");
		Categoria cat8 = new Categoria(null, "Teste");

		

		Produto p1 = new Produto(null, "Computador", 2000.);
		Produto p2 = new Produto(null, "Impressora", 2000.);
		Produto p3 = new Produto(null, "Mouse", 2000.);

		Produto p4 = new Produto(null, "Mesa de Escritório", 300.);
		Produto p5 = new Produto(null, "Toalha", 50.);
		Produto p6 = new Produto(null, "Colcha", 200.);

		Produto p7 = new Produto(null, "Tv true color", 1200.);
		Produto p8 = new Produto(null, "Roçadeira", 800.);
		Produto p9 = new Produto(null, "Abajour", 100.);
		
		Produto p10 = new Produto(null, "Pendente", 100.);
		Produto p11 = new Produto(null, "Shampoo", 90.);


		// cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		// cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1,cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat2));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3,cat4,cat5,cat6,cat7,cat8));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3,p4,p5,p6,p7,p8,p9,p10,p11));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		System.out.println("teste1");

		// est1.getCidades().addAll(Arrays.asList(c1));
		// est2.getCidades().addAll(Arrays.asList(c2, c3));
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		// System.out.println("teste2");

		Cliente cli1 = new Cliente(null, "Maria Silva", "ronsh@terra.com.br", "333433434", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("222237277", "98988979"));

		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 104", "Jardim", "25600865", cli1, c1);
		Endereco e2 = new Endereco(null, "Av Imperador", "10", "Apto 100", "Centro", "25600654", cli1, c2);

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);

		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.Quitado, ped1, 6);

		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.Pendente, ped2, sdf.parse("20/10/2017 00:00"),
				null);
		ped2.setPagamento(pagto2);
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
		
		ItemPedido it1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.0);
		ItemPedido it2 = new ItemPedido(ped1, p3, 0.00, 2, 80.0);
		ItemPedido it3 = new ItemPedido(ped2, p2, 100.00, 1, 800.0);
		
		itemPedidoRepository.saveAll(Arrays.asList(it1,it2,it3));

	}

}
